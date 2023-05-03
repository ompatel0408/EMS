<%@page import="com.bean.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPEhtml>
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
										<b>Projects</b>
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
																		aria-valuemax="100"
																		style="width: <%=p.getProgress()%>%"></div>
																</div>
																<small><%=p.getProgress()%>% Complete </small>
															</td>
															<td class="project-actions text-right">
																<button type="button" class="btn btn-info btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectDetails<%=p.getProjectId()%>">
																	View</button>
																<div class="modal fade"
																	id="modal-projectDetails<%=p.getProjectId()%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Project Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																				<input type="hidden" name="update" value="notupdate">
																			</div>
																			<%
																			for (int i = 0; i < projects.size(); i++) {
																			%>
																			<div class="modal-body">
																				<%
																				if (projects.get(i).getProjectId().equals(p.getProjectId())) {
																				%>
																				<div class="form-group">
																					<label for="exampleInputEmail1">PO Date</label>
																					<p><%=p.getPoDate()%></p>
																				</div>
																				<div class="form-group">
																					<div class="form-group">
																						<label for="exampleInputEmail1">Advance
																							Pay Percent</label>
																						<p><%=p.getAdvancePayPercent()%></p>
																					</div>
																				</div>
																				<div class="form-group">
																					<div class="form-group">
																						<label for="exampleInputEmail1">After Pay
																							Percent</label>
																						<p><%=p.getAfterPayPercent()%></p>
																					</div>
																				</div>
																				<%
																				}
																				%>
																			</div>
																			<%}%>
																		</div>
																		<!-- /.modal-project show -->
																	</div>
																</div>
																<button type="button" class="btn btn-success btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectEdit<%=p.getProjectId()%>">
																	<i class="fas fa-pencil-alt"></i>Edit
																</button>
																<div class="modal fade"
																	id="modal-projectEdit<%=p.getProjectId()%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Project Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="col-m-5">
																				<div class="form-group p-3">
																					<label>Select </label>
																					<p id="select-error<%=p.getProjectId()%>"></p>
																					<form action="ProjectServlet">
																						<select
																							class="form-control select2 select2-hidden-accessible"
																							name="changeField" style="width: 100%;"
																							data-select2-id="1" tabindex="-1"
																							aria-hidden="true"
																							id="input-form<%=p.getProjectId()%>"
																							onChange="dropDown('<%=p.getProjectId()%>')">
																							<option selected="selected" data-select2-id="3">Select
																								option you want to update..</option>
																							<option value="clientpoid">Client_PO_ID</option>
																							<option value="advancepaypercent">Advance_Pay_Percent</option>
																							<option value="afterpaypercent">After_Pay_Percent</option>
																							<option value="clientid">Client_Id</option>
																						</select>
																						<div class="form-group"
																							id="hide-text<%=p.getProjectId()%>"
																							style="display: none;">
																							<label for="placeholderChange"
																								id="lableName<%=p.getProjectId()%>" class="mt-2"></label>
																							<input type="text" class="form-control"
																								name="newData"
																								id="placeholderChange<%=p.getProjectId()%>">
																						</div>
																						<input type="hidden" name="projectId"
																							value="<%=p.getProjectId()%>"><input
																							type="hidden" name="update" value="update">
																						<button type="submit"
																							class="btn btn-primary mt-2 disabled"
																							id="input-update<%=p.getProjectId()%>">Save
																							changes</button>
																					</form>
																				</div>
																			</div>
																			<!-- /.modal-project show -->
																		</div>
																	</div>
																</div>
																<button type="button" class="btn btn-danger btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectDelete<%=p.getProjectId()%>">
																	<i class="fas fa-trash"></i>Delete
																</button>
																<div class="modal fade"
																	id="modal-projectDelete<%=p.getProjectId()%>">
																	<div class="modal-dialog">
																		<div class="modal-content bg-danger">
																			<div class="modal-header">
																				<h4 class="modal-title">Delete Project</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true"></span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<p>Are you sure you want to delete this project?</p>
																			</div>
																			<div class="modal-footer justify-content-between">
																				<button type="button" class="btn btn-outline-light"
																					data-dismiss="modal">Close</button>
																				<form action="ProjectServlet" method="get">
																					<input type="hidden" name="projectId"
																						value=<%=p.getProjectId()%>><input
																						type="hidden" name="update" value="notupdate">
																					<input type="hidden" name="view" value="notview">
																					<button type="submit" class="btn btn-outline-light">Delete
																						Project</button>
																				</form>
																			</div>
																		</div>
																	</div>
																</div>
															</td>
															<!-- Project Edit model -->
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
	<div class="modal fade" id="modal-addProject">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Add Project Details</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="ProjectServlet" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputPassword1">Quotation Id</label><input
								type="text" class="form-control" name="clientPoId"
								id="exampleInputPassword" required
								placeholder="Enter Quotation Id" value="" readonly />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Advance Pay Percent</label><input
								type="number" class="form-control" name="advancePayPercent"
								onkeyup="Calculate()" required id="exampleInputPassword1"
								placeholder="Advance Pay Percent" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">After Pay Percent</label><input
								type="number" class="form-control" required readonly
								name="afterPayPercent" id="exampleInputPassword2"
								placeholder="After Pay Percent" />
						</div>
						<div class="form-group">
							<label for="exampleInputPassword3">Client Id</label><input
								type="number" class="form-control" required name="clientId"
								id="exampleInputPassword3" placeholder="Enter Client Id"
								value="" readonly />
						</div>
					</div>
					<div class="modal-footer justify-content-between">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-add project -->
	</div>
	<footer class="main-footer">
		<strong>Copyright &copy;2023 <a href="#">EMS Project
				Private Limited</a>.
		</strong>All rights reserved.
	</footer>
	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->
	<script>
 const dropDown=(projectId)=>{
 console.log(projectId)
 let value = document.getElementById("input-form"+projectId).value;
 console.log(value);
 
if (value == "Select option you want to update..") {
 document.getElementById("input-form"+projectId).style.borderColor = "red";
 document.getElementById("select-error"+projectId).innerText = "Please, Select any one optoins.";
 document.getElementById("select-error"+projectId).style.color = "red";
 document.getElementById("hide-text"+projectId).style.display = "none";
 document.querySelector("#input-update"+projectId).classList.add("disabled");
 }
 else {
 document.querySelector("#input-update"+projectId).classList.remove("disabled");
 document.getElementById("hide-text"+projectId).style.display = "block";
 document.getElementById("lableName"+projectId).innerHTML = document.getElementById("input-form"+projectId).value;
 document.getElementById("placeholderChange"+projectId).setAttribute("placeholder", "Enter " + value);
 document.getElementById("input-form"+projectId).style.borderColor = "blue";
 document.getElementById("select-error"+projectId).innerText = "";
 document.getElementById("select-error"+projectId).style.color = "red";
 }
 
}
 
function Calculate(){
 document.getElementById('exampleInputPassword2').value = (100 - (parseInt(document.getElementById('exampleInputPassword1').value)));
 }
 

</script>
	<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="assets/dist/js/adminlte.js"></script>
	<script src="assets/plugins/moment/moment.min.js"></script>
	<script src="./custom/custom.js"></script>
	<script type="text/javascript">
 window.onload = function(){
 if(localStorage.getItem("Token") == "true"){
 $('#modal-addProject').modal('show');
 localStorage.setItem("Token",'false')
 
document.getElementsByName('clientPoId')[0].value = localStorage.getItem("QuotationId")
 document.getElementsByName('clientId')[0].value = localStorage.getItem("clientId");
 }else{
 document.getElementById('AddProjectBTN').disabled = true;
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
	<!-- DataTables & Plugins -->
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
 $(document).ready(function () {
 $("#example1").DataTable({
 "responsive": true, "lengthChange": false, "autoWidth": false,
 "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
 }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
 })
 </script>
	<script>
 function changeStatus(userId, currentStatus) {
 location.href = "ChangeStudentStatus?userId=" + userId + "&currentStatus=" + currentStatus;
 }
 </script>
</body>
</html>