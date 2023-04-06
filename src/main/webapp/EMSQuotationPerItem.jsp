<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSQuotationPerItem</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Quotation per offer</h3>

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
					<form onsubmit="event.preventDefault();submitForm()">
						<div class="row">
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Offers</label> <select id="itemId"
										class="form-control"
										onchange="disableLogic();getXHRRequestToQuotationPerItemCatagory()"
										onload="getOffers()" required>
										<option value="select" selected>Select Offers</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label>Category</label> <select id="categoryId"
										class="form-control" placeholder="select category"
										onchange="getXHRRequestToQuotationPerItemGrade()" required
										disabled>
										<option value="select" selected>Select category</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Grade</label> <select id="gradeId" class="form-control"
										placeholder="select Grade"
										onchange="getXHRRequestToQuotationPerItemSize()" required
										disabled>
										<option value="">Select Grade</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Size</label> <select id="sizeId" class="form-control"
										placeholder="select size" required disabled>
										<option value="">Select size</option>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Quantity</label> <input type="text" class="form-control"
										id="Quantity" placeholder="Enter Quantity" disabled>
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label>Weight</label> <input type="text" class="form-control"
										id="waight-id" placeholder="Enter Waight" disabled>
								</div>
								<div class="form-group">
									<label>Profit Percentage</label> <input type="text"
										class="form-control" id="profit-id"
										placeholder="Enter Profit Percentage"
										onblur="DisablePercentage()" disabled>
								</div>
							</div>
							<!-- /.col -->
							<div class="col-md-6">
								<div class="form-group">
									<label>Units</label> <input type="text" class="form-control"
										id="unit-id" placeholder="Enter Units" disabled>
								</div>
								<div class="form-group">
									<label>Price</label> <input type="text" class="form-control"
										id="price-id" placeholder="Enter Price" disabled>
								</div>
							</div>
							<div class="col">
								<div class="col-md-6 float-right">
									<div class="form-group mt-3 d-flex justify-content-around">
										<button type="submit" id="add_Quatation"
											class="btn btn-primary w-25 disabled">
											<strong>+</strong> Add Quatation per offer
										</button>
									</div>
								</div>
							</div>
						</div>
					</form>
					<form
						onsubmit="event.preventDefault();XHRRequestForQuotationPerItem()">
						<div class="form-group mt-3 d-flex justify-content-around">
							<button type="submit" id="processTo"
								class="btn btn-danger w-25 disabled">
								<strong> Process to quotation per offer </strong>
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
								<th style="width: 15%">Offers</th>
								<th style="width: 10%">Category</th>
								<th style="width: 10%">Grade</th>
								<th style="width: 12%">Size</th>
								<th style="width: 7%">Quantity</th>
								<th style="width: 15%">Weight(kg)</th>
								<th style="width: 9%">Units</th>
								<th style="width: 10%">Price</th>
								<th style="width: 9%"></th>
							</tr>
						</thead>
						<tbody id="MyTable"></tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<div class="modal fade" id="modal-editItem">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Project Details</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close" id="enableItemName">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="col-m-5">
							<div class="form-group p-3">
								<label>Select </label>
								<p id="select-error"></p>
								<form onsubmit="event.preventDefault();updateField()">
									<select
										class="form-control select2 select2-hidden-accessible my-3"
										style="width: 100%;" data-select2-id="1" tabindex="-1"
										aria-hidden="true" id="input-form">
										<option selected="selected" data-select2-id="3">Select
											option you want to update..</option>
										<option value="quantity">Quantity</option>
										<option value="unit">Units</option>
										<option value="price">price</option>
										<option value="waight">Weight</option>
									</select>

									<div class="form-group" id="hide-text" style="display: none;">
										<label for="placeholderChange" id="lableName" class="mt-2"></label>
										<input type="text" class="form-control" id="placeholderChange"
											placeholder="Enter">
									</div>
									<div class="form-group" id="hide-num" style="display: none;">
										<label for="" id="lableName1">Value</label> <input
											type="number" class="form-control" id="placeholderChange1"
											placeholder="Enter value">
									</div>
									<button type="submit" class="btn btn-primary mt-2 disabled"
										id="input-update">Save changes</button>
								</form>
							</div> 
						</div>
						<!-- /.modal-project show -->
					</div>
				</div>
				<!-- /.modal-edit items -->
			</div>
			<div class="modal fade" id="modal-itemDelete">
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
								id="deleteClicked">Delete Item</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="EMSQuotationPerItem.js"></script>
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
