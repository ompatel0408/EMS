<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMS Store</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Store Item Add</h3>
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
							
							<div class="col-sm-4">
								<!-- text input -->
								<div class="form-group">
									<label>Category</label> <select id="category-id"
										class="form-control" required>
										<option value="select" selected>Select category</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Grade</label> <select id="grade-id" class="form-control" required>
										<option value="select grade">Select Grade</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label>Size</label> <select type="text" id="size-id"
										class="form-control" required>
										<option >Select size</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Quantity</label> <input type="text" class="form-control"
										id="quantaty-id" placeholder="Quantity" required>
								</div>
							</div>
							<div
								class="col-sm-6 d-flex justify-content-center align-items-center mt-auto">
								<div class="form-group">
									<button type="submit" class="btn btn-primary"
										id="add-store">
										<strong>+</strong> add Item
									</button>
								</div>
							</div>
						</div>
					</form>
					<form onsubmit="event.preventDefault();XHRRequestForStore()">
						<div
							class="col-sm-12 d-flex justify-content-center align-items-center mt-auto">
							<div class="form-group">
								<button type="submit" class="btn btn-danger disabled"
									id="processTo">
									<strong>+ Process to Store </strong>
								</button>
							</div>
						</div>
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
								<th style="width: 5%">Sr.No</th>
								<th style="width: 15%">Category</th>
								<th style="width: 15%">Grade</th>
								<th style="width: 15%">Size</th>
								<th style="width: 20%">Quantity</th>
								<th style="width: 20%"></th>
							</tr>
						</thead>
						<tbody id="MyTable"></tbody>
					</table>
				</div>
				<!-- /.card-body -->
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
	</div>
	</body>
	<script type="text/javascript" src="EMSStore.js"></script>
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