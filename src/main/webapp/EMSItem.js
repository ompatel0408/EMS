document.getElementById('ClientId1').addEventListener('change', () => {

	document.getElementById('ClientId1').disabled = true;
	document.getElementById('ItemName').disabled = false;
	document.getElementById('TagNo').disabled = false;
	document.getElementById('Remarks').disabled = false;
	document.getElementById('Quantity').disabled = false;
	document.getElementById('DelivaryDate').disabled = false;
	document.querySelector('#AddItemBtn').classList.remove('disabled');
});
var EditId;
var deleteId;
var data = []
var servletData = []
var response;
var myMap = new Map();
var myArray = []
function submitForm() {

	console.log(data)
	console.log(EditId)
	var js1 = data.filter(x=>x.count == EditId)
	
	js1[0].tagNo = document.getElementById('TagNo').value;
	js1[0].quantity = document.getElementById('Quantity').value;
	js1[0].ItemName = document.getElementById('ItemName').value
	js1[0].delivaryDate = document.getElementById('DelivaryDate').value
	
	var json =
	{
		ClientId: document.getElementById('ClientId1').value,
		ItemName: document.getElementById('ItemName').value,
		tagNo: document.getElementById('TagNo').value,
		remarks: document.getElementById('Remarks').value,
		quantity: document.getElementById('Quantity').value,
		delivaryDate: document.getElementById('DelivaryDate').value,
		TotalPrice: JSON.stringify(parseInt(document.getElementById('Quantity').value) * 0),
		offerCode:document.getElementById('offerCode').value
	}
	servletData.push(json);
	appendMap();
}

function appendMap(){
	
	document.getElementById("MyTable3").innerHTML = "";
	var table = document.getElementById("MyTable3");
	console.log(myArray)
	console.log(myMap)
	for (var i = 0; i < myArray.length; i++) {
		var newRow = document.createElement("tr");
		newRow.setAttribute('id',`tr${myArray[i]}`)
		newRow.innerHTML = `
    		<td id="${myArray[i]}">${myArray[i]}</td>
    		<td><a id="ItemName${myArray[i]}">${myMap.get(myArray[i]).ItemName}</a> <br></td>
    		<td><a id="Quantity${myArray[i]}">${myMap.get(myArray[i]).quantity}</a> <br></td>
    		<td><a id="TagNo${myArray[i]}">${myMap.get(myArray[i]).tagNo}</a> <br></td>
    		<td><a id="DelivaryDate${myArray[i]}">${myMap.get(myArray[i]).delivaryDate}</a> <br></td>
    		 <td class="project-actions text-right">
                  <button type="button" class="btn btn-success btn-sm" onclick = "updateField(this.id)" id="Edit${myArray[i]}">
                        <i class="fas fa-pencil-alt"></i>
                  </button>
                  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" onclick="deleteItem(this.id)" id="Delete${myArray[i]} onchange= "enableUpdate()"">
                        <i class="fas fa-trash"></i>
                  </button>
              </td>
    		`;
		table.appendChild(newRow);
	}	
}

window.onload = function getClients1(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSItemServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data)
    		appendClients(Data)
  		}
	}
  	var data = { token:"Projects" }
	xhr.send(JSON.stringify(data));	
}


function appendClients(Clients){

	var ClientsSelect = document.getElementById("ClientId1");
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

function XHRRequestForItem() {

	document.getElementById('ClientId1').disabled = false;
	document.getElementById('ItemName').disabled = true;
	document.getElementById('TagNo').disabled = true;
	document.getElementById('Remarks').disabled = true;
	document.getElementById('Quantity').disabled = true;
	document.getElementById('DelivaryDate').disabled = true;
	document.querySelector('#AddItemBtn').classList.add('disabled');
	document.querySelector('#ProcessId').classList.add('disabled')
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS/EMSItemServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			response = xhr.responseText;
			XHRRequestForQuotationId()
		}
	}
	// send the request
	xhr.send(JSON.stringify(servletData));
}

function XHRRequestForQuotationId()
{
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSItemServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var res = JSON.parse(xhr.responseText);
			localStorage.setItem('QuotationId',res.quotationId)
			localStorage.setItem('clientId',res.clientId)
			localStorage.setItem('Token','true')
			window.location.href = "ProjectServlet?projectId=0&update=notupdate"
		}
	}
	// send the request
	xhr.send(JSON.stringify({ClientName:document.getElementById('ClientId1').value,token:"QuotationId"}));	
}




function GetOfferData(){
	
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSOffersServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			response = JSON.parse(xhr.responseText);
			appendJsonData(response)			
			
		}
	}
	// send the request
	xhr.send(JSON.stringify({ClientName:document.getElementById('ClientId1').value,Token:"Offers"}));
}


function appendJsonData(response){
var counter = 0;	
	console.log(response)
	
	for(let i=0;i<response.length;i++){
		
		var json = {
			ItemName: response[i].offerName,
			quantity:response[i].quantity,
			tagNo:0,
			delivaryDate:0,
			remarks: response[i].remarks,
			count :++counter,
			offerCode:response[i].offerCode			
		}
		data.push(json)
	}
	
	appendFunc()
}

var maincounter = 1;
var updateJson = []
var deleteValue = "";
var flag = true;
function deleteItem(deleteId) {
	deleteValue = deleteId;
	
}
document.getElementById("deleteClicked").addEventListener("click", () => {
	var deleteId1 = parseInt(deleteValue.substring(6));
	console.log(deleteId1)
	console.log(data)
	
	var js1 = data.filter(x=>x.count == deleteId1)
	
	document.getElementById(`tr${js1[0].count}`).remove();
	
	data = data.filter(x=>x.count !== deleteId1)
	myArray = myArray.filter(x=>x !== deleteId1)
});

let editValue = "";

function updateField(editId) {
	
	editValue = editId;
	
	EditId = parseInt(editValue.substring(4));
	
	console.log(data)
	console.log(EditId)
	var js1 = data.filter(x=>x.count == EditId)
	document.getElementById('Quantity').value =js1[0].quantity;
	document.getElementById('ItemName').value = js1[0].ItemName;
	document.getElementById('DelivaryDate').value = js1[0].delivaryDate,
	document.getElementById('Remarks').value = js1[0].remarks;
	document.getElementById('offerCode').value = js1[0].offerCode;
}

function appendFunc() {
	
	document.getElementById("MyTable3").innerHTML = "";
	var table = document.getElementById("MyTable3");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.setAttribute('id',`tr${i+1}`)
		myMap.set(i+1,data[i])
		myArray.push(i+1);
		newRow.innerHTML = `
    		<td id="${i + 1}">${i + 1}</td>
    		<td><a id="ItemName${i + 1}">${data[i].ItemName}</a> <br></td>
    		<td><a id="Quantity${i + 1}">${data[i].quantity}</a> <br></td>
    		<td><a id="TagNo${i + 1}">${data[i].tagNo}</a> <br></td>
    		<td><a id="DelivaryDate${i + 1}">${data[i].delivaryDate}</a> <br></td>
    		 <td class="project-actions text-right">
                  <button type="button" class="btn btn-success btn-sm" onclick = "updateField(this.id)" id="Edit${i+1}">
                        <i class="fas fa-pencil-alt"></i>
                  </button>
                  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" onclick="deleteItem(this.id)" id="Delete${i+1}">
                        <i class="fas fa-trash"></i>
                  </button>
              </td>
    		`;
		table.appendChild(newRow);
	}
	if (document.getElementById('MyTable3').hasChildNodes != null) {
		document.querySelector('#ProcessId').classList.remove('disabled');
	}
}

function enableUpdate(){
	
}

window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie2=".concat(JSON.stringify(data));
  event.returnValue = "Are you sure you want to leave this page?"
});













