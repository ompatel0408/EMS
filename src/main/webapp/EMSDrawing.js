window.onload = function getProject1(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSProductionServlet',true);
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
function appendProjects(Data){
	var ClientsSelect = document.getElementById("projectId");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select Projects"selected>Select Projects</option>`;
	for (let i = 0; i < Data.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = Data[i];
		createdAt.innerHTML = Data[i];
		ClientsSelect.appendChild(createdAt);
	}
}
function getOffers() {
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSDrawingServlet', true);
	xhr.onreadystatechange = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendOffer(Data)
		}
	}
	xhr.send(JSON.stringify({ ProjectId: document.getElementById('projectId').value, token: "offer" }));
}
function getSubItem() {
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSDrawingServlet', true);
	xhr.onreadystatechange = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendSubItem(Data)
		}
	}
	xhr.send(JSON.stringify({ itemcode: document.getElementById('OfferId').value, token: 'subItem' }));
}
function appendSubItem(Clients){
	var ClientsSelect = document.getElementById("SubItem");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select SubItems"selected>Select SubItems</option>`;
	for (let i = 0; i < Clients.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = Clients[i].subitemcode;
		createdAt.innerHTML = Clients[i].subitemcode;
		ClientsSelect.appendChild(createdAt);
	}
}
function appendOffer(Clients){
	var ClientsSelect = document.getElementById("OfferId");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select offers"selected>Select offers</option>`;
	for (let i = 0; i < Clients.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = Clients[i];
		createdAt.innerHTML = Clients[i];
		ClientsSelect.appendChild(createdAt);
	}
}
