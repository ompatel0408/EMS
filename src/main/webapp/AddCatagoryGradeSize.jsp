
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList,com.bean.CatagoryGradeSizeBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	ArrayList<CatagoryGradeSizeBean> catagories = (ArrayList<CatagoryGradeSizeBean>) request.getAttribute("data");
	ArrayList<String> catName = new ArrayList<String>();
	ArrayList<Integer> catId = new ArrayList<Integer>();
	for (CatagoryGradeSizeBean i : catagories)
	{
     	catName.add(i.getCatagoryName());
		catId.add(i.getCatagoryId());
	}
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="content-wrapper p-3">
		<div class="card card-default">
			<div class="card-header">
				<h3 class="card-title">Category</h3>

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
				<form action="CatagoryGradeSizeServlet" method="post">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
							<input type="hidden" id="data-from-server" value="<%=catName  %>" />
							<input type="hidden" id="data-from-serverId" value="<%=catId  %>" />
								<button type="button" id="add-category"
									class="btn btn-primary w-100" >
									<strong>+</strong> Add Category
								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<button type="button" id="add-grade"
									class="btn btn-primary w-100">
									<strong>+</strong> Add Grade
								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<button type="button" id="add-size"
									class="btn btn-primary w-100">
									<strong>+</strong> Add Size
								</button>
							</div>
						</div>
					</div>
					<div class="m-0 p-0 " id="append-here"></div>
					<div class="col">
						<div class="col-md-6 float-right">
							<div class="form-group mt-3">
								<button type="submit" id="processTo"
									class="btn float-right btn-danger">
									<strong> Process to Item </strong>
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Page specific script -->
<script src="AddCatagoryGradeSize.js">
        
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

</html>