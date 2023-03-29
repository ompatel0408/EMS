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
				<li class="nav-item"><a href="ClientServlet?clientId=0&update=notupdate" class="nav-link"> <i
						class="nav-icon fas ion-person"></i>
						<p>Client</p>
				</a></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-chart-pie"></i>
						<p>
							Access Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Super Admin</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Admin</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Store</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Employee</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-columns   "></i>
						<p>
							Projects <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="ProjectServlet?projectId=0&update=notupdate" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add Project</p>
						</a></li>
						<li class="nav-item"><a href="EMSClientListServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add Offers</p>
						</a></li>
						<li class="nav-item"><a href="EMSItem.jsp" class="nav-link" onclick="AddHeaders()"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add orders</p>
						</a></li>
						<li class="nav-item"><a href="EMSQuotationPerItem.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>QuotationPerOffers</p>
						</a></li>
						<li class="nav-item"><a href="EMSFinalQuotation.jsp" class="nav-link" > <i
								class="far fa-circle nav-icon"></i>
								<p>FinalQuotation</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-edit"></i>
						<p>
							Indent <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="IndentServlet" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Generate Indent</p>
						</a></li>
						<li class="nav-item"><a
							href="ListIndentServlet?token=1&projectid=0&itemcode=0"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Indent list</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Approval Pending Indent</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>My Indent</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-table"></i>
						<p>
							Purchase Orders <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="EMSPurchase.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Generate P.O</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>P.O List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-copy"></i>
						<p>
							GRN <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="EMSGRN.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Generate GRN</p>
						</a></li>
						<li class="nav-item"><a href="EMSGRNList.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>GRN List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-tree"></i>
						<p>
							Issue Note <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Generate Issue Note</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Issue Note List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-book"></i>
						<p>
							Stock Management <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="CatagoryGradeSizeServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add stock</p>
						</a></li>
						<li class="nav-item"><a href="EMSStore.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add stock Per Project</p>
						</a></li>
						<li class="nav-item"><a href="EMSStoreListServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Stock List</p>
						</a></li>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Issue Note List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-file"></i>
						<p>
							Production<i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="EMSProduction.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Project Status</p>
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
						<li class="nav-item"><a href="EMSLogin.jsp" class="nav-link"> <i
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
<script type="text/javascript" src="EMSDirectorsDashboard.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
	<script src="assets/dist/js/models.js"></script>
	<!-- jQuery -->
	<script src="assets/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="assets/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="assets/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="assets/plugins/moment/moment.min.js"></script>
	<script src="assets/plugins/inputmask/jquery.inputmask.min.js"></script>
	<!-- date-range-picker -->
	<script src="assets/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="assets/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script
		src="assets/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- BS-Stepper -->
	<script src="assets/plugins/bs-stepper/js/bs-stepper.min.js"></script>
	<!-- dropzonejs -->
	<script src="assets/plugins/dropzone/min/dropzone.min.js"></script>
	<!-- AdminLTE App -->
	<script src="assets/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
