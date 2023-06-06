function appendInputs(value) {
	if (value < 20) {
		document.getElementById("appendHere").innerHTML = "";
		let target = document.getElementById("appendHere");
		for (let i = 1; i <= value; i++) {
			let ctag = document.createElement('div');
			ctag.innerHTML = `<div class="form-group">
 <label for="item-id">Enter Phase Count ${i} </label> <input
 type="text" class="form-control" name="phase${i}" id="phase${i}"
 placeholder="Enter Phase name">`;
			target.appendChild(ctag)
		}
	}
	else {
		document.getElementById("appendHere").innerHTML = "max input is 20";
	}
}
window.onload = function getProject1(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSProductionServlet',true);
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
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSDrawingServlet', true);
	xhr.onreadystatechange = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendOffer(Data)
		}
	}
	xhr.send(JSON.stringify({ ProjectId: document.getElementById('projectId').value, token: "offer" }));
}
function appendOffer(Clients){
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