<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSFinalQuotation</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Dispatch</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>
				<div class="card-body">
					<form action="EMSDispatchItemServlet" method="POST">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>Client</label> <select class="form-control"
										id="clientId" name="clientId">
									</select>
								</div>
								<div class="form-group">
									<label>Vehicle Number</label> <input type="text"
										class="form-control" id="vehicleNoId"
										placeholder="Vehicle Number" name="vehicleNo" pattern="[A-Za-z0-9_\- ]{1,50}"  required="required">
								</div>
								<div class="form-group">
									<label>Travel Company</label> <input type="text"
										class="form-control" id="travelCom"
										name="travelCom" placeholder="Travel Company Name" pattern="[A-Za-z0-9_ ]{1,260}"  required="required">
								</div>
								<div class="form-group">
									<label>Checked By</label> <input type="text"
										class="form-control" id="checkBy"
										name="checkBy" placeholder="Checked person" pattern="[A-Za-z_ ]{1,260}"  required="required">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Offers</label> <select class="form-control" name="offerId"
										id="offerId">
									</select> 
								</div>
								<div class="form-group">
									<label>Travel Company Owner</label> <input type="text"
										class="form-control" id="travelComOwnr"
										name="travelComOwnr" placeholder="Travel Company Owner" pattern="[A-Za-z_ ]{1,260}"  required="required">
								</div>
								<div class="form-group">
									<label class="float-left">Destination Address</label>
									<textarea class="form-control" rows="3"
										placeholder="Destination Address..." name="destAddress"
										id="destAddress" style="height: 125px;" 
										maxlength="350" required="required"></textarea>
								</div>
							</div>
							<div
								class="col-md-12 d-flex justify-content-center align-items-center mt-2">
								<button type="submit" class="btn btn-danger" id="submitBtn">
									<strong>+ Process to Dispatch </strong>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="EMSDispatchItems.js"></script>
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