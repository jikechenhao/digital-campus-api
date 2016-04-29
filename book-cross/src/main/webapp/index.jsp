<%--
  Created by IntelliJ IDEA.
  User: lizhiqiang
  Date: 2016/3/31
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/stylesheets/bootstrap-custom.css">
    <script src="/static/javascripts/angular.min.js"></script>
</head>
<body>
<div class="container">
    <%--<section>
        <div class="row">
            <div class="col-sm-12">

                <div data-ng-app="myApp" data-ng-controller="myCtrl">
                    <p>这里有一个angularjs表达式:{{5+5}}</p>

                    姓名: <input type="text" data-ng-model="name"><br> 年龄:
                    <input type="text" data-ng-model="age"><br> 地址: <input
                        type="text" data-ng-model="address"><br> 性别: <input
                        type="text" data-ng-model="sex"><br> <br> 双向绑定:
                    {{name}} {{age}} {{address}} {{sex}}

                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <button type="button" class="btn btn-primary btn-block">
                    <span class="fa fa-file fa-lg"></span>
                </button>
            </div>
        </div>
    </section>--%>
    <h1>以下是功能测试</h1>
    <section>
        <h3>测试上传书</h3>
        <form action="m/cross/excel/book/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="testfile"/>
            <button type="submit" class="btn btn-primary">测试上传书</button>
        </form>
    </section>
    <hr/>
    <section>
        <h3>测试上传套系</h3>
        <form action="m/cross/excel/group/upload" method="post" enctype="multipart/form-data">
            <input type="file" name="testfile"/>
            <button type="submit" class="btn btn-primary">测试上传套系</button>
        </form>
    </section>
    <hr/>
    <section>
        <a href="/m/cross/excel/book/download" target="_self">下载图书模板</a>
        <hr/>
        <a href="/m/cross/excel/group/download" target="_self">下载套系模板</a>
    </section>
    <hr/>
    <section>
        <form action="/m/cross/book/c" method="post">
            书名：<input type="text" name="name"/>
            数量：<input type="text" name="storeTotal"/>
            <button type="submit" class="btn btn-primary">测试新增书</button>
        </form>
    </section>
    <section>
        <form action="/m/cross/book/r/1" method="post">
            书名：<input type="text" name="name"/>
            <button type="submit" class="btn btn-primary">测试根据书名获取书</button>
        </form>
    </section>
    <section>
        <form action="/m/cross/book/u/0" method="post">
            ID:<input type="text" name="id"/>
            书名：<input type="text" name="name"/>
            库存：<input type="text" name="storeTotal"/>
            <button type="submit" class="btn btn-primary">测试根据id修改书</button>
        </form>
    </section>
    <section>
        <form action="/m/cross/book/u/1" method="post">
            ID:<input type="text" name="id"/>
            增量：<input type="text" name="increment"/>
            <button type="submit" class="btn btn-primary">测试根据id增减库存</button>
        </form>
    </section>
    <section>
        <form action="/m/cross/book/d" method="post">
            id：<input type="text" name="id"/>
            <button type="submit" class="btn btn-primary">测试根据ID删除书</button>
        </form>
    </section>
    <hr/>
    <section>
        <form action="/m/cross/group/c" method="post">
            套系名：<input type="text" name="name"/>
            <button type="submit" class="btn btn-primary">测试新建套系</button>
        </form>
    </section>
    <section>
        <form action="/m/cross/group/u/0" method="post">
            id：<input type="text" name="id"/>
            套系名：<input type="text" name="name"/>
            <button type="submit" class="btn btn-primary">测试修改套系</button>
        </form>
    </section>
    <section>
        <form action="/m/cross/group/u/1" method="post">
            套系ID:<input type="text" name="groupId"/>
            书ID：<input type="text" name="bookId"/>
            增量：<input type="text" name="initTotal"/>
            <button type="submit" class="btn btn-primary">测试调整套系中图书的数量</button>
        </form>
    </section>
    <hr/>
    <button type="button" class="btn btn-primary">测试新增活动流程</button>

</div>
<script src="/static/javascripts/jquery.min.js"></script>
<script src="/static/javascripts/bootstrap.min.js"></script>
<script>
    /*    var app = angular.module('myApp', []);
     app.controller('myCtrl', function ($scope) {
     $scope.name = "李志强";
     $scope.age = 20;
     $scope.address = "china";
     $scope.sex = "男";
     });*/

    var createProcess = function () {
        $.post(
                '', {}, function (result) {
                    console.log(result);
                }, 'json'
        );
    }


</script>
</body>
</html>
