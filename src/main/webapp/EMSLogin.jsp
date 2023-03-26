<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&amp;display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="assets/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">
</head>
<body class="hold-transition lockscreen">
	<!-- Automatic element centering -->
	<div class="lockscreen-wrapper">
		<div class="lockscreen-logo">
			<a class="mb-4"><b>EMS Project</b> Private Limited</a>
		</div>
		<!-- START LOCK SCREEN ITEM -->
		<div class="lockscreen-item">
			<!-- lockscreen image -->
			<div class="lockscreen-image">
				<img src="assets/dist/img/user1-128x128.jpg" alt="User Image">
			</div>
			<form class="lockscreen-credentials mt-5" action="EMSLoginServlet" method="post">
				<div class="input-group">
					<input type="password" class="form-control" name="password" placeholder="password">

					<div class="input-group-append">
						<button type="submit" class="btn">
							<i class="fas fa-arrow-right text-muted"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="help-block text-center p-3">Enter your password to
			retrieve your session</div>
		<div class="lockscreen-footer text-center">
			Copyright © 2014-2021 <b><a class="text-black">EMS Projects Pvt, Ltd.</a></b><br> All rights reserved
		</div>
	</div>
	<!-- /.center -->

	<!-- jQuery -->
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>


</body>
</html>