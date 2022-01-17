<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>test</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="resources/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Montserrat:300,400,500,700"
          rel="stylesheet">
</head>
<body>
<header id="header">
    <div class="container-fluid">

        <div id="logo" class="pull-left">
            <h1><a href="#intro" class="scrollto">Library</a></h1>
        </div>

        <nav id="nav-menu-container">
            <ul class="nav-menu">
                <li class="menu-active"><a href="#intro"><fmt:message key="menu.home"/></a></li>
                <li><a href="login.jsp"><fmt:message key="menu.login"/></a></li>
                <li class="menu-has-children"><a href=""><fmt:message key="menu.lang"/></a>
                    <ul>
                        <li><a href="?lang=ru"><fmt:message key="lang.ru"/></a></li>
                        <li><a href="?lang=en"><fmt:message key="lang.en"/></a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</header>
<section id="intro">
    <div class="intro-container">
        <div id="introCarousel" class="carousel  slide carousel-fade" data-ride="carousel">

            <ol class="carousel-indicators"></ol>

            <div class="carousel-inner" role="listbox">

                <div class="carousel-item active">
                    <div class="carousel-background"><img src="resources/img/intro-carousel/1.jpg" alt=""></div>
                    <div class="carousel-container">
                        <div class="carousel-content">
                            <h2><fmt:message key="carousel.largeAssortment"/></h2>
                            <p><fmt:message key="carousel.largeAssortment.description"/></p>
                            <a href="login.jsp" class="btn-get-started scrollto"><fmt:message key="menu.login"/></a>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <div class="carousel-background"><img src="resources/img/intro-carousel/2.jpg" alt=""></div>
                    <div class="carousel-container">
                        <div class="carousel-content">
                            <h2><fmt:message key="carousel.largeAssortment"/></h2>
                            <p><fmt:message key="carousel.largeAssortment.description"/></p>
                            <a href="login.jsp" class="btn-get-started scrollto"><fmt:message key="menu.login"/></a>
                        </div>
                    </div>
                </div>

                <div class="carousel-item">
                    <div class="carousel-background"><img src="resources/img/intro-carousel/3.jpg" alt=""></div>
                    <div class="carousel-container">
                        <div class="carousel-content">
                            <h2><fmt:message key="carousel.largeAssortment"/></h2>
                            <p><fmt:message key="carousel.largeAssortment.description"/></p>
                            <a href="login.jsp" class="btn-get-started scrollto"><fmt:message key="menu.login"/></a>
                        </div>
                    </div>
                </div>

            </div>

            <a class="carousel-control-prev" href="#introCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon ion-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>

            <a class="carousel-control-next" href="#introCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon ion-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>

        </div>
    </div>
</section>
<footer id="footer">
    <div class="footer-top">
        <div class="container">
            <div class="row">

                <div class="col-lg-3 col-md-6 footer-info">
                    <h3>Library</h3>
                    <p><fmt:message key="footer.description"/></p>
                </div>

                <div class="col-lg-3 col-md-6 footer-links">
                    <h4><fmt:message key="footer.links"/></h4>
                    <ul>
                        <li><i class="ion-ios-arrow-right"></i> <a href="#"><fmt:message key="menu.home"/></a></li>
                        <li><i class="ion-ios-arrow-right"></i> <a href="login.jsp"><fmt:message key="menu.login"/></a></li>
                    </ul>
                </div>

                <div class="col-lg-3 col-md-6 footer-contact">
                    <h4>Contact Us</h4>
                    <p>
                        Novoorlovska 2 Street <br>
                        Dnipro, DP 49000<br>
                        Ukraine <br>
                        <strong>Phone:</strong> +3 8096 418 5431<br>
                        <strong>Email:</strong> sergeylozovskiy@gmail.com<br>
                    </p>

                    <div class="social-links">
                        <a href="#" class="twitter"><i class="fa fa-twitter"></i></a>
                        <a href="#" class="facebook"><i class="fa fa-facebook"></i></a>
                        <a href="#" class="instagram"><i class="fa fa-instagram"></i></a>
                        <a href="#" class="google-plus"><i class="fa fa-google-plus"></i></a>
                        <a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
                    </div>

                </div>

                <div class="col-lg-3 col-md-6 footer-newsletter">
                    <h4>Our Newsletter</h4>
                    <p>Tamen quem nulla quae legam multos aute sint culpa legam noster magna veniam enim veniam illum
                        dolore legam minim quorum culpa amet magna export quem marada parida nodela caramase seza.</p>
                </div>

            </div>
        </div>
    </div>

</footer>
<a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>
<div id="preloader"></div>
<!-- JavaScript Libraries -->
<script src="resources/lib/jquery/jquery.min.js"></script>
<script src="resources/lib/jquery/jquery-migrate.min.js"></script>
<script src="resources/lib/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="resources/lib/easing/easing.min.js"></script>
<script src="resources/lib/superfish/hoverIntent.js"></script>
<script src="resources/lib/superfish/superfish.min.js"></script>
<script src="resources/lib/wow/wow.min.js"></script>
<script src="resources/lib/waypoints/waypoints.min.js"></script>
<script src="resources/lib/counterup/counterup.min.js"></script>
<script src="resources/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="resources/lib/isotope/isotope.pkgd.min.js"></script>
<script src="resources/lib/lightbox/js/lightbox.min.js"></script>
<script src="resources/lib/touchSwipe/jquery.touchSwipe.min.js"></script>

<script src="resources/js/main.js"></script>
</body>
</html>