window.onload = function(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet',true);
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data)
			appendLiveProjects(Data)
  		}
	}
	
	xhr.send();
		
}

function appendLiveProjects(data){
	
	var table = document.getElementById("MyTable");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");

		newRow.innerHTML = `
							<td id="${i + 1}">${i + 1}</td>
                            <td><a id="ClientId${i + 1}"></a><br></td>
                             <td><a id="projectId${i + 1}"></a><br></td>         
                             <td class="project_progress">               
                                    <div class="progress progress-sm">
                                         <div class="progress-bar bg-green" role="progressbar" aria-valuenow="77" aria-valuemin="0" aria-valuemax="100" style="width: 77%"></div>
                                    </div>    
                                    <small>
                                         77% Complete
                                    </small>        
                             </td>               
                             <td class="project-state">
                                    <span class="badge badge-success">Live</span>
                             </td>
                              <td class="project-actions text-right">          
                                 <a class="btn btn-primary btn-sm" href="#">
                                    <i class="fas fa-folder"></i>
                                           View
                                  </a>                 
                           </td>
    		`;
		table.appendChild(newRow);

		document.getElementById(`ClientId${i + 1}`).innerHTML = data[i].clientName;

		document.getElementById(`projectId${i + 1}`).innerHTML = data[i].projectName;

		}
		//getListSideBar();
		getUserId()
}
var userId;
function getUserId(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet',true);
	xhr.onload = function() {
  		if (xhr.status == 200) {
    		userId = JSON.parse(xhr.responseText);
    		console.log(userId)
  		}
	}
	
	xhr.send(JSON.stringify({Abcd:"ABCD"}));
	//getListSideBar()
}
function getListSideBar(){
	var gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/ShowManagementAccess?change=2&userId=11',true);
    		xhr.setRequestHeader('Content-type', 'application/json');
    		xhr.onload = function() {
    	  		if (xhr.status == 200) {
    	    		gradeData = JSON.parse(xhr.responseText);
    	    		console.log(gradeData)
    	    		if(gradeData.accessManagement==1){
    	  				document.getElementById('accessmanagement-target-id').style.display='block';
    	  			}
    	    		if(gradeData.addClient==1 || gradeData.showClient==1){
    	    			document.getElementById('client-target-id').style.display='block';
    	    		}if(gradeData.addProject==1 || gradeData.showProject==1 || gradeData.addOffer==1 || gradeData.quotationPerOffers==1 || gradeData.finalQuotation==1){
    	    			document.getElementById('project-target-id').style.display='block';
    	    		}
    	    		if(gradeData.generateIndent==1 || gradeData.indentList==1 || gradeData.approvalePending==1){
    	    			document.getElementById('indent-target-id').style.display='block';
    	    		}if(gradeData.generatePo==1 || gradeData.poList==1){
    	    			document.getElementById('purchase-target-id').style.display='block';
    	    		}
    	    		if(gradeData.generateGrn==1 || gradeData.grnList==1){
    	    			document.getElementById('grn-target-id').style.display='block';
    	    		}
    	    		if(gradeData.addStock==1 || gradeData.stockList==1){
    	    			document.getElementById('stock-target-id').style.display='block';
    	    		}
    	    		if(gradeData.sellIssuedList==1 || gradeData.sellItemList==1){
    	    			document.getElementById('sellmanagement-target-id').style.display='block';
    	    		}
    	    		if(gradeData.addClient==1){
    	  				document.getElementById('addclient-target-id').style.display='block';
    	  			}if(gradeData.showClient==1){
    	  				document.getElementById('showclient-target-id').style.display='block';
    	  			}if(gradeData.addProject==1){
    	  				document.getElementById('addproject-target-id').style.display='block';
    	  			}if(gradeData.showProject==1){
    	  				document.getElementById('showproject-target-id').style.display='block';
    	  			}if(gradeData.approvalPending==1){
    	  				document.getElementById('approvalpending-target-id').style.display='block';
    	  			}if(gradeData.generatePo==1){
    	  				document.getElementById('generatepo-target-id').style.display='block';
    	  			}if(gradeData.finalQuotation==1){
    	  				document.getElementById('finalquotation-target-id').style.display='block';
    	  			}if(gradeData.generateGrn==1){
    	  				document.getElementById('generategrn-target-id').style.display='block';
    	  			}if(gradeData.generateIndent==1){
    	  				document.getElementById('generateindent-target-id').style.display='block';
    	  			}if(gradeData.grnList==1){
    	  				document.getElementById('grnList-target-id').style.display='block';
    	  			}if(gradeData.indentList==1){
    	  				document.getElementById('indentlist-target-id').style.display='block';
    	  			}if(gradeData.poList==1){
    	  				document.getElementById('polist-target-id').style.display='block';
    	  			}if(gradeData.quotationPerOffers==1){
    	  				document.getElementById('quotationperoffers-target-id').style.display='block';
    	  			}if(gradeData.sellIssuedList==1){
    	  				document.getElementById('sellissuedlist-target-id').style.display='block';
    	  			}if(gradeData.sellItemList==1){
    	  				document.getElementById('sellitemlist-target-id').style.display='block';
    	  			}if(gradeData.showFinalQuotation==1){
    	  				document.getElementById('showfinalquotation-target-id').style.display='block';
    	  			}if(gradeData.showOffer==1){
    	  				document.getElementById('showoffer-target-id').style.display='block';
    	  			}if(gradeData.showQuotationPerOffer==1){
    	  				document.getElementById('showquotationperoffer-target-id').style.display='block';
    	  			}if(gradeData.showStockList==1){
    	  				document.getElementById('showstocklist-target-id').style.display='block';
    	  			}if(gradeData.stockList==1){
    	  				document.getElementById('stocklist-target-id').style.display='block';
    	  			}if(gradeData.addOffer==1){
    	  				document.getElementById('addoffer-target-id').style.display='block';
    	  			}if(gradeData.addStock==1){
    	  				document.getElementById('addstock-target-id').style.display='block';
    	  			}
    	  		}
    		}
			xhr.send();
}
