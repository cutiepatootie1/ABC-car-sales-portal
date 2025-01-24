<%@ page contentType="text/html; charset=US-ASCII"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!--  Enable Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<!--  Access the Static Resources without using spring URL -->
<link href="/css/style.css" rel="stylesheet" />
<script src="/js/custom.js"></script>


</head>

<body>

	<%@ include file="header.jsp"%>
   
   <!-- Modal -->
    <div class="modal fade" id="confirm" tabindex="-1" aria-labelledby="confirmation" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="confirmation">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            ...
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
        </div>
        </div>
    </div>
    </div>

	<!-- First Container -->
	<div class="container-fluid bg-1">
		<div>
			<h2 class="text-center">User List</h2>

			<div class="row">

				<div class="col-md-12">

					<table class="table table-striped table-bordered">

						<thead>
							<tr>
								<th>No.</th>
								<th>User ID </th>
								<th>First Name</th>
                                <th>Last Name</th>
								<th>Email Address </th>
								<th>Actions  <p><small class="">*Warning: changes are permanent</small></p></th>
							</tr>
						</thead>
						<c:if test="${not empty userlist}">
						<tbody>
						
						<% int i=1;%>
						<c:forEach var="user" items="${userlist}">
							<tr>
								<td><%=i %></td>
								<td>${user.id}</td>
								<td>${user.firstName}</td>
                                <td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>
                                    <sec:authorize access="hasRole('admin')">
                                        <a  href="delete_user?id=${user.id}">
                                        <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#confirm">Delete</button></a>
                                    </sec:authorize>
                                </td>
							</tr>
							<% i++; %>
						</c:forEach>

						</tbody>
                        </c:if>
					</table>
					
					
					
				</div>
			</div>
			
			<!--  End User Lists  -->

		</div>
	</div>
    <%@ include file="footer.jsp"%>
	</body>
	</html>