<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.EMSPerMachineMntBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
int srNo = 0;
ArrayList<EMSPerMachineMntBean> arlst = (ArrayList<EMSPerMachineMntBean>) request.getAttribute("data");
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper">
			<%
			if (arlst.size() != 0) {
			%>
			<section class="content">
				<div class="container-fluid mt-3">
					<div class="row">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title mt-2">
										<b>Machine Name : <%=arlst.get(0).getMcName()%></b>
									</h3>
									<h3 class="card-title mt-2 float-right">
										<b>Model Name : <%=arlst.get(0).getModelName()%></b>
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
															<th style="width: 15%">M/c Given date</th>
															<th style="width: 15%">Due Date</th>
															<th style="width: 25%">Maintenance Company</th>
															<th style="width: 15%">received Date</th>
															<th style="width: 15%">Total Price</th>
															<th style="width: 10">Other</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (EMSPerMachineMntBean db : arlst) {
														%>
														<tr>
															<td><%=++srNo%></td>
															<td><%=db.getGiveDate()%></td>
															<td><%=db.getDueDate()%></td>
															<td><%=db.getComName()%></td>
															<td><%=db.getReciveDate() == null ? "Not Received" : db.getReciveDate()%></td>
															<td><%=db.getPrice()%></td>
															<td><button type="button"
																	class="btn btn-primary btn-sm" data-toggle="modal"
																	data-target="#modal-projectDetails<%=srNo%>">
																	<i class="fas fa-folder"></i>
																</button>
																<div class="modal fade"
																	id="modal-projectDetails<%=srNo%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Others</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<label for="exampleInputEmail1">Invoice </label>
																					<p><%=db.getInvoice()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">Remark </label>
																					<p><%=db.getRemark()%></p>
																				</div>
																			</div>
																		</div>
																	</div>
																</div></td>
														</tr>
														<%
														}
														%>
													</tbody>
												</table>
											</div>
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
			<%
			} else {
			%>
			<section class="content">
				<div class="container-fluid mt-3 d-flex aline-item-center justify-content-center">
					<h2>Still not any maintenance in this machine.</h2>
				</div>
			</section>

			<%
			}
			%>
			<!-- content -->
		</div>
		<!-- content-wrapper -->
	</div>
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
		function changeStatus(userId, currentStatus) {
			location.href = "ChangeStudentStatus?userId=" + userId
					+ "&currentStatus=" + currentStatus;
		}
	</script>
</body>
</html>