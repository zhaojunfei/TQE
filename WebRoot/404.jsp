<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
    <title>404|页面未找到</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width" />
    <link rel="stylesheet" href="<%=path%>/css/404.css">
</head>
<body>
    <div id="error">
      <a href="<%=path%>">
        <img src="<%=path%>/image/404.png" alt="404 page not found" id="error404-image" />
      </a>
    </div>
</body>
</html>
