<%--Created by IntelliJ IDEA.
User: kangseongbeom
Date: 2021-05-14
Time: 오후 7:06
To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="kr.ac.jejunu.UserDao" %>
<%@ page import="kr.ac.jejunu.User" %>
<%@ page import="kr.ac.jejunu.DaoFactory" %>

<%
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    User user = userDao.findById(1);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello, <%=user.getName()%></h1>
</body>
</html>
