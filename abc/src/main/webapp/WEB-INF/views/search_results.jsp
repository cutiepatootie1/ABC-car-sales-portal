<%@ page contentType="text/html; charset=US-ASCII"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!--  Enable Bootstrap -->
<link href="/css/card.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<%-- google font cdn --%>
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

	<%@ include file="header.jsp"%>

	<!-- First Container -->
	<div class="container-fluid bg-1">
		<div>
			<h2 class="text-center">Search Car Results</h2>

			<!--  Search Car Lists  -->
			
			<div class="row">
				<div class="col-md-12">
				<c:url var="get_search_url" value="search"/>
				<form action="${get_search_url}" id="myform" method="get" class="form-inline text-right" style="padding:20px;">
					<input class="form-control mr-sm-2" type="search" placeholder="Search" value="${keyword}" name="keyword" />
						<input type="submit" value="Search" class="btn btn-info"/>

				</form>

				</div>
			</div>

			<c:if test="${empty keyword}">
								<div>
									<h4 class="text-danger text-center">Enter a search parameter</h4>
								</div>
			</c:if>

			<div class="row">
                    <div class="col">
                        <div class="card-group">

							<c:if test="${empty search_cars}">
								<div>
									<h4 class="text-danger text-center"> No Matching Item Found. </h4>	
									<h5 class="text-danger text-center"> Please try with another keyword.</h5>	
								</div>	
							</c:if>

                            <c:if test="${not empty search_cars}">

                                <%
                                int i = 1;
                                %>
                                <c:forEach var="cars" items="${search_cars}">
                                            <div class="card">
                                                <img src="/images/${cars.carphoto}"  class="card-img-top" >
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
	<script src="js/card.js"></script>
</body>
</html>