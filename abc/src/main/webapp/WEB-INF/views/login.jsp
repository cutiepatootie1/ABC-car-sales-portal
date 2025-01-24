<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/css/login.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        
        <div class="container">
            <h2>Trade cars with ABC Cars!</h2>
            <h2>Login</h2>

            <form action="/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}"
                            value="${_csrf.token}" />
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id ="username" name="username" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary">Login</button><br>
                <p class="h5">Haven't created an account yet? Register <a href="register">HERE</a></p>
            </form>
            <!-- Change errorMessage to error_string as per the controller -->
            <c:if test="${not empty error_str}">
                <div class="error-message">
                    <p>${error_str}</p>
                </div>
            </c:if>
        </div>
        <%@include file="footer.jsp"%>
    </body>
</html>