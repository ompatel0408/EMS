<%@page import="com.bean.EMSPurchaseBean"%>
<%@page import="com.bean.EMSVendorsBean"%>
<%@page import="com.dao.EMSVendorsDao,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html lang="en">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>data</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	text-indent: 0;
}

.s1 {
	color: black;
	font-family: "Times New Roman", serif;
	font-style: normal;
	font-weight: bold;
	text-decoration: none;
	font-size: 20pt;
}

.s2 {
	color: black;
	font-family: "Times New Roman", serif;
	font-style: normal;
	font-weight: bold;
	text-decoration: none;
	font-size: 8.5pt;
}

.s3 {
	color: black;
	font-family: "Times New Roman", serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 8.5pt;
}

.s4 {
	color: black;
	font-family: "Times New Roman", serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 8.5pt;
}

.s5 {
	color: black;
	font-family: "Times New Roman", serif;
	font-style: normal;
	font-weight: bold;
	text-decoration: none;
	font-size: 9pt;
}

.s6 {
	color: black;
	font-family: "Times New Roman", serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 9pt;
}

.s7 {
	color: #00F;
	font-family: Arial, sans-serif;
	font-style: normal;
	font-weight: normal;
	text-decoration: none;
	font-size: 8.5pt;
}

.s8 {
	color: black;
	font-family: Calibri, sans-serif;
	font-style: normal;
	font-weight: bold;
	text-decoration: none;
	font-size: 8.5pt;
}

table, tbody {
	vertical-align: top;
	overflow: visible;
}
</style>
</head>
<body>
	<%
	EMSVendorsBean vdao = (EMSVendorsBean) request.getAttribute("vendorDet");
	EMSPurchaseBean pbean = (EMSPurchaseBean) request.getAttribute("poDet");
	ArrayList<EMSPurchaseBean> podet = (ArrayList<EMSPurchaseBean>) request.getAttribute("povendorDet");
	int sr = 0;
	double totalAmtBeforeTax = 0;
	String payment="";
	double grandTotal=0;
	%>
	<table style="border-collapse: collapse; margin-left: 6.31pt"
		cellspacing="0">
		<tbody>
			<tr style="height: 49pt">


				<td
					style="width: 445pt; border-top-style: solid; border-top-width: 2pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="8"><img width="100" height="57" class=""
					src="data:image/jpg;base64,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">
					<p class="d-inline fs-4 fw-bold float-right"
						style="padding-top: 12pt; text-indent: 0pt; text-align: left;">EMS
						PROJECTS PVT. LTD.</p></td>
				<td
					style="width: 296pt; border-top-style: solid; border-top-width: 2pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					colspan="8">
					<p class="s1"
						style="padding-top: 12pt; padding-left: 53pt; text-indent: 0pt; text-align: left;">PURCHASE
						ORDER</p>
				</td>
			</tr>
			<tr style="height: 12pt">
				<td
					style="width: 445pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-right-style: solid; border-right-width: 1pt"
					colspan="8" bgcolor="#FFFF00">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; text-align: left;">Factory
						: Survey No.-478, Near Kuha Bus Stand, Village-Kuha,Ta. Dascroi,
						Ahmedabad-382433.</p>
				</td>
				<td
					style="width: 296pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					colspan="8" rowspan="3">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; text-align: left;">PROJECT
						DETAIL :- PA077</p>
				</td>
			</tr>
			<tr style="height: 9pt">
				<td
					style="width: 348pt; border-left-style: solid; border-left-width: 2pt"
					colspan="5">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 8pt; text-align: left;">OFF
						: Survey No.-478, Near Kuha Bus Stand, Village-Kuha,Ta. Dascroi,
						Ahmedabad-382433.</p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 348pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt"
					colspan="5">
					<p
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						<a href="mailto:info@emsgroup.net" class="s4" target="_blank">Contact
							- 09099060280, Email id - </a><a href="http://www.emsgroup.net/"
							class="s4" target="_blank">info@emsgroup.net, Wed site :-
							www.emsgroup.net</a>
					</p>
				</td>
				<td
					style="width: 29pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 32pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 12pt">
				<td
					style="width: 139pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt"
					colspan="3">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 10pt; text-align: left;">Purchase
						Order No.:-</p>
				</td>
				<td
					style="width: 97pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 10pt; text-align: left;">
						<%=pbean.getPONumber()%></p>
				</td>
				<td
					style="width: 112pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 10pt; text-align: left;">Order
						Date :- 26/12/2022</p>
				</td>
				<td
					style="width: 29pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 32pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 107pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt"
					colspan="3">
					<p class="s2"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 10pt; text-align: left;">Delivery
						Days/Date :- 1 Day</p>
				</td>
				<td
					style="width: 28pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 40pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 41pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 74pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt"
					colspan="2">
					<p class="s2"
						style="padding-top: 1pt; padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						Indent No. :-</p>
				</td>
				<td
					style="width: 65pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p class="s2"
						style="padding-top: 1pt; padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">NA
					</p>
				</td>
				<td
					style="width: 97pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 141pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt"
					colspan="2">
					<p class="s2"
						style="padding-top: 1pt; padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						Material Indent Date :- 24/12/2022</p>
				</td>
				<td
					style="width: 32pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 107pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt"
					colspan="3">
					<p class="s2"
						style="padding-top: 1pt; padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						Department :- Mechanical</p>
				</td>
				<td
					style="width: 28pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 40pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 41pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 50pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">To,</p>
				</td>
				<td
					style="width: 45pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 65pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 97pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 112pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 29pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 32pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 266pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					colspan="7">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">COMPANY
						COMMERCIAL DETAIL:-</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 139pt; border-left-style: solid; border-left-width: 2pt"
					colspan="3">
					<p class="s5"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						Name :-
						<%=vdao.getVendorName()%>
					</p>
				</td>
				<td style="width: 97pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 112pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 266pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					colspan="7" bgcolor="#FFFF00">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">GST
						No : 24AABCE3534D1Z8</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 348pt; border-left-style: solid; border-left-width: 2pt"
					colspan="5">
					<p class="s6"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						Address :-
						<%=vdao.getAddress()%></p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 266pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					colspan="7" rowspan="4">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; text-align: left;">PAN
						No. - AABCE3534D</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s6"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">.</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 65pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 97pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 112pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 12pt">
				<td
					style="width: 236pt; border-left-style: solid; border-left-width: 2pt"
					colspan="4">
					<p class="s6"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 10pt; text-align: left;">Contact
						Person :- Mr. J.P.Dewasi (9725942571)</p>
				</td>
				<td style="width: 112pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 14pt">
				<td
					style="width: 74pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt"
					colspan="2">
					<p class="s6"
						style="padding-top: 1pt; padding-left: 1pt; text-indent: 0pt; text-align: left;">Email
						id :-</p>
				</td>
				<td
					style="width: 65pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p
						style="padding-top: 1pt; padding-left: 2pt; text-indent: 0pt; text-align: left;">
						<a href="mailto:laxmiforgefit@yahoo.com" class="s7"><%=vdao.getEmail()%></a>
					</p>
				</td>
				<td
					style="width: 112pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 29pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 32pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				
			</tr>
			<tr style="height: 21pt">
				<td
					style="width: 29pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">Sr.
						No</p>
				</td>
				<td
					style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">HSN
						Code</p>
				</td>
				<td
					style="width: 45pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 3pt; text-indent: 0pt; text-align: left;">Material Description
						</p>
				</td>
				<td
					style="width: 97pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 12pt; text-indent: 0pt; text-align: left;">Product
						Description</p>
				</td>
				<td
					style="width: 112pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 43pt; padding-right: 41pt; text-indent: 0pt; text-align: center;">
						Size</p>
				</td>
				<td
					style="width: 29pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 7pt; text-indent: 0pt; text-align: left;">Qty.</p>
				</td>
				<td
					style="width: 32pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 6pt; text-indent: 0pt; text-align: left;">UOM</p>
				</td>
				<td
					style="width: 36pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-left: 10pt; padding-right: 6pt; text-indent: -1pt; line-height: 10pt; text-align: left;">
						Rate/ Unit</p>
				</td>
				<td
					style="width: 30pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 6pt; text-indent: 0pt; text-align: left;">Disc.</p>
				</td>
				<td
					style="width: 42pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-left: 6pt; padding-right: 4pt; text-indent: 8pt; line-height: 10pt; text-align: left;">
						Net Amount</p>
				</td>
				<td
					style="width: 65pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="2">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 22pt; text-indent: 0pt; text-align: left;">SGST</p>
				</td>
				<td
					style="width: 68pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="2">
					<p class="s2"
						style="padding-top: 5pt; padding-left: 24pt; text-indent: 0pt; text-align: left;">CGST</p>
				</td>
				<td
					style="width: 41pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-left: 11pt; padding-right: 3pt; text-indent: -4pt; line-height: 10pt; text-align: left;">
						Taxable Value</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s2"
						style="padding-left: 10pt; padding-right: 7pt; text-indent: 5pt; line-height: 10pt; text-align: left;">
						Total Amount</p>
				</td>
			</tr>
			<%
			int temp = 0;
			for (EMSPurchaseBean c : podet) {
			%>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="padding-left: 10pt; text-indent: 0pt; line-height: 9pt; text-align: left;"><%=++sr%></p>
				</td>
				<td
					style="width: 65pt; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="padding-left: 22pt; text-indent: 0pt; line-height: 9pt; text-align: left;"></p>
				</td>
				<td
					style="width: 45pt; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="padding-left: 22pt; text-indent: 0pt; line-height: 9pt; text-align: left;"></p>
				</td>
				<td
					style="width: 50%; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-left: 43pt; padding-right: 41pt; text-indent: 0pt; line-height: 9pt; text-align: center;">
						<%=c.getProductDescription()%></p>
				</td>
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s8"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getSize()%></p>
				</td>
				<td
					style="width: 32pt; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;"><%=c.getQuantity()%></p>
				</td>
				<td
					style="width: 30pt; border-left-style: solid; border-left-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getUom()%></p>
				</td>
				<td
					style="width: 42pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getRatePerKg()%></p>
				</td>
				<td
					style="width: 25pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getDiscount()%></p>
				</td>
				<td
					style="width: 40pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getNetAmount()%></p>
				</td>
				<td
					style="width: 28pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getSGST()%></p>
				</td>
				<td
					style="width: 40pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"></p>
				</td>
				<td
					style="width: 41pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getSGST()%></p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"></p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getTaxableValue()%></p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=c.getTotalAmount()%></p>
				</td>
				
			</tr>
			<%
			totalAmtBeforeTax += (c.getQuantity() * Double.parseDouble(c.getRatePerKg()));
			grandTotal+=c.getTotalAmount();
			payment=c.getPaymentTerms();
			%>
			<%
			}
			%>
			<tr style="height: 11pt">
				<td
					style="width: 475pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="9">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;"></p>
				</td>
				<td
					style="width: 42pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s2"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
				<td
					style="width: 25pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					rowspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 40pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					rowspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 28pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					rowspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 40pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					rowspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 41pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					rowspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					rowspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 517pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="10">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Payment Terms:<%=payment %></p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 517pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="10">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;"></p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Total
						Amount before Tax</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s2"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=totalAmtBeforeTax%></p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: center;">1</p>
				</td>
				<td
					style="width: 45pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 371pt; border-top-style: solid; border-top-width: 1pt"
					colspan="6">
					<p class="s3"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Delivery
						of Material to be made at the address of "EMS Projects Pvt. Ltd."
						Purchase Department as under.</p>
				</td>
				<td
					style="width: 30pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">
						Transportation</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 74pt; border-left-style: solid; border-left-width: 2pt"
					colspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 443pt; border-right-style: solid; border-right-width: 1pt"
					colspan="8" bgcolor="#FFFF00">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Survey
						No-478, Nr. Kuha Bus Stop, Village-Kuha, Taluko-Dascroi,
						Ahmedabad-382433.</p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Packing
						&amp; Forwarding</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: center;">2</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 274pt" colspan="3">
					<p class="s3"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Cheque
						will be issued in the name of Firm Raising Invoice.</p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 30pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Others</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: center;">3</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 303pt" colspan="4">
					<p class="s3"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Any
						excess Qty. Supplied by the supplier will not accepted by "EMS
						Projects Pvt. Ltd."</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 30pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Net
						Amount</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s2"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: center;">4</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 443pt; border-right-style: solid; border-right-width: 1pt"
					colspan="8">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Buyers
						Complete Purchase Order No., Date must appear in all invoice,
						Delivery Chalan &amp; Weight Slip (according to Material)</p>
				</td>
				<td
					style="width: 133pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="4">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">SGST
						@</p>
				</td>
				<td
					style="width: 41pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">9.00%</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: center;">5</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 303pt" colspan="4">
					<p class="s3"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Only
						"EMS Projects Pvt. Ltd." has right to cancel order issued incase
						of any dispute.</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 30pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 133pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="4">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">CGST
						@</p>
				</td>
				<td
					style="width: 41pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">9.00%</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.00</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: center;">6</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 335pt" colspan="5">
					<p class="s3"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Material
						Accepted by us Monday to Sunday (9.00 A.M. to 6.00 P.M.) &amp;
						Tuesday Weekly Off.</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 30pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Round
						Off</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s3"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;">0.48</p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 45pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 274pt" colspan="3">
					<p class="s3"
						style="padding-left: 2pt; text-indent: 0pt; line-height: 9pt; text-align: left;">All
						your correspondence must bear our PO No &amp; Date else payment
						may delayed.</p>
				</td>
				<td style="width: 29pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 32pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 36pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td style="width: 30pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-right-style: solid; border-right-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 174pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 1pt"
					colspan="5">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">GRAND
						TOTAL</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p class="s2"
						style="text-indent: 0pt; line-height: 9pt; text-align: right;"><%=grandTotal %></p>
				</td>
			</tr>
			<tr style="height: 11pt">
				<td
					style="width: 29pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 45pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 274pt; border-bottom-style: solid; border-bottom-width: 1pt"
					colspan="3">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 29pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 32pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 36pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-bottom-style: solid; border-bottom-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 224pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 1pt; border-right-style: solid; border-right-width: 2pt"
					colspan="6">
					<p class="s3"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">For,
						"EMS Projects Pvt. Ltd."</p>
				</td>
			</tr>
			<tr style="height: 20pt">
				<td
					style="width: 74pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 2pt"
					colspan="2">
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; text-align: left;">Prepared
						By</p>
				</td>
				<td
					style="width: 65pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 97pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 112pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 61pt; border-top-style: solid; border-top-width: 1pt"
					colspan="2">
					<p class="s2"
						style="padding-left: 7pt; text-indent: 0pt; line-height: 10pt; text-align: left;">Checked
						By</p>
				</td>
				<td
					style="width: 36pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 30pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 25pt; border-top-style: solid; border-top-width: 1pt; border-left-style: solid; border-left-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 40pt; border-top-style: solid; border-top-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 109pt; border-top-style: solid; border-top-width: 1pt"
					colspan="3">
					<p class="s2"
						style="padding-left: 22pt; text-indent: 0pt; line-height: 10pt; text-align: left;">Authorized
						By</p>
				</td>
				<td
					style="width: 50pt; border-top-style: solid; border-top-width: 1pt; border-right-style: solid; border-right-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
			</tr>
			<tr style="height: 19pt">
				<td
					style="width: 74pt; border-left-style: solid; border-left-width: 2pt; border-bottom-style: solid; border-bottom-width: 2pt"
					colspan="2">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
					<p class="s2"
						style="padding-left: 1pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Mr.
						Bhargav Patel</p>
				</td>
				<td
					style="width: 65pt; border-bottom-style: solid; border-bottom-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 97pt; border-bottom-style: solid; border-bottom-width: 2pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 209pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 2pt"
					colspan="4">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
					<p class="s2"
						style="padding-left: 103pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Mr.
						Kinjal Prajapati</p>
				</td>
				<td
					style="width: 30pt; border-bottom-style: solid; border-bottom-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 42pt; border-bottom-style: solid; border-bottom-width: 2pt; border-right-style: solid; border-right-width: 1pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 25pt; border-left-style: solid; border-left-width: 1pt; border-bottom-style: solid; border-bottom-width: 2pt">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
				</td>
				<td
					style="width: 199pt; border-bottom-style: solid; border-bottom-width: 2pt; border-right-style: solid; border-right-width: 2pt"
					colspan="5">
					<p style="text-indent: 0pt; text-align: left;">
						<br>
					</p>
					<p class="s2"
						style="padding-left: 14pt; text-indent: 0pt; line-height: 9pt; text-align: left;">Mr.
						K.M. Chaudhary / Mr. Paresh Patel</p>
				</td>
			</tr>
		</tbody>
	</table>
	<!-- <p style="text-indent: 0pt;text-align: left;"/> -->


</body>
<script type="text/javascript">
	window.print()
</script>
</html>