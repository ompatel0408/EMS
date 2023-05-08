<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendor</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add Vendor</h3>

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
									<label>Vendor Name</label> <input type="text" id="vendorName"
										class="form-control" placeholder="Enter VendorName" pattern="[A-Za-z ]{1,50}" required="required">
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Email</label> <input type="email" class="form-control"
										id="email" placeholder="Enter Email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,4}$" required="required">
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Email(optional)</label> <input type="text" class="form-control"
										id="email1" placeholder="Enter Email" value="none">
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Phone Number</label> <input type="number" id="mobile"
										class="form-control" placeholder="Enter Mob-Number" pattern="[0-9]{10}" required="required">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Phone Number(optional)</label> <input type="number" id="mobile1"
										class="form-control" value="0" placeholder="Enter Mob-Number" pattern="[0-9]{10}">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Address</label> <input type="text" class="form-control"
										id="address" placeholder="Enter Address">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>GST</label> <input type="text" class="form-control" 
										id="gst" placeholder="Enter GST">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>PAN Number</label> <input type="text" class="form-control" 
										id="pannumber" placeholder="Enter Pan Number">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Bank Name</label> <input type="text" class="form-control" 
										id="bankName" placeholder="Enter Bank Name">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Account Number</label> <input type="text" class="form-control" 
										id="acNumber" placeholder="Enter Account Number">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>IFSC Code</label> <input type="text" class="form-control" 
										id="ifsc" placeholder="Enter IFSC Code">
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="float-left">Remark</label>
									<textarea class="form-control" rows="3"
										placeholder="Enter Remark..." name="Remarks"
										id="Remarks" style="height: 40px;" maxlength="5000"></textarea>
								</div>
							</div>
						</div>
						<div class="col">
                                <div class="col-md-6 float-right">
                                    <div class="form-group mt-3 d-flex justify-content-around">
                                        <button type="submit" id="add_vendor" class="btn btn-primary w-25">
                                            <strong>+</strong> Add Vendor
                                            </button>
                                        
                                    </div>
                                </div>
                            </div>
					</form>
					<form
						onsubmit="event.preventDefault();XHRRequestForVendor()">
						<div class="form-group mt-3 d-flex justify-content-around">
							<button type="submit" id="processTo"
								class="btn btn-danger w-25 disabled">
								<strong> Process to Vendor </strong>
							</button>
						</div>
					</form>
				</div>
			</div>
			<div class="card card-default">
				<!-- /.card-header -->
				<div class="card-header">
					<h3 class="card-title">Vendors</h3>
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
								<th style="width: 15%">Vendor Name</th>
								<th style="width: 10%">Email</th>
								<th style="width: 10%">Address</th>
								<th style="width: 10%">Mobile</th>
								<th style="width: 10%">GST</th>
								<th style="width: 10%">PAN</th>
								<th style="width: 10%">Ac.No</th>
								<th style="width: 10%">IFSC</th>
							</tr>
						</thead>
						<tbody id="MyTable10"></tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<div class="modal fade" id="modal-projectDelete">
				<div class="modal-dialog">
					<div class="modal-content bg-danger">
						<div class="modal-header">
							<h4 class="modal-title">Delete Vendor</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true"></span>
							</button>
						</div>
						<div class="modal-body">
							<p>Are you sure you want to delete this Vendor?</p>
						</div>
						<div class="modal-footer justify-content-between">
							<button type="button" class="btn btn-outline-light"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-outline-light"
								id="deleteClicked">Delete Vendor</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="EMSVendors.js"></script>
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