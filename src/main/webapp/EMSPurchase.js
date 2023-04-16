var EditId;
var givenQuote = 0
var totalAmountAppended = 0
var progressBar = document.querySelector('.progress-bar');
var progress = 0;
var percent = 0
var data = [];
var servletData = []
var Loss = false;
var myMap = new Map();
var myArray = []


var exampleElement = document.getElementById('success');
document.getElementById('progressDescOfAvailable').innerHTML="Add Project and Items to see the Magic"
window.onload = function getProjects1() {
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS/EMSPurchaseServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendProjects(Data)
		}
	}
	xhr.send();
}

function appendProjects(projects) {

	var projectsSelect = document.getElementById("ProjectId1");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select category"selected>Select Project</option>`;
	for (let i = 0; i < projects.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById('ProjectId1').addEventListener('change', () => {
	console.log('quoate')
	document.getElementById('ProjectId1').disabled = true;
	document.getElementById('category-id').disabled = true;
	document.getElementById('grade-id').disabled = true;
	document.getElementById('size-id').disabled = true;
	document.getElementById('orderQuan-id').disabled = false;
	document.getElementById('unit-id').disabled = false;
	document.getElementById('vendor-id').disabled = false;
	document.getElementById('rate-id').disabled = false;
	document.getElementById('remark-Id').disabled = false;
	document.getElementById('DiscountPercentage').disabled = false;
	document.getElementById('DiscountAmount').disabled = false;
	document.getElementById('Nil').disabled = false;
	document.getElementById('FivePer').disabled = false;
	document.getElementById('TwelvePer').disabled = false;
	document.getElementById('EighteenPer').disabled = false;
	document.getElementById('TwentyEightPer').disabled = false;
	document.getElementById('ThreePer').disabled = false;
	document.querySelector('#add-store').classList.remove('disabled');
});



function ProjectChange() {

	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSPurchaseServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendVendors(Data)
		}
	}
	xhr.send(JSON.stringify({ Token: "Vendors" }));

}


function appendVendors(projects) {

	var projectsSelect = document.getElementById("vendor-id");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select Vendor"selected>Select vendor</option>`;
	for (let i = 0; i < projects.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}


var response;
function getIndentList() {

	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSPurchaseServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			response = JSON.parse(xhr.responseText);
			console.log(response)
			appendJsonData(response)
		}
	}
	
	// send the request
	xhr.send(JSON.stringify({projectId: document.getElementById('ProjectId1').value, Token: "Offers" }));
	
	let Data = ""
	let xhr2 = new XMLHttpRequest();
	xhr2.open('PUT', 'http://localhost:8080/EMS/EMSPurchaseServlet', true);
	xhr2.setRequestHeader('Content-type', 'application/json');
	xhr2.onreadystatechange = function() {
		if (xhr2.readyState === XMLHttpRequest.DONE && xhr2.status === 200) {
			Data = JSON.parse(xhr2.responseText);
			console.log(JSON.stringify(Data));
			givenQuote = Data
			document.getElementById('progressDesc').innerHTML = "Your Quoatation of Given Project is: " + (givenQuote)
		}
	};
	xhr2.send(JSON.stringify({ Token: "quotations", project: document.getElementById('ProjectId1').value }));
}

function appendJsonData(response) {
	
	var counter = 0;
	for (let i = 0; i < response.length; i++) {

		var json = {
			category: response[i].category,
			grade: response[i].grade,
			size: response[i].Size,
			TotalAmount: 0,
			vendorName: "_",
			count: ++counter,
			quantity: response[i].quantity
		}
		data.push(json)
		console.log(data)
	}

	appendFunc()
}
function appendFunc() {

	if (document.getElementById('MyTable16').hasChildNodes != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	}

	document.getElementById("MyTable16").innerHTML = "";
	var table = document.getElementById("MyTable16");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.setAttribute('id', `tr${i + 1}`);
		myMap.set(i+1,data[i])
		myArray.push(i+1);
		newRow.innerHTML = `
                              <td id="${i + 1}">${i + 1}</td>
                              <td><a id="category-${i + 1}">${data[i].category}</a> <br></td>
                              <td><a id="grade-${i + 1}"> ${data[i].grade} </a> <br></td>
                              <td><a id="size-${i + 1}"> ${data[i].size} </a> <br></td>
                              <td><a id="TotalAmount-${i + 1}"> ${data[i].TotalAmount} </a> <br></td>
                              <td><a id="vendor-${i + 1}"> ${data[i].vendorName} </a> <br></td>                       
                              <td class="project-actions text-right">
                              <button type="button" class="btn btn-success btn-sm" onclick = "updateField(this.id)" id="Edit${i + 1}">
                                      <i class="fas fa-pencil-alt"></i>
                              </button>
                              <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
                                      <i class="fas fa-trash"></i>
                              </button>
                              </td>`;
		table.appendChild(newRow);
	}
}



var deleteValue = "";
var flag = true;
function deleteItem(deleteId) {
	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {


	var deleteId1 = parseInt(deleteValue.substring(6));
	var js1 = data.filter(x=>x.count == deleteId1)
	
	document.getElementById(`tr${js1[0].count}`).remove();
	
	
	data = data.filter(x=>x.count !== deleteId1)
	myArray = myArray.filter(x=>x !== deleteId1)
	
	flag = false;
	totalAmountAppended-=parseFloat(js1[0].TotalAmount)
    percent=(totalAmountAppended*100)/givenQuote
    console.log(percent)
    progressBar.style.width = percent + '%';
    progressBar.setAttribute('aria-valuenow', percent);
    change()

});



let editValue = "";
function updateField(editId) {
	editValue = editId;
	
	
	EditId = parseInt(editValue.substring(4));
	
	console.log(data)	
	
	var js1 = data.filter(x => x.count == EditId)
	
	
	document.getElementById('orderQuan-id').value = js1[0].quantity;
	document.getElementById('category-id').value = js1[0].category,
	document.getElementById('grade-id').value = js1[0].grade;
	document.getElementById('size-id').value = js1[0].size;
	
}
function calculateAmount() {
	document.getElementById('amount-id').value = parseFloat(document.getElementById('rate-id').value) * parseFloat(document.getElementById('orderQuan-id').value);
}
function calculateDiscountAmount() {
	document.getElementById('DiscountAmount').disabled = true;
	document.getElementById('DiscountAmount').value = parseFloat(document.getElementById('amount-id').value) * (parseFloat(document.getElementById('DiscountPercentage').value) / 100);
	document.getElementById('Totalamount-id').value = parseFloat(document.getElementById('amount-id').value) - (parseFloat(document.getElementById('amount-id').value) * (parseFloat(document.getElementById('DiscountPercentage').value) / 100));
}

function calculateDiscountPercentage() {
	document.getElementById('DiscountPercentage').disabled = true;
	document.getElementById('DiscountPercentage').value = (parseFloat(document.getElementById('DiscountAmount').value) / parseFloat(document.getElementById('amount-id').value)) * 100;
	document.getElementById('Totalamount-id').value = parseFloat(document.getElementById('amount-id').value) - parseFloat(document.getElementById('DiscountAmount').value)
}



var gstAmount;
var gst;
var count = 0;
var originalAmount;
function clickOfRadioButton() {
	
		originalAmount = parseFloat(document.getElementById('Totalamount-id').value);
	
	console.log("originalAmount :"+originalAmount)
	var radioButton = document.getElementsByName('chks');
	for (var i = 0; i < radioButton.length; i++) {
		if (radioButton[i].checked) {
			gstAmount = originalAmount * (parseFloat(radioButton[i].value) / 100)
			gst = parseFloat(radioButton[i].value)
			break;
		}
	}
	console.log("GST :"+gst)
	console.log("GST Amount :"+gstAmount)
	console.log("Total Amount :"+(gstAmount + originalAmount))
	document.getElementById('Totalamount-id').value = gstAmount + originalAmount;
}

var submitData = []

function submitForm() {
	
	console.log("Edit Id :"+EditId)
	
	var js1 = data.filter(x => x.count == EditId)
	
	console.log(")))***********")
	console.log(js1)
	js1[0].TotalAmount = document.getElementById('Totalamount-id').value;
	js1[0].vendorName = document.getElementById('vendor-id').value;
	//appendFunc()
	
	var json =
	{
		ProjectId: document.getElementById('ProjectId1').value,
		category: document.getElementById('category-id').value,
		grade: document.getElementById('grade-id').value,
		size: document.getElementById('size-id').value,
		unit: document.getElementById('unit-id').value,
		orderQuan: document.getElementById('orderQuan-id').value,
		vendorName: document.getElementById('vendor-id').value,
		rate: document.getElementById('rate-id').value,
		discountAmount: document.getElementById('DiscountAmount').value,
		TotalAmount: document.getElementById('Totalamount-id').value,
		GST: gst,
		PaymentTerms:document.getElementById('PaymentTermsId').value,
		loss:Loss
	}

	submitData.push(json)
	console.log(submitData)
	appendMap();
	
	totalAmountAppended+=parseFloat(document.getElementById('Totalamount-id').value)
    percent=(totalAmountAppended*100)/givenQuote
    console.log(percent)
    progressBar.style.width = percent + '%';
    progressBar.setAttribute('aria-valuenow', percent);
    change()
}



function appendMap(){
	
	
	if (document.getElementById('MyTable16').hasChildNodes != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	}

	document.getElementById("MyTable16").innerHTML = "";
	var table = document.getElementById("MyTable16");
	for (var i = 0; i < myArray.length; i++) {
		var newRow = document.createElement("tr");
		newRow.setAttribute('id', `tr${myArray[i]}`);
		newRow.innerHTML = `
                              <td id="${myArray[i]}">${myArray[i]}</td>
                              <td><a id="category-${myArray[i]}">${myMap.get(myArray[i]).category}</a> <br></td>
                              <td><a id="grade-${myArray[i]}"> ${myMap.get(myArray[i]).grade} </a> <br></td>
                              <td><a id="size-${myArray[i]}"> ${myMap.get(myArray[i]).size} </a> <br></td>
                              <td><a id="TotalAmount-${myArray[i]}"> ${myMap.get(myArray[i]).TotalAmount} </a> <br></td>
                              <td><a id="vendor-${myArray[i]}"> ${myMap.get(myArray[i]).vendorName} </a> <br></td>                       
                              <td class="project-actions text-right">
                              <button type="button" class="btn btn-success btn-sm" onclick = "updateField(this.id)" id="Edit${myArray[i]}">
                                      <i class="fas fa-pencil-alt"></i>
                              </button>
                              <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" id="Delete${myArray[i]}" onclick="deleteItem(this.id)">
                                      <i class="fas fa-trash"></i>
                              </button>
                              </td>`;
		table.appendChild(newRow);
	}
}



function submitFormToServlet() {
	document.getElementById('ProjectId1').disabled = false;
	document.getElementById('category-id').disabled = true;
	document.getElementById('grade-id').disabled = true;
	document.getElementById('size-id').disabled = true;
	document.getElementById('orderQuan-id').disabled = true;
	document.getElementById('unit-id').disabled = true;
	document.getElementById('vendor-id').disabled = true;
	document.getElementById('rate-id').disabled = true;
	document.getElementById('remark-Id').disabled = true;
	document.getElementById('DiscountPercentage').disabled = true;
	document.getElementById('DiscountAmount').disabled = true;
	document.getElementById('Nil').disabled = true;
	document.getElementById('FivePer').disabled = true;
	document.getElementById('TwelvePer').disabled = true;
	document.getElementById('EighteenPer').disabled = true;
	document.getElementById('TwentyEightPer').disabled = true;
	document.getElementById('ThreePer').disabled = true;
	document.querySelector('#add-store').classList.add('disabled');
	document.querySelector('#processTo').classList.add('disabled');

	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS/EMSPurchaseServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response
	xhr.onload = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			console.log(response);
			window.location.href = "EMSPurchaseListServlet?delete=no";
		}
	}
	// send the request
	xhr.send(JSON.stringify(submitData));
}

function change()
{
	
if(percent >=0 && percent<80)
      	{
			  document.getElementById('profitAlert').innerHTML="Good to Go"
			  document.getElementById('profitAlert').style.color="white"
			  exampleElement.classList.remove('bg-gradient-warning');
			  exampleElement.classList.remove('bg-danger');
			  exampleElement.classList.add('bg-gradient-success');
			  document.getElementById("progressBarId").style.backgroundColor = "white";
			  progressBar.style.backgroundColor = 'white';
			  Loss = false;
		  }
    	else if (percent >= 80 && percent < 85) {
			document.getElementById("progressBarId").style.backgroundColor = "yellow";
      		progressBar.style.backgroundColor = 'yellow';
      		document.getElementById('profitAlert').innerHTML="You are Iching forward to make the loss"
      		document.getElementById('profitAlert').style.color="white"
      		exampleElement.classList.remove('bg-danger');
      		exampleElement.classList.remove('bg-gradient-success');
      		exampleElement.classList.add('bg-gradient-warning');
      		Loss = false;
    	}
    	else if (percent >= 85 && percent < 100) {
      		progressBar.style.backgroundColor = 'Red';
      		exampleElement.classList.remove('bg-danger');
      		exampleElement.classList.remove('bg-gradient-success');
      		exampleElement.classList.add('bg-gradient-warning');
      		document.getElementById('profitAlert').innerHTML="You are Iching forward to make the loss"
      		document.getElementById('profitAlert').style.color="red"
      		Loss = false;
    	}
    	else if(percent >= 100)
    	{
			progressBar.style.backgroundColor = 'Red';
			document.getElementById('profitAlert').innerHTML="You are In totally loss"
      		document.getElementById('profitAlert').style.color="red"
      		exampleElement.classList.remove('bg-gradient-warning');
      		exampleElement.classList.remove('bg-gradient-success');
      		exampleElement.classList.add('bg-danger');
      		Loss = true;
		}
    	document.getElementById('totalNumber').innerHTML=percent+"%"
    	document.getElementById('progressDescOfAvailable').innerHTML="So You Have now total : "+(parseFloat(givenQuote)-parseFloat(totalAmountAppended))+" Amount Left &nbsp;&nbsp;&nbsp;&nbsp; and the Purchase Amount till now is: "+parseFloat(totalAmountAppended)
}


window.addEventListener("beforeunload", function(event) {
	event.preventDefault();
	document.cookie = "myCookie1=".concat(JSON.stringify(data));
	event.returnValue = "Are you sure you want to leave this page?"
});






