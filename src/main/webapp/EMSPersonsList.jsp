<%@page import="com.bean.PersonBean"%>
<%@page import="com.bean.EMSFinalQuotationBean"%>
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
ArrayList<PersonBean> person = (ArrayList<PersonBean>) request.getAttribute("person");
int srNo = 1;
%>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
		<section class="content-wrapper">
			<div class="card m-2">
				<div class="card-header">
					<h3 class="card-title">Persons</h3>
				</div>
				<div class="card-body p-0">
					<table class="table table-striped projects">
						<thead>
							<tr>
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Person Name</th>
								<th style="width: 17%">Email</th>
								<th style="width: 17%">Number</th>
								<th style="width: 17%">Designation</th>
								<th style="width: 17%">Action</th>
							</tr>
						</thead>
						<tbody>
							<%
							for (PersonBean pb : person) {
							%>
							<tr>
								<td><%=srNo++%></td>
								<td><%=pb.getName()%></td>
								<td><%=pb.getEmail()%></td>
								<td><%=pb.getNumber()%></td>
								<td><%=pb.getDesg()%></td>
								<td>
									<button type="button" class="btn btn-danger btn-sm"
										data-toggle="modal" onclick="setDeleteId('<%=pb.getId()%>')"
										data-target="#modal-projectDelete<%=pb.getId()%>">
										<i class="fas fa-trash"></i>
									</button>
									<div class="modal fade" id="modal-projectDelete<%=pb.getId()%>">
										<div class="modal-dialog">
											<div class="modal-content bg-danger">
												<div class="modal-header">
													<h4 class="modal-title">Delete Person</h4>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true"></span>
													</button>
												</div>
												<div class="modal-body">
													<p>Are you sure you want to delete this Person?</p>
												</div>
												<div class="modal-footer justify-content-between">
													<button type="button" class="btn btn-outline-light"
														data-dismiss="modal">Close</button>
													<button type="button" class="btn btn-outline-light"
														onclick="deletePerson()">Delete Quotation</button>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>

		</section>
		<footer class="main-footer">
			<strong>Copyright &copy; 2023 <a href="#">EMS Project
					Private Limited</a>.
			</strong> All rights reserved.
		</footer>
		<aside class="control-sidebar control-sidebar-dark"></aside>
	</div>
	<script>
	
		var deleteId = 0;
		function setDeleteId(id)
		{
			deleteId = id;	
		}
		var newId;
		function deletePerson()
		{
			let Data;
			var xhr = new XMLHttpRequest();
			xhr.open('DELETE',
					'http://localhost:8080/EMS/EMSAddPersonServlet', true);
			xhr.setRequestHeader('Content-type', 'application/json');
			xhr.onload = function() {
				if (xhr.status === 200) {
					Data = JSON.parse(xhr.responseText);
					console.log(Data)
					location.reload();
				}
			}
			xhr.send(JSON.stringify({"data" : deleteId}));
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