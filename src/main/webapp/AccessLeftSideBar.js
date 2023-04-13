document.addEventListener("DOMContentLoaded", () => {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet', true);
	xhr.onload = function() {
		if (xhr.status == 200) {
			var userId = JSON.parse(xhr.responseText);
			console.log("-------->" + userId)
			getListSideBar(userId);
		}
	}
	xhr.send(JSON.stringify({ Abcd: "ABCD" }));
	//getProjectStatus();
}); 	

function getListSideBar(userId){
	
	var gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', `http://localhost:8080/EMS2/ShowManagementAccess?change=2&userId=${userId}`,true);
    		xhr.setRequestHeader('Content-type', 'application/json');
    		xhr.onload = function() {
    	  		if (xhr.status == 200) {
    	    		gradeData = JSON.parse(xhr.responseText);
    	    		console.log(gradeData)
    	    		if(gradeData.accessManagement==1){
    	  				document.getElementById('accessmanagement-target-id').style.display='block';
    	  				console.log(gradeData.accessManagement)
    	  			}
    	    		//if(gradeData.addClient==1 || gradeData.showClient==1){
    	    		//	document.getElementById('client-target-id').style.display='block';
    	    		//}if(gradeData.addProject==1 || gradeData.showProject==1 || gradeData.addOffer==1 || gradeData.quotationPerOffers==1 || gradeData.finalQuotation==1){
    	    		//	document.getElementById('project-target-id').style.display='block';
    	    		//}
    	    		//if(gradeData.generateIndent==1 || gradeData.indentList==1 || gradeData.approvalePending==1){
    	    		//	document.getElementById('indent-target-id').style.display='block';
    	    		//}if(gradeData.generatePo==1 || gradeData.poList==1){
    	    		//	document.getElementById('purchase-target-id').style.display='block';
    	    		//}
    	    		//if(gradeData.generateGrn==1 || gradeData.grnList==1){
    	    		//	document.getElementById('grn-target-id').style.display='block';
    	    		//}
    	    		//if(gradeData.addStock==1 || gradeData.stockList==1){
    	    		//	document.getElementById('stock-target-id').style.display='block';
    	    		//}
    	    		if(gradeData.addClient==1){
    	  				document.getElementById('addclient-target-id').style.display='block';
    	  			}if(gradeData.addProject==1){
    	  				document.getElementById('addproject-target-id').style.display='block';
    	  			}if(gradeData.addDrawing==1){
    	  				document.getElementById('adddrawing-target-id').style.display='block';
    	  			}if(gradeData.generatePo==1){
    	  				document.getElementById('generatepo-target-id').style.display='block';
    	  			}if(gradeData.finalQuotation==1){
    	  				document.getElementById('finalquotation-target-id').style.display='block';
    	  			}if(gradeData.generateGrn==1){
    	  				document.getElementById('generategrn-target-id').style.display='block';
    	  			}if(gradeData.generateIndent==1){
    	  				document.getElementById('generateindent-target-id').style.display='block';
    	  			}if(gradeData.grnList==1){
    	  				document.getElementById('grnlist-target-id').style.display='block';
    	  			}if(gradeData.indentList==1){
    	  				document.getElementById('indentlist-target-id').style.display='block';
    	  			}if(gradeData.poList==1){
    	  				document.getElementById('polist-target-id').style.display='block';
    	  			}if(gradeData.quotationPerOffers==1){
    	  				document.getElementById('quotationperoffer-target-id').style.display='block';
    	  			}if(gradeData.drawingList==1){
    	  				document.getElementById('drawinglist-target-id').style.display='block';
    	  			}if(gradeData.orderList==1){
    	  				document.getElementById('orderlist-target-id').style.display='block';
    	  			}if(gradeData.showFinalQuotation==1){
    	  				document.getElementById('finalquotationlist-target-id').style.display='block';
    	  			}if(gradeData.showQuotationPerOffer==1){
    	  				document.getElementById('quotationperofferlist-target-id').style.display='block';
    	  			}if(gradeData.addNewStock==1){
    	  				document.getElementById('addnewstock-target-id').style.display='block';
    	  			}if(gradeData.stockList==1){
    	  				document.getElementById('stocklist-target-id').style.display='block';
    	  			}if(gradeData.addOffer==1){
    	  				document.getElementById('addoffer-target-id').style.display='block';
    	  			}if(gradeData.addOrder==1){
    	  				document.getElementById('addorder-target-id').style.display='block';
    	  			}if(gradeData.issueNoteList==1){
    	  				document.getElementById('issuenotelist-target-id').style.display='block';
    	  			}if(gradeData.generateIssueNote==1){
    	  				document.getElementById('generateissuenote-target-id').style.display='block';
    	  			}if(gradeData.addGeneralStore==1){
    	  				document.getElementById('addgeneralstore-target-id').style.display='block';
    	  			}if(gradeData.projectStatus==1){
    	  				document.getElementById('projectstatus-target-id').style.display='block';
    	  			}if(gradeData.addVendor==1){
    	  				document.getElementById('addvendor-target-id').style.display='block';
    	  			}if(gradeData.vendorList==1){
    	  				document.getElementById('vendorlist-target-id').style.display='block';
    	  			}
    	  			if(gradeData.showLogs==1){
    	  				document.getElementById('showlogs-target-id').style.display='block';
    	  			}
    	  			if(gradeData.addUser==1){
    	  				document.getElementById('adduser-target-id').style.display='block';
    	  			}if(gradeData.addGraph==1){
    	  				document.getElementById('addgraph-target-id').style.display='block';
    	  			}if(gradeData.showOrder==1){
    	  				document.getElementById('showorder-target-id').style.display='block';
    	  			}
    	  		}
 	}
			xhr.send();
	}