<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSQuotationPerItem</title>
</head>
<body>
<div class="wrapper">
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="content-wrapper p-3">
            <div class="card card-default">
                <div class="card-header">
                    <h3 class="card-title">Add Quotation per item </h3>

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
                                    <label for="item-id">Offers</label>
                                    <select id="itemId" class="form-control" onchange="disableLogic();getXHRRequestToQuotationPerItemCatagory()" onload="getOffers()" required>
                                        <option value="select" selected>Select Offers</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Category</label>
                                    <select id="categoryId" class="form-control"
                                        placeholder="select category" onchange="getXHRRequestToQuotationPerItemGrade()" required disabled>
                                        <option value="select" selected>Select category</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <label>Grade</label>
                                    <select id="gradeId" class="form-control" placeholder="select Grade" onchange="getXHRRequestToQuotationPerItemSize()" required disabled>
                                        <option value="">Select Grade</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <label>Size</label>
                                    <select id="sizeId" class="form-control" placeholder="select size"
                                        required disabled>
                                        <option value="">Select size</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input type="text" class="form-control" id="Quantity"
                                        placeholder="Enter Quantaty" disabled>
                                </div>
                                <!-- /.form-group -->
                                <div class="form-group">
                                    <label>Weight</label>
                                    <input type="text" class="form-control" id="waight-id" placeholder="Enter Waight" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Profit Percentage</label>
                                    <input type="text" class="form-control" id="profit-id" placeholder="Enter Profit Percentage" onblur="DisablePercentage()" disabled>
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Units</label>
                                    <input type="text" class="form-control" id="unit-id" placeholder="Enter Units" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input type="text" class="form-control" id="price-id" placeholder="Enter Price" disabled>
                                </div>
                            </div>
                            <div class="col">
                                <div class="col-md-6 float-right">
                                    <div class="form-group mt-3 d-flex justify-content-around">
                                        <button type="submit" id="add_Quatation" class="btn btn-primary w-25 disabled">
                                            <strong>+</strong> Add
                                            Quatation</button>
                                        
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </form>
                    <form onsubmit="event.preventDefault();XHRRequestForQuotationPerItem()">
                    <div class="form-group mt-3 d-flex justify-content-around">
                    	<button type="submit" id="processTo" class="btn btn-danger w-25 disabled">
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
                                <th style="width: 15%">Offers</th>
                                <th style="width: 10%">Category</th>
                                <th style="width: 10%">Grade</th>
                                <th style="width: 12%">Size</th>
                                <th style="width: 7%">Quantaty</th>
                                <th style="width: 9%">Units</th>
                                <th style="width: 15%">Waight(kg)</th>
                                <th style="width: 10%">Price</th>
                                <th style="width: 9%"></th>
                            </tr>
                        </thead>
                        <tbody id="MyTable"></tbody>
                    </table>
                </div>
                <!-- /.card-body -->
            </div>
        </div>
        </div>
</body>
<script type="text/javascript" src="EMSQuotationPerItem.js"></script>
</html>