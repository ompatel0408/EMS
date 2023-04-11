<%@page import="com.bean.QuotationPerItemBean"%>
<%@page import="java.util.ArrayList,com.bean.ClientBean"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<style>
#myModal {
	padding-top: 0px;
	margin-top: 0;
	overflow: hidden;
	height: 1100px;
}
</style>

<body class="hold-transition sidebar-mini layout-fixed">
	<%
	ArrayList<QuotationPerItemBean> lists = (ArrayList<QuotationPerItemBean>) request.getAttribute("lists");
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="wrapper">

		<div class="content-wrapper">
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">

								<div class="card-header">
									<h3 class="card-title">Offer Code : ${offerCode }</h3>
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
															<th style="width: 17%">Category</th>
															<th style="width: 17%">Grade</th>
															<th style="width: 20%">Size</th>
															<th style="width: 20%">Quantity</th>
															<th style="width: 20%">Price</th>
														</tr>
													</thead>
													<tbody>

														<%
														int temp = 0;
														for (QuotationPerItemBean list : lists) {
														%>
														<tr>
															<td><%=++temp%></td>
															<td><%=list.getCatagory()%></td>
															<td><a> <%=list.getGrade()%>
															</a> <br></td>
															<td><a> <%=list.getSize()%>
															</a> <br></td>
															<td><a> <%=list.getQuantity()%>
															</a> <br></td>
															<td><a> <%=list.getpricePerItem()%>
															</a> <%-- <td>
																						<button type="button"
																							class="btn btn-danger btn-sm"
																							data-toggle="modal"
																							data-target="#modal-projectDelete<%=list.getCatagoryId()%><%=list.getGradeId()%><%=list.getSize()%>">
																							<i class="fas fa-trash"></i>
																							Delete
																						</button>
																				</td> --%>
																<div class="modal fade"
																	id="modal-projectDelete<%=list.getCatagoryId()%><%=list.getGradeId()%><%=list.getSize()%>">
																	<div class="modal-dialog">
																		<div class="modal-content bg-danger">
																			<div class="modal-header">
																				<h4 class="modal-title">Delete</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true"></span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<p>Are you sure you want to delete this?</p>
																			</div>
																			<div class="modal-footer justify-content-between">
																				<button type="button" class="btn btn-outline-light"
																					data-dismiss="modal">Close</button>
																				<form action="QuotationPerItemServlet">
																					<input type="hidden" name="offer" value=2>
																					<input type="hidden" name="offerCode"
																						value="${offerCode}"> <input type="hidden"
																						name="cid" value="<%=list.getCatagoryId()%>">
																					<input type="hidden" name="gid"
																						value="<%=list.getGradeId()%>"> <input
																						type="hidden" name="sid"
																						value="<%=list.getSize()%>">
																					<button type="submit" value="DELETE"
																						class="btn btn-outline-light">Delete
																						Client</button>
																				</form>
																			</div>

																		</div>
																	</div>
																</div> <%
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