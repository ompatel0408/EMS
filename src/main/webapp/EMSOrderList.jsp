<%@page import="com.bean.ItemBean"%>
<%@page import="jakarta.mail.FetchProfile.Item"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EMS</title>
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">

</head>
<%
ArrayList<ItemBean> items= (ArrayList<ItemBean>) request.getAttribute("items");
int srNo=1;
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->

		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>


		<!-- Content Wrapper. Contains page content -->
		<section class="content-wrapper">
			<!-- Default box -->
			<div class="card m-2">
				<div class="card-header">
					<h3 class="card-title">Item List for Client: `${clientName}` </h3>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Item Name</th>
								<th style="width: 17%">Tag No</th>
								<th style="width: 17%">Quantity</th>
								<th style="width: 17%">Delivery Date</th>
								<th style="width: 17%">Quatation Id</th>
								<th style="width: 17%">Price</th>
								
							</tr>
						</thead>
						<tbody>

							<%
							for (ItemBean ib : items) {
							%>
							<tr>

								<td><%=srNo++%></td>
								<td><%=ib.getItemName()%></td>
								<td><%=ib.getTagNo()%></td>
								<td><%=ib.getQuantity()%></td>
								<td><%=ib.getDeliveryDate()%></td>
								<td><%=ib.getQuotationId()%></td>
								<td><%=ib.getTotalPrice()%></td>
								<td class="project-actions text-right">
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->
			<!-- Add Projext model -->
			

		</section>
		<footer class="main-footer">
			<strong>Copyright &copy; 2023 <a href="#">EMS Project
					Private Limited</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<script>
	
		function dropDown (projectId){
        	console.log(typeof(projectId))
		
            let value = document.getElementById("input-form"+projectId).value;
            console.log(value);
            if (value == "Select option you want to update..") {
                document.getElementById("input-form"+projectId).style.borderColor = "red";
                document.getElementById("select-error"+projectId).innerText = "Please, Select any one optoins.";
                document.getElementById("select-error"+projectId).style.color = "red";
                document.getElementById("hide-text"+projectId).style.display = "none";
                document.querySelector("#input-update"+projectId).classList.add("disabled");
            }
            else {
                document.querySelector("#input-update"+projectId).classList.remove("disabled");
                document.getElementById("hide-text"+projectId).style.display = "block";
                document.getElementById("lableName"+projectId).innerHTML = document.getElementById("input-form"+projectId).value;
                document.getElementById("placeholderChange"+projectId).setAttribute("placeholder", "Enter " + value);
                document.getElementById("input-form"+projectId).style.borderColor = "blue";
                document.getElementById("select-error"+projectId).innerText = "";
                document.getElementById("select-error"+projectId).style.color = "red";
            }
       
		}
    </script>
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

</body>

</html>