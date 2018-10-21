<html>
    <head>
        <meta charset="utf-8">
        <title>商品列表</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
        <link  href="/sell/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper" class="toggled">
            <#include "../common/nav.ftl">
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>订单id</th>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>地址</th>
                                    <th>金额</th>
                                    <th>订单状态</th>
                                    <th>支付状态</th>
                                    <th>创建时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                        <#list orderDtoPage.content as orderDto>
                        <tr>
                            <td>${orderDto.getOrderId()}</td>
                            <td>${orderDto.getBuyerName()}</td>
                            <td>${orderDto.getBuyerPhone()}</td>
                            <td>${orderDto.getBuyerAddress()}</td>
                            <td>${orderDto.getOrderAmount()}</td>
                            <td>${orderDto.getOrderStatusEnum().msg}</td>
                            <td>${orderDto.getPayStatusEnum().msg}</td>
                            <td>${orderDto.getCreatTime()}</td>
                            <td><a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a></td>
                            <td>
                                    <#if orderDto.getOrderStatusEnum().msg=="新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">
                                            取消
                                        </a>
                                    </#if>
                            </td>
                        </tr>
                        </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                          <#if page lte 1>
                               <li class="disabled">
                                   <a href="#">上一页</a>
                               </li>
                          <#else>
                                 <li>
                                     <a href="/sell/seller/order/list?page=${page-1}&size=${size}">上一页</a>
                                 </li>
                          </#if>
                        <#list 1..orderDtoPage.getTotalPages() as index>
                            <#if page==index>
                               <li class="disabled">
                                   <a href="#">${index}</a>
                               </li>
                            <#else>
                               <li >
                                   <a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                               </li>
                            </#if>
                        </#list>
                        <#if page gte orderDtoPage.getTotalPages()>
                            <li class="disabled">
                                <a href="#">下一页</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${page+1}&size=${size}">下一页</a>
                            </li>
                        </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>