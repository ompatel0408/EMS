<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%
		String nameErr = (String)request.getAttribute("nameErr");
		String phoneErr = (String)request.getAttribute("phoneErr");
		String dpErr = (String)request.getAttribute("dpErr");
		String roleErr = (String)request.getAttribute("roleErr");
	%>
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">Add User</h3>

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
					<form action="EMSUsersSevlet" method="Post">
						<div class="row">
							<div class="col-sm-6">
								<!-- text input -->
								<div class="form-group">
									<label>User Name</label> <input type="text" name="userName"
										class="form-control" placeholder="Enter Userame"> <br>
										<p style="color : red;";><%=nameErr == null ? "" : nameErr %></p>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Email</label> <input type="email" class="form-control"
										name="email" placeholder="Enter Email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,4}$" required="required">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Phone Number</label> <input type="number" name="mobile"
										class="form-control" placeholder="Enter phone number" pattern="[0-9]{10}" required="required"> <br>
										<p style="color : red;";><%=phoneErr == null ? "" : phoneErr %></p>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Department Name</label> <select name="departmantName" class="form-control" required>
										<option value="0">Select Department</option>
										<option value="Mechanical">Mechanical</option>
										<option value="Electrical">Electrical</option>
										<option value="Administrative">Administrative</option>
									</select> <br>
									<p style="color : red;";><%=dpErr == null ? "" : dpErr %></p>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Role</label> <select name="role" class="form-control" required>
										<option value="0">Select Role</option>
										<option value="1">Admin</option>
										<option value="2">Sub Admin</option>
										<option value="3">Purchase</option>
										<option value="4">Store</option>
										<option value="5">GRN</option>
										<option value="6">Production</option>
									</select>
									<p style="color : red;";><%=roleErr == null ? "" : roleErr %></p>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="col-md-6 float-right">
								<div class="form-group mt-3 d-flex justify-content-around">
									<button type="submit" id="add_vendor"
										class="btn btn-primary w-25">
										<strong>+</strong> Add User
									</button>

								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
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