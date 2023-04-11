<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.bean.EMSStoreBean"%>
<%@page import="java.util.ArrayList"%>
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
	<%
	ArrayList<EMSStoreBean> store = (ArrayList<EMSStoreBean>) request.getAttribute("store");
	ArrayList<EMSStoreBean> allStore = (ArrayList<EMSStoreBean>) request.getAttribute("allstore");
	int srNo = 1;
	int srNo1 = 1;
	%>
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
										<b>Store List </b>
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
															<th style="width: 5%">Sr.No</th>
															<th style="width: 17%">Category</th>
															<th style="width: 17%">Grade</th>
															<th style="width: 17%">Size</th>
															<th style="width: 17%">Quantity</th>
															<th style="width: 17%"></th>
														</tr>
													</thead>
													<tbody id="">
														<%
														for (EMSStoreBean s : store) {
														%>
														<tr>
															<td><%=srNo++%></td>
															<td><%=s.getCategory()%><br></td>
															<td><%=s.getGrade()%><br></td>
															<td><%=s.getSize()%><br></td>
															<td><%=s.getQuantity()%><br></td>
															<td class="project-actions text-right">
																<button type="button" class="btn btn-primary btn-sm"
																	data-toggle="modal" data-target="#modal-projectDetails">
																	<i class="fas fa-folder"></i>
																</button>
																<button type="button" class="btn btn-danger btn-sm"
																	data-toggle="modal" data-target="#modal-stockDelete"
																	id="Delete1">
																	<i class="fas fa-trash"></i>
																</button>
															</td>
														</tr>
														<%
														}
														%>
														</tr>
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



	<div class="modal fade" id="modal-stockDelete">
		<div class="modal-dialog">
			<div class="modal-content bg-danger">
				<div class="modal-header">
					<h4 class="modal-title">Delete Stock</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<p>Are you sure you want to delete this Stock?</p>
				</div>
				<div class="modal-footer justify-content-between">
					<button type="button" class="btn btn-outline-light"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-outline-light"
						id="deleteClicked">Delete Project</button>
				</div>
			</div>
		</div>
	</div>
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