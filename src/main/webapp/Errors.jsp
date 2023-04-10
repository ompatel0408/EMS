<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="d-flex align-items-center justify-content-center pl-5">
		<div class="text-center">
			<h1 class="display-1 fw-bold">500</h1>
			<p class="fs-3">
				<span class="text-danger">Opps!</span> Something went wrong
			</p>
			<a href="${referer}" class="btn btn-danger">Back to Dashboard</a>
		</div>
	</div>
</body>
</html>