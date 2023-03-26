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
                                        Show Client
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowClient()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowClient() %>,'ShowClient')" value="" id="defaultCheck1">
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
                                        <input class="form-check-input" type="checkbox" <%=access.getAddClient()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAddClient() %>,'AddClient')" value="" id="defaultCheck2">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck3">
                                        Show Project
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowProject()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowProject() %>,'ShowProject')" value="" id="defaultCheck3">
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
                                        Show Offer
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getShowOffer()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowOffer() %>,'ShowOffer')" value="" id="defaultCheck5">
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
                                        <input class="form-check-input" type="checkbox" <%=access.getIndentList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowClient() %>,'IndentList')" value="" id="defaultCheck11">
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
                                    <label class="form-check-label" for="defaultCheck17">
                                        Add Sell Items 
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" onclick="changeStatus(<%=access.getUserId() %>,<%=access.getShowClient() %>,'ShowClient')"  value="" id="defaultCheck17">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>18</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck18">
                                        Show Sell Item 
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getSellItemList()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getSellItemList() %>,'SellItemList')"  value="" id="defaultCheck18">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>19</td>
                                <td>
                                    <label class="form-check-label" for="defaultCheck1">
                                        Access Management
                                    </label>
                                </td>
                                <td>
                                    <div class="form-check d-flex align-items-center">
                                        <input class="form-check-input" type="checkbox" <%=access.getAccessManagement()==1?"checked":"" %> onclick="changeStatus(<%=access.getUserId() %>,<%=access.getAccessManagement() %>,'AccessManagement')" value="" id="defaultCheck1">
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
 
     </body>
    </html>
    