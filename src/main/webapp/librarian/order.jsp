<%@ page import="com.library.entity.Orders" %>
<%@ page import="com.library.entity.User" %>
<%@ page import="com.library.enums.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    String lang = request.getParameter("lang");
    Orders order = (Orders) request.getSession().getAttribute("order");
    User user = (User) request.getSession().getAttribute("user");
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
                <li><a href="/librarian/selectViewers"><fmt:message key="viewers"/></a></li>
                <li><a href="/librarian/selectOrders"><fmt:message key="orders"/></a></li>
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
            <h3><fmt:message key="order"/> №<%=order.getId()%>
            </h3>
        </div>
        <div class="form" style="margin: 0 auto;">
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="viewer"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (lang != null) {
                            if (lang.toLowerCase().equals("en")) {
                                out.println(order.getViewer().getFullName("en"));
                            } else {
                                out.println(order.getViewer().getFullName("ru"));
                            }
                        } else {
                            out.println(order.getViewer().getFullName("ru"));
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="book"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (lang != null) {
                            if (lang.toLowerCase().equals("en")) {
                                out.println(order.getBook().getTitleEn());
                            } else {
                                out.println(order.getBook().getTitleRu());
                            }
                        } else {
                            out.println(order.getBook().getTitleRu());
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="author"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (lang != null) {
                            if (lang.toLowerCase().equals("en")) {
                                out.println(order.getBook().getAuthorEn());
                            } else {
                                out.println(order.getBook().getAuthorRu());
                            }
                        } else {
                            out.println(order.getBook().getAuthorRu());
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="datePublication"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (order.getBook().getDatePublication() != null) {
                            out.println(order.getBook().getDatePublication());
                        } else {
                            out.println("-");
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="dateFrom"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (order.getDateFrom() != null) {
                            out.println(order.getDateFrom());
                        } else {
                            out.println("-");
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="dateTo"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (order.getDateTo() != null) {
                            out.println(order.getDateTo());
                        } else {
                            out.println("-");
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="dateCreated"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (order.getDateCreated() != null) {
                            out.println(order.getDateCreated());
                        } else {
                            out.println("-");
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="dateDecision"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (order.getDateDecision() != null) {
                            out.println(order.getDateDecision());
                        } else {
                            out.println("-");
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="dateReturned"/>
                </div>
                <div class="form-group col-md-2">
                    <%
                        if (order.getDateReturned() != null) {
                            out.println(order.getDateReturned());
                        } else {
                            out.println("-");
                        }
                    %>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="status"/>
                </div>
                <div class="form-group col-md-2">
                    <fmt:message key="<%=order.getStatusString()%>"/>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="penalty"/>
                </div>
                <div class="form-group col-md-2">
                    <%=order.getPenaltyString()%>
                </div>
            </div>
            <div class="form-row">
                <%if (order.getStatus().equals(OrderStatus.CREATED)) {%>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <form action="/librarian/updateOrder" method="post">
                        <input type="hidden" value="<%=order.getId()%>" name="orderId"/>
                        <input type="hidden" value="issued" name="type"/>
                        <input type="submit" value="<fmt:message key="confirm"/>" class="btn-green"/>
                    </form>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <form action="/librarian/updateOrder" method="post">
                        <input type="hidden" value="<%=order.getId()%>" name="orderId"/>
                        <input type="hidden" value="rejected" name="type"/>
                        <input type="submit" value="<fmt:message key="reject"/>" class="btn-red"/>
                    </form>
                </div>
                <%}%>
                <%if (order.getStatus().equals(OrderStatus.ISSUED)) {%>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <form action="/librarian/updateOrder" method="post">
                        <input type="hidden" value="<%=order.getId()%>" name="orderId"/>
                        <input type="hidden" value="returned" name="type"/>
                        <input type="submit" value="<fmt:message key="returned"/>" class="btn-green"/>
                    </form>
                </div>
                <%}%>
            </div>

        </div>
    </div>

</section>
</body>
</html>