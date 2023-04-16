<%@page import="com.bean.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EMS</title>
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
</head>
<%
ArrayList<ProjectBean> projects = (ArrayList<ProjectBean>) request.getAttribute("projects");
Date d = new Date();
DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
String today = df.format(d);
int srNo = 1;
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<!-- Preloader -->
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">

								<div class="card-header">
									<h3 class="card-title mt-2">
										<b>Project List For Indent</b>
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
															<th style="width: 17%">Client Name</th>
															<th style="width: 17%">Project Id</th>
															<th>Project Progress</th>
															<th style="width: 20%"></th>
														</tr>
													</thead>
													<tbody>
														<%
														for (ProjectBean p : projects) {
														%>
														<tr>
															<td><%=srNo++%></td>
															<td><%=p.getClientName()%></td>
															<td><%=p.getProjectId()%></td>
															<td class="project_progress">
																<div class="progress progress-sm">
																	<div class="progress-bar bg-green" role="progressbar"
																		aria-valuenow="77" aria-valuemin="0"
																		aria-valuemax="100" style="width: 77%"></div>
																</div> <small> 77% Complete </small>
															</td>
															<td><a
																href="ListIndentServlet?token=2&projectid=<%=p.getProjectId()%>&itemcode=0"><button
																		class="btn btn-primary">Show All Items</button></a></td>
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
	<script>
		function dropDown(projectId) {
			console.log(typeof (projectId))

			let value = document.getElementById("input-form" + projectId).value;
			console.log(value);
			if (value == "Select option you want to update..") {
				document.getElementById("input-form" + projectId).style.borderColor = "red";
				document.getElementById("select-error" + projectId).innerText = "Please, Select any one optoins.";
				document.getElementById("select-error" + projectId).style.color = "red";
				document.getElementById("hide-text" + projectId).style.display = "none";
				document.querySelector("#input-update" + projectId).classList
						.add("disabled");
			} else {
				document.querySelector("#input-update" + projectId).classList
						.remove("disabled");
				document.getElementById("hide-text" + projectId).style.display = "block";
				document.getElementById("lableName" + projectId).innerHTML = document
						.getElementById("input-form" + projectId).value;
				document.getElementById("placeholderChange" + projectId)
						.setAttribute("placeholder", "Enter " + value);
				document.getElementById("input-form" + projectId).style.borderColor = "blue";
				document.getElementById("select-error" + projectId).innerText = "";
				document.getElementById("select-error" + projectId).style.color = "red";
			}
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