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
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>商品名</th>
                                <th>商品类型</th>
                                <th>价格</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th colspan="2">商品状态</th>
                            </tr>
                            </thead>
                            <tbody>
                           <#list productInfoPage.content as productInfo>
                                <tr>
                                    <td>${productInfo.productId}</td>
                                    <td>${productInfo.productName}</td>
                                    <td>${productInfo.categoryType}</td>
                                    <td>${productInfo.productPrice}</td>
                                    <td>${productInfo.productStock}</td>
                                    <td>${productInfo.productDescrition}</td>
                                    <td>${productInfo.createTime}</td>
                                    <td><a href="/sell/seller/product/revise?productId=${productInfo.productId}">详情</a></td>
                                    <td>
                                        <#if productInfo.getProductStatusEnum()=="Up">
                                            <a href="/sell/seller/product/undercarriage?productId=${productInfo.productId}">下架</a>
                                        <#else>
                                             <a href="/sell/seller/product/ground?productId=${productInfo.productId}">上架</a>
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
                                    <a href="/sell/seller/product/list?page=${page-1}&size=${size}">上一页</a>
                                </li>
                            </#if>
                            <#list 1..productInfoPage.getTotalPages() as index>
                                <#if page==index >
                                     <li class="disabled">
                                         <a href="#">${index}</a>
                                     </li>
                                <#else>
                                    <li>
                                        <a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a>
                                    </li>
                                </#if>
                            </#list>
                            <#if page gte productInfoPage.getTotalPages()>
                                <li class="disabled">
                                     <a href="#">下一页</a>
                                 </li>
                            <#else>
                                <li>
                                    <a href="/sell/seller/product/list?page=${page+1}&size=${size}">下一页</a>
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