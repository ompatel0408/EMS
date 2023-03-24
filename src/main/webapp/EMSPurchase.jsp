<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSPurchase</title>
</head>
<body>
<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="content-wrapper p-3">
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
                    <form onsubmit="event.preventDefault();submitForm()">
                        <div class="row">
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label for="item-id">Project</label>
                                    <select id="ProjectId1" class="form-control" onload="getProjects1()" required>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Category</label>
                                    <select  id="category-id" class="form-control" onchange="getXHRRequestToPurchaseGrade()"
                                         required disabled>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <label>Grade</label>
                                    <select id="grade-id" class="form-control" onchange="getXHRRequestToPurchaseSize()"
                                        required disabled>
                                        <option value="select" selected>Select grade</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <label>Size</label>
                                    <select id="size-id" class="form-control"
                                        required disabled>
                                        <option value="">Select size</option>
                                        
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Rate</label>
                                    <div>
                                        <input class="form-control" id="rate-id" maxlength="20" name="HSNcode"
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
                                        <input class="form-control" id="orderQuan-id" maxlength="20" name="HSNcode" onchange="calculateAmount()"
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
                                    <label>Vendor Name</label>
                                    <input type="text" class="form-control" id="vendor-id" placeholder="Enter vendor name"
                                        disabled>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="float-left">Remark</label>
                                    <textarea class="form-control" rows="3" placeholder="Enter Remark..." id="remark-Id"
                                        style="height: 39px;" disabled></textarea>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Discount(%)</label>
                                    <input type="text" class="form-control" id="DiscountPercentage" onblur="calculateDiscountAmount()" placeholder="0" disabled>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label>Discount Amount</label>
                                    <input type="text" class="form-control" id="DiscountAmount" onblur="calculateDiscountPercentage()" placeholder="0" disabled>
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


                            <!-- <div class="row m-t-10"> -->
                            <div class="col-md-6">
                                <br>
                                <label><input type="radio" checked="checked" name="GSTTaxType" id="GST1"
                                        value="1" disabled>&nbsp;&nbsp;SGST/CGST</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                <label><input type="radio" name="GSTTaxType" id="GST2"
                                        value="2" disabled>&nbsp;&nbsp;IGST</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label><input type="radio" name="GSTTaxType" id="GST3"
                                        value="3" disabled>&nbsp;&nbsp;UTGST</label>
                            </div>
                            <!-- </div> -->

                            <div class="col-sm-6 d-flex justify-content-center align-items-center mt-auto ">
                                <button type="button" class="btn btn-primary disabled" id="add-store" onclick="appendChildOfPurchase()"> <strong>+
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
                        <button type="button" class="btn btn-tool" data-card-wi dget="collapse">
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
                                <th style="width: 2%">Sr.No</th>
                                <th style="width: 15%">ProjectId</th>
                                <th style="width: 10%">Category</th>
                                <th style="width: 10%">Grade</th>
                                <th style="width: 12%">Size</th>
                                <th style="width: 10%">TotalAmount</th>
                                <th style="width: 10%">VendorName</th>
                                <th style="width: 9%"></th>
                            </tr>
                        </thead>
                        <tbody id="MyTable"></tbody>
                    </table>
                </div>
                <div class="modal fade" id="modal-editItem">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Project Details</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"
                                    id="enableItemName">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="col-m-5">
                                <div class="form-group p-3">
                                    <label>Select </label>
                                    <p id="select-error"></p>
                                    <form onsubmit="event.preventDefault();updateField()">
                                        <select class="form-control select2 select2-hidden-accessible my-3"
                                            style="width: 100%;" data-select2-id="1" tabindex="-1" aria-hidden="true"
                                            id="input-form">
                                            <br>
                                            <option selected="selected" data-select2-id="3">Select
                                                option you want to update..</option>
                                            <option value="category">Category</option>
                                            <option value="grade">Grade</option>
                                            <option value="size">Size</option>
                                            <option value="quantity">Quantaty</option>
                                        </select>

                                        <div class="form-group" id="hide-text" style="display: none;">
                                            <label for="placeholderChange" id="lableName" class="mt-2"></label>
                                            <input type="text" class="form-control" id="placeholderChange"
                                                placeholder="Enter">
                                        </div>
                                        <div class="form-group" id="hide-date" style="display: none;">
                                            <label for="" id="lableName1">Proje</label> <input type="date"
                                                class="form-control" id="placeholderChange1"
                                                placeholder="Enter Delivery Date">
                                        </div>
                                        <!-- text input -->
                                        <div class="form-group" id="category-id-model" style="display: none;">
                                            <label>Category</label>
                                            <select type="text" id="category-id" class="form-control"
                                                placeholder="select category" required>
                                                <option value="select" selected>Select category</option>
                                                <option value="ms">MS</option>
                                                <option value="ss">SS</option>
                                            </select>
                                        </div>
                                        <div class="form-group" id="grade-id-model" style="display: none;">
                                            <label>Grade</label>
                                            <select type="text" id="grade-id" class="form-control"
                                                placeholder="select Grade" required>
                                                <option value="">Select Grade</option>
                                                <option value="">aaa</option>
                                                <option value="">bbb</option>
                                                <option value="">ccc</option>
                                            </select>
                                        </div>
                                        <div class="form-group" id="size-id-model" style="display: none;">
                                            <label>Size</label>
                                            <select type="text" id="size-id" class="form-control"
                                                placeholder="select size" required>
                                                <option value="">Select size</option>
                                                <option value="">AAA</option>
                                                <option value="">BBB</option>
                                                <option value="">CCC</option>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-primary mt-2 disabled"
                                            id="input-update">Save
                                            changes</button>
                                    </form>
                                </div>
                            </div>
                            <!-- /.modal-project show -->
                        </div>
                    </div>
                    <!-- /.modal-edit items -->
                </div>
                <div class="modal fade" id="modal-projectDelete">
                    <div class="modal-dialog">
                        <div class="modal-content bg-danger">
                            <div class="modal-header">
                                <h4 class="modal-title" onclick=" deleteItem()">Delete Project</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true"></span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure you want to delete this project?</p>
                            </div>
                            <div class="modal-footer justify-content-between">
                                <button type="button" class="btn btn-outline-light" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-outline-light">Delete Project</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card card-default">
                <div class="card-header">
                    <h3 class="card-title"> Progress of Purchase and Quotation </h3>
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
                    <div class="tab-content">
                        <div class="tab-pane active" id="timeline">
                            <!-- The timeline -->
                            <div class="timeline timeline-inverse">
                                <!-- timeline time label -->
                                <div class="time-label">
                                    <span class="bg-gradient-info">
                                        10 Feb. 2014
                                    </span>
                                </div>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <div>
                                    <i class="fas fa-user bg-info"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                                        <h3 class="timeline-header border-0">
                                       	 Updated by
                                       <b>
                                       	 	<i> 
                                       			 Paresh patel 
                                       		</i>
                                       	</b>
                                             
                                        </h3>
                                    </div>
                                </div>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <div>
                                    <i class="fas fa-comments bg-gradient-success"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                        <h3 class="timeline-header">
                                        <!--<a href="#">Jay White</a> -->
                                         <font color="lightgreen">
                                         <b>No worries!</b>
                                         </font>
                                         </h3>
											
                                        <div class="timeline-body">
                                            No worries! Your purchase amount is currently below the quotation amount. You have room to make additional purchases without incurring any additional fees or charges. Please let us know if you have any questions or concerns.
                                        </div>    
                                    </div>
                                </div>
                                <div>
                                    <i class="fas fa-user bg-info"></i>

                                    <div class="timeline-item">
                                        <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>
                                        
                                        <h3 class="timeline-header border-0">
                                       	 Updated by
                                       	<b>
                                       	 	<i> 
                                       			 Paresh patel 
                                       		</i>
                                       	</b>
                                           
                                        </h3>
                                    </div>
                                </div>
                                <div>
									<i class="fas fa-comments bg-danger"></i>
                                    <div class="timeline-item">
                                        <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                        <h3 class="timeline-header">
                                        <!--<a href="#">Jay White</a> -->
                                        <font color="red">
                                        	<b> Warning:</b>
                                        </font> 
                                        
                                            </h3>
                                        <div class="timeline-body">
                                            Your purchase amount has exceeded the quotation amount. Please be aware that any additional purchases will be applied towards the quotation amount and may result in additional fees or charges. We strongly advise that you review your purchase history and adjust your spending accordingly to avoid any further overage charges.
                                        </div>
                                        
                                    </div>
                                </div>
                                <!-- END timeline item -->
                                <!-- timeline time label -->
                                <div class="time-label">
                                    <span class="bg-gradient-info">
                                        3 Jan. 2014
                                    </span>
                                </div>
                                <div>
                                    <i class="far fa-clock bg-gray"></i>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.tab-content -->
                </div><!-- /.card-body -->
            </div>
        </div>
        </div>
</body>
<script type="text/javascript" src="EMSPurchase.js"></script>
</html>