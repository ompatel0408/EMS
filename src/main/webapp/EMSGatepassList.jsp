<%@page import="com.bean.EMSGetpassinwordBean"%>
<%@page import="com.bean.EMSGetpassOutwordBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMS Gatepass List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">
</head>

<%
int srNo = 0;
	ArrayList <EMSGetpassOutwordBean> algpb = (ArrayList<EMSGetpassOutwordBean>) request.getAttribute("data");
	ArrayList <EMSGetpassinwordBean> algpbd = (ArrayList<EMSGetpassinwordBean>) request.getAttribute("data1");
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content mt-3">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title mt-2">
										<b>Remaining Gatepass Items</b>
									</h3>
								</div>
								<div class="card-body">
									<div id="example1_wrapper"
										class="dataTables_wrapper dt-bootstrap4">
										<div class="row">
											<div class="col-sm-12">
												<table id="example1"
													class="table table-bordered table-striped dataTable dtr-inline"
													aria-describedby="example1_info">
													<thead>
														<tr>
															<th style="width: 2%">Sr.No</th>
															<th style="width: 12%">Vendor Name</th>
															<th style="width: 15%">material Name</th>
															<th style="width: 12%">Quantity</th>
															<th style="width: 12%">Size</th>
															<th style="width: 12%">Vehicle Number</th>
															<th style="width: 12%">Will Receive?</th>
															<th style="width: 12%">Received</th>
															<th style="width: 10%">Other</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (EMSGetpassOutwordBean gb : algpb) {
														%>
														<tr>
															<td><%=++srNo%></td>
															<td><%=gb.getVendorName()%></td>
															<td><%=gb.getMatName()%></td>
															<td><%=gb.getQty()%></td>
															<td><%=gb.getSize()%></td>
															<td><%=gb.getVehicleNo()%></td>
															<td><%=gb.getReceive() == 0 ? "Yes" : "No"%></td>
															<td><%=gb.getReceived() == 0 ? "Yes" : "No"%></td>
															<td>
																<button type="button" class="btn btn-primary btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectDetails<%=srNo%>">
																	<i class="fas fa-folder"></i>
																</button>
																<div class="modal fade" id="modal-projectDetails<%=srNo%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Other Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																						<label for="exampleInputEmail1">Issue Date</label>
																					<p><%=gb.getIssueDate()%></p>
																				</div>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<label for="exampleInputEmail1">Address</label>
																					<p><%=gb.getAddress()%></p>
																				</div>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<label for="exampleInputEmail1">Remark</label>
																					<p><%=gb.getRemark()%></p>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
														<%
														}
														%>
													</tbody>
												</table>
												<!-- <table>-->
											</div>
											<!-- col-sm-12 -->
										</div>
										<!-- row -->
									</div>
									<!-- example1_wrapper -->
								</div>
								<!-- /.card-body -->
							</div>
							<!-- card -->
						</div>
						<!-- col-12 -->
					</div>
					<!-- row -->
				</div>
				<!-- container-fluid -->
			</section>
			<!-- content -->
			<section class="content mt-3">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title mt-2">
										<b>Received Gatepass items</b>
									</h3>
								</div>
								<div class="card-body">
									<div id="example2_wrapper"
										class="dataTables_wrapper dt-bootstrap4">
										<div class="row">
											<div class="col-sm-12">
												<table id="example2"
													class="table table-bordered table-striped dataTable dtr-inline"
													aria-describedby="example2_info">
													<thead>
														<tr>
															<th style="width: 2%">Sr.No</th>
															<th style="width: 12%">Vendor Name</th>
															<th style="width: 15%">material Name</th>
															<th style="width: 12%">Quantity</th>
															<th style="width: 12%">Received Date</th>
															<th style="width: 12%">Vehicle Number</th>
															<th style="width: 10%">Other</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (EMSGetpassinwordBean gb : algpbd) {
														%>
														<tr>
															<td><%=++srNo%></td>
															<td><%=gb.getVendor()%></td>
															<td><%=gb.getItemName()%></td>
															<td><%=gb.getQty()%></td>
															<td><%=gb.getReceiveDate()%></td>
															<td><%=gb.getVehicleNo()%></td>
															<td>
																<button type="button" class="btn btn-primary btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectDetails<%=srNo%>">
																	<i class="fas fa-folder"></i>
																</button>
																<div class="modal fade" id="modal-projectDetails<%=srNo%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Other Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<label for="exampleInputEmail1">Remark</label>
																					<p><%=gb.getRemark()%></p>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
														<%
														}
														%>
													</tbody>
												</table>
												<!-- <table>-->
											</div>
											<!-- col-sm-12 -->
										</div>
										<!-- row -->
									</div>
									<!-- example2_wrapper -->
								</div>
								<!-- /.card-body -->
							</div>
							<!-- card -->
						</div>
						<!-- col-12 -->
					</div>
					<!-- row -->
				</div>
				<!-- container-fluid -->
			</section>
		</div>
		<!-- content-wrapper -->
	</div>
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
	<!-- DataTables  & Plugins -->
	<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="assets/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="assets/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="assets/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="assets/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="assets/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="assets/plugins/jszip/jszip.min.js"></script>
	<script src="assets/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="assets/plugins/pdfmake/vfs_fonts.js"></script>
	<script src="assets/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script src="assets/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="assets/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<!-- AdminLTE App -->
	<script>
		$(document).ready(
				function() {
					$("#example1").DataTable(
							{
								"responsive" : true,
								"lengthChange" : false,
								"autoWidth" : false,
								"buttons" : [ "copy", "csv", "excel", "pdf",
										"print", "colvis" ]
							}).buttons().container().appendTo(
							'#example1_wrapper .col-md-6:eq(0)');
				})
	</script>
	<script>
		$(document).ready(
				function() {
					$("#example2").DataTable(
							{
								"responsive" : true,
								"lengthChange" : false,
								"autoWidth" : false,
								"buttons" : [ "copy", "csv", "excel", "pdf",
										"print", "colvis" ]
							}).buttons().container().appendTo(
							'#example2_wrapper .col-md-6:eq(0)');
				})
	</script>
	<script>
		function changeStatus(userId, currentStatus) {
			location.href = "ChangeStudentStatus?userId=" + userId
					+ "&currentStatus=" + currentStatus;
		}
	</script>
</body>
</html>