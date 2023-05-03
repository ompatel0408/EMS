window.onload = function getProject1() {
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS/EMSDrawingServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendProjects(Data)
		}
	}
	xhr.send();
}
function appendProjects(Data) {
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
function appendOffer(Clients) {
	var ClientsSelect = document.getElementById("ItemCode");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select offers"selected>Select Items</option>`;
	for (let i = 0; i < Clients.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = Clients[i];
		createdAt.innerHTML = Clients[i];
		ClientsSelect.appendChild(createdAt);
	}
}
function getSubItems() {
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSDrawingServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendSubItems(Data)
		}
	}
	xhr.send(JSON.stringify({ itemcode: document.getElementById('ItemCode').value, token: "subItem" }));
}
function appendSubItems(Clients) {
	var ClientsSelect = document.getElementById("subItemId");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select offers"selected>Select Sub Items</option>`;
	for (let i = 0; i < Clients.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = Clients[i].subitemcode;
		createdAt.innerHTML = Clients[i].subitemcode;
		ClientsSelect.appendChild(createdAt);
	}
}
function getPhase() {
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/DailyProgressReportServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendPhase(Data)
		}
	}
	xhr.send(JSON.stringify({ projectId: document.getElementById('projectId').value, itemcode: document.getElementById('ItemCode').value, token: "phase" }));
}
function appendPhase(Clients) {
	var ClientsSelect = document.getElementById("phaseId");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select offers"selected>Select Phase</option>`;
	for (let i = 0; i < Clients.length; i++) {
		let createdAt = document.createElement("option");
		console.log(Clients[i])
		createdAt.value = Clients[i];
		createdAt.innerHTML = Clients[i];
		ClientsSelect.appendChild(createdAt);
	}
}
