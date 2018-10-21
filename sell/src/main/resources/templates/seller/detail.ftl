<html>
<head>
    <meta charset="utf-8">
    <title>详情页面</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link  href="/sell/css/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>订单号</th>
                            <th>订单金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDto.orderId}</td>
                            <td>${orderDto.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>商品号</th>
                            <th>商品类型</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总价</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDto.getOrderDetailList() as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productPrice*orderDetail.productQuantity}</td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
        <#if orderDto.getOrderStatusEnum().msg=="新订单">
        <div class="col-md-12 column">
            <a href="/sell/seller/order/finish?orderId=${orderDto.orderId}" type="button" class="btn btn-default btn-primary">完成订单</a>
            <a hred="sell/seller/order/cancel?orderId=${orderDto.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
        </div>
        </#if>
            </div>
        </div>
    </div>
</div>

</body>
</html>