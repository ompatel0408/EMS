<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.bean.EMSStoreBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<%
ArrayList<EMSStoreBean> store = (ArrayList<EMSStoreBean>) request.getAttribute("store");
	ArrayList<EMSStoreBean> allStore = (ArrayList<EMSStoreBean>) request.getAttribute("allstore");
int srNo=1;
int srNo1=1;
%>
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
			<section class="content-wrapper p-3">
            <div class="card card-default">
                <!-- /.card-header -->
                <div class="card-header">
                    <h3 class="card-title"> <strong> Store</strong>(Stock) <strong>List </strong> </h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped projects">
                        <thead>
                            <tr>
                                <th style="width: 5%">Sr.No</th>
                                <th style="width: 17%">Category</th>
                                <th style="width: 17%">Grade</th>
                                <th style="width: 17%">Size</th>
                                <th style="width: 17%">Quantity</th>
                                <th style="width: 17%"></th>
                            </tr>
                        </thead>
                        <tbody id="">
                            	<%
								for (EMSStoreBean s : store) {
								%>
                            <tr>
                                <td><%= srNo++%></td>
                                <td><%=s.getCategory() %><br></td>
                                <td><%=s.getGrade() %><br></td>
                                <td><%=s.getSize() %><br></td>
                                <td><%=s.getQuantity() %><br></td>
                                <td class="project-actions text-right">
                                    <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                        data-target="#modal-projectDetails">
                                        <i class="fas fa-folder"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                        data-target="#modal-stockDelete" id="Delete1">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                            <%} %>
                        </tbody>
                    </table>
                </div>
                </div>
            </section>
                <!-- /.card-body -->
            
            <div class="modal fade" id="modal-stockDelete">
                <div class="modal-dialog">
                    <div class="modal-content bg-danger">
                        <div class="modal-header">
                            <h4 class="modal-title">Delete Stock</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true"></span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this Stock?</p>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-outline-light" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-outline-light" id="deleteClicked">Delete Project</button>
                        </div>
                    </div>
                </div>
            </div>
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