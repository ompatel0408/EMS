
<%@page import="com.bean.ProjectBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EMS</title>
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">
</head>
<%
ArrayList<ProjectBean> projects = (ArrayList<ProjectBean>) request.getAttribute("projects");
Date d = new Date();
DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
String today = df.format(d);
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
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Client Name</th>
								<th style="width: 17%">Project Id</th>
								<th>Project Progress</th>
								<th style="width: 20%"></th>
							</tr>
						</thead>
						<tbody>
							<%
							for (ProjectBean p : projects) {
							%>
							<tr>
								<td><%=srNo++%></td>
								<td><%=p.getClientName()%></td>
								<td><%=p.getProjectId()%></td>
								<td class="project_progress">
									<div class="progress progress-sm">
										<div class="progress-bar bg-green" role="progressbar"
											aria-valuenow="77" aria-valuemin="0" aria-valuemax="100"
											style="width: 77%"></div>
									</div> <small> 77% Complete </small>
								</td>
								<td><a href="ListIndentServlet?token=2&projectid=<%=p.getProjectId()%>&itemcode=0"><button class="btn btn-primary"> Show All Items</button></a></td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->
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
	<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="assets/dist/js/adminlte.js"></script>
	<script src="assets/plugins/moment/moment.min.js"></script>
	<script src="./custom/custom.js"></script>
</body>
</html>


