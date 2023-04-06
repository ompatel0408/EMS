<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.custom-file-upload {
	border: 1px solid #ccc;
	display: inline-block;
	padding: 6px 12px;
	cursor: pointer;
	border-radius: 4px;
	font-size: 16px;
	font-weight: bold;
	color: #555;
	background-color: #fff;
	transition: all 0.3s ease;
	background: linear-gradient(to bottom, #fff, #f2f2f2);
}

.custom-file-upload::before {
	content: "\f093";
	font-family: "Font Awesome 5 Free";
	font-weight: 900;
	margin-right: 5px;
}

.custom-file-upload:hover {
	background-color: #ddd;
}

input[type="file"] {
	display: none;
}

input[type="file"]:not(:focus)+.custom-file-upload::after {
	color: green;
	font-weight: bold;
}
/* This is optional, it adds an icon to the label */
.custom-file-upload i {
	margin-right: 5px;
}

.upload {
	color: #07074D;
	font-weight: 500;
	font-size: 25px;
	line-height: 24px;
	display: block;
	margin-bottom: 10px;
}
</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<div class="content-wrapper p-3">
			<div class="card card-default">
				<div class="card-header">
					<h3 class="card-title">ADD DRAWING</h3>

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
					<form action="EMSDrawingServlet" method="post"
						enctype="multipart/form-data">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group">
									<label>Project</label> <select class="form-control"
										onload="getProject1()" id="projectId" name ="projectId"
										onchange="getOffers()">
									</select>
								</div>
								<div class="card-header">
									<h3 class="card-title">Upload The Bill</h3>
								</div>
								<div class="btn-group">
									<div class="dropzone">
										<input type="file" name="file" id="inputFile" /> <label for="inputFile" 
											class="custom-file-upload"> <i
											class="fas fa-cloud-upload-alt"></i> Choose File
										</label> 
										<p id="file-name"></p>
									</div>
								</div>
							</div>
							<div class="col-md-6">
<!-- 								<div class="form-group">

									<label>Vendor name</label> <input type="text" name="VendorName"
										class="form-control" id="quantaty-id"
										placeholder="Enter Vendor name">
								</div> -->
								<div class="form-group">
									<label>Offers</label> <select class="form-control"
										 id="OfferId" name="offerId">
									<option value="select Offers"selected>Select Offers</option>
									</select>
								</div>
								
								<!-- /.form-group -->
								<div class="card-header">
									<h3 class="card-title">Upload The Truck Pic</h3>
								</div>
								<div class="btn-group">
									<div class="dropzone">
										<input type="file" name="file1" id="inputFile1" />  <label for="inputFile1"
											class="custom-file-upload"> <i
											class="fas fa-cloud-upload-alt"></i> Choose File
										</label>
										<p id="file-name"></p>
									</div>
								</div>
							</div>
							<div class="col">
								<div class="col-md-6 float-right">
									<button type="submit" id="processTo"
										class="btn btn-danger w-25">
										<strong> Process to GRN </strong>
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
	<!-- <script src="assets/plugins/dropzone/min/dropzone.min.js"></script> -->
	<script src="assets/dist/js/adminlte.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
	
	window.onload = function getProject1(){
		let Data;
		var xhr = new XMLHttpRequest();
		xhr.open('GET', 'http://localhost:8080/EMS2/EMSDrawingServlet',true);
		xhr.onload = function() {
	  		if (xhr.status === 200) {
	    		Data = JSON.parse(xhr.responseText);
	    		console.log(Data)
	    		appendProjects(Data)
	  		}
		}
		xhr.send();	
	}
	
	function appendProjects(Data){
		var ClientsSelect = document.getElementById("projectId");
		console.log(ClientsSelect)
		ClientsSelect.innerHTML = `<option value="select Projects"selected>Select Projects</option>`;
		for(let i=0; i<Data.length; i++)
		{
			let createdAt = document.createElement("option");
			createdAt.value = Data[i];
			createdAt.innerHTML = Data[i];
			ClientsSelect.appendChild(createdAt);
		}
	}
	

	function getOffers() {
			let Data;
			var xhr = new XMLHttpRequest();
			xhr.open('PUT', 'http://localhost:8080/EMS2/EMSDrawingServlet',true);
			xhr.onreadystatechange = function() {
		  		if (xhr.status === 200) {
		    		Data = JSON.parse(xhr.responseText);
		    		console.log(Data)
		    		appendOffer(Data)
		  		}
			}
			xhr.send(JSON.stringify({ProjectId:document.getElementById('projectId').value}));	
		}

		function appendOffer(Clients){
			var ClientsSelect = document.getElementById("OfferId");
			console.log(ClientsSelect)
			ClientsSelect.innerHTML = `<option value="select offers"selected>Select offers</option>`;
			for(let i=0; i<Clients.length; i++)
			{
				let createdAt = document.createElement("option");
				createdAt.value = Clients[i];
				createdAt.innerHTML = Clients[i];
				ClientsSelect.appendChild(createdAt);
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

</html>