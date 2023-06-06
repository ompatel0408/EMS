<script src="AccessLeftSideBar.js"></script>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="#" class="brand-link"> <img
		src="assets/dist/img/EMS.jpeg" alt="AdminLTE Logo"
		class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">Super Admin</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="info">
				<%
				String name = (String) session.getAttribute("name");
				%>
				<a href="#" class="d-block"><h4><%=name%></h4></a>
			</div>
		</div>
		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item"><a href="EMSDirectorsDashboard.jsp"
					class="nav-link active">
						Dashboard
				</a></li>
				<li class="nav-item" id="addclient-target-id" style="display: none;"><a
					href="ClientServlet?clientId=0&update=notupdate" class="nav-link">
						<i class="nav-icon fas ion-person"></i>
						Client
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
								Give Access </a></li>
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
								Add Offers
						</a></li>
						<li class="nav-item" id="quotationperoffer-target-id"
							style="display: none;"><a href="EMSQuotationPerItem.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								QuotationPerOffers
						</a></li>

						<li class="nav-item" id="quotationperofferlist-target-id"
							style="display: none;"><a
							href="QuotationPerItemListServlet?offer=1" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								Quotation Per Offer List
						</a></li>

						<li class="nav-item" id="finalquotation-target-id"
							style="display: none;"><a href="EMSFinalQuotation.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								FinalQuotation
						</a></li>

						<li class="nav-item" id="finalquotationlist-target-id"
							style="display: none;"><a
							href="EMSFinalQuotationListServlet?quotationId=0&update=notupdate"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								FinalQuotation List
						</a></li>

						<li class="nav-item" id="addorder-target-id"
							style="display: none;"><a href="EMSItem.jsp"
							class="nav-link" onclick="AddHeaders()"> <i
								class="far fa-circle nav-icon"></i>
								Add orders
						</a></li>
						<li class="nav-item" id="orderlist-target-id"
							style="display: none;"><a
							href="EMSItemListServlet?itemCode=0&update=notupdate"
							class="nav-link" onclick="AddHeaders()"> <i
								class="far fa-circle nav-icon"></i>
								orders List
						</a></li>
						<li class="nav-item" id="addproject-target-id"
							style="display: none;"><a
							href="ProjectServlet?projectId=0&update=notupdate"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Add Project
						</a></li>
						<li class="nav-item" id="adddrawing-target-id"
							style="display: none;"><a href="EMSDrawing.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Add Drawings
						</a></li>
						<li class="nav-item" id="drawinglist-target-id"
							style="display: none;"><a href="EMSDrawingList.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Drawing List
						</a></li>
						<li class="nav-item" id="drawingRevision-id"
							style="display: none;"><a href="DrawingRevision.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Drawing Revision List
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-columns   "></i>
						<p>
							Daily Progress <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">

						<li class="nav-item" id="dpr-id" style="display: none;"><a
							href="DailyProgressReport.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Daily Process Report
						</a></li>
						<li class="nav-item" id="dprList-id" style="display: none;"><a
							href="DPRList.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								DPR List
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
								Generate Indent
						</a></li>
						<li class="nav-item" id="indentlist-target-id"
							style="display: none;"><a
							href="ListIndentServlet?token=1&projectid=0&itemcode=0"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Indent list
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
								Generate P.O
						</a></li>
						<li class="nav-item"><a href="EMSConsumablePurchase.jsp"
							id="EMSConsumable-id" style="display: block;" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								Purchase consumable
						</a></li>
						<li class="nav-item" id="polist-target-id" style="display: none;"><a
							href="EMSPurchaseListServlet?delete=no" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								P.O List
						</a></li>
						<li class="nav-item"><a href="VendorProjectListPO.jsp"
							id="poPrint-id" style="display: none;" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								P.O List Print
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
								Generate GRN
						</a></li>
						<li class="nav-item" id="grnlist-target-id" style="display: none;"><a
							href="EMSGRNList.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								GRN List
						</a></li>
						<li class="nav-item" id="grnApprovelPen-id" style="display: none;"><a
							href="GRNApprovalPending.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								GRN Approval Pending
						</a></li>

						<li class="nav-item" id="grnPeningOrder-id" style="display: none;"><a
							href="EMSGRNPendingList.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								GRN Pending Orders List
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
							Generate Issue Note
						</a></li>
						<li class="nav-item" id="issuenotelist-target-id"
							id="indentlist-target-id" style="display: none;"><a
							href="EMSIssueNoteListServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Issue Note List
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
								Add new stock
						</a></li>
						<li class="nav-item" id="addgeneralstore-target-id"
							style="display: none;"><a href="EMSStore.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Add to general store
						</a></li>
						<li class="nav-item" id="stocklist-target-id"
							style="display: none;"><a href="EMSStoreListServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Stock List
						</a></li>
						<li class="nav-item" id="gatePassOut-id" style="display: none;"><a
							href="EMSGatepassOutword.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Gate pass Out word
						</a></li>
						<li class="nav-item" id="gatePassIn-id" style="display: none;"><a
							href="EMSGatepassinword.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Gate pass In word
						</a></li>
						<li class="nav-item" id="gatePassList-id" style="display: none;"><a
							href="EMSGetpassListServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Gete pass List
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-file"></i>
						<p>
							Production<i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="phase-id" style="display: none;"><a
							href="ProductionPhase.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Project Status
						</a></li>
						<li class="nav-item" id="phaseUpdate-id" style="display: none;"><a
							href="PhaseUpdate.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Phase Update
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
								Add Vendor
						</a></li>
						<li class="nav-item" id="vendorlist-target-id"
							style="display: none;"><a
							href="EMSVendorsServlet?vendorId=0&update=notupdate"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								list Vendor
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-edit"></i>
						<p>
							Persons <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="addvendor-target-id"
							style="display: block;"><a href="EMSAddPersons.jsp"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Add Persons
						</a></li>
						<li class="nav-item" id="vendorlist-target-id"
							style="display: block;"><a href="EMSAddPersonServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								list Persons
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-copy"></i>
						<p>
							Machines <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="addMachine-id" style="display: none;"><a
							href="EMSAddMachine.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Add Machine
						</a></li>
						<li class="nav-item" id="showMachine-id" style="display: none;"><a
							href="EMSAddMachineListServlet" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Show Machines
						</a></li>
						<li class="nav-item" id="giveMntMcn-id" style="display: none;"><a
							href="EMSAddMachineInMnt.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Give for Maintenance
						</a></li>
						<li class="nav-item" id="getMntMcn-id" style="display: none;"><a
							href="EMSReceiveFromMnt.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Receive from Maintenance
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <i
						class="nav-icon fas fa-file"></i>
						<p>
							Dispatch<i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item" id="dispatchItem-id" style="display: none;"><a
							href="EMSDispatchItems.jsp" class="nav-link"> <i
								class="far fa-circle nav-icon"></i>
								Dispatch Item
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
								View Logs
						</a></li>
					</ul></li>
				<li class="nav-item"><a href="#" class="nav-link"> <!-- <i class="nav-icon fas fa-edit"></i> -->
						<i class="nav-icon far fa-plus-square"></i>
						<p>
							Setting <i class="fas fa-angle-left right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="EMSLogoutServlet"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								Logout
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