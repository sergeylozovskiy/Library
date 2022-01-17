<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    String lang = request.getParameter("lang");
    String bookId = (String) request.getSession().getAttribute("bookId");
    String viewerId = (String) request.getSession().getAttribute("viewerId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>test</title>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="../resources/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="../resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../resources/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700"
          rel="stylesheet">
</head>

<body class="section-bg">
<header id="header" style="background: #000">
    <div class="container-fluid">

        <div id="logo" class="pull-left">
            <h1><a href="main.jsp" class="scrollto">Library</a></h1>
        </div>

        <nav id="nav-menu-container">
            <ul class="nav-menu">
                <li><a href="/viewer/getBooks"><fmt:message key="books"/></a></li>
                <li><a href="/viewer/getOrders"><fmt:message key="orders"/></a></li>
                <li class="menu-has-children"><a href=""><fmt:message key="menu.lang"/></a>
                    <ul>
                        <li><a href="?lang=ru"><fmt:message key="lang.ru"/></a></li>
                        <li><a href="?lang=en"><fmt:message key="lang.en"/></a></li>
                    </ul>
                </li>
                <li><a href="../login.jsp"><fmt:message key="signOut"/></a></li>
            </ul>
        </nav>
    </div>
</header>
<section id="contact" class="section-bg wow fadeInUp" style="margin-top: 100px">
    <div class="container">

        <div class="section-header" style="margin-bottom: 24px">
            <h3><fmt:message key="createOrder"/></h3>
        </div>
        <div class="form" style="margin: 0 auto;">
            <form action="/viewer/createOrder" method="post">
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="dateFrom"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="date" name="dateFrom" title="" placeholder="<fmt:message key="dateFrom"/>"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="dateTo"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="date" name="dateTo" title="" placeholder="<fmt:message key="dateTo"/>"/>
                    </div>
                </div>
                <input type="hidden" title="" value="<%=bookId%>" name="bookId"/>
                <input type="hidden" title="" value="<%=viewerId%>" name="viewerId"/>
                <div class="form-row">
                    <button type="submit" class="btn-green"><fmt:message key="save"/></button>
                </div>
            </form>
        </div>
    </div>

</section>
</body>
</html>