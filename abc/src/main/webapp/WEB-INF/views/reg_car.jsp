<%@ page contentType="text/html; charset=US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ page import="com.carportal.abc.dao.Cars"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<!--  Enable Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<%-- google font cdn --%>
 <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

    <link href="/css/styles.css" rel="stylesheet" />

</head>

<body>

	<%@ include file="header.jsp"%>


	<!-- First Container -->
	<div class="container-fluid bg-1 text-center">
		<small>Thank you for trusting us!</small>
		<h3 class="margin">Tell us about your car</h3>

		<hr>

		<div class="row">

			<div class="col-md-4"></div>

			<div class="col-md-4">


				<form:form method="POST" action="/registered_car" modelAttribute="cars"
					enctype="multipart/form-data">

					<input type="hidden" value="${_csrf.token}" />
					<form:hidden path="id" />

					<c:if test="${cars.id>0}">
						<div class="form-group">
							<img src="/images/${cars.carphoto}" width="300px">
							<form:hidden path="carphoto" />
						</div>
					</c:if>

					<div class="form-group">
						<label for="carphoto">Upload Car Photo:</label> 
						<input type="file" name="file" />
					</div>


					<div class="form-group">
						<form:label path="make">Make:</form:label>
						<form:input path="make" />
					</div>


					<div class="form-group">
						<label for="model">Model:</label>
						<form:input path="model" />
					</div>

					<div class="form-group">
						<label for="registration">Registration Year:</label>
						<form:input path="registration" />

					</div>

					<div class="form-group">
						<label for="price">Price:</label>
						<form:input path="price" />
					</div>

					<input type="submit" class="btn btn-success" name="Add" value="Save" />
					<input type="button" class="btn btn-danger" value="Cancel" onclick="cancel()" />

					<script>
						function cancel() {
							window.location.href = "cars"
						}
					</script>
				</form:form>

			</div>


			<div class="col-md-4"></div>

		</div>

	</div>

</body>
</html>
