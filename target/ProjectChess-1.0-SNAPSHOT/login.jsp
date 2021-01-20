<%--
  Created by IntelliJ IDEA.
  User: didef
  Date: 1/19/2021
  Time: 6:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Enter your name</p>
<form method="get" action="<%=request.getContextPath()%>/enter">
    <input type="text" name="name">
    <button>Enter</button>
</form>

</body>
</html>
