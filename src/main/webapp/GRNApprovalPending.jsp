<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GRNApprovalPending</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">GRN approval Pending</h3>

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
					<form>
						<div class="row">
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label for="MaterialCat">Material Category</label> <select
										id="MaterialCat" class="form-control" onchange="getProjects()" required>
										<option value="select" selected>Select Material Category</option>
										<option value="Consumables">Consumables</option>
										<option value="projectDependent">Project Dependent</option>
									</select>
								</div>
							</div>

							<div class="col-sm-3">
								<div class="form-group">
									<label for="project-id">ProjectID</label> <select
										id="projectId" class="form-control" onchange="getAllPurchaseDetails()" disabled required>
										<option value="select" selected>Select ProjectID</option>
									</select>
								</div>


							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Category</label> <input type="text" class="form-control"
										id="category-id" placeholder="Enter Category" disabled>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Grade</label> <input type="text" class="form-control"
										id="grade-id" placeholder="Enter Grade" disabled>
								</div>
							</div>


							<div class="col-md-6">

								<div class="form-group">
									<label>Size</label> <input type="text" class="form-control"
										id="size-id" placeholder="Enter Size" disabled>
								</div>
								<div class="form-group">
									<label>Quantity</label> <input type="text" class="form-control"
										id="Quantity" placeholder="Enter Quantaty" disabled>
								</div>
							</div>
							<!-- /.col -->
							<div class="col-md-6">
								<div class="form-group">
									<label>Units</label> <input type="text" class="form-control"
										id="unit-id" placeholder="Enter Units" disabled>
								</div>
							</div>
							<div class="col">
								<div class="col-md-6 float-right">
									<div class="form-group mt-3 d-flex justify-content-around">
										<button type="button" id="add_Quatation"
											class="btn btn-primary w-25 disabled" onclick="submitForm()">
											<strong>+</strong> Add Quotation
										</button>

									</div>
								</div>
							</div>
						</div>
					</form>
					<form onsubmit="event.preventDefault();submitFormToServlet()">
						<div class="form-group mt-3 d-flex justify-content-around">
							<button type="submit" id="processTo"
								class="btn btn-danger w-25 disabled">
								<strong> Process to items </strong>
							</button>
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
								<th style="width: 2%">Sr.No</th>
								<th style="width: 15%">ProjectID</th>
								<th style="width: 15%">ItemName</th>
								<th style="width: 15%">Category</th>
								<th style="width: 15%">Grade</th>
								<th style="width: 12%">Size</th>
								<th style="width: 10%">Quantity</th>
								<th style="width: 10%">Units</th>
								<th style="width: 10%"></th>
							</tr>
						</thead>
						<tbody id="MyTable18"></tbody>
					</table>
				</div>
			</div>
			<div class="modal fade" id="modal-itemDelete">
				<div class="modal-dialog">
					<div class="modal-content bg-danger">
						<div class="modal-header">
							<h4 class="modal-title">Delete Stock</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true"></span>
							</button>
						</div>
						<div class="modal-body">
							<p>Are you sure you want to delete this Stock?</p>
						</div>
						<div class="modal-footer justify-content-between">
							<button type="button" class="btn btn-outline-light"  id="closeMdl"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-outline-light"
								id="deleteClicked">All Received</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="GRNApprovalPending.js"></script>
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
<script src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="assets/dist/js/adminlte.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>