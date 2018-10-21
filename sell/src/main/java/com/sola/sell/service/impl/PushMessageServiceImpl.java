package com.sola.sell.service.impl;

import com.sola.sell.config.WachatAccountConfig;
import com.sola.sell.dto.OrderDto;
import com.sola.sell.service.PushMessageService;
import com.sola.sell.config.WachatAccountConfig;
import com.sola.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WachatAccountConfig accountConfig;
    @Override
    public void orderStatus(OrderDto orderDto) {
        WxMpTemplateMessage templateMessage=new WxMpTemplateMessage();
        //用户openid
        templateMessage.setToUser(orderDto.getBuyerOpenid());
        //模板消息接口id
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("tem1"));
        List<WxMpTemplateData> data= Arrays.asList(
                new WxMpTemplateData("first","不用客气，我看看情况"),
                new WxMpTemplateData("key1",orderDto.getOrderId()),
                new WxMpTemplateData("key2",orderDto.getBuyerName()),
                new WxMpTemplateData("key3",orderDto.getBuyerPhone()),
                new WxMpTemplateData("key4","￥"+orderDto.getOrderAmount()),
                new WxMpTemplateData("last","不知道行不行你看看行就行，不行拉到，看看有谁")

        );
        templateMessage.setData(data);
        try{
        wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException wXE){
            log.info("[微信消息推送失败] {}",wXE.getError().getErrorMsg());
        }
    }
}
