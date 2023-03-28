
<%@page import="com.bean.ItemBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EMS</title>
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<%
ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getAttribute("items");
int srno=1;
%>
	<div class="wrapper">
		<!-- Preloader -->
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<section class="content-wrapper p-3">
			<div class="card card-default">
				<!-- /.card-header -->
				<div class="card-header">
					<h3 class="card-title">
						<strong> Project item List </strong>
					</h3>
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
				<div class="card-header">
					<h4 class="card-title">
						Project id : <span id="projectIdApp"> ${projectId } </span>
					</h4>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 10%">Sr.No</th>
								<th style="width: 20%">Item code</th>
								<th style="width: 20%">Item name</th>
								<th style="width: 20%">Quantity</th>
								<th style="width: 17%"></th>
							</tr>
						</thead>
						<%for(int i=0;i<items.size();i++){ %>
						<tbody id="myTable">
							<tr>
								<td><%=srno++ %></td>
								<td><a><%=items.get(i).getItemCode() %></a> <br></td>
								<td><a><%=items.get(i).getItemName() %></a> <br></td>
								<td><a><%=items.get(i).getQuantity() %></a> <br></td>
								<td><a href="ListIndentServlet?token=3&projectid=${projectId}&itemname=<%=items.get(i).getItemName()%>"><button class="btn btn-primary"> Show Indent</button></a></td>
							</tr>
						</tbody>
						<%} %>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<!-- <div class="modal fade" id="modal-projectDetails">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h4 class="modal-title">Stock Details</h4>
<button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">&times;</span>
</button>
</div>
<div class="modal-body">
<div class="form-group">
<label for="exampleInputEmail1">Project Id</label>
<p>Project 1</p>
</div>
<div class="form-group">
<div class="form-group">
<label for="exampleInputEmail1">Project Id</label>
<p>Project 1</p>
</div>
</div>
</div>
</div>
<! /.modal-project show -
</div>
</div> -->
			<div class="modal fade" id="modal-stockDelete">
				<div class="modal-dialog">
					<div class="modal-content bg-danger">
						<div class="modal-header">
							<h4 class="modal-title">Delete Project Item</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true"></span>
							</button>
						</div>
						<div class="modal-body">
							<p>Are you sure you want to delete this Project Item?</p>
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
		</section>
		</div>
		</body>
</html>