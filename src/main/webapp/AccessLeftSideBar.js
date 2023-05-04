document.addEventListener("DOMContentLoaded", () => {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/EMS/EMSDirectorsDashboardServlet', true);
	xhr.onload = function() {
		if (xhr.status == 200) {
			var userId = JSON.parse(xhr.responseText);
			getListSideBar(userId);
		}
	}
	xhr.send(JSON.stringify({ Abcd: "ABCD" }));
	//getProjectStatus();
}); 	

function getListSideBar(userId){
	
	var gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', `http://localhost:8080/EMS/ShowManagementAccess?change=2&userId=${userId}`,true);
    		xhr.setRequestHeader('Content-type', 'application/json');
    		xhr.onload = function() {
    	  		if (xhr.status == 200) {
    	    		gradeData = JSON.parse(xhr.responseText);
    	    		
    	    		console.log(gradeData)
    	    		if(gradeData.accessManagement==1){
    	  				document.getElementById('accessmanagement-target-id').style.display='block';
    	  				console.log(gradeData.accessManagement)
    	  			}
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
    	  			}if(gradeData.addVendor==1){
    	  				document.getElementById('addvendor-target-id').style.display='block';
    	  			}if(gradeData.vendorList==1){
    	  				document.getElementById('vendorlist-target-id').style.display='block';
    	  			}if(gradeData.showLogs==1){
    	  				document.getElementById('showlogs-target-id').style.display='block';
    	  			}if(gradeData.addUser==1){
    	  				document.getElementById('adduser-target-id').style.display='block';
    	  			}if(gradeData.addGraph==1){
    	  				document.getElementById('addgraph-target-id').style.display='block';
    	  			}if(gradeData.showOrder==1){
    	  				document.getElementById('showorder-target-id').style.display='block';
    	  			}if(gradeData.dpr==1){
    	  				document.getElementById('dpr-id').style.display='block';
    	  			}if(gradeData.dprList==1){
    	  				document.getElementById('dprList-id').style.display='block';
    	  			}if(gradeData.drawingRevision==1){
    	  				document.getElementById('drawingRevision-id').style.display='block';
    	  			}if(gradeData.phase==1){
    	  				document.getElementById('phase-id').style.display='block';
    	  			}if(gradeData.phaseUpdate==1){
    	  				document.getElementById('phaseUpdate-id').style.display='block';
    	  			}if(gradeData.addMachine==1){
    	  				document.getElementById('addMachine-id').style.display='block';
    	  			}if(gradeData.showMachine==1){
    	  				document.getElementById('showMachine-id').style.display='block';
    	  			}if(gradeData.giveMntMachine==1){
    	  				document.getElementById('giveMntMcn-id').style.display='block';
    	  			}if(gradeData.receiveMntMachine==1){
    	  				document.getElementById('getMntMcn-id').style.display='block';
    	  			}if(gradeData.gateOutWrd==1){
    	  				document.getElementById('gatePassOut-id').style.display='block';
    	  			}if(gradeData.gateInWrd==1){
    	  				document.getElementById('gatePassIn-id').style.display='block';
    	  			}if(gradeData.gateList==1){
    	  				document.getElementById('gatePassList-id').style.display='block';
    	  			}if(gradeData.poPrint==1){
    	  				document.getElementById('poPrint-id').style.display='block';
    	  			}if(gradeData.approvelPending==1){
    	  				document.getElementById('grnApprovelPen-id').style.display='block';
    	  			}if(gradeData.approvelPenList==1){
    	  				document.getElementById('grnPeningOrder-id').style.display='block';
    	  			}
    	  			
    	  		}
 	}
			xhr.send();
	}