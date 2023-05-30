<%@page import="com.dao.EMSDirectorsDashboardDao"%>
<%@page import="com.bean.EMSDirectorsDashboardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<style>
.toolbar {
	margin-left: 10px;
}

r

button {
	background: #fff;
	color: #222;
	border: 1px solid #e7e7e7;
	border-bottom: 2px solid #ddd;
	border-radius: 2px;
	padding: 4px 17px;
}

#chart {
	height: fit-content;
	margin-top: 35px;
}
</style>
</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Dashboard</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Dashboard v1</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Small boxes (Stat box) -->
					<div class="row">
						<div class="col-lg-3 col-6" id="showorder-target-id"
							style="display: none;">
							<!-- small box -->
							<div class="small-box bg-info">
								<div class="inner">
									<h3 id="liveorders"></h3>

									<p>New Orders</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="GeneralListServlet?clientId=null"
									class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-success">
								<div class="inner">
									<h3 id="projectCount">0</h3>

									<p>Done Projects</p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a href="EMSDispatchItemsListServlet" class="small-box-footer">show
									Projects <i class="fas fa-arrow-circle-right"></i>
								</a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6" id="adduser-target-id"
							style="display: none;">
							<!-- small box -->
							<div class="small-box bg-warning">
								<div class="inner">
									<h3 id="TotalUser"></h3>
									<p>Add user</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a href="EMSUser.jsp" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-danger">
								<div class="inner">
									<h3>0</h3>
									<p>Projects in Loss</p>
								</div>
								<div class="icon">
									<i class="ion ion-pie-graph"></i>
								</div>
								<a href="ProjectInLossServlet" class="small-box-footer">More
									info <i class="fas fa-arrow-circle-right"></i>
								</a>
							</div>
						</div>
					</div>
					<div class="row"></div>
				</div>
			</section>
			<div id="addgraph-target-id" style="display: none;">
				<section class="content">
					<div class="p-4">
						<h1 class="">Net Profit Graph</h1>
					</div>
					<div class="container-fluid">
						<div class="toolbar">
							<button id="one_month" class="">1M</button>

							<button id="six_months">6M</button>

							<button id="one_year" class="">1Y</button>

							<button id="ytd" class="">YTD</button>

							<button id="all" class="active">ALL</button>
						</div>
						<div id="chart"></div>
					</div>
				</section>
			</div>
			<div class="mt-5 p-4">
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-body">
							<div id="example1_wrapper"
								class="dataTables_wrapper dt-bootstrap4">
								<div class="row">
									<div class="col-sm-12">
										<table id="example1"
											class="table table-bordered table-striped dataTable dtr-inline"
											aria-describedby="example1_info">
											<thead>
												<tr>
													<th style="width: 1%">sr.no</th>
													<th style="width: 15%">Client Name</th>
													<th style="width: 15%">Project Name</th>
													<th>Project Progress</th>
													<th style="width: 8%" class="text-center">Status</th>
													<th style="width: 10%">Start Date</th>
													<th style="width: 20%"></th>
												</tr>
											</thead>
											<tbody id="MyTable">
												<%
												int count = 1;
												%>
												<%
												for (EMSDirectorsDashboardBean EDDB : EMSDirectorsDashboardDao.getInstacne().getDataOfLiveProjects()) {
												%>
												<tr>
													<td><%=count++%></td>
													<td><a><%=EDDB.getClientName()%></a><br></td>
													<td><a><%=EDDB.getProjectName()%></a><br></td>
													<td class="project_progress">
														<div class="progress progress-sm">
															<div class="progress-bar bg-green" role="progressbar"
																aria-valuenow="77" aria-valuemin="0" aria-valuemax="100"
																style="width:<%=EDDB.getProgress()%>%"></div>
														</div> <small> <%=EDDB.getProgress()%>%
															Complete
													</small>
													</td>
													<td class="project-state"><span
														class="badge badge-success">Live</span></td>
													<td><a><%=EDDB.getStartDate()%></a><br></td>
													<td class="project-actions text-right"><a
														class="btn btn-primary btn-sm" href="#"> <i
															class="fas fa-folder"></i> View
													</a></td>
												</tr>
												<%}%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="EMSDirectorsDashboard.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	<script src="assets/dist/js/custom.js"></script>
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
	<script src="assets/dist/js/demo.js"></script>
	<!-- DataTables  & Plugins -->
	<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="assets/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="assets/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="assets/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="assets/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="assets/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="assets/plugins/jszip/jszip.min.js"></script>
	<script src="assets/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="assets/plugins/pdfmake/vfs_fonts.js"></script>
	<script src="assets/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script src="assets/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="assets/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<!-- AdminLTE App -->
	<script>
		$(document).ready(
				function() {
					$("#example1").DataTable({
						"responsive" : true,
						"lengthChange" : false,
						"autoWidth" : false,
						"buttons" : [ "copy", "csv", "excel", "pdf", "print" ]
					}).buttons().container().appendTo(
							'#example1_wrapper .col-md-6:eq(0)');
				})
	</script>
	<script>
		function changeStatus(userId, currentStatus) {
			location.href = "ChangeStudentStatus?userId=" + userId
					+ "&currentStatus=" + currentStatus;
		}
	</script>
</body>

</html>