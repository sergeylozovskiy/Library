<%@ page import="com.library.entity.Book" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}" scope="session"/>
<fmt:setBundle basename="messages"/>
<%
    List books = (List) session.getAttribute("books");
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
            <h3><fmt:message key="books"/></h3>
        </div>
        <div class="form" style="margin: 0 auto;">
            <div class="form-row">
                <input type="button" class="btn-green" onclick="location.href='book.jsp';" style="margin-bottom: 24px"
                       value="<fmt:message key="addBook"/>"/>
            </div>
        </div>
        <div class="form" style="margin: 0 auto;">
            <div class="form-row">
                <div class="form-group col-md-1" style="font-weight: bold; text-transform: uppercase">
                    ???
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
                <div class="form-group col-md-2">
                    <form action="/admin/getBook" method="post">
                        <input type="hidden" value="<%=book.getId()%>" name="id"/>
                        <input type="submit" value="<fmt:message key="edit"/>" class="btn-green"/>
                    </form>
                </div>
                <div class="form-group col-md-1">
                    <form action="/admin/deleteBook" method="post">
                        <input type="hidden" value="<%=book.getId()%>" name="id"/>
                        <input type="submit" value="<fmt:message key="delete"/>" class="btn-red"/>
                    </form>
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