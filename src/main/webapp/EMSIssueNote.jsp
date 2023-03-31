<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
         <jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
<div class="content-wrapper p-3">
            <div class="card card-default">
                <div class="card-header">
                    <h2 class="card-title">Material Issue Card</h2>

                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <!-- /.card-header -->
                <div class="card-body">
                    <form onsubmit="submitForm(event)">
                        <div class="row">
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>ProjectID</label>
                                      <select type="text" id="ProjectId1" 	class="form-control" ></select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Category</label>
                                    <select id="categoryId" class="form-control"
                                         placeholder="select category" required disabled>
                                        <!-- <option value="select" selected>Select category</option> -->
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <label>Grade</label>
                                    <select id="gradeId" class="form-control" required disabled>
                                        <option value="">Select Grade</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="form-group">
                                    <label>Size</label>
                                    <select id="sizeId" class="form-control"
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
                                
                                <div class="form-group">
                                    <label>Remark </label>
                                    <textarea class="form-control" rows="3" placeholder="Enter..." id="remarkId" disabled
                                        style="height: 125px;"></textarea>
                                </div>
                            </div>
                            <!-- /.col -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Units</label>
                                    <input type="text" class="form-control"  id="unit-id" placeholder="Enter Units" disabled>
                                </div>
                                	<div class="form-group">
                                    <label>Issue Person</label>
                                    <input type="text" class="form-control" id="issue-per-id"
                                        placeholder="Enter Issue Person" disabled="">
                                </div>
                                <div class="form-group">
                                    <label>Contractor </label>
                                    <input type="text" class="form-control" id="contracter-id"
                                        placeholder="Enter Contracter" disabled="">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group mt-2 d-flex justify-content-around">
                                    <button type="submit" id="submit-item-btn" class="btn btn-primary w-25" disabled=""> <strong>+</strong> Add
                                        Item</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form onsubmit="XhrRequestForSendData(event)">
                        <div class="col-md-12">
                            <div class="form-group d-flex justify-content-around">
                                <button type="submit" id="processTo" class="btn btn-danger w-25 disabled">
                                    <strong> Process to items </strong> </button>
                            </div>
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
                        <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped projects">
                        <thead>
                            <tr>
                                <th style="width: 2%">Sr.No</th>
                                <th style="width: 13%">Category</th>
                                <th style="width: 13%">grade</th>
                                <th style="width: 13%">Size</th>
                                <th style="width: 13%">unit</th>
                                <th style="width: 13%">quantity</th>
                                <th style="width: 13%">Issue Person</th>
                                <th style="width: 13%">Contractor</th>
                                <th style="width: 10%"></th>
                            </tr>
                        </thead>
                        <tbody id="MyTable"></tbody>
                    </table>
                </div>
                <!-- /.card-body�-->
            </div>
        </div>
        <div class="modal fade" id="modal-editItem">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Project Details</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="enableItemName">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="col-m-5">
                        <div class="form-group p-3">
                            <label>Select </label>
                            <form onsubmit="updateField(event)">
                                <p id="select-error"></p>
                                <select class="form-control select2 select2-hidden-accessible my-3" style="width: 100%;"
                                    data-select2-id="1" id="input-form" name="project">
                                    <option selected="selected" data-select2-id="3" value="select">Select
                                        option you want to update..</option>
                                    <option value="mtd">Material Description</option>
                                    <option value="remark">Remark</option>
                                    <option value="contracter">Contracter</option>
                                    <option value="issueper">Issue Person</option>
                                    <option value="quantity">Quantaty</option>
                                </select>
                                <div class="form-group" id="mtd" style="display: none;">
                                    <label>Material Description </label>
                                    <textarea class="form-control" rows="3" placeholder="Enter..." id="materialId-mdl"
                                        style="height: 125px;"></textarea>
                                </div>
                                <div class="form-group" id="hide-text" style="display: none;">
                                    <label for="model-text" id="lableName" class="mt-2"></label>
                                    <input type="text" class="form-control" id="model-text" placeholder="Enter">
                                </div>
                                <div class="form-group" id="num-text" style="display: none;">
                                    <label for="model-num" id="lableName" class="mt-2"></label>
                                    <input type="number" class="form-control" id="model-num" placeholder="Enter Quantity">
                                </div>
                                <button type="submit" class="btn btn-primary mt-2 disabled" id="input-update">Save
                                    changes
                                </button>
                            </form>
                        </div>
                    </div>
                    <!-- /.modal-project show -->
                </div>
            </div>
            <!-- /.modal-edit items�-->
        </div>
        <div class="modal fade" id="modal-projectDelete">
            <div class="modal-dialog">
                <div class="modal-content bg-danger">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete IssueNote</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true"></span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to delete this IssueNote?</p>
                    </div>
                    <div class="modal-footer justify-content-between">
                        <button type="button" class="btn btn-outline-light" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-outline-light" id="deleteClicked">Delete IssueNote</button>
                    </div>
                </div>
            </div>
        </div>
	<script type="text/javascript" src="EMSIssueNote.js"></script>
</body>
</html>