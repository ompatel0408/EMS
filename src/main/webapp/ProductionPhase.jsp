<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPEhtml>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMS</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Gate Pass Inword</h3>
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
					<form action="ProductionPhaseServlet" method="POST">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="item-id">Project</label><select id="projectId"
										name="projectId" class="form-control" required
										onchange="getOffers()">
										<option value="select" selected>Select Project</option>
									</select>
								</div>
								<div class="form-group">
									<label for="item-id">Items</label><select id="ItemCode"
										name="ItemCode" class="form-control" required>
										<option value="select" selected>Select Items</option>
									</select>
								</div>
								<div class="form-group mt-3 d-flex justify-content-around">
									<button type="submit" id="add_Quatation" class="btn btn-danger">
										<strong>&nbsp;+ &nbsp;</strong>Process with Phase
									</button>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="item-id">Enter Phase Count </label><input
										type="number" class="form-control" name="phaseCount"
										pattern=".{,2}" onkeyup="appendInputs(this.value)"
										placeholder="Enter Phase name">
								</div>
								<div class="col-md-6" id="appendHere"></div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src=ProductionPhase.js></script>
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