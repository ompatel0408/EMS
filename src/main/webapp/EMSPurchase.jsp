<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSPurchase</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="content-wrapper p-3">
	<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">
						<b>Purchase and Quotation Calculation Graph</b>
					</h3>
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
					<div class="row">
						<div class=" col-12">
							<div class="info-box bg-gradient-success" id="success">
								<span class="info-box-icon"><i class=" ion-stats-bars"></i></span>

								<div class="info-box-content">
									<span class="info-box-text py-3">Percentage of profit</span> <span class="info-box-number" id="totalNumber">0%</span>

									<div class="progress h-25">
										<div class="progress-bar" id="progressBarId" style="width: 0%;"></div>
									</div>
									<span class="progress-description m-2 h-75" id="progressDesc"></span>
									<span class="progress-description m-2 h-75" id="progressDescOfAvailable"></span>
									<span id="profitAlert"></span>
									<!-- <span class="progress-description m-2 h-75"> Your total quotation without profit is <b> 1000000</b>, With profit <b>1500000</b>.
									<br> You are purchased items total amount's of <span id="totalPurchase">600000</span>, and your profit is 400000.
									 </span> -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
            <div class="card card-default">
                <div class="card-header">
                    <h3 class="card-title"><b>Purchase Order</b></h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>

                <div class="card-body">
                    <form onsubmit="event.preventDefault();submitFormToServlet()">
                        <div class="row">
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label for="item-id">Project</label>
                                    <select id="ProjectId1" class="form-control" onload="getProjects1()" onblur="ProjectChange()" onchange="getIndentList();" required>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Category</label>
                                    <input type="text" class="form-control" id="category-id" placeholder="Category" disabled>
                                </div>
                            </div>
                            <div class="col-sm-3">
                            
                            <div class="form-group">
                                    <label>Grade</label>
                                    <input type="text" class="form-control" id="grade-id" placeholder="Grade" disabled>
                            </div>
                            </div>
                            <div class="col-sm-3">
                            <div class="form-group">
                                    <label>Size</label>
                                    <input type="text" class="form-control" id="size-id" placeholder="Size" disabled>
                            </div>
                            </div>

                            <div class="col-sm-6">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Rate</label>
                                    <div>
                                        <input class="form-control" id="rate-id" maxlength="20" name="HSNcode" onkeyup="calculateAmount()"
                                            placeholder="Enter Rate" type="text" value="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>Amount</label>
                                    <div>
                                        <input class="form-control" id="amount-id" maxlength="20" name="HSNcode"
                                            type="text" value="0" disabled>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                            <div class="row">
                           <div class="col-md-6">
                                <div class="form-group">
                                    <label>Order Quantity</label>
                                    <div>
                                        <input class="form-control" id="orderQuan-id" maxlength="20" name="HSNcode"
                                            placeholder="Enter Order quantity" type="text" value="" disabled>
                                    </div>
                                </div>
                                </div>
                               
                               <div class="col-md-6">
                                <div class="form-group">
                                    <label>Unit</label>
                                    <div>
                                        <input class="form-control" id="unit-id" maxlength="20" name="unit" 
                                            placeholder="Enter Unit" type="text" value="" disabled>
                                    </div>
                                </div>
                                </div>
                                 </div>
                                 <div class="form-group">
                                    <label for="vendor-id">Vendor Name</label>
                                    <select id="vendor-id" class="form-control" required>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="float-left">Remark</label>
                                    <textarea class="form-control" rows="3" placeholder="Enter Remark..." id="remark-Id"
                                        style="height: 39px;" disabled></textarea>
                                </div>
                                <div class="form-group">
                                    <label for="PaymentTermsId">Payment Terms</label>
                                    <select id="PaymentTermsId" class="form-control" required>
                                    	<option value="">Select Payment terms</option>
                                    	<option value="1">Immidiete</option>
                                    	<option value="7">within one week</option>
                                    	<option value="15">within one half month</option>
                                    	<option value="30">within one month</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Discount(%)</label>
                                    <input type="text" class="form-control" id="DiscountPercentage" onkeyup="calculateDiscountAmount()" placeholder="0" disabled>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Discount Amount</label>
                                    <input type="text" class="form-control" id="DiscountAmount" onkeyup="calculateDiscountPercentage()" placeholder="0" disabled>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Total Amount</label>
                                    <input type="text" value="0" class="form-control" id="Totalamount-id"
                                        placeholder="0.0" disabled>
                                </div>
                                <!-- </div> -->
                            </div>
                            

                            <div class="col-md-2">
                                <label><input type="radio" class="chk" name="chks" value="0.00" id="Nil" onclick="clickOfRadioButton()"  disabled>&nbsp;&nbsp;Nil
                                    (0.00 %)</label>
                            </div>
                            <div class="col-md-2">
                                <label><input type="radio" class="chk" name="chks" value="5.00" id="FivePer" onclick="clickOfRadioButton()" disabled>&nbsp;&nbsp;GST
                                    (5.00 %)</label>
                            </div>
                            <div class="col-md-2">
                                <label><input type="radio" class="chk" name="chks" value="12.00" id="TwelvePer" onclick="clickOfRadioButton()" disabled>&nbsp;&nbsp;GST
                                    (12.00 %)</label>
                            </div>
                            <div class="col-md-2">
                                <label><input type="radio" class="chk" name="chks" value="18.00" id="EighteenPer" onclick="clickOfRadioButton()" disabled>&nbsp;&nbsp;GST
                                    (18.00 %)</label>
                            </div>
                            <div class="col-md-2">
                                <label><input type="radio" class="chk" name="chks" value="28.00" id="TwentyEightPer" onclick="clickOfRadioButton()" disabled>&nbsp;&nbsp;GST
                                    (28.00 %)</label>
                            </div>
                            <div class="col-md-2">
                                <label><input type="radio" class="chk" name="chks" value="3.00" id="ThreePer" onclick="clickOfRadioButton()" disabled>&nbsp;&nbsp;GST
                                    (3.00
                                    %)</label>
                            </div>



                            <div class="col-sm-6 d-flex justify-content-center align-items-center mt-auto ">
                                <button type="button" class="btn btn-primary disabled" id="add-store" onclick="submitForm()"> <strong>+
                                        Add to Purchase </strong></button>
                            </div>
                            <div class="col-md-12 d-flex justify-content-center align-items-center mt-2">
                                <button type="submit" class="btn btn-danger disabled" id="processTo"> <strong>+
                                        Process to Purchase </strong></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Content Header (Page header) -->


            <div class="card card-default">
                <!-- /.card-header -->
                <div class="card-header">
                    <h3 class="card-title">Added Items</h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped projects overflow-scroll">
                        <thead>
                            <tr>
                                <th style="width: 10%">Sr.No</th>
                                <th style="width: 10%">Category</th>
                                <th style="width: 10%">Grade</th>
                                <th style="width: 12%">Size</th>
                                <th style="width: 10%">TotalAmount</th>
                                <th style="width: 10%">VendorName</th>
                                <th style="width: 9%"></th>
                            </tr>
                        </thead>
                        <tbody id="MyTable16"></tbody>
                    </table>
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
        </div>
        </div>
</body>
<script type="text/javascript" src="EMSPurchase.js"></script>
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