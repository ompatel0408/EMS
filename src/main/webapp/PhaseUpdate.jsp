<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMS DPR</title>
</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Daily Progress Report</h3>

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
					<form action="DailyProgressReportServlet" method="POST">
						<input type="hidden" value="delete" name="delete">
						<div class="row">
							<div class="col-sm-4">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Select Projects</label> <select
										id="projectId" class="form-control" name="projectId" required onchange="getOffers()">
										<option value="select" selected>Select Offers</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Select Items</label> <select id="ItemCode"
										class="form-control" required name="itemId" onchange="getSubItems()">
										<option value="select" selected>Select Items</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Select Sub Items</label> <select
										id="subItemId" class="form-control" name="subItemId" required onchange="getPhase()">
										<option value="select" selected>Select Offers</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Select Phase</label> <select
										id="phaseId" class="form-control" name="phase"required >
										<option value="select" selected>Select Phase</option>
									</select>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group d-flex justify-content-around">
									<button type="submit" id="add_Quatation"
										class="btn btn-primary w-25 ">
										<strong>+</strong> Add Phase Working
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="PhaseUpdate.js"></script>
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
	<script
		src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
	<script src="assets/dist/js/adminlte.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>