package com.sola.sell.service.impl;

import com.sola.sell.converter.OrderMaster2OrderDtoConverter;
import com.sola.sell.database.OrderDetail;
import com.sola.sell.database.OrderMaster;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.dto.CartDto;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.repository.OrderDetailReporsitory;
import com.sola.sell.repository.OrderMasterRepository;
import com.sola.sell.service.OrderService;
import com.sola.sell.service.PayService;
import com.sola.sell.service.ProductInfoService;
import com.sola.sell.service.PushMessageService;
import com.sola.sell.util.KeyUtil;
import com.sola.sell.converter.OrderMaster2OrderDtoConverter;
import com.sola.sell.database.OrderDetail;
import com.sola.sell.database.OrderMaster;
import com.sola.sell.database.ProductInfo;
import com.sola.sell.dto.CartDto;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.enums.OrderStatusEnum;
import com.sola.sell.enums.PayStatusEnum;
import com.sola.sell.enums.ResultEnum;
import com.sola.sell.exception.SellException;
import com.sola.sell.repository.OrderDetailReporsitory;
import com.sola.sell.repository.OrderMasterRepository;
import com.sola.sell.service.PayService;
import com.sola.sell.service.ProductInfoService;
import com.sola.sell.service.PushMessageService;
import com.sola.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailReporsitory orderDetailReporsitory;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayService payService;

    @Autowired
    private PushMessageService pushMessageService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        List<CartDto> cartDtoList=new ArrayList<>();
        String orderId= KeyUtil.getUniqueKey();
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        //查询商品
        for(OrderDetail orderDetail:orderDto.getOrderDetailList()){
            ProductInfo productInfo=productInfoService.findone(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //一件商品计算订单总价
            orderAmount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.
                    getProductQuantity())).add(orderAmount);
           //订单详情
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);

            orderDetailReporsitory.save(orderDetail);
            CartDto cartDto=new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDtoList.add(cartDto);

        }
        //写入订单数据库（orderMaster orderDetail）
           OrderMaster orderMaster=new OrderMaster();
           orderDto.setOrderId(orderId);

           BeanUtils.copyProperties(orderDto,orderMaster);
           orderMaster.setOrderAmount(orderAmount);
           orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
           orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
           orderMasterRepository.save(orderMaster);
        //下单成功扣库存
        /*List<CartDto> cartDtoList=orderDto.getOrderDetailList().stream().map(e->
                new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());*/
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }
/*
* 查询订单
* */
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList=orderDetailReporsitory.findByOrderId(orderId);
        if(orderDetailList.isEmpty()){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage=orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDto> orderDtoList= OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());

        //查询订单列表
        Page<OrderDto> orderDtoPage=new PageImpl<>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster=new OrderMaster();

        //判断订单状态
        if(!orderDto.getPayStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单]状态不正确，orderId={},orderStatus={}",
                    orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updataResult=orderMasterRepository.save(orderMaster);
        if(updataResult==null){
            log.error("[取消订单]更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("[取消订单]订单中无商品详情,orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList=orderDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());

        //支付成功，需要退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refund(orderDto);
        }
        return orderDto;
    }

    //完成订单
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto){
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态]订单状态不正确,orderId={}，OrderStatus={]",
                    orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("[完结订单]更新失败,orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //推送微信模板消息
        pushMessageService.orderStatus(orderDto);

        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {

        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单状态]订单状态不正确,orderId={}，OrderStatus={]",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单状态]订单状态不正确,orderId={}，OrderStatus={]",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("[完结订单]更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    public Page<OrderDto> findAllList(Pageable pageable) {
        Page<OrderMaster> pageList=orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList=OrderMaster2OrderDtoConverter.convert(pageList.getContent());
        //列表
        Page<OrderDto> orderDtoPage=new PageImpl<OrderDto>(orderDtoList,pageable,pageList.getTotalElements());
        return orderDtoPage;
    }
}
