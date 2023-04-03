<%@page import="com.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
ArrayList<UserBean> users = (ArrayList<UserBean>) request.getAttribute("users");

int srNo=1;
%>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="Header.jsp"></jsp:include>
		<jsp:include page="LeftSideBar.jsp"></jsp:include>
   
	<section class="content-wrapper p-3">
            <div class="card card-default">
                <!-- /.card-header -->
                <div class="card-header">
                    <h3 class="card-title"> <strong> Access Management </strong> </h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped projects">
                        <thead>
                        
                            <tr>
                                <th style="width: 5%">Sr.No</th>
                                <th style="width: 20%">User Name</th>
                                <th style="width: 20%">Email</th>
                                <th style="width: 20%">Mobile Number</th>
                                <th style="width: 20%">Department Name</th>
                                <th style="width: 20%">Access Permission</th>
                            </tr>
                        </thead>
                        <tbody id="myTable">
                        <%
							for (UserBean u : users) {
							%>
                            <tr>
                                <td><%=srNo++ %></td>
                                <td><%=u.getUserName()%><br></td>
                                <td> <%=u.getEmail() %><br></td>
                                <td> <%=u.getPhNum() %><br></td>
                                <td><%=u.getDepartmentName() %> <br></td>
                                <td class="-actions text-riprojectght" >
                                	<form action="ShowManagementAccess" method="GET">
                                    <button type="submit" class="btn btn-sm btn-danger toastrDefaultError">
                                        <i class="nav-icon fas fa-edit"></i>
                                    </button>
                                    <input type="hidden" name="change" value="0">
                                    <input type="hidden" name="userId" value=<%=u.getUserId()%>></form>
                                </td>
                            </tr>
                           <%} %>
                        </tbody>
                    </table>
                </div>
                <!-- /.card-body -->
            </div>
        </section>
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
	<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
	<script src="assets/dist/js/adminlte.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</html>