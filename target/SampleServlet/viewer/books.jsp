<%@ page import="com.library.entity.Book" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.library.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    List books = (List) session.getAttribute("books");
    User user = (User) session.getAttribute("user");
%>
<%
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
            <h3><fmt:message key="books"/></h3>
        </div>
        <div class="form" style="margin: 0 auto;">
            <form action="/viewer/searchBook" method="post">
                <div class="form-row" style="margin-bottom: 24px">
                    <div class="form-group col-md-6">
                        <input type="text" name="title" title="" placeholder="<fmt:message key="title"/>"/>
                        <input type="text" name="author" title="" placeholder="<fmt:message key="author"/>"/>
                        <input type="submit" value="<fmt:message key="search"/>" class="btn-green"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="form" style="margin: 0 auto;">
            <form action="/viewer/sortBook" method="post">
                <input type="hidden" value="<%=lang%>" name="lang"/>
                <div class="form-row" style="margin-bottom: 24px">
                    <div class="form-group col-md-7">
                        <select name="field" title="">
                            <option selected value="title"><fmt:message key="title"/></option>
                            <option value="author"><fmt:message key="author"/></option>
                            <option value="datePublication"><fmt:message key="datePublication"/></option>
                            <option value="publication"><fmt:message key="publication"/></option>
                        </select>
                        <select name="type" title="">
                            <option selected value="desc"><fmt:message key="desc"/></option>
                            <option value="asc"><fmt:message key="asc"/></option>
                        </select>
                        <input type="submit" value="<fmt:message key="sort"/>" class="btn-green"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="form" style="margin: 0 auto;">
            <div class="form-row">
                <div class="form-group col-md-1" style="font-weight: bold; text-transform: uppercase">
                    №
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="title"/>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="author"/>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="publication"/>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="datePublication"/>
                </div>
                <div class="form-group col-md-2" style="font-weight: bold; text-transform: uppercase">
                    <fmt:message key="action"/>
                </div>
            </div>
            <%
                Iterator it = books.iterator();
                int n = 1;
                while (it.hasNext()) {
                    Book book = (Book) it.next();
            %>
            <div class="form-row">
                <div class="form-group col-md-1">
                    <p><%=n%>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><%
                        if (lang != null) {
                            if (lang.toLowerCase().equals("en")) {
                                out.println(book.getTitleEn());
                            } else {
                                out.println(book.getTitleRu());
                            }
                        } else {
                            out.println(book.getTitleRu());
                        }
                    %>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><%
                        if (lang != null) {
                            if (lang.toLowerCase().equals("en")) {
                                out.println(book.getAuthorEn());
                            } else {
                                out.println(book.getAuthorRu());
                            }
                        } else {
                            out.println(book.getAuthorRu());
                        }
                    %>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><%
                        if (lang != null) {
                            if (lang.toLowerCase().equals("en")) {
                                out.println(book.getPublicationEn());
                            } else {
                                out.println(book.getPublicationRu());
                            }
                        } else {
                            out.println(book.getPublicationRu());
                        }
                    %>
                    </p>
                </div>
                <div class="form-group col-md-2">
                    <p><%=book.getDatePublication()%>
                    </p>
                </div>
                <div class="form-group col-md-1">
                    <%if (book.getAmount() > 0) {%>
                    <form action="/viewer/getBookForOrder" method="post">
                        <input type="hidden" value="<%=book.getId()%>" name="bookId"/>
                        <input type="hidden" value="<%=user.getId()%>" name="viewerId"/>
                        <input type="submit" value="<fmt:message key="create"/>" class="btn-green"/>
                    </form>
                    <%}%>
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