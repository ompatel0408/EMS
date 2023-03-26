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
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label for="item-id">Project</label> <select name="projectId"
										id="ProjectId1" class="form-control" required>
										<option value="select" selected>Select Item</option>
									</select>
								</div>
							</div>  
							<div class="col-sm-3">
								<!-- text input -->
								<div class="form-group">
									<label>Category</label> <select id="category-id"
										class="form-control" required disabled="">
										<option value="select" selected>Select category</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Grade</label> <select id="grade-id" class="form-control"
										required disabled="">
										<option value="select grade">Select Grade</option>
									</select>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label>Size</label> <select type="text" id="size-id"
										class="form-control" required disabled="">
										<option >Select size</option>
									</select>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Quantity</label> <input type="text" class="form-control"
										id="quantaty-id" placeholder="Quantity" disabled="">
								</div>
							</div>
							<div
								class="col-sm-6 d-flex justify-content-center align-items-center mt-auto">
								<div class="form-group">
									<button type="submit" class="btn btn-primary disabled"
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
								<th style="width: 15%">Project</th>
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
							<form onsubmit="updateField(event)">
								<p id="select-error"></p>
								<select
									class="form-control select2 select2-hidden-accessible my-3"
									style="width: 100%;" data-select2-id="1" id="input-form"
									name="project">
									<br>
									<option selected="selected" data-select2-id="3">Select
										option you want to update..</option>
									<option value="category">Category</option>
									<option value="grade">Grade</option>
									<option value="size">Size</option>
									<option value="quantity">Quantity</option>
								</select>

								<div class="form-group" id="hide-text" style="display: none;">
									<label for="placeholderChange" id="lableName" class="mt-2"></label>
									<input type="text" class="form-control" id="placeholderChange"
										placeholder="Enter">
								</div>
								<!-- text input -->
								<div class="form-group" id="category-id-model"
									style="display: none;">
									<label>Category</label> <select id="category-id-select"
										class="form-control" name="project" required>
										<option value="select" selected>Select category</option>
										<option value="ms">MS</option>
										<option value="ss">SS</option>
									</select>
								</div>
								<div class="form-group" id="grade-id-model"
									style="display: none;">
									<label>Grade</label> <select id="grade-id-select"
										class="form-control" required>
										<option value="select">Select Grade</option>
										<option value="aaa">aaa</option>
										<option value="bb">bbb</option>
										<option value="cc">ccc</option>
									</select>
								</div>
								<div class="form-group" id="size-id-model"
									style="display: none;">
									<label>Size</label> <select type="text" id="size-id-select"
										class="form-control" required>
										<option value="select">Select size</option>
										<option value="aa">AAA</option>
										<option value="bb">BBB</option>
										<option value="cc">CCC</option>
									</select>
								</div>
								<button type="submit" class="btn btn-primary mt-2 disabled"
									id="input-update" data-dismiss="modal">Save changes</button>
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
</html>