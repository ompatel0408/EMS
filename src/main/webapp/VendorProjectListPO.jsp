<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMS purchase order</title>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>

	<div class="content-wrapper p-3">
		<div class="card card-default">
			<div class="card-header">
				<h3 class="card-title">
					<b>Purchase and Quotation Calculation Graph</b>
				</h3>
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
				<div class="row">
					<div class="col-md-6">
						<form>
							<div class="form-group">
								<label for="item-id">Vendors</label> <select id="vendorList"
									class="form-control" required>
								</select>
							</div>
							<div class="form-group">
								<label for="item-id">Project</label> <select id="projectId"
									class="form-control" required>
								</select>
							</div>
							<div
								class="d-flex justify-content-center align-items-center mt-auto ">
								<button type="button" class="btn btn-primary w-50" id="print"
									onclick="setVendorValue()">
									<strong>+ Print PO </strong>
								</button>
							</div>
						</form>
					</div>
					<div class="col-sm-6">
						<form action="EMSSendMailWithAttechedFileServlet" method="post"
						enctype="multipart/form-data">
							<div class="card-header">
								<h3 class="card-title">Upload The PDF for sending mail</h3>
							</div>
							<div class="btn-group">
								<div class="dropzone">
									<input type="file" name="file" id="inputFile" /> <label
										for="inputFile" class="custom-file-upload"></label>
									<p id="file-name"></p>
								</div>
							</div>
							<input type="hidden" name="vendorName" id="vendorName">
							<div
								class="d-flex justify-content-center align-items-center mt-auto ">
								<button type="submit" class="btn btn-danger w-50" id="print">
									<strong>+ Send PO Through Mail</strong>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- <select id="vendorList">
		<option value="Select Vendor">Select Vendor</option>
	</select> -->
</body>
<script type="text/javascript" src="VendorProjectListPO.js"></script>
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