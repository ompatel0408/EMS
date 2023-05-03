<script src="AccessLeftSideBar.js"></script>
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
				<li class="nav-item"><a href="EMSDirectorsDashboard.jsp"
					class="nav-link active">
						<p>Dashboard</p>
				</a></li>
				<li class="nav-item" id="addclient-target-id" style="display: none;"><a
					href="ClientServlet?clientId=0&update=notupdate" class="nav-link">
						<i class="nav-icon fas ion-person"></i>
						<p>Client</p>
				</a></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-chart-pie"></i>
						<p>
							Access Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="accessmanagement-target-id"
							style="display: none;"><a href="AccessManagementServlet"
							class="nav-link"><i class="far fa-circle nav-icon"></i>
								<p>Give Access</p> </a></li>
						<!-- <li class="nav-item"><a href="EMSUser.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add User</p>
						</a></li> -->
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-columns   "></i>
						<p>
							Projects <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">

						<li class="nav-item" id="addoffer-target-id"
							style="display: none;"><a href="EMSClientListServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add Offers</p>
						</a></li>
						<li class="nav-item" id="quotationperoffer-target-id"
							style="display: none;"><a href="EMSQuotationPerItem.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>QuotationPerOffers</p>
						</a></li>

						<li class="nav-item" id="quotationperofferlist-target-id"
							style="display: none;"><a
							href="QuotationPerItemListServlet?offer=1" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Quotation Per Offer List</p>
						</a></li>

						<li class="nav-item" id="finalquotation-target-id"
							style="display: none;"><a href="EMSFinalQuotation.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>FinalQuotation</p>
						</a></li>

						<li class="nav-item" id="finalquotationlist-target-id"
							style="display: none;"><a
							href="EMSFinalQuotationListServlet?quotationId=0&update=notupdate"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>FinalQuotation List</p>
						</a></li>

						<li class="nav-item" id="addorder-target-id"
							style="display: none;"><a href="EMSItem.jsp"
							class="nav-link" onclick="AddHeaders()"> <i
								class="far fa-circle nav-icon"></i>
								<p>Add orders</p>
						</a></li>
						<li class="nav-item" id="orderlist-target-id"
							style="display: none;"><a
							href="EMSItemListServlet?itemCode=0&update=notupdate"
							class="nav-link" onclick="AddHeaders()"> <i
								class="far fa-circle nav-icon"></i>
								<p>orders List</p>
						</a></li>
						<li class="nav-item" id="addproject-target-id"
							style="display: none;"><a
							href="ProjectServlet?projectId=0&update=notupdate"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add Project</p>
						</a></li>
						<li class="nav-item" id="adddrawing-target-id"
							style="display: none;"><a href="EMSDrawing.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add Drawings</p>
						</a></li>
						<li class="nav-item" id="drawinglist-target-id"
							style="display: none;"><a href="EMSDrawingList.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Drawing List</p>
						</a></li>
					</ul></li>
					<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-columns   "></i>
						<p>
							Daily Progress <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">

						<li class="nav-item" id="projectstatus-target-id"><a
							href="DailyProgressReportServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>DPR List</p>
						</a></li>
						<li class="nav-item" id="projectstatus-target-id"><a
							href="DailyProgressReport.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Daily Process Report</p>
						</a></li>

					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-edit"></i>
						<p>
							Indent <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generateindent-target-id"
							style="display: none;"><a href="IndentServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Generate Indent</p>
						</a></li>
						<li class="nav-item" id="indentlist-target-id"
							style="display: none;"><a
							href="ListIndentServlet?token=1&projectid=0&itemcode=0"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Indent list</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-table"></i>
						<p>
							Purchase<i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generatepo-target-id"
							style="display: none;"><a href="EMSPurchase.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Generate P.O</p>
						</a></li>
						<li class="nav-item" id="polist-target-id" style="display: none;"><a
							href="EMSPurchaseListServlet?delete=no" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>P.O List</p>
						</a></li>
						<li class="nav-item"><a href="VendorProjectListPO.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>P.O List Print</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-copy"></i>
						<p>
							GRN <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generategrn-target-id"
							style="display: none;"><a href="EMSGRN.jsp" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Generate GRN</p>
						</a></li>
						<li class="nav-item" id="grnlist-target-id" style="display: none;"><a
							href="EMSGRNList.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>GRN List</p>
						</a></li>
						<li class="nav-item" id="generategrn-target-id"><a
							href="GRNApprovalPending.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>GRN Approval Pending</p>
						</a></li>
						
						<li class="nav-item" id="generategrn-target-id"><a
							href="EMSGRNPendingList.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>GRN Pending Orders List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-tree"></i>
						<p>
							Issue Note <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generateissuenote-target-id"
							style="display: none;"><a href="EMSIssueNote.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Generate Issue Note</p>
						</a></li>
						<li class="nav-item" id="issuenotelist-target-id"
							id="indentlist-target-id" style="display: none;"><a
							href="EMSIssueNoteListServlet" class="nav-link"> <i
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
						<li class="nav-item" id="addnewstock-target-id"
							style="display: none;"><a href="CatagoryGradeSizeServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add new stock</p>
						</a></li>
						<li class="nav-item" id="addgeneralstore-target-id"
							style="display: none;"><a href="EMSStore.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add to general store</p>
						</a></li>
						<li class="nav-item" id="stocklist-target-id"
							style="display: none;"><a href="EMSStoreListServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Stock List</p>
						</a></li>
						<li class="nav-item" id="stocklist-target-id"
							style="display: block;"><a href="EMSGatepassOutword.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Gatepass Outword</p>
						</a></li>
						<li class="nav-item" id="stocklist-target-id"
							style="display: block;"><a href="EMSGatepassinword.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Gatepass Inword</p>
						</a></li>
						<li class="nav-item" id="stocklist-target-id"
							style="display: block;"><a href="EMSGetpassListServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Getepass List</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-file"></i>
						<p>
							Production<i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="projectstatus-target-id"
							style="display: block;"><a href="ProductionPhase.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Project Status</p>
						</a></li>
						<li class="nav-item" id="projectstatus-target-id"
							style="display: block;"><a href="PhaseUpdate.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Phase Update</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-edit"></i>
						<p>
							Vendor <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="addvendor-target-id"
							style="display: none;"><a href="EMSVendors.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add Vendor</p>
						</a></li>
						<li class="nav-item" id="vendorlist-target-id"
							style="display: none;"><a
							href="EMSVendorsServlet?vendorId=0&update=notupdate"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>list Vendor</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-copy"></i>
						<p>
							Machines <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="generategrn-target-id"
							style="display: block;"><a href="EMSAddMachine.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Add Machine</p>
						</a></li>
						<li class="nav-item" id="generategrn-target-id"
							style="display: block;"><a href="EMSAddMachineListServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Show Machines</p>
						</a></li>
						<li class="nav-item" id="generategrn-target-id"
							style="display: block;"><a href="EMSAddMachineInMnt.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>Give for Maintenance</p>
						</a></li>
						<li class="nav-item" id="grnlist-target-id"><a
							href="EMSReceiveFromMnt.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Receive from Maintenance</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-file"></i>
						<p>
							Dispatch<i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="projectstatus-target-id" style=""><a
							href="EMSDispatchItems.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								<p>Dispatch Item</p>
						</a></li>

					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <!-- <i class="nav-icon fas fa-edit"></i> -->
						<i class="nav-icon far fa-plus-square"></i>
						<p>
							Logs <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="EMSALLUpdateLogs.jsp"
							id="showlogs-target-id" style="display: none;" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>View Logs</p>
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <!-- <i class="nav-icon fas fa-edit"></i> -->
						<i class="nav-icon far fa-plus-square"></i>
						<p>
							Setting <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="EMSLogin.jsp" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
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