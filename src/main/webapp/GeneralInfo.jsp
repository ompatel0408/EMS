<%@page import="java.util.ArrayList,com.bean.ClientBean"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<style>
#myModal {
	padding-top: 0px;
	margin-top: 0;
	overflow: hidden;
	height: 1100px;
}
</style>

<body class="hold-transition sidebar-mini">
	<%
	ArrayList<ClientBean> clients = (ArrayList<ClientBean>) request.getAttribute("clients");
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="wrapper">
		<div class="content-wrapper">
			<section class="content" style="margin-left: 90%;">
			</section>
			<!-- Content Header (Page header) -->
			<div class="card m-2">
				<div class="card-header">
					<h3 class="card-title mt-2"> <b>Clients </b></h3>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Client Id</th>
								<th style="width: 17%">Client Name</th>
								<th style="width: 20%">Phone number</th>
								<th style="width: 15%">Email</th>
								<th style="width: 30%"></th>
							</tr>
						</thead>
						<tbody>

							<%
							int temp = 0;
							for (ClientBean c : clients) {
							%>
							<tr>
								<td><%=++temp%></td>
								<td><a><%=c.getClientId()%></a> <br></td>
								<td><a><%=c.getClientName()%> </a> <br></td>
								<td><a><%=c.getPhoneNumber()%></a> <br></td>
								<td><a><%=c.getEmail()%></a> <br></td>
								<td class="project-actions text-right">
									<a href="GeneralListServlet?clientId=<%=c.getClientId()%>"><button type="button" class="btn btn-info btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDetails<%=c.getClientId()%>">View Projects
									</button></a>
									<a href="EMSItemListServlet?clientId=<%=c.getClientId()%>"><button type="button" class="btn btn-info btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDetails<%=c.getClientId()%>">View Orders
									</button></a>
								</td>
								<%
								}
								%>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>

		</div>
	</div>s
	<!-- jQuery -->
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="assets/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<!-- InputMask -->
	<script src="assets/plugins/moment/moment.min.js"></script>
	<script src="assets/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script
		src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- dropzonejs -->
	<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
	<!-- AdminLTE App -->
	<script src="assets/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="assets/dist/js/demo.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>