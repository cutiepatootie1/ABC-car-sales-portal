<%@ page contentType="text/html; charset=US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>

        <!--  Enable Bootstrap -->
        <link rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <link href="/css/login.css" rel="stylesheet" />

    </head>

    <body>

        <%@include file="header.jsp"%>

        <!-- First Container -->
        <div class="container-fluid bg-1 text-center">
            <h3 class="margin">Make profit or buy your first car with us! </h3>

            <div class="row">

                <div class="col-md-4"></div>
                <div class="col-md-4">


                    <c:if test="${register_success != null}">
                        <div class="alert alert-success">
                            <p>${register_success} Click <a href="login"> Sign In</a> to use our portal...</p>
                        </div>
                    </c:if>

                <form:form action="registered" method="POST"  modelAttribute="user">

                    <div class="form-group">
                        <label for="firstname">First Name:</label>
                        <form:input path="firstName" class="form-control" required="true"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="lastname">Last Name:</label>
                        <form:input path="lastName" class="form-control" required="true"/>
                    </div>

                    <div class="form-group">
                        <label for="email">Email:</label>
                        <form:input path="email" class="form-control" required="true"/>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Your Password:</label>
                        <form:password path="password" class="form-control" required="true"/>
                    </div>
                    
                    <input type="submit" value="Register" class="btn btn-primary"/>
                        
                </form:form>

                </div>


                <div class="col-md-4"></div>

            </div>

        </div>


        <%@ include file="footer.jsp"%>

    </body>
</html>
