<html>
<head>
    <meta charset="utf-8">
    <title>商品修改页面</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link  href="/sell/css/style.css" rel="stylesheet">
</head>
<body>
<div class="toggled" id="wrapper">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>商品名称</label><input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label >价格</label><input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}" />
                        </div >
                        <div class="form-group">
                            <label >库存</label><input name="productStock" type="text" class="form-control" value="${(productInfo.productStock)!''}" />
                        </div>
                        <div class="form-group">
                            <label >描述</label><input name="productDescrition" type="text" class="form-control" value="${(productInfo.productDescrition)!''}" />
                        </div>
                        <div class="form-group">
                            <label >图片</label><input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}" />
                        </div>
                        <div class="form-group">
                            <label >类别</label>
                            <select class="form-control" name="categoryType">
                        <#list categoryList as category>
                            <option value="${category.categoryType}"
                                   <#if (productInfo.categoryType)?? && productInfo.categoryType==category.categoryType>
                                        selected
                                   </#if>
                            >
                                ${category.categoryName}
                            </option>
                        </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default btn-primary">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>