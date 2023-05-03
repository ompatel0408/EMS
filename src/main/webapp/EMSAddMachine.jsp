<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSMachine</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Machine</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>
				<div class="card-body">
					<form action="EMSAddMachineServlet" method="POST">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>Machine Name</label>  <input type="text"
										class="form-control" id="mName"
										placeholder="Machine Name" name="mName">
								</div>
								<div class="form-group">
									<label>Model Number</label> <input type="text"
										class="form-control" id="vehicleNoId"
										placeholder="Model Number" name="modelNo">
								</div>
								<div class="form-group">
									<label>Invoice</label> <input type="text"
										class="form-control" id="invoice"
										name="invoice" placeholder="Invoice">
								</div>
								<div class="form-group">
									<label>Purchase Date</label> <input type="date"
										class="form-control" id="purchaseDate" 
										name="purchaseDate" placeholder="Purchase Date">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Machine Company Name</label> <input type="text"
										class="form-control" id="mcname"
										name="mcname" placeholder="Machine Company Name">									</select> 
								</div>
								<div class="form-group">
									<label>Maintenance Due Date</label> <input type="date"
										class="form-control" id="mntDueDate"
										name="mntDueDate" placeholder="Maintenance Due Date">
								</div>
								<div class="form-group">
									<label class="float-left">Remark</label>
									<textarea class="form-control" rows="3"
										placeholder="Destination Address..." name="remark"
										id="remark" style="height: 125px;" 
										maxlength="350"></textarea>
								</div>
							</div>
							<div
								class="col-md-12 d-flex justify-content-center align-items-center mt-2">
								<button type="submit" class="btn btn-danger" id="submitBtn">
									<strong>+ Add Machine </strong>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="assets/dist/js/models.js"></script>
<script src="assets/plugins/jquery/jquery.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/plugins/select2/js/select2.full.min.js"></script>
<script src="assets/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<script src="assets/plugins/moment/moment.min.js"></script>
<script src="assets/plugins/inputmask/jquery.inputmask.min.js"></script>
<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
<script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<script src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="assets/dist/js/adminlte.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>