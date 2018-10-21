<html>
<head>
    <meta charset="utf-8">
    <title>商品类别列表</title>
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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>类目名称</label><input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!''}" />
                        </div>
                        <div class="form-group">
                            <label >类型编号</label><input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!''}" />
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