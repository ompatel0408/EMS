<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSFinalQuotation</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Total Quotation</h3>
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
							<div class="col-sm-6">
								<!-- text input -->
								<div class="form-group">
									<label>Clients</label> <select class="form-control"
										onload="getClients1()" id="ClientId1"
										onchange="getQuotation()">
									</select>
								</div>
								<div class="form-group">
									<label>Final Delivery Date</label>
									<div class="input-group date" id="reservationdate"
										data-target-input="nearest">
										<input type="date" class="form-control"
											required name="finalDeliveryDate" id="finalDeliveryDate"
											data-target="#reservationdate" />
										<div class="input-group-append" data-target="#reservationdate"
											data-toggle="datetimepicker"></div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Quotation Amount</label> <input type="text"
										class="form-control" id="QuotationAmount"
										placeholder="Quotation amount" name="QuotationAmount" disabled>
								</div>
								<div class="form-group">
									<label>Total Offers</label> <input type="text" class="form-control"
										id="QuotationQuantity" name="QuotationQuantity"
										placeholder="Quantity" disabled>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="float-left">Remark</label>
									<textarea class="form-control" rows="3"
										placeholder="Enter Remark..." name="FinalQuotationRemarks"
										id="FinalQuotationRemarks" style="height: 40px;" maxlength="150"></textarea>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Discount(%)</label> <input type="text" required="required"
										class="form-control" id="discountPercentage"
										name="discountPercentage" placeholder="0" 
										onkeyup="calculateDiscountAmount()" pattern="^\d+(\.\d+)?$">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Discount Amount</label> <input type="text"
										class="form-control" id="discountAmount" name="discountAmount" 
										onkeyup="calculateDiscountPercentage()" placeholder="0"
										pattern="^\d+(\.\d+)?$">
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label>Total Amount</label> <input type="text"
										class="form-control" id="TotalAmount" name="TotalAmount"
										placeholder="0.0" disabled>
								</div>
								<!-- </div> -->
							</div>
							<div class="col-md-8 mt-2">
								<div class="form-group d-flex justify-content-between">
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio"
											id="customRadio1" onclick="clickOfRadioButton()" value="0.00"
											name="customRadio" checked> <label for="customRadio1"
											class="custom-control-label">Nill(0.00%)</label>
									</div>
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio" onclick="clickOfRadioButton()"
											id="customRadio2" value="5.00" name="customRadio"> <label
											for="customRadio2" class="custom-control-label">GST(5.00%)</label>
									</div>
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio" onclick="clickOfRadioButton()"
											id="customRadio3" value="12.00" name="customRadio"> <label
											for="customRadio3" class="custom-control-label">GST(12.00%)</label>
									</div>
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio" onclick="clickOfRadioButton()"
											id="customRadio4" value="18.00" name="customRadio"> <label
											for="customRadio4" class="custom-control-label">GST(18.00%)</label>
									</div>
									<div class="custom-control custom-radio">
										<input class="custom-control-input" type="radio" onclick="clickOfRadioButton()"
											id="customRadio5" value="28.00" name="customRadio"> <label
											for="customRadio5" class="custom-control-label">GST(28.00%)</label>
									</div>
								</div>
							</div>
							<div
								class="col-md-12 d-flex justify-content-center align-items-center mt-2">
								<button type="submit" class="btn btn-danger">
									<strong>+ Process to Quatation </strong>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="EMSFinalQuotation.js"></script>
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