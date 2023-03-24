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
	
}