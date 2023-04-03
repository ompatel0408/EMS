<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
					<h3 class="card-title">Add Vendor</h3>

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
					<form action="AddUserSevlet" method="Post">
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
										name="email" placeholder="Enter Email">
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Phone Number</label> <input type="number" name="mobile"
										class="form-control" placeholder="Enter phone number"> <br>
										<p style="color : red;";><%=phoneErr == null ? "" : phoneErr %></p>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Department Name</label> <select name="departmantName" class="form-control" required>
										<option value="0">Select Department</option>
										<option value="Mechanical">Mechanical</option>
										<option value="Electrical">Electrical</option>
									</select> <br>
									<p style="color : red;";><%=dpErr == null ? "" : dpErr %></p>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label>Role</label> <select name="role" class="form-control" required>
										<option value="0">Select Role</option>
										<option value="1">Sub Admin</option>
										<option value="2">Purchase</option>
										<option value="3">Store</option>
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
</html>