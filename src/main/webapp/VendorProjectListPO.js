window.onload=function()
{
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSPurchaseServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data)
    		appendVendors(Data)
  		}
	}
  	var data = { Token:"Vendors" }
	xhr.send(JSON.stringify(data));	
}

function appendVendors(Clients){

	var ClientsSelect = document.getElementById("vendorList");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select Vendors"selected>Select Vendors</option>`;
	for(let i=0; i<Clients.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = Clients[i];
		createdAt.innerHTML = Clients[i];
		ClientsSelect.appendChild(createdAt);
	}
}
document.getElementById("vendorList").addEventListener('change',()=>{
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS/EMSPurchaseServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendProjects(Data)
  		}
	}
	xhr.send();	
})
function appendProjects(projects)
{
var projectsSelect = document.getElementById("projectId");
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


function getPrice()
{
	console.log("Yeah")
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/PrintPo',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data)
    		document.getElementById('transportId').value=Data
  		}
	}
	xhr.send(JSON.stringify({Token:"transportPrice",project:document.getElementById('projectId').value,vendor:document.getElementById('vendorList').value}));	
}



function setVendorValue(){
	document.getElementById('vendorName').value = document.getElementById('vendorList').value;
}


document.getElementById('print').addEventListener('click',()=>{
	var vendorSelect = document.getElementById("vendorList").value;
	var projectsSelect = document.getElementById("projectId").value;
	var transport = document.getElementById("transportId").value;
	window.location.href='PrintPo?vendor='+vendorSelect+'&project='+projectsSelect+'&transport='+transport
})