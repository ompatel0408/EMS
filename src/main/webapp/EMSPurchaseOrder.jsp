<%@page import="com.bean.EMSPurchaseBean"%>
<%@page import="java.util.ArrayList,com.bean.ClientBean"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link rel="stylesheet"
	href="assets/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="assets/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="assets/dist/css/adminlte.min.css">
</head>
<style>
#myModal {
	padding-top: 0px;
	margin-top: 0;
	overflow: hidden;
	height: 1100px;
}
	
</style>

<body class="hold-transition sidebar-mini layout-fixed">
	<%
	ArrayList<EMSPurchaseBean> pos = (ArrayList<EMSPurchaseBean>) request.getAttribute("pos");
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<div class="wrapper">

	<div class="content-wrapper">
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="card">

								<div class="card-header">
									<h3 class="card-title mt-2">
										<b>Purchase Order </b>
									</h3>
											
								</div>
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
								<th style="width: 2%">Sr.No</th>
								<th style="width: 17%">Vendor Name</th>
								<th style="width: 17%">PO Number</th>
								<th style="width: 20%">Date</th>
								<th style="width: 20%">Total Amount</th>
								<th style="width: 20%"></th>
							</tr>
						</thead>
						<tbody>

							<%
							int temp = 0;
							for (EMSPurchaseBean po : pos) {
							%>
							<tr>
								<td><%=++temp%></td>
								<td><a><%=po.getVendorName()%></a> <br></td>
								<td><a><%=po.getPONumber()%> </a> <br></td>
								<td><a><%=po.getCurrentDate()%></a> <br></td>
								<td><a><%=po.getNetAmount()%></a> <br></td>
								<td>
									<button type="button" class="btn btn-danger btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDelete<%=po.getPoId()%>">
										<i class="fas fa-trash"></i> Delete
									</button>
									<button type="button" class="btn btn-info btn-sm"
										data-toggle="modal"
										data-target="#modal-projectDetails<%=po.getPoId()%>">View
									</button>
								</td>
								<div class="modal fade"
									id="modal-projectDelete<%=po.getPoId()%>">
									<div class="modal-dialog">
										<div class="modal-content bg-danger">
											<div class="modal-header">
												<h4 class="modal-title">Delete</h4>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true"></span>
												</button>
											</div>
											<div class="modal-body">
												<p>Are you sure you want to delete this PO?</p>
											</div>
											<div class="modal-footer justify-content-between">
												<button type="button" class="btn btn-outline-light"
													data-dismiss="modal">Close</button>
												<form action="EMSPurchaseListServlet">
													<input type="hidden" name="delete" value="yes"> <input
														type="hidden" name="poid" value="<%=po.getPoId()%>">
													<button type="submit" value="DELETE"
														class="btn btn-outline-light">Delete Purchase Order</button>
												</form>
											</div>

										</div>
									</div>
								</div>
								<!-- Modal View Code -->
								<div class="modal fade"
									id="modal-projectDetails<%=po.getPoId()%>"> 
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title">Project Details</h4>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true"> </span>
												</button>
											</div>
											<div class="modal-body">
												<%
												for (int j = 0; j < pos.size(); j++) {
													if (pos.get(j).getPoId() == po.getPoId()) {
												%>
												<div class="form-group">
													<label for="exampleInputEmail1">Vendor Name</label>
													<p><%=pos.get(j).getVendorName()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Indent Id</label>
													<p><%=pos.get(j).getIndentId()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Product Description</label>
													<p><%=pos.get(j).getProductDescription()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Size</label>
													<p><%=pos.get(j).getSize()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Quantity</label>
													<p><%=pos.get(j).getQuantity()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Units</label>
													<p><%=pos.get(j).getUom()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Rate Per Kg</label>
													<p><%=pos.get(j).getRatePerKg()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Discount</label>
													<p><%=pos.get(j).getDiscount()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">Total Amount</label>
													<p><%=pos.get(j).getNetAmount()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">SGST</label>
													<p><%=pos.get(j).getSGST()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">CGST</label>
													<p><%=pos.get(j).getCGST()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">CurrentDate</label>
													<p><%=pos.get(j).getCurrentDate()%></p>
												</div>
												<div class="form-group">
													<label for="exampleInputEmail1">PO Number</label>
													<p><%=pos.get(j).getPONumber()%></p>
												</div>
												<%
												}
												}
												%>
											</div>
										</div>
										<!-- /.modal-project show -->
									</div>
								</div>
								<%
								}
								%>
								</tr>
													</tbody>
												</table>
												<!-- <table>-->
											</div>
											<!-- col-sm-12 -->
										</div>
										<!-- row -->
									</div>
									<!-- example1_wrapper -->
								</div>
								<!-- /.card-body -->
							</div>
							<!-- card -->
						</div>
						<!-- col-12 -->
					</div>
					<!-- row -->
				</div>
				<!-- container-fluid -->
			</section>
			<!-- content -->
		</div>
		<!-- content-wrapper -->
	</div>
	<!-- jQuery -->
	<script>
		var newId;
		function dropDown(clientId){
			console.log(clientId.toString());
			newId = clientId.toString();
			console.log(newId);
		
		document.getElementById("input-form"+newId).addEventListener("change", () => {
            let value = document.getElementById("input-form"+newId).value;
            if (value == "Select option you want to update..") {
                document.getElementById("input-form"+newId).style.borderColor = "red";
                document.getElementById("select-error"+newId).innerText = "Please, Select any one optoins.";
                document.getElementById("select-error"+newId).style.color = "red";
                document.getElementById("hide-text"+newId).style.display = "none";
                document.querySelector("#input-update"+newId).classList.add("disabled");
            }
            else {
                document.querySelector("#input-update"+newId).classList.remove("disabled");
                document.getElementById("hide-text"+newId).style.display = "block";
                document.getElementById("lableName"+newId).innerHTML = document.getElementById("input-form"+newId).value;
                document.getElementById("placeholderChange"+newId).setAttribute("placeholder", "Enter " + value );
                document.getElementById("input-form"+newId).style.borderColor = "blue";
                document.getElementById("select-error"+newId).innerText = "";
                document.getElementById("select-error"+newId).style.color = "red";
            }
        })
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
                $(document).ready(function () {
                  $("#example1").DataTable({
                    "responsive": true, "lengthChange": false, "autoWidth": false,
                    "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
                  }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
                })
              </script>
	<script>
                function changeStatus(userId, currentStatus) {
                  location.href = "ChangeStudentStatus?userId=" + userId + "&currentStatus=" + currentStatus;
                }
              </script>

</html>