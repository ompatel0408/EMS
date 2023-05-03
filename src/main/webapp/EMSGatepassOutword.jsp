<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
	String err = (String) request.getAttribute("error");
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Gate Pass Outword</h3>
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
					<form action="EMSGetpassOutwordServlet" method="POST">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="item-id">Vendors</label> <select id="vendorId"
										name="vendorId" class="form-control" required>
										<option value="select" selected>Select Vendors</option>
									</select>
								</div>
								<div class="form-group">
									<label>Quantity</label> <input type="number" class="form-control" name="qty"
										id="Quantity" placeholder="Enter Quantity">
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label>Issue Date</label> <input type="date"
										class="form-control" name="idate" id="idate">
								</div>
								<div class="form-group">
									<label>Vehicle Number</label> <input type="text"
										class="form-control" id="vehicleNo" name="vehicleNo"
										placeholder="Enter Vehicle Number">
								</div>
								<div class="form-group d-flex justify-content-around mt-5">
									<label >Item will receive? : </label>
									<div class="custom-control custom-radio">
										<input type="radio" checked="checked" name="receive" id="yes" class="custom-control-input" value="1"> <label class="custom-control-label" for="yes" checked>Yes</label>
									</div>
									<div class="custom-control custom-radio">
										<input type="radio" name="receive" id="no" class="custom-control-input" value="0"> <label class="custom-control-label" for="no">No</label>
									</div>
								</div>
							</div>
							<!-- /.col -->
							<div class="col-md-6">
								<div class="form-group">
									<label>Material/machine Name</label> <input type="text"
										class="form-control" name="mname" id="mname"
										placeholder="Enter Material or machine Name">
									<span class="text-danger">
										<%=err == null ? "" : err%>
									</span>
								</div>
								<div class="form-group">
									<label>Size</label> <input type="text" class="form-control"
										name="size" id="size-id" placeholder="Enter size">
								</div>
								<div class="form-group">
									<label>Address</label>
									<textarea class="form-control" rows="3" placeholder="Enter Address" name="address"
										id="address" style="height: 85px;"></textarea>
								</div>
								<div class="form-group">
									<label>Remark</label>
									<textarea class="form-control" rows="3" placeholder="Enter Remark" name="remark"
										id="remark" style="height: 85px;"></textarea>
								</div>
							</div>
							<div class="col">
								<div class="form-group mt-3 d-flex justify-content-around">
									<button type="submit" id="add_Quatation"
										class="btn btn-danger w-25 ">
										<strong>+</strong> Add Gatepass
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
<script type="text/javascript" src=EMSGetpassOutword.js></script>
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