

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMS Production</title>

</head>

<body>
<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		
		<div class="content-wrapper" style="min-height: 1345.6px;">
		<br>
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<b>Production</b>
					</h3>
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

					<form action="EMSProductionServlet" method="post">
						<div class="row">
							<div class="col-sm-6">
								<!-- text input -->
								<div class="form-group">
									<label>ProjectID</label> <select id="ProjectId1"
										name="projectId" class="form-control"
										placeholder="select projectId">
										<option value="select" selected>Select projectId</option>
										
									</select>
								</div>
								<div class="form-group">
									<label>Remark</label>
									<textarea class="form-control" name="Remark" rows="3"
										placeholder="Enter remark"></textarea>
								</div>
							</div>

							<div class="col-sm-6">
								<div class="form-group ">
									<label for="workdoneper">WorkDonePercentage</label> <input
										type="number" name="workDonePer" class="form-control"
										id="workdoneper" placeholder="Enter WorkDonePercentage">
								</div>
							</div>
						</div>


						<h5 class="mt-4 mb-2">Quality Checked Yes or No ?</h5>
						<br>
						<div class="row">
							<div class="col-lg-3">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <input type="radio"
											name="qualityCheck" value="checked">
										</span>
									</div>
									<input type="text" class="form-control"
										placeholder="Yes, Quality checked">
								</div>

							</div>

							<div class="col-lg-3">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"><input type="radio"
											name="qualityCheck" value="unchecked"></span>
									</div>
									<input type="text" class="form-control"
										placeholder="No, Quality checked ">
								</div>
							</div>
						</div>
						<br>

						<div class="text-center">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
						<footer class=" text-center">
							<br> <strong>Copyright © 2023 <a href="#">EMS
									project Pvt Ltd</a>.
							</strong> All rights reserved.
						</footer>
					</form>

				</div>
			</div>
		</div>

	</div>
	</div>

</body>
<script type="text/javascript" src="EMSProduction.js"></script>
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
</html>
