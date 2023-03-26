<%@page import="java.util.ArrayList,com.bean.ClientBean"%>
<!DOCTYPE html>
<html>

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#myModal {
	padding-top: 0px;
	margin-top: 0;
	overflow: hidden;
	height: 1100px;
}
</style>
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">
	<%
	ArrayList<ClientBean> clients = (ArrayList<ClientBean>) request.getAttribute("clients");
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<section class="content-wrapper">
		<!-- Default box -->
		<div class="card m-2">
			<div class="card-header">
				<h3 class="card-title">Client</h3>
				<div class="card-tools">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#modal-addProject">Add Client</button>
					<div class="modal fade" id="modal-addProject"
						data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">Add Client Details</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<form action="ClientServlet?clientId=0&update=ems"
										method="post">
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
												type="number" class="form-control"
												id="exampleInputPassword1" placeholder="Password"
												name="phone">
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
							<!-- /.modal-add project -->
						</div>
					</div>
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
								<td><a> <%=c.getClientId()%>
								</a> <br></td>
								<td><a> <%=c.getClientName()%>
								</a> <br></td>
								<td><a> <%=c.getPhoneNumber()%>
								</a> <br></td>
								<td><a> <%=c.getEmail()%>
								</a> <br></td>
								<td class="project-actions text-right">
									<button type="button" class="btn btn-info btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDetails<%=c.getClientId()%>">
										View</button>
									<div class="modal fade"
										id="modal-projectDetails<%=c.getClientId()%>">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Project Details</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">X</span>
													</button>
												</div>
												<div class="modal-body float-left">
													<%
													for (int j = 0; j < clients.size(); j++) {
														if (clients.get(j).getClientId() == c.getClientId()) {
													%>
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
													<%
													}
													}
													%>
												</div>
											</div>
										</div>
										<!-- /.modal-project show -->
									</div>
									<button type="button" class="btn btn-success btn-sm"
										data-toggle="modal"
										data-target="#updateClientModal<%=c.getClientId()%>">
										<i class="fas fa-pencil-alt"></i> Edit
									</button>
									<div class="modal fade"
										id="updateClientModal<%=c.getClientId()%>">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Project Details</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="col-m-5">
													<div class="form-group p-3">
														<label class="float-left">Select </label>
														<p id="select-error"></p>
														<form action="ClientServlet" method="GET">
															<input type="hidden" name="clientId"
																value=<%=c.getClientId()%>> <input type="hidden"
																name="update" value="update"> <select
																class="form-control select2 select2-hidden-accessible"
																style="width: 100%;" data-select2-id="1" tabindex="-1"
																aria-hidden="true" id="input-form" name="editedColumn" onClick="dropDown('<%=c.getClientId()%>')">
																<br>
																<option selected="selected" data-select2-id="3">Select
																	option you want to update..</option>
																<option value="CLIENT_NAME">CLIENT_NAME</option>
																<option value="GST_NO">GST_NO</option>
																<option value="PHONE_NUMBER">PHONE_NUMBER</option>
																<option value="EMAIL">EMAIL</option>
																<option value="PAN_NO">PAN_NO</option>
																<option value="ADDRESS">ADDRESS</option>
															</select>
															<div class="form-group" id="hide-text<%=c.getClientId() %>"
																style="display: none;">
																<label for="placeholderChange" id="lableName<%=c.getClientId() %>"
																	class="mt-2 float-left"></label> <input type="text"
																	class="form-control" id="placeholderChange"
																	placeholder="Enter email" name="changes">
															</div>
															<button type="submit"
																class="btn btn-primary mt-2 disabled" id="input-update<%=c.getClientId()%>">Save
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
													<p>Are you sure you want to delete this Client?</p>
												</div>
												<div class="modal-footer justify-content-between">
													<button type="button" class="btn btn-outline-light"
														data-dismiss="modal">Close</button>
													<form action="ClientServlet" method="get">
														<input type="hidden" name="clientId"
															value=<%=c.getClientId()%>> <input type="hidden"
															name="update" value="not">
														<button type="submit" class="btn btn-outline-light">Delete
															Client</button>
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
			<!-- /.card -->
			<!-- Add Projext model -->



			<!-- Project Edit model -->
	</section>
	</div>
</body>
<script>
	
function dropDown (projectId){
	console.log(typeof(projectId))

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
<!--<script src="./custom/custom.js"></script>  -->

</html>