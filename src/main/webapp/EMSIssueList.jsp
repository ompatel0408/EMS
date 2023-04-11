<%@page import="com.bean.EMSIssueNoteBean"%>
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
	ArrayList<EMSIssueNoteBean> issue = (ArrayList<EMSIssueNoteBean>) request.getAttribute("issue");
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
									<h3 class="card-title mt-2">
										<b>Issue Notes </b>
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
															<th style="width: 10%">ProjectId</th>
															<th style="width: 13%">Catagory</th>
															<th style="width: 13%">Grade</th>
															<th style="width: 13%">size</th>
															<th style="width: 10%">Quantity</th>
															<th style="width: 10%">UOM</th>
															<th style="width: 13%">Person Name</th>
															<th style="width: 13%">Contractor Name</th>
															<th style="width: 20%"></th>
														</tr>
													</thead>
													<tbody>

														<%
														int temp = 0;
														for (EMSIssueNoteBean c : issue) {
														%>
														<tr>
															<td><%=++temp%></td>
															<td><a><%=c.getPid()%></a> <br></td>
															<td><a><%=c.getCatagory()%> </a> <br></td>
															<td><a><%=c.getGrade()%></a> <br></td>
															<td><a><%=c.getSize()%></a> <br></td>
															<td><a><%=c.getQuantity()%></a> <br></td>
															<td><a><%=c.getUom()%></a> <br></td>
															<td><a><%=c.getIssuePerson()%></a> <br></td>
															<td><a><%=c.getContractor()%></a> <br></td>
															<td class="project-actions text-right">
																<button type="button" class="btn btn-info btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectDetails<%=c.getIssueId()%>">View
																</button>
																<button type="button" class="btn btn-success btn-sm"
																	data-toggle="modal"
																	onclick="dropDown(<%=c.getIssueId()%>)"
																	data-target="#updateClientModal<%=c.getIssueId()%>">
																	<i class="fas fa-pencil-alt"></i> 
																</button> <!-- updateModalCode -->
																<div class="modal fade"
																	id="updateClientModal<%=c.getIssueId()%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Client Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="col-m-5">
																				<div class="form-group p-3">
																					<label>Select </label>
																					<p id="select-error"></p>
																					<form action="ClientServlet" method="GET">
																						<input type="hidden" name="clientId"
																							value=<%=c.getIssueId()%>> <select
																							name="editedColumn"
																							class="form-control select2 select2-hidden-accessible"
																							style="width: 100%;"
																							id="input-form<%=c.getIssueId()%>">
																							<option selected="selected" data-select2-id="3">Please,
																								Select any one optoins.</option>
																							<option value="CLIENTNAME">CLIENTNAME</option>
																							<option value="GSTNO">GSTNO</option>
																							<option value="PHONENUMBER">PHONENUMBER</option>
																							<option value="EMAIL">EMAIL</option>
																							<option value="PANNO">PANNO</option>
																							<option value="ADDRESS">ADDRESS</option>
																						</select>
																						<div class="form-group"
																							id="hide-text<%=c.getIssueId()%>"
																							style="display: none;">
																							<label for="placeholderChange"
																								id="lableName<%=c.getIssueId()%>" class="mt-2"></label>
																							<input type="text" class="form-control"
																								id="placeholderChange<%=c.getIssueId()%>"
																								name="changes">
																						</div>
																						<input type="hidden" value="update" name="update">
																						<button type="submit"
																							class="btn btn-primary mt-2 disabled"
																							id="input-update<%=c.getIssueId()%>">Save
																							changes</button>
																					</form>
																				</div>
																			</div>
																			<!-- /.modal-project show -->
																		</div>
																	</div>
																</div> <!-- Modal View Code -->
																<div class="modal fade" id="myModal">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header ">
																				<h4 class="modal-title">Add Client Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true">&times;</span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<form action=""
																					method="post">
																					<div class="form-group">
																						<label for="exampleInputPassword1">Client
																							Name</label> <input type="text" class="form-control"
																							id="exampleInputPassword1"
																							placeholder="Enter Client Name" name="clientName">
																					</div>
																					<div class="form-group">
																						<label for="exampleInputPassword1">GST</label> <input
																							type="text" class="form-control"
																							id="exampleInputPassword1"
																							placeholder="Enter GST" name="gst">
																					</div>
																					<div class="form-group">
																						<label for="exampleInputPassword1">Phone</label> <input
																							type="number" class="form-control"
																							id="exampleInputPassword1"
																							placeholder="Enter Phone Number" name="phone">
																					</div>
																					<div class="form-group">
																						<label for="exampleInputPassword1">Email</label> <input
																							type="text" class="form-control"
																							id="exampleInputPassword1" placeholder="Password"
																							name="email">
																					</div>
																					<div class="form-group">
																						<label for="exampleInputPassword1">PAN No</label>
																						<input type="text" class="form-control"
																							id="exampleInputPassword1"
																							placeholder="Enter PAN No" name="pan">
																					</div>
																					<div class="form-group">
																						<label for="exampleInputPassword1">Address</label>
																						<input type="text" class="form-control"
																							id="exampleInputPassword1"
																							placeholder="Enter Address" name="address">
																					</div>
																					<div class="modal-footer justify-content-between">
																						<button type="button" class="btn btn-default"
																							data-dismiss="modal">Close</button>
																						<button type="submit" class="btn btn-primary">Save
																							changes</button>
																					</div>
																				</form>

																			</div>

																		</div>
																	</div>
																</div>
																<div class="modal fade"
																	id="modal-projectDetails<%=c.getIssueId()%>">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header">
																				<h4 class="modal-title">Project Details</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true"> </span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<%
																				for (int j = 0; j < issue.size(); j++) {
																					if (issue.get(j).getIssueId() == c.getIssueId()) {
																				%>
																				<div class="form-group">
																					<label
																						for="exampleInputEmail1 d-flex flex-direction-column align-items-flex-start">Client
																						Id</label>
																					<p class=""><%=issue.get(j).getIssueId()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">Client Name</label>
																					<p><%=issue.get(j).getContractor()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">Address</label>
																					<p><%=issue.get(j).getIssuePerson()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">Email</label>
																					<p><%=issue.get(j).getCatagory()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">GST NUMBER</label>
																					<p><%=issue.get(j).getGrade()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">PAN NUMBER</label>
																					<p><%=issue.get(j).getSize()%></p>
																				</div>
																				<div class="form-group">
																					<label for="exampleInputEmail1">ADDRESS</label>
																					<p><%=issue.get(j).getQuantity()%></p>
																				</div>
																				<%
																				}
																				}
																				%>
																				</form>
																			</div>
																		</div>
																		<!-- /.modal-project show -->
																	</div>
																</div> <!-- End View Modal -->
																<button type="button" class="btn btn-danger btn-sm"
																	data-toggle="modal"
																	data-target="#modal-projectDelete<%=c.getIssueId()%>">
																	<i class="fas fa-trash"></i> Delete
																</button>
																<div class="modal fade"
																	id="modal-projectDelete<%=c.getIssueId()%>">
																	<div class="modal-dialog">
																		<div class="modal-content bg-danger">
																			<div class="modal-header">
																				<h4 class="modal-title">Delete Client</h4>
																				<button type="button" class="close"
																					data-dismiss="modal" aria-label="Close">
																					<span aria-hidden="true"></span>
																				</button>
																			</div>
																			<div class="modal-body">
																				<p>Are you sure you want to delete this Client?</p>
																			</div>
																			<div class="modal-footer justify-content-between">
																				<button type="button" class="btn btn-outline-light"
																					data-dismiss="modal">Close</button>
																				<form action="ClientServlet" method="get">
																					<input type="hidden" name="clientId"
																						value=<%=c.getIssueId()%>> <input
																						type="hidden" name="update" value="notupdate">
																					<button type="submit" value="DELETE"
																						class="btn btn-outline-light">Delete
																						Client</button>
																				</form>
																			</div>

																		</div>
																	</div>
																</div>
																<div class="modal fade modal-xl" id="updateModal"
																	data-bs-backdrop="static" data-bs-keyboard="false"
																	tabindex="-1" aria-labelledby="staticBackdropLabel"
																	aria-hidden="true">
																	<div class="modal-dialog">
																		<div class="modal-content">
																			<div class="modal-header ">
																				<h1 class="modal-title fs-5"
																					id="staticBackdropLabel">Delete Client</h1>
																				<button type="button" class="btn-close"
																					data-bs-dismiss="modal" aria-label="Close"></button>
																			</div>
																			<div class="modal-body">

																				<form>
																					<input type="button" class="btn btn-warning"
																						value="Are You Sure">
																				</form>
																			</div>

																		</div>
																	</div>
																</div>
															</td>
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

	<script>
	
		var newId;
		function dropDown(clientId){
			console.log(clientId.toString());
			newId = clientId.toString();
			console.log(newId);
		
		document.getElementById("input-form"+newId).addEventListener("change", () => {
            let value = document.getElementById("input-form"+newId).value;
            if (value == "Select option you want to update..") {
                document.getElementById("input-form"+newId).style.borderColor = "red";
                document.getElementById("select-error"+newId).innerText = "Please, Select any one optoins.";
                document.getElementById("select-error"+newId).style.color = "red";
                document.getElementById("hide-text"+newId).style.display = "none";
                document.querySelector("#input-update"+newId).classList.add("disabled");
            }
            else {
                document.querySelector("#input-update"+newId).classList.remove("disabled");
                document.getElementById("hide-text"+newId).style.display = "block";
                document.getElementById("lableName"+newId).innerHTML = document.getElementById("input-form"+newId).value;
                document.getElementById("placeholderChange"+newId).setAttribute("placeholder", "Enter " + value );
                document.getElementById("input-form"+newId).style.borderColor = "blue";
                document.getElementById("select-error"+newId).innerText = "";
                document.getElementById("select-error"+newId).style.color = "red";
            }
        })
		}
    </script>

	<!-- jQuery -->
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