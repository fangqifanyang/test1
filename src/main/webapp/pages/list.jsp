
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>查询了所有账户信息</h3>
<c:forEach items="${users}" var="user">
    ${user.name}
</c:forEach>
</body>
</html>
