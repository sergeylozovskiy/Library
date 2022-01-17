<%@ page import="com.library.entity.User" %>
<%@ page import="com.library.service.OrdersService" %>
<%@ page import="com.library.entity.Orders" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.entity.Orders" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="com.library.util.DatabaseFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    List users = (List) session.getAttribute("users");
    String lang = request.getParameter("lang");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>test</title>
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
                <li><a href="/admin/showAllUsers"><fmt:message key="allUsers"/></a></li>
                <li><a href="/admin/getAllBooks"><fmt:message key="books"/></a></li>
                <li><a href="/admin/getLibrarianList"><fmt:message key="librarians"/></a></li>
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
            <h3><fmt:message key="allUsers"/></h3>
        </div>

        <div class="form" style="margin: 0 auto;">
            <form action="/admin/searchUser" method="post">
                <div class="form-row" style="margin-bottom: 24px">
                    <div class="form-group col-md-8">
                        <input type="text" name="login" title="" placeholder="<fmt:message key="login"/>"/>

                        <select name="role" title="">
                            <option selected value=""><fmt:message key="allUsers"/></option>
                            <option value="Administrator"><fmt:message key="Administrator"/></option>
                            <option value="Librarian"><fmt:message key="Librarian"/></option>
                            <option value="Viewer"><fmt:message key="Viewer"/></option>
                        </select>
                        <%-- <input type="text" name="role" title="" placeholder="<fmt:message key="role(English)"/>"/> --%>
                    </div>
                    <input type="submit" value="<fmt:message key="search"/>" class="btn-green"/>
                </div>
            </form>
        </div>


        <div class="form" style="margin: 0 auto;">
            <div class="form-row">
                <div class="form-group col-md-1" style="font-weight: bold; text-transform: uppercase">
                    №
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="login"/>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="role"/>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="active"/>
                </div>
                <div class="form-group col-md-5" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="action"/>
                </div>
            </div>
            <%
                Iterator it = users.iterator();
                int n = 1;
//                Connection connection = DatabaseFunctions.getConnection();
//                Statement statement = connection.createStatement();
//                OrdersService ordersService = new OrdersService((Connection) statement);
                while (it.hasNext()) {
                    User user = (User) it.next();
         //           ordersService = (OrdersService) it.next();
            %>
            <div class="form-row">
                <div class="form-group col-md-1">
                    <p><%=n%>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><%=user.getLogin()%>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><fmt:message key="<%=user.getRoleString()%>"/>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><fmt:message key="<%=user.getActiveString()%>"/>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <form action="/admin/blockUser" method="post">
                        <input type="hidden" value="<%=user.getId()%>" name="id"/>
                        <input type="submit" value="<fmt:message key="blockUser"/>" class="btn-red"/>
                    </form>
                </div>
                <div class="form-group col-md-2">
                    <form action="/admin/unblockUser" method="post">
                        <input type="hidden" value="<%=user.getId()%>" name="id"/>
                        <input type="submit" value="<fmt:message key="unblockUser"/>" class="btn-green"/>
                    </form>
                </div>
                <div class="form-group col-md-1">
                    <form action="/admin/deleteUser" method="post">
                        <input type="hidden" value="<%=user.getId()%>" name="id"/>
                        <input type="submit" value="<fmt:message key="delete"/>" class="btn-red"/>
                    </form>
                </div>
                <div class="form-group col-md-2">
<%--                    <p><fmt:message key="<%=ordersService.getCountOrdersById(user.getId())%>=%>"/>--%>
                    </p>
                </div>
            </div>
            <%
                    n++;
                }
            %>

        </div>
    </div>

</section>
</body>
</html>