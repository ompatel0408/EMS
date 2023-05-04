<%@page import="com.bean.AccessBean"%>
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
AccessBean access = (AccessBean) request.getAttribute("access");

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
                    <h3 class="card-title"> <strong> Permission Master </strong> </h3>
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
                                <th style="width: 15%">Sr.No</th>
                                <th style="width: 50%">Permission Name</th>
                                <th style="width: 20%">Value</th>
                            </tr>
                        </thead>
                        <tbody id="myTable">
                            <tr>
                                <td>1</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Show Stock List
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getStockList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getStockList() %>,'ShowStockList')" value="" id="defaultCheck1">
                                    </div> 
                                </td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck2">
                                        Add Client
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input  required class="form-check-input" type="checkbox" <%=access.getAddClient()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddClient() %>,'AddClient')" value="" id="defaultCheck2">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck3">
                                        Add Drawing
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddDrawing()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddDrawing() %>,'AddDrawing')" value="" id="defaultCheck3">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck4">
                                        Add Project
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddProject()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddProject() %>,'AddProject')" value="" id="defaultCheck4">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck5">
                                        List Drawing
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getDrawingList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getDrawingList()%>,'ListDrawing')" value="" id="defaultCheck5">
                                    </div> 
                                </td>
                            </tr>
                            <tr>
                                <td>6</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck6">
                                        Add Offer
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddOffer() %>,'AddOffer')" value="" <%=access.getAddOffer()==1?"checked":"" %> id="defaultCheck6">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>7</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck7">
                                        Show Quotation Per Item
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowQuotationPerOffer()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowQuotationPerOffer() %>,'ShowQuotationPerOffer')" value="" id="defaultCheck7">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>8</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck8">
                                        Add Quotation Per Item
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getQuotationPerOffers()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getQuotationPerOffers() %>,'QuotationPerOffer')" value="" id="defaultCheck8">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>9</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck9">
                                        Show Final Quotation
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowFinalQuotation()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowFinalQuotation() %>,'ShowFinalQuotation')" value="" id="defaultCheck9">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>10</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck10">
                                        Add Final Quotation
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getFinalQuotation()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getFinalQuotation() %>,'FinalQuotation')" value="" id="defaultCheck10">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>11</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck11">
                                        Show Indent List
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getIndentList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getIndentList()%>,'IndentList')" value="" id="defaultCheck11">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>12</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck12">
                                        Generate Indent
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGenerateIndent()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGenerateIndent() %>,'GenerateIndent')" value="" id="defaultCheck12">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>13</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck13">
                                        Show Purchase Order
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getPoList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getPoList() %>,'POList')" value="" id="defaultCheck13">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>14</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck14">
                                        Add Purchase Order 
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGeneratePo()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGeneratePo() %>,'GeneratePO')" value="" id="defaultCheck14">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>15</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck15">
                                        Generate GRN 
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGenerateGrn()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGenerateGrn() %>,'GenerateGrn')" value="" id="defaultCheck15">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>16</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck16">
                                        Show GRN 
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGrnList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGrnList() %>,'grnlist')" value="" id="defaultCheck16">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>17</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Add Order
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddOrder()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddOrder() %>,'AddOrder')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>18</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        List Order
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getListOrder()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getListOrder() %>,'ListOrder')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>20</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Generate Issue Note
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGenerateIssueNote()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGenerateIssueNote() %>,'GenerateIssueNote')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>21</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Issue Note List
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getIssueNoteList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getIssueNoteList() %>,'IssueNoteList')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>22</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Add New Stock
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddNewStock()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddNewStock() %>,'AddNewStock')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>23</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Project Status
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getProjectStatus()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getProjectStatus() %>,'ProjectStatus')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>24</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Add Vendor
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddVendor()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddVendor() %>,'AddVendor')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>25</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Vendor List
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getVendorList()==1?"checked":"" %> onclick="changeStatus(<%=access.getVendorList() %>,<%=access.getProjectStatus() %>,'ListVendor')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>26</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Add General Store
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddGeneralStore()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddGeneralStore() %>,'AddGeneralStore')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr> 
                            <tr>
                                <td>27</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Add User
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddUser()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddUser() %>,'AddUser')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>28</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Show Logs
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowLogs()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowLogs() %>,'Logs')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>29</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Show Graph
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddGraph()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddGraph() %>,'Graph')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>30</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Show Order On Database
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowOrder()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowOrder() %>,'Orders')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>31</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Daily Project Report</label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getDpr()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getDpr() %>,'dpr')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>32</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Daily Project Report List
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getDprList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getDprList() %>,'dprList')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>33</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Drawing Revision
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getDrawingRevision()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getDrawingRevision() %>,'drawingRevision')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>34</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Show Phases
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getPhase()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getPhase() %>,'phase')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>35</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Update Phases
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getPhaseUpdate()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getPhaseUpdate() %>,'phaseUpdate')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>36</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Add Machines
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAddMachine()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddMachine() %>,'addMachine')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>37</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Show Machines
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowMachine()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowMachine() %>,'showMachine')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>38</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Give Machine in Maintenance
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGiveMntMachine()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGiveMntMachine() %>,'giveMntMachine')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>39</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Receive Machine from Maintenance
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getReceiveMntMachine()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getReceiveMntMachine() %>,'receiveMntMachine')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>40</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Machine Maintenance History
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getListMntMachine()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getListMntMachine() %>,'listMntMachine')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>41</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        GatePass Outward
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGateOutWrd()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGateOutWrd() %>,'gateOutWrd')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>42</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        GatePass Inward
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGateInWrd()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGateInWrd() %>,'gateInWrd')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>43</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        GatePass List
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getGateList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getGateList() %>,'gateList')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>44</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        PO Print
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getPoPrint()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getPoPrint() %>,'poPrint')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>45</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Approval Pending
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getApprovelPending()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getApprovelPending() %>,'approvelPending')" value="" id="defaultCheck1">
                                    </div>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <!-- /.card-body -->
            </div>	
        </section>
       </div>
       
<script type="text/javascript">
	function changeStatus(userId,status,field){
	  location.href="ShowManagementAccess?userId="+userId+"&status="+status+"&field="+field+"&change=1";
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
    