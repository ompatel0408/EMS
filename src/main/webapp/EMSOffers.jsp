<%@page import="com.bean.ClientBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSQuotation</title>
</head>
<body>
<%
	ArrayList<ClientBean> clients = (ArrayList<ClientBean>) request.getAttribute("clients");
	%>
<div class="wrapper">
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="content-wrapper p-3">
		<div class="card card-default">
			<div class="card-header">
				<h3 class="card-title">Add offers</h3>

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
								<label>Client Name</label>
								<select id="ProjectId1" onload="getProjects()"  class="form-control" required>
								<option value="client">Select Client Name</option>
								<%for(ClientBean c : clients) {%>
									<option value=<%=c.getClientId() %>><%=c.getClientName()%></option>
								<%} %>
								</select>
							</div>
							<div class="form-group">
								<label>Quantity</label> <input type="number"
									class="form-control" id="Quantity" placeholder="Enter Quantity"
									>
							</div>
							<!-- <div class="form-group">
								<label for="">Delivery Date</label> <input type="date"
									class="form-control" id="DelivaryDate"
									placeholder="Enter Delivery Date">
							</div> -->

							<!-- /.form-group -->
						</div>
						<!-- /.col -->
						<div class="col-md-6">
							<div class="form-group">
								<label>Offer name</label> <input type="text"
									class="form-control" id="ItemName"
									placeholder="Enter Offer name">
							</div>
							<!-- /.form-group -->
							<div class="form-group">
								<label>Remark</label>
								<textarea class="form-control" rows="3" placeholder="Enter..."
									id="Remarks" style="height: 125px;"></textarea>
							</div>
							<div class="form-group mt-5 d-flex justify-content-around">
								<button type="submit" id="AddItemBtn"
									class="btn btn-primary w-25">
									<strong>+</strong> Add offers
								</button>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-md-6"></div>
					</div>
				</form>
				<!-- Main proceed Button -->
				<form onsubmit="event.preventDefault();XHRRequestForOffer()">
					<div class="form-group mt-5 d-flex justify-content-around">
						<button type="submit" id="ProcessId"
							class="btn btn-danger w-25">
							<strong> Process to offers </strong>
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
							<th style="width: 20%">Client Name</th>
							<th style="width: 20%">Offer Name</th>
							<th style="width: 15%">Quantity</th>
							<th style="width: 20%"></th>
						</tr>
					</thead>
					<tbody id="MyTable2"></tbody>
				</table>
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.row -->
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
						<form action="OfferServlet">
							<select class="form-control select2 select2-hidden-accessible"
								style="width: 100%;" data-select2-id="1" tabindex="-1"
								aria-hidden="true" id="input-form">
								<br>
								<option selected="selected" data-select2-id="3">Select
									option you want to update..</option>
								<option value="ItemName">Item Name</option>
								<option value="quantity">Quantaty</option>
								<option value="delivaryDate">Delivery Date</option>
							</select>

							<div class="form-group" id="hide-text" style="display: none;">
								<label for="placeholderChange" id="lableName" class="mt-2"></label>
								<input type="text" class="form-control" id="placeholderChange"
									placeholder="Enter">
							</div>
							<div class="form-group" id="hide-date" style="display: none;">
								<label for="" id="lableName1">Delivery Date</label> <input type="date"
									class="form-control" id="placeholderChange1"
									placeholder="Enter Delivery Date">
							</div>
							<button type="submit" class="btn btn-primary mt-2 disabled"
								id="input-update">Save changes</button>
						</form>
					</div>
				</div>
				<!-- /.modal-project show -->
			</div>
		</div>
		<!-- /.modal-edit items -->
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
						onclick="deleteItem()" data-dismiss="modal">Delete Item</button>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript" src="EMSOffers.js"></script>
</html>