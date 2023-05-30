window.onload = function()
{
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSAddMachineInMntServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data);
    		appendMachine(Data);
  		}
	}
  	var data = { token:"machines" }
	xhr.send(JSON.stringify(data));	
}

function appendMachine(projects)
{
	var projectsSelect = document.getElementById("machineId");
	projectsSelect.innerHTML = `<option value="select Machines"selected>Select Machines</option>`;
	for (let i = 0; i < projects.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById("machineId").addEventListener("change", ()=>{
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSAddMachineInMntServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data);
    		appendModel(Data);
  		}
	}
  	var data = { token:"modelNo", data : document.getElementById("machineId").value }
	xhr.send(JSON.stringify(data));
});

function appendModel(projects)
{
	var projectsSelect = document.getElementById("modelId");
	projectsSelect.innerHTML = `<option value="select Model Number"selected>Select Model Number</option>`;
	for (let i = 0; i < projects.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}

