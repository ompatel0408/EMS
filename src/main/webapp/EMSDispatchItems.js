window.onload = function getClients1(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSItemServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data)
    		appendClients(Data)
  		}
	}
  	var data = {token:"Projects"}
	xhr.send(JSON.stringify(data));	
}

function appendClients(Clients)
{
	var ClientsSelect = document.getElementById("clientId");
	console.log(ClientsSelect)
	ClientsSelect.innerHTML = `<option value="select Clients"selected>Select Clients</option>`;
	for(let i=0; i<Clients.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = Clients[i];
		createdAt.innerHTML = Clients[i];
		ClientsSelect.appendChild(createdAt);
	}
}

document.getElementById("clientId").addEventListener("change", () => {
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open("PUT","http://192.168.1.9:8080/EMS/EMSDispatchItemServlet",true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() 
	{
  		if (xhr.status === 200) 
  		{
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data);
    		appendOffer(Data);
  		}
	}
	var data = { token:document.getElementById("clientId").value }
	xhr.send(JSON.stringify(data));	
});

function appendOffer(data)
{
	var ClientsSelect = document.getElementById("offerId");
	console.log(data)
	ClientsSelect.innerHTML = `<option value="select Offer"selected>Select Offer</option>`;
	for(let i=0; i<data.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = data[i];
		createdAt.innerHTML = data[i];
		ClientsSelect.appendChild(createdAt);
	}
	enableItems();
}

function enableItems()
{
	document.getElementById('vehicleNo').disabled = false;
	document.getElementById('travelCom').disabled = false;
	document.getElementById('checkBy').disabled = false;
	document.getElementById('offerId').disabled = false;
	document.getElementById('travelComOwnr').disabled = false;
	document.getElementById('destAddress').disabled = false;
	document.getElementById('submitBtn').disabled = false;
}