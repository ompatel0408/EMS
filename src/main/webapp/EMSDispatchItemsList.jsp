<%@page import="com.bean.EMSDispatchBean"%>
<%@page import="java.util.ArrayList"%>
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
ArrayList<EMSDispatchBean> aledb = (ArrayList<EMSDispatchBean>) request.getAttribute("data");
%>
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
										<b>Final Quotation</b>
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
															<th style="width: 12%">Client Name</th>
															<th style="width: 15%">Offer Name</th>
															<th style="width: 12%">Dispatch Date</th>
															<th style="width: 12%">Vehicle Number</th>
															<th style="width: 12%">Travel Company Owner</th>
															<th style="width: 12%">Travel Company</th>
															<th style="width: 12%">Checked By</th>
															<th style="width: 10%">Address</th>
														</tr>
													</thead>
													<tbody>
														<%
														for (EMSDispatchBean db : aledb) {
														%>
														<tr>
															<td><%=++srNo%></td>
															<td><%=db.getclientId()%></td>
															<td><%=db.getOfferId()%></td>
															<td><%=db.getDate()%></td>
															<td><%=db.getVehicleNo()%></td>
															<td><%=db.getTravelComOwnr()%></td>
															<td><%=db.getTravelCom()%></td>
															<td><%=db.getCheckBy()%></td>
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
																				<h4 class="modal-title">Destination Address</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<div class="form-group">
																					<label for="exampleInputEmail1">Destination Address</label>
																					<p><%=db.getDest()%></p>
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
		function changeStatus(userId, currentStatus) {
			location.href = "ChangeStudentStatus?userId=" + userId
					+ "&currentStatus=" + currentStatus;
		}
	</script>
	<script>
		var newId;
		function dropDown(projectId) {
			console.log(typeof (projectId))
			console.log(projectId.toString());
			newId = projectId.toString();
			console.log(newId);

			let value = document.getElementById("input-form" + newId).value;
			console.log(value);
			if (value == "Select option you want to update..") {
				document.getElementById("input-form" + newId).style.borderColor = "red";
				document.getElementById("select-error" + newId).innerText = "Please, Select any one optoins.";
				document.getElementById("select-error" + newId).style.color = "red";
				document.getElementById("hide-text" + newId).style.display = "none";
				document.querySelector("#input-update" + newId).classList
						.add("disabled");
			} else {
				document.querySelector("#input-update" + newId).classList
						.remove("disabled");
				document.getElementById("hide-text" + newId).style.display = "block";
				document.getElementById("lableName" + newId).innerHTML = document
						.getElementById("input-form" + newId).value;
				document.getElementById("placeholderChange" + newId)
						.setAttribute("placeholder", "Enter " + value);
				document.getElementById("input-form" + newId).style.borderColor = "blue";
				document.getElementById("select-error" + newId).innerText = "";
				document.getElementById("select-error" + newId).style.color = "red";
			}

		}
	</script>

</body>
</html>