<%@page import="com.bean.ItemBean"%>
<%@page import="jakarta.mail.FetchProfile.Item"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EMS</title>
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">

</head>
<%
ArrayList<ItemBean> items= (ArrayList<ItemBean>) request.getAttribute("items");

int srNo=1;
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->

		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>


		<!-- Content Wrapper. Contains page content -->
		<section class="content-wrapper">
			<!-- Default box -->
			<div class="card m-2">
				<div class="card-header">
					<h3 class="card-title">Final Quotation</h3>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Client Name</th>
								<th style="width: 17%">Item Name</th>
								<th style="width: 17%">Tag No</th>
								<th style="width: 17%">Quantity</th>
								<th style="width: 17%">Delivery Date</th>
								
							</tr>
						</thead>
						<tbody>

							<%
							for (ItemBean ib : items) {
							%>
							<tr>

								<td><%=srNo++%></td>
								<td><%=ib.getClientName()%></td>
								<td><%=ib.getItemName()%></td>
								<td><%=ib.getTagNo()%></td>
								<td><%=ib.getQuantity()%></td>
								<td><%=ib.getDeliveryDate()%></td>
								<td class="project-actions text-right">
									<button type="button" class="btn btn-success btn-sm"
										data-toggle="modal"
										data-target="#modal-projectEdit<%=ib.getItemCode()%>">
										<i class="fas fa-pencil-alt"></i> Edit
									</button>
									<div class="modal fade"
										id="modal-projectEdit<%=ib.getItemCode()%>">
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
													
														<label>Select </label>
														<p id="select-error<%=ib.getItemCode()%>"></p>
														<form
															action="EMSItemListServlet">
															
															<select
																class="form-control select2 select2-hidden-accessible"
																name="changeField" style="width: 100%;"
																data-select2-id="1" tabindex="-1" aria-hidden="true"
																id="input-form<%=ib.getItemCode()%>" onchange="dropDown('<%=ib.getItemCode()%>')">
																<option selected="selected" data-select2-id="3">Select
																	option you want to update..</option>
																	
																<option>ItemName</option>
																 <option>Quantity</option> 
																 <option>TagNo</option> 
																 <option>DeliveryDate</option> 
															</select>
															<div class="form-group" id="hide-text<%=ib.getItemCode()%>"
																style="display: none;">
																<label for="placeholderChange" id="lableName<%=ib.getItemCode()%>"
																	class="mt-2"></label> <input type="text"
																	class="form-control" name="newData"
																	id="placeholderChange<%=ib.getItemCode()%>">
															</div>
															<input type="hidden" name="itemCode" value="<%=ib.getItemCode()%>">
															<input type="hidden" name="update" value="update">
															<button type="submit"
																class="btn btn-primary mt-2 disabled" id="input-update<%=ib.getItemCode()%>">Save
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
										data-target="#modal-projectDelete<%=ib.getItemCode()%>">
										<i class="fas fa-trash"></i> Delete
									</button>
									<div class="modal fade"
										id="modal-projectDelete<%=ib.getItemCode()%>">
										<div class="modal-dialog">
											<div class="modal-content bg-danger">
												<div class="modal-header">
													<h4 class="modal-title">Delete Order</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true"></span>
													</button>
												</div>
												<div class="modal-body">
													<p>Are you sure you want to delete this Order?</p>
												</div>
												<div class="modal-footer justify-content-between">
													<button type="button" class="btn btn-outline-light"
														data-dismiss="modal">Close</button>
													<form action="EMSItemListServlet" method="get">
                                                                        <input type="hidden" name="itemCode"
                                                                            value=<%=ib.getItemCode()%>>
                                                                        <input type="hidden" name="update"
                                                                            value="notupdate">
                                                                        <button type="submit"
                                                                            class="btn btn-outline-light">Delete
                                                                            Order</button>
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
				</div>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->
			<!-- Add Projext model -->
			

		</section>
		<footer class="main-footer">
			<strong>Copyright &copy; 2023 <a href="#">EMS Project
					Private Limited</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
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
	<script src="./custom/custom.js"></script>

</body>

</html>