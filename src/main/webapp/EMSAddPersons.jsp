<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add persons</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Person</h3>

					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
						<button type="button" class="btn btn-tool"
							data-card-widget="remove">
							<i class="fas fa-times"></i>
						</button>
					</div>
				</div>
				<div class="card-body">
					<form action="EMSAddPersonServlet" method="POST">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Person name</label> <input type="text"
										class="form-control" id="pname" name="pname" pattern="[A-Za-z_ ]{2,15}"
										required="required" placeholder="Enter person name">
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label>Number</label> <input type="number" class="form-control"
										name="number" placeholder="Enter Number" pattern="[0-9]{10}"
										required="required">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Email(Optional)</label> <input type="text"
										class="form-control" name="emailid" 
										placeholder="Enter Email">
								</div>
								<div class="form-group">
									<label>Designation</label> <input type="text"
										class="form-control" name="desg" pattern="[A-Za-z_ ]{2,15}"
										required="required" placeholder="Enter designation">
								</div>
							</div>
						</div>
						<div class="col">
							<div class="form-group mt-3 d-flex justify-content-around">
								<button type="submit" class="btn btn-danger w-25">
									<strong>+</strong> Add Person
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="assets/plugins/jquery/jquery.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/plugins/select2/js/select2.full.min.js"></script>
<script
	src="assets/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<script src="assets/plugins/moment/moment.min.js"></script>
<script src="assets/plugins/inputmask/jquery.inputmask.min.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<script
	src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<script
	src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="assets/dist/js/adminlte.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>