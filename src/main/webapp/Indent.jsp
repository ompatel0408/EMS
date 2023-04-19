<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.bean.CatagoryGradeSizeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%
	ArrayList<String> projectIds = (ArrayList<String>) request.getAttribute("projectIds");
	ArrayList<CatagoryGradeSizeBean> catagories = (ArrayList<CatagoryGradeSizeBean>) request.getAttribute("categoryIds");
	ArrayList<String> catName = new ArrayList<String>();
	ArrayList<Integer> catId = new ArrayList<Integer>();
	for (CatagoryGradeSizeBean i : catagories) {
		catName.add(i.getCatagoryName());
		catId.add(i.getCatagoryId());
	}
	%>
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Indent Request</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse">
							<i class="fas fa-minus"></i>
						</button>
						<button type="button" class="btn btn-tool"
							data-card-widget="remove">
							<i class="fas fa-times"></i>
						</button>
					</div>
				</div>
				<div class="card-body">
					<form onsubmit="submitForm(event)">
						<div class="row">
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Project</label> <select type="text"
										id="ProjectId1" onchange="getItemNames()" class="form-control" required>
										<option value="select" selected>Select Projects</option>
										<%
										for (int i = 0; i < projectIds.size(); i++) {
										%>
										<option value="<%=projectIds.get(i)%>"><%=projectIds.get(i)%></option>
										<%
										}
										%>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label>Category</label> <select id="category-id"
										class="form-control" onchange="getGrade()" required>
										<option value="select" selected>Select category</option>
										<%
										for (int i = 0; i < catId.size(); i++) {
										%>
										<option value="<%=catId.get(i)%>"><%=catName.get(i)%></option>
										<%
										}
										%>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Grade</label> <select id="grade-id" onchange="getSize()"
										class="form-control" required>
										<option value="Select Grade">Select Grade</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Size</label>&nbsp;&nbsp;&nbsp;&nbsp;<span id="quantity"></span><select type="text" id="size-id" onchange="getCount()" 
										class="form-control" placeholder="select size" required>
										<option value="Select Size">Select Size</option>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Item Name</label>
									<select id="itemName"
										class="form-control" required>
										<option value="Select Orders">Select Orders</option>
									</select>
								</div>
								<div class="form-group" id="quantity-id">
									<label>Quantity</label> <input type="text" class="form-control"
										id="Quantity" placeholder="Enter Quantity"
										onclick="getCount()">
								</div>
								<!-- /.form-group -->
							</div>
							<!-- /.col -->
							<div class="col-md-6">
								<div class="form-group">
									<label>Remark</label>
									<textarea maxlength="100" class="form-control" rows="3"
										placeholder="Enter..." style="height: 125px;" id="remark-id"></textarea>
								</div>
							</div>
							<div class="col">
								<div class="col-md-6 float-right">
									<div class="form-group mt-3">
										<button type="submit" id="add-indent"
											class="btn btn-primary w-25">
											<strong>+</strong> Add Indent
										</button>
									</div>
								</div>
							</div>
						</div>
					</form>
					<form onsubmit="event.preventDefault();submitForm1()">
						<button type="submit" id="ProcessId"
							class="btn float-right btn-danger w-25">
							<strong> Process to Indent </strong>
						</button>
					</form>
				</div>
			</div>
			<div class="card card-default">
				<!-- /.card-header -->
				<div class="card-header">
					<h3 class="card-title">Added Items</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool" data-card-wi
							dget="collapse">
							<i class="fas fa-minus"></i>
						</button>
						<button type="button" class="btn btn-tool"
							data-card-widget="remove">
							<i class="fas fa-times"></i>
						</button>
					</div>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects overflow-scroll">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 12%">Project</th>
								<th style="width: 9%">Category</th>
								<th style="width: 11%">Grade</th>
								<th style="width: 11%">Size</th>
								<th style="width: 11%">Item Name</th>
								<th style="width: 11%">Quantity</th>
								<th style="width: 9%">Remark</th>
								<th style="width: 9%"></th>
							</tr>
						</thead>
						<tbody id="MyTable11"></tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-projectDelete">
			<div class="modal-dialog">
				<div class="modal-content bg-danger">
					<div class="modal-header">
						<h4 class="modal-title">Delete Item</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"></span>
						</button>
					</div>
					<div class="modal-body">
						<p>Are you sure you want to delete this Item?</p>
					</div>
					<div class="modal-footer justify-content-between">
						<button type="button" class="btn btn-outline-light"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-outline-light"
							id="deleteClicked" data-dismiss="modal">Delete Item</button>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript" src="Indent.js"></script>
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
</html>