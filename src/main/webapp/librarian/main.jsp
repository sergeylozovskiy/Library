<%@ page import="com.library.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    User user = (User) session.getAttribute("user");
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

        <div class="section-header">
            <h3><fmt:message key="welcome"/>, <%=user.getLogin()%>
            </h3>
        </div>
        <div class="form" style="margin: 0 auto">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <form action="/librarian/selectViewers" method="post">
                        <div class="text-center my-4">
                            <button type="submit" class="btn-green"><fmt:message key="viewers"/></button>
                        </div>
                    </form>
                </div>
                <div class="form-group col-md-4">
                    <form action="/librarian/selectOrders" method="post">
                        <div class="text-center my-4">
                            <button type="submit" class="btn-green"><fmt:message key="orders"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</section>
<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
<div id="preloader"></div>
<!-- JavaScript Libraries -->
<script src="../resources/lib/jquery/jquery.min.js"></script>
<script src="../resources/lib/jquery/jquery-migrate.min.js"></script>
<script src="../resources/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../resources/lib/easing/easing.min.js"></script>
<script src="../resources/lib/superfish/hoverIntent.js"></script>
<script src="../resources/lib/superfish/superfish.min.js"></script>
<script src="../resources/lib/wow/wow.min.js"></script>
<script src="../resources/lib/waypoints/waypoints.min.js"></script>
<script src="../resources/lib/counterup/counterup.min.js"></script>
<script src="../resources/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="../resources/lib/isotope/isotope.pkgd.min.js"></script>
<script src="../resources/lib/lightbox/js/lightbox.min.js"></script>
<script src="../resources/lib/touchSwipe/jquery.touchSwipe.min.js"></script>

<script src="../resources/js/main.js"></script>
</body>
</html>