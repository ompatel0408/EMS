<%@page import="com.bean.EMSDrawingBean"%>
<%@page import="com.dao.EMSDrawingDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<section class="content-wrapper p-3">
			<div class="card card-default">
				<!-- /.card-header -->
				<div class="card-header">
					<h3 class="card-title">
						<strong> Drawing List </strong>
					</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool"
							data-card-widget="collapse" title="Collapse">
							<i class="fas fa-minus"></i>
						</button>
					</div>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 15%">Drawing Id</th>
								<th style="width: 30%">projectId</th>
								<th style="width: 30%">Offer Id</th>
								<th style="width: 20%"></th>
							</tr>
						</thead>
						<tbody id="myTable">
							<%for (EMSDrawingBean EGB : EMSDrawingDao.getInstance().getAllData()) {%>
							<tr>
								<td id="GRNId"><%=EGB.getDrawingId()%></td>
								<td><a><%=EGB.getProjectId()%></a> <br></td>
								<td><a><%=EGB.getOfferCode()%></a> <br></td>
								<td class="project-actions text-right">
									<button type="button" class="btn btn-primary btn-sm"
										data-toggle="modal"
										data-target="#modal-grnDetails<%=EGB.getDrawingId()%>">
										<i class="fas fa-folder"></i>
									</button>
									<div class="modal fade" id="modal-grnDetails<%=EGB.getDrawingId()%>">
										<div class="modal fade show" id="modal-xl"
											style="padding-right: 19px; display: block;"
											aria-modal="true" role="dialog">
											<div class="modal-dialog modal-xl">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title">Extra Large Modal</h4>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">×</span>
														</button>
													</div>
													<div class="modal-body">
														<div class="form-group">
															<label for="">GRN picture</label> <br> <img
																src="DrawingImages/<%=EGB.getClientDrawing()%>" alt="path1"
																class="h-100 w-100">
														</div>
														<div class="form-group">
															<label for="">GRN picture</label> <br> <img
																src="DrawingImages/<%=EGB.getEMSDrawing()%>" alt="path2"
																class="h-100 w-100">
														</div>
													</div>
													<div class="modal-footer justify-content-between">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal-dialog -->
										</div>
									</div>
								</td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
			</div>
		</section>
	</div>
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