<%@page import="com.bean.EMSFinalQuotationBean"%>
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
ArrayList<EMSFinalQuotationBean> quotations = (ArrayList<EMSFinalQuotationBean>) request.getAttribute("quotations");
int srNo = 1;
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
					<h3 class="card-title">Quotations</h3>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Client Name</th>
								<th style="width: 17%">Quotation Date</th>
								<th style="width: 17%">Quotation Amount</th>
								<th style="width: 17%">Quantity</th>
								<th style="width: 17%">Final Delivery Date</th>
							</tr>
						</thead>
						<tbody>

							<%
							for (EMSFinalQuotationBean qb : quotations) {
							%>
							<tr>

								<td><%=srNo++%></td>
								<td><%=qb.getClientName()%></td>
								<td><%=qb.getQuotationDate()%></td>
								<td><%=qb.getQuotationAmount()%></td>
								<td><%=qb.getQuantity()%></td>
								<td><%=qb.getFinalDelivaryDate()%></td>
								<td class="project-actions text-right">
									<button type="button" class="btn btn-info btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDetails<%=qb.getQuotationId()%>">
										<i class="fas fa-folder"></i>
									</button>
									<div class="modal fade"
										id="modal-projectDetails<%=qb.getQuotationId()%>">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">Project Details</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<input type="hidden" name="view" value="view"> <input
														type="hidden" name="update" value="notupdate">
												</div>
												<%
												for (int i = 0; i < quotations.size(); i++) {
												%>
												<div class="modal-body">
													<%
													if (quotations.get(i).getQuotationId() == (qb.getQuotationId())) {
													%>
													<div class="form-group">
														<label for="exampleInputEmail1">Discount Amount</label>
														<p><%=qb.getDiscountAmount()%></p>
													</div>
													<div class="form-group">
														<div class="form-group">
															<label for="exampleInputEmail1">Discount
																Percentage</label>
															<p><%=qb.getDiscountPercentage()%></p>
														</div>
													</div>
													<%
													}
													%>

												</div>
												<%
												}
												%>
											</div>
											<!-- /.modal-project show -->
										</div>
									</div>
									<button type="button" class="btn btn-success btn-sm"
										data-toggle="modal"
										data-target="#modal-projectEdit<%=qb.getQuotationId()%>">
										<i class="fas fa-pencil-alt"></i>
									</button>
									<div class="modal fade"
										id="modal-projectEdit<%=qb.getQuotationId()%>">
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
														<p id="select-error<%=qb.getQuotationId()%>"></p>
														<form action="EMSFinalQuotationListServlet">
															<input type="hidden" name="ClientName" value="<%=qb.getClientName()%>">
															<select
																class="form-control select2 select2-hidden-accessible"
																name="changeField" style="width: 100%;"
																data-select2-id="1" tabindex="-1" aria-hidden="true"
																id="input-form<%=qb.getQuotationId()%>"
																onchange="dropDown('<%=qb.getQuotationId()%>')">
																<option selected="selected" data-select2-id="3">Select
																	option you want to update..</option>

																<option>QuotationDate</option>
																<option>FinalDelivaryDate</option>
															</select>
															<div class="form-group"
																id="hide-text<%=qb.getQuotationId()%>"
																style="display: none;">
																<label for="placeholderChange"
																	id="lableName<%=qb.getQuotationId()%>s" class="mt-2"></label>
																<input type="text" class="form-control" name="newData"
																	id="placeholderChange<%=qb.getQuotationId()%>">
															</div>
															<input type="hidden" name="quotationId"
																value="<%=qb.getQuotationId()%>"> <input
																type="hidden" name="update" value="update">
															<button type="submit"
																class="btn btn-primary mt-2 disabled"
																id="input-update<%=qb.getQuotationId()%>">Save
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
										data-target="#modal-projectDelete<%=qb.getQuotationId()%>">
										<i class="fas fa-trash"></i>
									</button>
									<div class="modal fade"
										id="modal-projectDelete<%=qb.getQuotationId()%>">
										<div class="modal-dialog">
											<div class="modal-content bg-danger">
												<div class="modal-header">
													<h4 class="modal-title">Delete Quotation</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true"></span>
													</button>
												</div>
												<div class="modal-body">
													<p>Are you sure you want to delete this Quotation?</p>
												</div>
												<div class="modal-footer justify-content-between">
													<button type="button" class="btn btn-outline-light"
														data-dismiss="modal">Close</button>
													<form action="EMSFinalQuotationListServlet" method="get">
														<input type="hidden" name="quotationId"
															value=<%=qb.getQuotationId()%>> <input
															type="hidden" name="update" value="notupdate">
														<button type="submit" class="btn btn-outline-light">Delete
															Quotation</button>
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
	
</body>

</html>