<%--
  Created by IntelliJ IDEA.
  User: jerry
  Date: 2016-11-11
  Time: 오후 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <title>BoardOnTheWeb</title>
</head>
<body>
<!--본 내용-->
</br>
<div class="container">

    <form action="/writing" method="post">
        <input type="text" class="form-control" id="title" name="title" placeholder="Title"></br>
        <textarea id="body" name="body" class="form-control" rows="10" placeholder="내용을 입력해주세요"></textarea></br>
        <div class="row">
            <div class="col-md-8"><!--비율을 위해 빈칸으로--></div>
            <div class="form-group col-md-2">
                <label class="sr-only" for="email">Email address</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="Enter email">
            </div>
            <div class="form-group col-md-2">
                <label class="sr-only" for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
        </div>
        <div class="col-md-offset-11"><button type="submit" class="btn btn-default">게시글 쓰기</button></div>
    </form>
</div>
</body>
</html>
