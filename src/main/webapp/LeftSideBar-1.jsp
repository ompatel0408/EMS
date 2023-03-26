<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="index3.html" class="brand-link"> <img
		src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo"
		class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">Super Admin</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img src="" class="img-circle elevation-2" alt="">
			</div>
			<div class="info">
				<a href="#" class="d-block">Paresh Patel</a>
			</div>
		</div>

		<!-- SidebarSearch Form -->
		<div class="form-inline">
			<div class="input-group" data-widget="sidebar-search">
				<input class="form-control form-control-sidebar" type="search"
					placeholder="Search" aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-sidebar">
						<i class="fas fa-search fa-fw"></i>
					</button>
				</div>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item"><a href="EMSDirectorsDashboard.jsp" class="nav-link active">
						<p>Dashboard</p>
				</a></li>
				<li class="nav-item" id="client-target-id" style="display:none;"><a href="ClientServlet?clientId=0&update=notupdate" class="nav-link"> <i
						class="nav-icon fas ion-person"></i>
						<p>Client</p>
				</a><ul class="nav nav-treeview">
						<li class="nav-item" id="addclient-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add Client</p>
						</a></li>
						<li class="nav-item" id="showclient-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Client list</p>
						</a></li>
					</ul></li>
				<li class="nav-item" id="accessmanagement-target-id" style="display:none;"><a href="AccessManagementServlet" class="nav-link"> <i
						class="nav-icon fas fa-chart-pie"></i>
						<p>
							Access Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					</li>
				<li class="nav-item" id="project-target-id" style="display:none;"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-columns   "></i>
						<p>
							Projects <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="addproject-target-id" style="display:none;"><a href="ProjectServlet?projectId=0&update=notupdate" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add Project</p>
						</a></li>
						<li class="nav-item" id="showproject-target-id" style="display:none;"><a href="ProjectServlet?projectId=0&update=notupdate" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>List Project</p>
						</a></li>
						<li class="nav-item" id="showproject-target-id" style="display:none;"><a href="EMSItem.jsp" class="nav-link" onclick="AddHeaders()"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add Offers</p>
						</a></li>
						<li class="nav-item" id="quotationperoffers-target-id" style="display:none;"><a href="EMSQuotationPerItem.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Quotation Per Offers</p>
						</a></li>
						<li class="nav-item" id="showquotationperoffer-target-id" style="display:none;"><a href="EMSFinalQuotation.jsp" class="nav-link" > <i
								class="far fa-circle nav-icon"></i>
								<p>Final Quotation</p>
						</a></li>
					</ul></li>
				<li class="nav-item" id="indent-target-id" style="display:none;"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-edit"></i>
						<p>
							Indent <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview" id="indent-target-id" style="display:none;">
						<li class="nav-item" id="generateindent-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Generate Indent</p>
						</a></li>
						<li class="nav-item" id="showindent-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Indent list</p>
						</a></li>
						<li class="nav-item" id="approvalpending-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Approval Pending Indent</p>
						</a></li>
					</ul></li>
				<li class="nav-item" id="purchase-target-id" style="display:none;"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-table"></i>
						<p>
							Purchase Orders <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generatepo-target-id" style="display:none;"><a href="EMSPurchase.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Generate P.O</p>
						</a></li>
						<li class="nav-item" id="polist-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>P.O List</p>
						</a></li>
					</ul></li>
				<li class="nav-item" id="grn-target-id" style="display:none;"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-copy"></i>
						<p>
							GRN <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generategrn-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Generate GRN</p>
						</a></li>
						<li class="nav-item" id="grnlist-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>GRN List</p>
						</a></li>
					</ul></li>
				<li class="nav-item" id="stock-target-id" style="display:none;"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-book"></i>
						<p>
							Stock Management <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="addstock-target-id" style="display:none;"><a href="EMSStore.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add stock</p>
						</a></li>
						<li class="nav-item" id="stocklist-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Stock List</p>
						</a></li>
					</ul></li>
				<li class="nav-item" id="sellmanagement-target-id" style="display:none;"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-file"></i>
						<p>
							Sell Management <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="sellitemlist-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Sell item List</p>
						</a></li>
						<li class="nav-item" id="sellissuedlist-target-id" style="display:none;"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Sell issued List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <!-- <i class="nav-icon fas fa-edit"></i> -->
						<i class="nav-icon far fa-plus-square"></i>
						<p>
							Setting <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Account setting</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Logout</p>
						</a></li>
					</ul></li>
			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
	<script type="text/javascript" src="UtilScript.js"></script>
</aside>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <script src="assets/plugins/jquery/jquery.min.js"></script>
    <!-- jQuery UI 1.11.4 -->
    <script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
    <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
    <script>	
        $.widget.bridge('uibutton', $.ui.button)
    </script>
    <!-- Bootstrap 4 -->
    <script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Sparkline -->
    <script src="assets/plugins/sparklines/sparkline.js"></script>
    <!-- JQVMap -->
    <script src="assets/plugins/jqvmap/jquery.vmap.min.js"></script>
    <script src="assets/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
    <!-- jQuery Knob Chart -->
    <script src="assets/plugins/jquery-knob/jquery.knob.min.js"></script>
    <!-- daterangepicker -->
    <script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
    <!-- Tempusdominus Bootstrap 4 -->
    <script src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
    <!-- Summernote -->
    <script src="assets/plugins/summernote/summernote-bs4.min.js"></script>
    <!-- overlayScrollbars -->
    <script src="assets/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
    <!-- AdminLTE App -->
    <script src="assets/dist/js/adminlte.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="assets/dist/js/demo.js"></script>
    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
    <script src="assets/dist/js/pages/dashboard.js"></script>
    <script src="assets/plugins/moment/moment.min.js"></script>