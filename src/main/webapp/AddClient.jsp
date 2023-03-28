<%@page import="java.util.ArrayList,com.bean.ClientBean"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<style>
#myModal {
	padding-top: 0px;
	margin-top: 0;
	overflow: hidden;
	height: 1100px;
}
</style>

<body class="hold-transition sidebar-mini">
	<%
	ArrayList<ClientBean> clients = (ArrayList<ClientBean>) request.getAttribute("clients");
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="wrapper">

		<div class="content-wrapper">
			<section class="content" style="margin-left: 90%;">
				<button type="button" class="btn btn-primary planButton"
					data-bs-toggle="modal" data-bs-target="#myModal">Add
					Client</button>
				<div class="modal fade" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header ">
								<h4 class="modal-title">Add Client Details</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="ClientServlet?clientId=0" method="post">
									<div class="form-group">
										<label for="exampleInputPassword1">Client Name</label> <input
											type="text" class="form-control" id="exampleInputPassword1"
											placeholder="Password" name="clientName">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">GST</label> <input
											type="text" class="form-control" id="exampleInputPassword1"
											placeholder="Password" name="gst">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Phone</label> <input
											type="number" class="form-control" id="exampleInputPassword1"
											placeholder="Password" name="phone">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Email</label> <input
											type="text" class="form-control" id="exampleInputPassword1"
											placeholder="Password" name="email">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">PAN No</label> <input
											type="text" class="form-control" id="exampleInputPassword1"
											placeholder="Password" name="pan">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Address</label> <input
											type="text" class="form-control" id="exampleInputPassword1"
											placeholder="Password" name="address">
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
			</section>
			<!-- Content Header (Page header) -->
			<div class="card m-2">
				<div class="card-header">
					<h3 class="card-title">Clients</h3>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Client Id</th>
								<th style="width: 17%">Client Name</th>
								<th style="width: 20%">Phone number</th>
								<th style="width: 20%">Email</th>
								<th style="width: 20%"></th>
							</tr>
						</thead>
						<tbody>

							<%
							int temp = 0;
							for (ClientBean c : clients) {
							%>
							<tr>
								<td><%=++temp%></td>
								<td><a><%=c.getClientId()%></a> <br></td>
								<td><a><%=c.getClientName()%> </a> <br></td>
								<td><a><%=c.getPhoneNumber()%></a> <br></td>
								<td><a><%=c.getEmail()%></a> <br></td>
								<td class="project-actions text-right">
									<button type="button" class="btn btn-info btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDetails<%=c.getClientId()%>">View
									</button>
									<button type="button" class="btn btn-success btn-sm"
										data-toggle="modal"
										data-target="#updateClientModal<%=c.getClientId()%>">
										<i class="fas fa-pencil-alt"></i> Edit
									</button> <!-- updateModalCode -->
									<div class="modal fade"
										id="updateClientModal<%=c.getClientId()%>">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Client Details</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="col-m-5">
													<div class="form-group p-3">
														<label>Select </label>
														<p id="select-error"></p>
														<form action="ClientServlet" method="GET">
															<input type="hidden" name="clientId"
																value=<%=c.getClientId()%>> <select
																name="editedColumn"
																class="form-control select2 select2-hidden-accessible"
																style="width: 100%;" data-select2-id="1" tabindex="-1"
																aria-hidden="true" id="input-form<%=c.getClientId()%>"
																onchange="dropDown(<%=c.getClientId()%>)">
																<br>
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
																id="hide-text<%=c.getClientId()%>"
																style="display: none;">
																<label for="placeholderChange"
																	id="lableName<%=c.getClientId()%>" class="mt-2"></label>
																<input type="text" class="form-control"
																	id="placeholderChange<%=c.getClientId()%>"
																	name="changes">
															</div>
															<input type="hidden" value="update" name="update">
															<button type="submit"
																class="btn btn-primary mt-2 disabled"
																id="input-update<%=c.getClientId()%>">Save
																changes</button>
														</form>
													</div>
												</div>
												<!-- /.modal-project show -->
											</div>
										</div>
									</div> <!-- Modal View Code -->
									<div class="modal fade"
										id="modal-projectDetails<%=c.getClientId()%>">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Project Details</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">×</span>
													</button>
												</div>
												<div class="modal-body">
													<%for(int j=0;j<clients.size();j++){ 
														if(clients.get(j).getClientId() == c.getClientId()){%>
													<div class="form-group">
														<label for="exampleInputEmail1">Client Id</label>
														<p><%=clients.get(j).getClientId()%></p>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail1">Client Name</label>
														<p><%=clients.get(j).getClientName()%></p>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail1">Address</label>
														<p><%=clients.get(j).getAddress()%></p>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail1">Email</label>
														<p><%=clients.get(j).getEmail()%></p>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail1">GST NUMBER</label>
														<p><%=clients.get(j).getGstNo()%></p>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail1">PAN NUMBER</label>
														<p><%=clients.get(j).getPanNo()%></p>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail1">ADDRESS</label>
														<p><%=clients.get(j).getAddress()%></p>
													</div>
													<%} } %>
													</form>
												</div>
											</div>
											<!-- /.modal-project show -->
										</div>
									</div> <!-- End View Modal -->
									<button type="button" class="btn btn-danger btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDelete<%=c.getClientId()%>">
										<i class="fas fa-trash"></i> Delete
									</button>
									<div class="modal fade"
										id="modal-projectDelete<%=c.getClientId()%>">
										<div class="modal-dialog">
											<div class="modal-content bg-danger">
												<div class="modal-header">
													<h4 class="modal-title">Delete Client</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true"></span>
													</button>
												</div>
												<div class="modal-body">
													<p>Are you sure you want to delete this project?</p>
												</div>
												<div class="modal-footer justify-content-between">
													<button type="button" class="btn btn-outline-light"
														data-dismiss="modal">Close</button>
													<form action="ClientServlet" method="get">
														<input type="hidden" name="clientId"
															value=<%=c.getClientId()%>> <input type="hidden"
															name="update" value="notupdate">
														<button type="submit" value="DELETE"
															class="btn btn-outline-light">Delete Client</button>
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
													<center>
														<h1 class="modal-title fs-5" id="staticBackdropLabel">Delete
															Client</h1>
													</center>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">

													<form>
														<center>
															<input type="button" class="btn btn-warning"
																value="Are You Sure">
														</center>
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
				</div>
				<!-- /.card-body -->
			</div>

		</div>
	</div>

	<script>
		function dropDown(clientId){
			alert(clientId)
	        document.getElementById("input-form"+clientId).addEventListener("change", () => {
	            let value = document.getElementById("input-form"+clientId).value;
	            //alert(value);
	            if (value == "Select option you want to update..") {
	                document.getElementById("input-form"+clientId).style.borderColor = "red";
	                document.getElementById("select-error"+clientId).innerText = "Please, Select any one optoins.";
	                document.getElementById("select-error"+clientId).style.color = "red";
	                document.getElementById("hide-text"+clientId).style.display = "none";
	                document.querySelector("#input-update"+clientId).classList.add("disabled");
	            }
	            else {
	                document.querySelector("#input-update"+clientId).classList.remove("disabled");
	                document.getElementById("hide-text"+clientId).style.display = "block";
	                document.getElementById("lableName"+clientId).innerHTML = document.getElementById("input-form"+clientId).value;
	                document.getElementById("placeholderChange"+clientId).setAttribute("placeholder", "Enter " + value );
	                document.getElementById("input-form"+clientId).style.borderColor = "blue";
	                document.getElementById("select-error"+clientId).innerText = "";
	                document.getElementById("select-error"+clientId).style.color = "red";
	            }
	        })
		}
    </script>

	<!-- jQuery -->
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="assets/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<!-- InputMask -->
	<script src="assets/plugins/moment/moment.min.js"></script>
	<script src="assets/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script
		src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- dropzonejs -->
	<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
	<!-- AdminLTE App -->
	<script src="assets/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="assets/dist/js/demo.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>