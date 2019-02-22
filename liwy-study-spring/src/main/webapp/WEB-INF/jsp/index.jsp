<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    out.println("Hello World！");
    out.println(request.getAttribute("userName"));
    out.println(request.getAttribute("password"));
    out.println(request.getAttribute("sex"));
    out.println(request.getAttribute("study"));
    out.println(request.getAttribute("auth"));
    out.println(request.getAttribute("classs"));
    out.println(request.getAttribute("flass"));
    out.println(session.getAttribute("auth"));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="file/form" enctype="multipart/form-data">
    <input type="text" name="name"/>
    <input type="file" name="file"/>
    <input type="submit"/>
</form>

    Hello LIWY
    <span>自动以次从pageScope、requestScope、sessionScope、applecationScope中查找</span>
    ${userName}
    ${password}
    ${sex}
    ${study}
    ${auth}
    ${classs}
    ${flass}
    <span>从pageScope中查找</span>
    ${pageScope.userName}
    ${pageScope.password}
    ${pageScope.sex}
    ${pageScope.study}
    ${pageScope.auth}
    ${pageScope.classs}
    ${pageScope.flass}
    <span>从requestScope中查找</span>
    ${requestScope.userName}
    ${requestScope.password}
    ${requestScope.sex}
    ${requestScope.study}
    ${requestScope.auth}
    ${requestScope.classs}
    ${requestScope.flass}
    <span>从sessionScope中查找</span>
    ${sessionScope.auth}
</body>
</html>
