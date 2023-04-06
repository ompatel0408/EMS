<%@page import="com.bean.EMSLogsBean,java.util.ArrayList,com.dao.EMSLogsDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EMSAllLogs</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">

<div class="wrapper">
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="LeftSideBar.jsp"></jsp:include>
<div class="content-wrapper p-3">
	<div class="card card-default">
                <div class="card-header">
                    <h3 class="card-title"> All Logs </h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <%for(EMSLogsBean ELB:EMSLogsDao.getInstance().getAllData()){%>
                <div class="card-body">
                    <div class="tab-content">
                        <div class="tab-pane active" id="timeline">
                            <!-- The timeline -->
                            <div class="timeline timeline-inverse">
                                <!-- timeline time label -->
                                <div class="time-label">
                                    <span class="bg-gradient-info">
                                        <%=ELB.getCurrentTime()%>
                                    </span>
                                </div>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                 <%if(ELB.getCategory().equals("INSERTED")){ %>
                                <div>
                                    <i class="fas fa-user bg-info"></i>
                                    <div class="timeline-item">
                                        <!-- <span class="time"><i class="far fa-clock"></i> 5 mins ago</span> -->
                                        <h3 class="timeline-header border-0">
                                       	 Updated by
                                       <b>
                                       	 	<i> 
                                       			 <%=ELB.getUserName()%>
                                       		</i>
                                       	</b>
                                             
                                        </h3>
                                    </div>
                                </div>
                                <!-- END timeline item -->
                                <!-- timeline item -->
                                <div>
                                    <i class="fas fa-comments bg-gradient-success"></i>

                                    <div class="timeline-item">
                                        <!-- <span class="time"><i class="far fa-clock"></i> 27 mins ago</span> -->

                                        <h3 class="timeline-header">
                                        <!--<a href="#">Jay White</a> -->
                                         <font color="lightgreen">
                                         <b><%=ELB.getCategory()%></b>
                                         </font>
                                         </h3>
											
                                        <div class="timeline-body">
                                            <%=ELB.getLOGS()%>
                                        </div>    
                                    </div>
                                </div>
                                <%}else if(ELB.getCategory().equals("UPDATED")){%>
                                <div>
                                    <i class="fas fa-user bg-info"></i>

                                    <div class="timeline-item">
                                        <!-- <span class="time"><i class="far fa-clock"></i> 5 mins ago</span> -->
                                        <h3 class="timeline-header border-0">
                                       	 Updated by
                                       	<b>
                                       	 	<i> 
                                       			 <%=ELB.getUserName()%>
                                       		</i>
                                       	</b>
                                           
                                        </h3>
                                    </div>
                                </div>
                                <div>
									<i class="fas fa-comments bg-warning"></i>
                                    <div class="timeline-item">
                                        <!--<span class="time"><i class="far fa-clock"></i> 27 mins ago</span> -->

                                        <h3 class="timeline-header">
                                        <!--<a href="#">Jay White</a> -->
                                        <font color="orange">
                                        	<b><%=ELB.getCategory()%></b>
                                        </font> 
                                        
                                            </h3>
                                        <div class="timeline-body">
                                            <%=ELB.getLOGS()%>
                                        </div>
                                    </div>
                                </div>
                                <%}else{%>
                                <div>
                                    <i class="fas fa-user bg-info"></i>

                                    <div class="timeline-item">
                                        <!-- <span class="time"><i class="far fa-clock"></i> 5 mins ago</span> -->
                                        <h3 class="timeline-header border-0">
                                       	 Updated by
                                       	<b>
                                       	 	<i> 
                                       			 <%=ELB.getUserName()%>
                                       		</i>
                                       	</b>
                                           
                                        </h3>
                                    </div>
                                </div>
                                 <div>
									<i class="fas fa-comments bg-danger"></i>
                                    <div class="timeline-item">
                                        <!-- <span class="time"><i class="far fa-clock"></i> 27 mins ago</span> -->

                                        <h3 class="timeline-header">
                                        <!--<a href="#">Jay White</a> -->
                                        <font color="red">
                                        	<b><%=ELB.getCategory()%></b>
                                        </font> 
                                        
                                            </h3>
                                        <div class="timeline-body">
                                            <%=ELB.getLOGS()%>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                                <div>
                                    <i class="far fa-clock bg-gray"></i>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- /.tab-content -->
             </div><!-- /.card-body -->
             <%}%>
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