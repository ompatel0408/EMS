window.onload = function getProjects(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSFinalQuotationServlet',true);
	
	xhr.onreadystatechange = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendProjects(Data)
  		}
	}
	xhr.send();	
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

function getQuotation(){
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSFinalQuotationServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendQuotation(Data)
  		}
	}
  	var data = {projectId:document.getElementById('ProjectId1').value,token:"Project"}
	xhr.send(JSON.stringify(data));	
}

function appendQuotation(Data){
	
	console.log(Data);
	document.getElementById('QuotationAmount').value = Data[0].quotationAmount;
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
		projectId:document.getElementById('ProjectId1').value,
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
			console.log(response);
		}
	}
	// send the request
	xhr.send(JSON.stringify(json));
	count = 0;
	console.log("count :"+count)
	
}

