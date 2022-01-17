<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    String lang = request.getParameter("lang");
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
            <h3><fmt:message key="addLibrarian"/></h3>
        </div>
        <div class="form" style="margin: 0 auto;">
            <form action="/admin/addLibrarian" method="post">
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="login"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="login" title=""/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="password"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="password" title=""/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="surname"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="surnameEn" title="" placeholder="Surname"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="surnameRu" title="" placeholder="Фамилия"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="name"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="nameEn" title="" placeholder="Name"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="nameRu" title="" placeholder="Имя"/>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                        <fmt:message key="patronymic"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="patronymicEn" title="" placeholder="Patronymic"/>
                    </div>
                    <div class="form-group col-md-2">
                        <input type="text" name="patronymicRu" title="" placeholder="Отчество"/>
                    </div>
                </div>
                <div class="form-row">
                    <button type="submit" class="btn-green" style="float: right"><fmt:message key="add"/></button>
                </div>
            </form>
        </div>
    </div>

</section>
</body>
</html>