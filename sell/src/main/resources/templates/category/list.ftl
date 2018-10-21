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
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>名称</th>
                                <th>类型</th>
                                <th>创建时间</th>
                                <th>更新时间</th>
                                <th>类目操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productCategories as category>
                                  <tr>
                                      <td>${category.categoryName}</td>
                                      <td>${category.categoryType}</td>
                                      <td>${category.createTime}</td>
                                      <td>${category.updateTime}</td>
                                      <td><a href="/sell/seller/category/revise?categoryId=${category.categoryId}">详情</a></td>

                                  </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>