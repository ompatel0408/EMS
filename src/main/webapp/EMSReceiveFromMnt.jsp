<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Machine receive from Maintenance</title>
</head>
<body>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Machine come from Maintenance</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>
				<!-- /.card-header -->
				<div class="card-body">
					<form action="EMSReceiveMachineFromMnt" method="POST">
						<div class="row">

							<div class="col-md-6">
								<div class="form-group">
									<label>Machine</label> <select id="machineId"
										name="machineId" class="form-control" required>
									</select>
								</div>
								<div class="form-group">
									<label>Received Date</label> <input type="date" class="form-control" required="required"
										name="rcvDate" id="rcvDate" placeholder="Enter Company Name">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Model number</label> <select id="modelId" name="modelId"
										class="form-control" required>  
										<option value="select Model no"selected>Select Model Number</option>
									</select>
								</div>
								<div class="form-group">
									<label for="">Maintenance Price</label> <input type="number" name="mntPrice"
										class="form-control" id="mntPrice" placeholder="Enter Price" name="mntPrice" required="required">
								</div>
								<input type="hidden" id="offerCode">
								<div class="form-group mt-5 d-flex justify-content-around">
									<button type="submit" id="AddItemBtn"
										class="btn btn-primary">
										<strong> + Add Machine </strong>
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="EMSReceiveFromMnt.js"></script>
<script src="assets/dist/js/models.js"></script>
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