<%@ page contentType="text/html; charset=US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
    <head>
        <link href="css/card.css" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


        <link href="/css/styles.css" rel="stylesheet" />
    </head>
    <body>
    <%@include file="header.jsp"%>
        <!-- First Container -->
        <div class="container-fluid bg-1">
            <div>
                <h2 class="text-center">Car List</h2>

                <c:if test="${not empty error_message}">
                    <p>${error_message}</p>
                </c:if>

                <c:if test="${empty cars}">
                    <sec:authorize access="hasRole('admin')">
                        <span> No Cars are added yet. </span>
                    </sec:authorize>
                    <sec:authorize access="hasRole('user')">
                        <span> so empty.... start selling cars by posting a new car listing
                        </span>
                    </sec:authorize>
                </c:if>


                <!--  All Car Lists  -->

                <div class="row">
                    <div class="col-md-12">

                        <sec:authorize access="hasRole('user')">
                            <a href="reg_car">
                                <button class="btn btn-primary">Post A Car For Sale</button>
                            </a>
                        </sec:authorize>

                        <c:url var="get_search_url" value="search" />
                        <form action="${get_search_url}" id="myform" method="get"
                            class="form-inline text-right" style="padding: 20px;">

                            <input class="form-control mr-sm-2" type="search"
                                placeholder="Search" value="" name="keyword" /> <input
                                type="submit" value="Search" class="btn btn-info" />

                        </form>

                    </div>
                </div>


                
                <div class="row">
                    <div class="col">
                        <div class="card-group">

                            <c:if test="${not empty cars}">

                                <%
                                int i = 1;
                                %>
                                <c:forEach var="cars" items="${cars}">
                                            <div class="card">
                                                <img src="/images/${cars.carphoto} " id="car-photo"  class="card-img-top" >
                                                <div class="card-body">
                                                    <h5 class="card-title">${cars.make} ${cars.model}</h5>
                                                    <p class="card-text">
                                                    <p>Manufacturer: ${cars.make}</p>
                                                    <p>Model: ${cars.model}</p>
                                                    <p>Registration Year :${cars.registration}</p>
                                                    <p>Sell Price:${cars.price}</p>

                                                    <p class="card-text">
                                                        <small class="text-muted"> 
                                                        <sec:authorize
                                                                access="hasRole('admin')">
                                                                <a href="edit?id=${cars.id}">
                                                                    <button class="btn btn-info">Update</button>
                                                                </a>
                                                                <a href="delete?id=${cars.id}">
                                                                    <button class="btn btn-danger">Delete</button>
                                                                </a>
                                                        </sec:authorize> 
                                                            <a href="cardetails?id=${cars.id}">
                                                                <button class="btn btn-info">View</button></a> 
                                                            <%-- <a href="test_drive?=${cars.id}">
                                                                    <button class="btn btn-info">Book for test drive</button>
                                                                </a> --%>
                                                        </small>
                                                    </p>
                                                </div>
                                            </div>

                                    <%
                                    i++;
                                    %>
                                </c:forEach>

                            </c:if>
            
                        </div>
                        <!-- End of Card -->

                    </div>
                </div>

                <!--  End Car Lists  -->

            </div>
        </div>
         <script src="/js/card.js" ></script>
    </body>
</html>