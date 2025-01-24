<%@ page contentType="text/html; charset=US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <link rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="https://kit.fontawesome.com/dd3bb92d24.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="/css/styles.css">
         <style>#my-map-canvas .text-marker{}.map-generator{ max-width: 100%; max-height: 100%; background: none;}</style>
    </head>
    <body>
        <%@include file="header.jsp"%>

        <section id="aboutUs">
            <div class="container-fluid text-center">
                <p class="h1">About Us</p>

                <div id="abtUs">
                    <p class="lead">Welcome to ABC Cars - Your Trusted Car Sales Partner</p><br>
                    <p>
                    At ABC Cars, we're driven by a passion for automobiles and a commitment to customer satisfaction. 
                    With years of experience in the industry, our team is dedicated to offering a seamless car buying 
                    and selling experience. Our extensive collection of vehicles, from sleek sports cars to reliable family 
                    sedans, ensures that you'll find the perfect match for your lifestyle and budget.</p><br>

                    We believe in transparency, quality, and service. That's why every car in our inventory is thoroughly 
                    inspected and comes with a detailed history report. Our knowledgeable staff is always here to help you 
                    with personalized advice, making sure you drive away confident and happy.<br>

                    Join the ABC Cars family and experience the difference where trust and value meet on the road to your next journey.
                </div>

                <hr>
                <sec:authorize access="hasRole('user')">
                <p class="h1">Offered services:</p>
                <a href="cars"><button class="btn btn-info" id="icon-text"><span class="fa-solid fa-car fa-fw"></span>Buy a used car</button></a>
                <a href="reg_car"><button class="btn btn-info" id="icon-text"><span class="fa-solid fa-sack-dollar fa-fw"></span>Sell your used car</button></a>
                <hr>
                </sec:authorize>

                <sec:authorize access="hasRole('user')">
                <p class="h1">Wanna know more? Contact us!</p><br>
                <div>
                    <div id="contact" id="map_inner">
                        <p id="icon-text"><span class="fa-brands fa-square-facebook fa-fw"></span>ABC Car Sales</p>
                        <p id="icon-text"><span class="fa-solid fa-envelope"></span> carsalesportal@gmail.com</p>
                    </div>
                        <%-- embedded google maps element --%>
                    <div style="text-decoration:none; overflow:hidden;max-width:100%;width:500px;height:500px;" id="map_inner">
                        <div id="my-map-canvas" style="height:100%; width:100%;max-width:100%;"><iframe style="height:100%;width:100%;border:0;" frameborder="0" 
                            src="https://www.google.com/maps/embed/v1/place?q=President+Ramon+Magsaysay+Ext,+Panagdait+Rd,+Cebu+City&key=AIzaSyBFw0Qbyq9zTFTd-tUY6dZWTgaQzuU17R8"></iframe>
                        </div>
                        <a class="google-map-html" rel="nofollow" href="https://www.bootstrapskins.com/themes" id="make-map-data">premium bootstrap themes</a>
                    </div>
                </div>
                </sec:authorize>

            </div>
        </section>
        <hr>
        <%@include file="footer.jsp"%>

        <script>
        $(window).on('resize', function() {
    var currCenter = map.getCenter();
    google.maps.event.trigger(map, 'resize');
    map.setCenter(currCenter);
})
        </script>
    </body>
</html>