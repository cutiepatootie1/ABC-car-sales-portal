<%@ page contentType="text/html; charset=US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>

    <!--  Enable Bootstrap -->
    <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<%-- google fonts cdn --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
	<%-- jquery is loaded before bootstrap js --%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/css/styles.css"/>
    <script>
	$(document).ready(function() {
    // Function to update model attribute
    function updateModelAttribute() {
		// Get car ID from URL
        var urlParams = new URLSearchParams(window.location.search);
        var carId = urlParams.get('id');

        $.ajax({
            url: '/timeRemaining', // URL of your server-side script
            type: 'GET',
            data: { id: carId }, // Replace 'yourCarId' with the actual car ID
            success: function(data) {
                // Update model attribute here
				$('#remainingDays').text(data.remainingDays);
				$('#remainingHours').text(data.remainingHours);
				$('#remainingMinutes').text(data.remainingMinutes);
                $('#remainingSeconds').text(data.remainingSeconds);

				if(parseInt(data.remainingDays) <= 0 &&
			   parseInt(data.remainingHours) <= 0 &&
				parseInt(data.remainingMinutes) <= 0 &&
				parseInt(data.remainingSeconds) <= 0){
					$('#bidButton').attr('disabled','disabled');
				}
            }
        });
    }

    // Call updateModelAttribute every 5 seconds
    setInterval(updateModelAttribute, 1000);
	});

    </script>
    <link href="/css/style.css" rel="stylesheet" />
    <script src="/js/custom.js"></script>


    </head>

<body>


	<%@ include file="header.jsp"%>


	<!-- First Container -->
	<div class="container">
	
		<div class="row">
		<h3 class="margin text-center">Car Information</h3>

			<div class="col-md-8">

				<form:form class="form-horizontal">

					<div class="form-group">
						<div class="col-sm-8"><img src="/images/${cars.carphoto}" style="max-width:750px;" ></div>
					</div>


					<div class="form-group">
						<label for="make" class="control-label col-sm-4">Car Make:
						</label>
						<div class="control-label col-sm-4">${cars.make}</div>
					</div>

					<div class="form-group">
						<label for="model" class="control-label col-sm-4">Car
							Model: </label>
						<div class="control-label col-sm-4">${cars.model}</div>
					</div>

					<div class="form-group">
						<label for="reg" class="control-label col-sm-4">Registration Year: </label>
						<div class="control-label col-sm-4">${cars.registration}</div>
					</div>

					<div class="form-group">
						<label for="price" class="control-label col-sm-4">Price: </label>
						<div class="control-label col-sm-4">${cars.price}</div>
					</div>

					<hr />

					<div class="form-group">
						<label for="price" class="control-label col-sm-4"> <img
							src="/images/avatar.svg" width="60px" alt="avatar"></label>
						<div class="col-sm-8" style="padding: 20px;">

							<form action="car_detail?id=${cars.id}" method="post">
								<input type="hidden" name="id" value="${cars.id}" />
								 <input
									type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" /> 
								
								<input type="number"
									placeholder="Enter Bid Price" name="bidprice"  min="${cars.price}" />

								<div class="form-group" style="padding: 20px;">
									<button id="bidButton" type="submit" class="btn btn-success">Bid for this car</button>
									<a href="cars" class="btn btn-danger">Go Back</a>
								</div>

							</form>
						</div>

					</div>

					<script>
						function goBack() {
							window.history.back();
						}
					</script>

				</form:form>


			</div>

			<div class="col-md-3 bg-3">
				<h4>Bidding ends in <span id="remainingDays"></span> days, <span id="remainingHours"></span> hrs, <span id="remainingMinutes"></span> mins and 
				<span id="remainingSeconds"></span> secs</h4>
			</div>

			<div class="col-md-3 bg-3">
				<h4> Bid History</h4><br/>
			<c:forEach var="bidding" items="${bidinfo}">
				
				<div class="card mb-3 card bg-info" style="padding:10px;">
				
					<div class="row" >
						<div class="col-md-6">
							<img class="card-img-top"
							src="/images/avatar.svg" width="30px" class="rounded-circle"
							class="mr-3" alt="avatar">
							<p>${bidding.bidderName}</p>
						</div>
						<div class="col-md-6">
							<div class="card-body">
								<p class="card-head"><small class="text-muted">${bidding.biddingTime}</small></p>
								<p class="card-text">Bid Price: ${bidding.biddingPrice}</p>
						
							</div>
						</div>
					</div>
				</div>
				
				<hr/>
			</c:forEach>

			</div>


		</div>

	</div>

</body>
</html>
