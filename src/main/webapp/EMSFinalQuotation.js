window.onload = function getClients1(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSFinalQuotationServlet',true);
	
	xhr.onreadystatechange = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendClient(Data)
  		}
	}
	xhr.send();	
}

function appendClient(clients){

	var clientsSelect = document.getElementById("ClientId1");
	console.log(clientsSelect)
	clientsSelect.innerHTML = `<option value="select Clients"selected>Select Clients</option>`;
	for(let i=0; i<clients.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = clients[i];
		createdAt.innerHTML = clients[i];
		clientsSelect.appendChild(createdAt);
	}
}

function getQuotation(){
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSFinalQuotationServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		console.log(Data)
    		appendQuotation(Data)
  		}
	}
  	var data = {ClientId:document.getElementById('ClientId1').value,token:"Clients"}
	xhr.send(JSON.stringify(data));	
}

function appendQuotation(Data){
	
	console.log(Data);
	document.getElementById('QuotationAmount').value = Data[0].quotationAmount;
	document.getElementById('TotalAmount').value = document.getElementById('QuotationAmount').value
	document.getElementById('QuotationQuantity').value = Data[0].quantity;
}
function calculateDiscountAmount(){
	console.log(parseFloat(document.getElementById('QuotationAmount').value) * (parseFloat(document.getElementById('discountPercentage').value) / 100));
	document.getElementById('discountAmount').disabled = true;
	document.getElementById('discountAmount').value = parseFloat(document.getElementById('QuotationAmount').value) * (parseFloat(document.getElementById('discountPercentage').value) / 100);
	document.getElementById('TotalAmount').value = parseFloat(document.getElementById('QuotationAmount').value) - (parseFloat(document.getElementById('QuotationAmount').value) * (parseFloat(document.getElementById('discountPercentage').value) / 100));
}

function calculateDiscountPercentage(){
 	document.getElementById('discountPercentage').disabled = true;
 	
	document.getElementById('discountPercentage').value = (parseFloat(document.getElementById('discountAmount').value) / parseFloat(document.getElementById('QuotationAmount').value)) * 100;
	document.getElementById('TotalAmount').value = parseFloat(document.getElementById('QuotationAmount').value)  - parseFloat(document.getElementById('discountAmount').value)
}

var gstAmount;
var count = 0;
var originalAmount;
function clickOfRadioButton(){
	count++;
	alert("hello")
	if(count <=1){
		alert("firstTime")
		originalAmount = parseFloat(document.getElementById('TotalAmount').value);
	}
	var radioButton = document.getElementsByName('customRadio');
	for(var i=0;i < radioButton.length;i++){
		if(radioButton[i].checked){
			gstAmount = originalAmount * (parseFloat(radioButton[i].value) / 100) 
			break;
		}
	}
	
	document.getElementById('TotalAmount').value = gstAmount +originalAmount;
	
}

function submitForm(){
	
	var json = {
		clientId:document.getElementById('ClientId1').value,
		finalDelivaryDate:document.getElementById('finalDeliveryDate').value,
		Quantity:document.getElementById('QuotationQuantity').value,
		remark:document.getElementById('FinalQuotationRemarks').value,
		discountPercentage:document.getElementById('discountPercentage').value,
		discountAmount:document.getElementById('discountAmount').value,
		TotalAmount:document.getElementById('TotalAmount').value
	}
	
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSFinalQuotationServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			window.location.href = "EMSItem.jsp"
		}
	}
	// send the request
	xhr.send(JSON.stringify(json));
	count = 0;
	console.log("count :"+count)
	
}
window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie1=".concat(JSON.stringify(data));
  event.returnValue = "Are you sure you want to leave this page?"
});




