<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSQuotation</title>
<style type="text/css">
#snackbar {
  visibility: hidden; /* Hidden by default. Visible on click */
  min-width: 350px; /* Set a default minimum width */
  margin-left: -125px; /* Divide value of min-width by 2 */
  background-color: red; /* Black background color */
  color: #fff; /* White text color */
  text-align: center; /* Centered text */
  border-radius: 2px; /* Rounded borders */
  padding: 16px; /* Padding */
  position: fixed; /* Sit on top of the screen */
  z-index: 1; /* Add a z-index if needed */
  left: 50%; /* Center the snackbar */
  bottom: 30px; /* 30px from the bottom */
}

/* Show the snackbar when clicking on a button (class added with JavaScript) */
#snackbar.show {
  visibility: visible; /* Show the snackbar */
  /* Add animation: Take 0.5 seconds to fade in and out the snackbar.
  However, delay the fade out process for 2.5 seconds */
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

/* Animations to fade the snackbar in and out */
@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div id="snackbar">Some text some message..</div>
<div class="wrapper">
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="content-wrapper p-3">
		<div class="card card-default">
			<div class="card-header">
				<h3 class="card-title">Add Orders</h3>

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
			<!-- /.card-header -->
			<div class="card-body">
				<form onsubmit="event.preventDefault();submitForm()">
					<div class="row">

						<div class="col-md-6">
							<div class="form-group">
								<label>Clients</label> <select id="ClientId1" onload="getClients1()" onchange="GetOfferData()"  class="form-control" required>
									
								</select>
							</div>
							<div class="form-group">
								<label>Tag No</label> <input type="text" class="form-control"
									id="TagNo" placeholder="Enter Tag No" required disabled>
							</div>
							 <div class="form-group">
								<label>Quantity</label> <input type="number"
									class="form-control" id="Quantity" placeholder="Enter Quantity"
									pattern="[0-9]"  required="required" disabled>
							</div> 
							<div class="form-group">
								<label for="">Delivery Date</label> <input type="date"
									class="form-control" id="DelivaryDate"
									placeholder="Enter Delivery Date" required disabled>
							</div>

							<!-- /.form-group -->
						</div>
						<!-- /.col -->
						<div class="col-md-6">
							<div class="form-group">
								<label>Order name</label> <input type="text"
									class="form-control" id="ItemName"
									placeholder="Enter Order name" pattern="[A-Za-z0-9_\- ]{1,100}"  required="required" disabled>
							</div> 
							<!-- /.form-group -->
							<div class="form-group">
								<label>Remark</label>
								<textarea class="form-control" rows="3" placeholder="Enter..."
									id="Remarks" style="height: 125px;" required disabled></textarea>
							</div>
							<input type="hidden" id="offerCode">
							<div class="form-group mt-5 d-flex justify-content-around">
								<button type="submit" id="AddItemBtn"
									class="btn btn-primary w-25 disabled">
									<strong>+</strong> Add Item
								</button>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-md-6"></div>
					</div>
				</form>
				<!-- Main proceed Button -->
				<form onsubmit="event.preventDefault();XHRRequestForItem()">
					<div class="form-group mt-5 d-flex justify-content-around">
						<button type="submit" id="ProcessId"
							class="btn btn-danger w-25 disabled">
							<strong> Process to items </strong>
						</button>
					</div>
				</form>
				<!-- /.row -->
			</div>
			<!-- /.card-body -->
		</div>
		<div class="card card-default">
			<!-- /.card-header -->
			<div class="card-header">
				<h3 class="card-title">Added Items</h3>
				<div class="card-tools">
					<button type="button" class="btn btn-tool"
						data-card-widget="collapse" title="Collapse">
						<i class="fas fa-minus"></i>
					</button>
					<button type="button" class="btn btn-tool"
						data-card-widget="remove" title="Remove">
						<i class="fas fa-times"></i>
					</button>
				</div>
			</div>
			<div class="card-body p-0">
				<table class="table table-striped projects">
					<thead>
						<tr>
							<th style="width: 2%">Sr.No</th>
							<th style="width: 20%">Item Name</th>
							<th style="width: 20%">Quantity</th>
							<th style="width: 20%">Tag No</th>
							<th style="width: 15%">Delevery Date</th>
							<th style="width: 20%"></th>
						</tr>
					</thead>
					<tbody id="MyTable3"></tbody>
				</table>
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.row -->
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
						 data-dismiss="modal" id="deleteClicked">Delete Item</button>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript" src="EMSItem.js"></script>
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