<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%--        import Bootstrap--%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />">

</head>
<body>
<h1>Products Data</h1>
<div class="searchDiv">
    <form action="searchProduct" method="get">
        Search Product<input type="text" class="form-control" name="searchKey" placeholder="enter product name"/>
        <input type="submit" value="Search" class="btn btn-success">
    </form>
</div>
<div class="tbl-div">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Show Details</th>
            <th scope="col">Add/Update</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productsList}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>
                    <!-- construct an "view details" link with product id -->
                    <c:url var="detailsLink" value="/showDetails">
                        <c:param name="productId" value="${item.id}" />
                    </c:url>
                    <a class="btn btn-primary" href="<c:out value="${detailsLink}" />">View Details</a>
                </td>
                <td>
                    <!-- construct an "update" link with product id -->
                    <c:url var="updateLink" value="/updateProduct">
                        <c:param name="productId" value="${item.id}" />
                    </c:url>
                    <a class="btn btn-warning" href="<c:out value="${updateLink}" />">Add/Update</a>
                </td>
                <td>
                    <!-- construct an "delete" link with product id -->
                    <c:url var="deleteLink" value="/deleteProduct">
                        <c:param name="productId" value="${item.id}" />
                    </c:url>
                    <a class="btn btn-danger" href="<c:out value="${deleteLink}" />">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form:form action="addProduct" method="get">
        <input type="submit" class="btn btn-success" value="Add new Product"/>
    </form:form>
</div>
</body>

</html>