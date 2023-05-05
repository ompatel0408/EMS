<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.dao.EMSGRNDao,com.bean.EMSGRNBean,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">

								<div class="card-header">
									<h3 class="card-title mt-2">
										<b>Clients </b>
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
															<th style="width: 15%">Sr.No</th>
															<th style="width: 30%">Vendor name</th>
															<th style="width: 30%">Received Date</th>
															<th style="width: 20%"></th>
														</tr>
													</thead>
													<tbody id="myTable">
														<%for (EMSGRNBean EGB : EMSGRNDao.getInstance().getAllData()) {%>
														<tr>
															<td id="GRNId"><%=EGB.getGRNID()%></td>
															<td><a><%=EGB.getVendorName()%></a> <br></td>
															<td><a><%=EGB.getReceiveDate()%></a> <br></td>
															<td class="project-actions text-right">
																<button type="button" class="btn btn-primary btn-sm"
																	data-toggle="modal"
																	data-target="#modal-grnDetails<%=EGB.getGRNID()%>">
																	<i class="fas fa-folder"></i>
																</button>
																<div class="modal fade"
																	id="modal-grnDetails<%=EGB.getGRNID()%>">
																	<div class="modal fade show" id="modal-xl"
																		style="padding-right: 19px; display: block;"
																		aria-modal="true" role="dialog">
																		<div class="modal-dialog modal-xl">
																			<div class="modal-content">
																				<div class="modal-header">
																					<h4 class="modal-title">Extra Large Modal</h4>
																					<button type="button" class="close"
																						data-dismiss="modal" aria-label="Close">
																						<span aria-hidden="true">×</span>
																					</button>
																				</div>
																				<div class="modal-body">
																					<div class="form-group">
																						<label for="">GRN picture</label> <br> <embed
																							src="GRNImages/<%=EGB.getPath1()%>" type="application/pdf"
																							 width="100%" height="600px">
																					</div>
																					<div class="form-group">
																						<label for="">GRN picture</label> <br> <embed
																							src="GRNImages/<%=EGB.getPath2()%>" type="application/pdf"
																							 width="100%" height="600px">
																					</div>
																				</div>
																				<div class="modal-footer justify-content-between">
																					<button type="button" class="btn btn-default"
																						data-dismiss="modal">Close</button>
																				</div>
																			</div>
																			<!-- /.modal-content -->
																		</div>
																		<!-- /.modal-dialog -->
																	</div>
																</div>
																<button type="button" class="btn btn-danger btn-sm"
																	data-toggle="modal"
																	data-target="#modal-stockDelete<%=EGB.getGRNID()%>"
																	id="Delete1">
																	<i class="fas fa-trash"></i>
																</button>
																<div class="modal fade"
																	id="modal-stockDelete<%=EGB.getGRNID()%>">
																	<div class="modal-dialog">
																		<div class="modal-content bg-danger">
																			<div class="modal-header">
																				<h4 class="modal-title">Delete GRN</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true"></span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<p>Are you sure you want to delete this GRN?</p>
																			</div>
																			<div class="modal-footer justify-content-between">
																				<button type="button" class="btn btn-outline-light"
																					data-dismiss="modal">Close</button>
																				<button type="button" class="btn btn-outline-light"
																					id="deleteClicked" onclick="deleteGNR()">Delete
																					GRN</button>
																			</div>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
														<%}%>

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
		</div>
		<!-- content-wrapper -->
	</div>
	<script type="text/javascript">
		function deleteGNR() {
			var xhr = new XMLHttpRequest();

			// specify the servlet URL and HTTP method
			xhr
					.open('DELETE', 'http://localhost:8080/EMS/EMSGRNServlet',
							true);

			// set headers
			xhr.setRequestHeader('Content-type', 'application/json');

			// handle the response
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var response = xhr.responseText;
					console.log(response)
					window.location.href = "EMSGRNList.jsp"
				}
			}
			// send the request
			xhr.send(JSON.stringify({
				grnId : document.getElementById('GRNId').innerHTML
			}));
		}
	</script>
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
	<script src="assets/dist/js/demo.js"></script>
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
		function changeStatus(userId, currentStatus) {
			location.href = "ChangeStudentStatus?userId=" + userId
					+ "&currentStatus=" + currentStatus;
		}
	</script>
</body>
</html>