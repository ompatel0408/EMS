window.onload = function getProjects(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://192.168.1.130:8080/EMS2/EMSProductionServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendProjects(Data)
  		}
	}
  	var data = { token:"Projects" }
	xhr.send(JSON.stringify(data));	
}


function appendProjects(projects){

	var projectsSelect = document.getElementById("ProjectId1");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select category"selected>Select Project</option>`;
	for(let i=0; i<projects.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}
