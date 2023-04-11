
/*

var givenQuote=0
var totalAmountAppended=0
var progressBar = document.querySelector('.progress-bar');
var progress = 0;  
var percent=0
var exampleElement = document.getElementById('success');
document.getElementById('progressDescOfAvailable').innerHTML="Add Project and Items to see the Magic"
window.onload = function getProjects1(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSPurchaseServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function() {
			if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendProjects(Data[0])
			appendCategory(Data[1])
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


function ProjectChange(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSPurchaseServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
			if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendVendors(Data)
			}
	}
	xhr.send(JSON.stringify({token:"Vendors"}));
	
	
	
	
let xhr2 = new XMLHttpRequest();
  xhr2.open('PUT', 'http://localhost:8080/EMS2/EMSPurchaseServlet',true);
  xhr2.setRequestHeader('Content-type', 'application/json');
  xhr2.onload = function() {
	if (xhr2.readyState === XMLHttpRequest.DONE && xhr2.status === 200) {
	  Data = JSON.parse(xhr2.responseText);
	  console.log(JSON.stringify(Data));
	  givenQuote=Data
	  document.getElementById('progressDesc').innerHTML="Your Quoatation of Given Project is: "+ (givenQuote)
	}
  };
  xhr2.send(JSON.stringify({token:"quotations",project:document.getElementById('ProjectId1').value }));	
}

function appendVendors(projects){

	var projectsSelect = document.getElementById("vendor-id");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select Vendor"selected>Select vendor</option>`;
	for(let i=0; i<projects.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}



function calculateAmount(){
		document.getElementById('amount-id').value  = parseFloat(document.getElementById('rate-id').value) * parseFloat(document.getElementById('orderQuan-id').value);
}

function calculateDiscountAmount(){
	
	document.getElementById('DiscountAmount').disabled = true;
	document.getElementById('DiscountAmount').value = parseFloat(document.getElementById('amount-id').value) * (parseFloat(document.getElementById('DiscountPercentage').value) / 100);
	document.getElementById('Totalamount-id').value = parseFloat(document.getElementById('amount-id').value) - (parseFloat(document.getElementById('amount-id').value) * (parseFloat(document.getElementById('DiscountPercentage').value) / 100));
}

function calculateDiscountPercentage(){
	  document.getElementById('DiscountPercentage').disabled = true;
	document.getElementById('DiscountPercentage').value = (parseFloat(document.getElementById('DiscountAmount').value) / parseFloat(document.getElementById('amount-id').value)) * 100;
	document.getElementById('Totalamount-id').value = parseFloat(document.getElementById('amount-id').value)  - parseFloat(document.getElementById('DiscountAmount').value)
}


var gstAmount;
var gst;
var count = 0;
var originalAmount;
function clickOfRadioButton(){
	count++;
	if(count <=1){
		originalAmount = parseFloat(document.getElementById('Totalamount-id').value);
	}
	var radioButton = document.getElementsByName('chks');
	for(var i=0;i < radioButton.length;i++){
		if(radioButton[i].checked){
			gstAmount = originalAmount * (parseFloat(radioButton[i].value) / 100)
			gst = parseFloat(radioButton[i].value) 
			break;
		}
	}
	
	document.getElementById('Totalamount-id').value = gstAmount +originalAmount;
	
}




document.getElementById("input-form").addEventListener("change", () => {

		let value = document.getElementById("input-form").value;
		console.log(value);
		if (value == "Select option you want to update..") {
			document.getElementById("input-form").style.borderColor = "red";
			document.getElementById("select-error").innerText = "Please, Select any one optoins.";
			document.getElementById("select-error").style.color = "red";
			document.getElementById("hide-text").style.display = "none";
			document.querySelector("#input-update").classList.add("disabled");
			document.getElementById("category-id-model").style.display = "none";
			document.getElementById("grade-id-model").style.display = "none";
			document.getElementById("size-id-model").style.display = "none";
			document.getElementById("hide-date").style.display = "none";
		}
		else if (value == "category") {
			document.querySelector("#input-update").classList.remove("disabled");
			document.getElementById("category-id-model").style.display = "block";
			document.getElementById("grade-id-model").style.display = "block";
			document.getElementById("size-id-model").style.display = "block";
			document.getElementById("hide-text").style.display = "none";
			document.getElementById("lableName1").innerHTML = document.getElementById("input-form").value;
			document.getElementById("placeholderChange1").setAttribute("placeholder", "Enter New value to that element");
			document.getElementById("input-form").style.borderColor = "blue";
			document.getElementById("select-error").innerText = "";
			document.getElementById("select-error").style.color = "red";
			document.getElementById("hide-date").style.display = "none";
		}
		else if (value == "grade") {
			document.querySelector("#input-update").classList.remove("disabled");
			document.getElementById("category-id-model").style.display = "none";
			document.getElementById("grade-id-model").style.display = "block";
			document.getElementById("size-id-model").style.display = "block";
			document.getElementById("hide-text").style.display = "none";
			document.getElementById("lableName1").innerHTML = document.getElementById("input-form").value;
			document.getElementById("placeholderChange1").setAttribute("placeholder", "Enter New value to that element");
			document.getElementById("input-form").style.borderColor = "blue";
			document.getElementById("select-error").innerText = "";
			document.getElementById("select-error").style.color = "red";
			document.getElementById("hide-date").style.display = "none";
		}
		else if (value == "size") {
			document.querySelector("#input-update").classList.remove("disabled");
			document.getElementById("category-id-model").style.display = "none";
			document.getElementById("grade-id-model").style.display = "none";
			document.getElementById("size-id-model").style.display = "block";
			document.getElementById("hide-text").style.display = "none";
			document.getElementById("lableName1").innerHTML = document.getElementById("input-form").value;
			document.getElementById("placeholderChange1").setAttribute("placeholder", "Enter New value to that element");
			document.getElementById("input-form").style.borderColor = "blue";
			document.getElementById("select-error").innerText = "";
			document.getElementById("select-error").style.color = "red";
			document.getElementById("hide-date").style.display = "none";
		}
		else {
			document.querySelector("#input-update").classList.remove("disabled");
			document.getElementById("hide-text").style.display = "block";
			document.getElementById("hide-date").style.display = "none";
			document.getElementById("lableName").innerHTML = document.getElementById("input-form").value;
			document.getElementById("placeholderChange").setAttribute("placeholder", "Enter New value to that element");
			document.getElementById("input-form").style.borderColor = "blue";
			document.getElementById("select-error").innerText = "";
			document.getElementById("select-error").style.color = "red";
			document.getElementById("category-id-model").style.display = "none";
			document.getElementById("grade-id-model").style.display = "none";
			document.getElementById("size-id-model").style.display = "none";
		}
})

document.getElementById('ProjectId1').addEventListener('change', () => {
		console.log('quoate')
		document.getElementById('ProjectId1').disabled = true;
		document.getElementById('category-id').disabled = false;
		document.getElementById('grade-id').disabled = false;
		document.getElementById('size-id').disabled = false;
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
		document.getElementById('GST1').disabled = false;
		document.getElementById('GST2').disabled = false;
		document.getElementById('GST3').disabled = false;
		document.querySelector('#add-store').classList.remove('disabled');    
});





var data = []

function submitForm(){
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
		document.getElementById('GST1').disabled = true;
		document.getElementById('GST2').disabled = true;
		document.getElementById('GST3').disabled = true;
		document.querySelector('#add-store').classList.add('disabled');
		document.querySelector('#processTo').classList.add('disabled');
		
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSPurchaseServlet', true);

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
	xhr.send(JSON.stringify(data));
}
window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie1=".concat(JSON.stringify(data));
  event.returnValue = "Are you sure you want to leave this page?"
});

var deleteValue = "";
function deleteItem(deleteId) {
	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {
	data.splice(((deleteValue.substring(6)) - 1), 1);
	document.getElementById("MyTable16").innerHTML = "";
		var table = document.getElementById("MyTable16");
		for (var i = 0; i < data.length; i++) {
			var newRow = document.createElement("tr");
			newRow.innerHTML = `
							  <td id="${i + 1}">${i + 1}</td>
							  <td><a id="ProjectId-${i + 1}">${data[i].ProjectId}</a> <br></td>
							  <td><a id="category-${i + 1}">${data[i].category}</a> <br></td>
							  <td><a id="grade-${i + 1}"> ${data[i].grade} </a> <br></td>
							  <td><a id="size-${i + 1}"> ${data[i].size} </a> <br></td>
							  <td><a id="TotalAmount-${i + 1}"> ${data[i].TotalAmount} </a> <br></td>
							  <td><a id="vendor-${i + 1}"> ${data[i].vendorName} </a> <br></td>                       
							  <td class="project-actions text-right">
							  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modal-editItem" onclick="editFinction(this.id)" id="Edit${i + 1}">
									  <i class="fas fa-pencil-alt"></i>
							  </button>
							  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-projectDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
									  <i class="fas fa-trash"></i>
							  </button>
							  </td>`;
			table.appendChild(newRow);
		}
		totalAmountAppended-=parseFloat(document.getElementById('Totalamount-id').value)
		percent=(totalAmountAppended*100)/givenQuote
			progressBar.style.width = percent + '%';
			progressBar.setAttribute('aria-valuenow', percent);
			change()
});

let editValue = "";
function editFinction(editId) {
	console.log(editId);
	editValue = editId;
}

function updateField() {
	editValue = editValue.substring(4)
	console.log("edited id : " + editValue);
	var fieldToChange = document.getElementById('input-form').value;
	
	if (fieldToChange == "quantity") {
		data[editValue - 1].quantity = document.getElementById('placeholderChange').value;
	}
	console.log(data)
	document.getElementById("MyTable16").innerHTML = "";
		var table = document.getElementById("MyTable16");
		for (var i = 0; i < data.length; i++) {
			var newRow = document.createElement("tr");
			newRow.innerHTML = `
							  <td id="${i + 1}">${i + 1}</td>
							  <td><a id="ProjectId-${i + 1}">${data[i].ProjectId}</a> <br></td>
							  <td><a id="category-${i + 1}">${data[i].category}</a> <br></td>
							  <td><a id="grade-${i + 1}"> ${data[i].grade} </a> <br></td>
							  <td><a id="size-${i + 1}"> ${data[i].size} </a> <br></td>
							  <td><a id="TotalAmount-${i + 1}"> ${data[i].TotalAmount} </a> <br></td>
							  <td><a id="vendor-${i + 1}"> ${data[i].vendorName} </a> <br></td>                       
							  <td class="project-actions text-right">
							  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modal-editItem" onclick="editFinction(this.id)" id="Edit${i + 1}">
									  <i class="fas fa-pencil-alt"></i>
							  </button>
							  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-projectDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
									  <i class="fas fa-trash"></i>
							  </button>
							  </td>`;
			table.appendChild(newRow);
		}
		$('#modal-projectDelete').modal('hide');
}



function getIndentList(){
	
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSPurchaseServlet',true);

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
		console.log(data)
	}
	
	appendFunc()
}
function appendFunc() {
	
	if (document.getElementById('MyTable16').hasChildNodes != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	 }

	var json =
	{
	   ProjectId: document.getElementById('ProjectId1').value,
	   category: document.getElementById('category-id').value,
	   grade: document.getElementById('grade-id').value,
	   size: document.getElementById('size-id').value,
	   unit:document.getElementById('unit-id').value,
	   orderQuan: document.getElementById('orderQuan-id').value,
	   vendorName: document.getElementById('vendor-id').value,
	   rate: document.getElementById('rate-id').value,
	   discountAmount:document.getElementById('DiscountAmount').value,
	   TotalAmount: document.getElementById('Totalamount-id').value,
	   GST:gst
	}
	console.log(json);
	data.push(json);
	console.log(data)
	document.getElementById("MyTable16").innerHTML = "";
		var table = document.getElementById("MyTable16");
		for (var i = 0; i < data.length; i++) {
			var newRow = document.createElement("tr");
			newRow.innerHTML = `
							  <td id="${i + 1}">${i + 1}</td>
							  <td><a id="ProjectId-${i + 1}">${data[i].ProjectId}</a> <br></td>
							  <td><a id="category-${i + 1}">${data[i].category}</a> <br></td>
							  <td><a id="grade-${i + 1}"> ${data[i].grade} </a> <br></td>
							  <td><a id="size-${i + 1}"> ${data[i].size} </a> <br></td>
							  <td><a id="TotalAmount-${i + 1}"> ${data[i].TotalAmount} </a> <br></td>
							  <td><a id="vendor-${i + 1}"> ${data[i].vendorName} </a> <br></td>                       
							  <td class="project-actions text-right">
							  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modal-editItem" onclick="editFinction(this.id)" id="Edit${i + 1}">
									  <i class="fas fa-pencil-alt"></i>
							  </button>
							  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-projectDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
									  <i class="fas fa-trash"></i>
							  </button>
							  </td>`;
			table.appendChild(newRow);
		}
		totalAmountAppended+=parseFloat(document.getElementById('Totalamount-id').value)
		percent=(totalAmountAppended*100)/givenQuote
			progressBar.style.width = percent + '%';
			progressBar.setAttribute('aria-valuenow', percent);
			change()
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
		  }
		else if (percent >= 80 && percent < 85) {
			document.getElementById("progressBarId").style.backgroundColor = "yellow";
				progressBar.style.backgroundColor = 'yellow';
				document.getElementById('profitAlert').innerHTML="You are Iching forward to make the loss"
				document.getElementById('profitAlert').style.color="white"
				exampleElement.classList.remove('bg-danger');
				exampleElement.classList.remove('bg-gradient-success');
				exampleElement.classList.add('bg-gradient-warning');
		}
		else if (percent >= 85 && percent < 100) {
				progressBar.style.backgroundColor = 'Red';
				exampleElement.classList.remove('bg-danger');
				exampleElement.classList.remove('bg-gradient-success');
				exampleElement.classList.add('bg-gradient-warning');
				document.getElementById('profitAlert').innerHTML="You are Iching forward to make the loss"
				document.getElementById('profitAlert').style.color="red"
		}
		else if(percent >= 100)
		{
			progressBar.style.backgroundColor = 'Red';
			document.getElementById('profitAlert').innerHTML="You are In totally loss"
				document.getElementById('profitAlert').style.color="red"
				exampleElement.classList.remove('bg-gradient-warning');
				exampleElement.classList.remove('bg-gradient-success');
				exampleElement.classList.add('bg-danger');
		}
		document.getElementById('totalNumber').innerHTML=percent+"%"
		document.getElementById('progressDescOfAvailable').innerHTML="So You Have now total : "+(parseFloat(givenQuote)-parseFloat(totalAmountAppended))+" Amount Left <br> and the Purchase Amount till now is: "+parseFloat(totalAmountAppended)
}


*/

var EditId;
var givenQuote = 0
var totalAmountAppended = 0
var progressBar = document.querySelector('.progress-bar');
var progress = 0;
var percent = 0
var data = [];
var servletData = []
var exampleElement = document.getElementById('success');
document.getElementById('progressDescOfAvailable').innerHTML="Add Project and Items to see the Magic"
window.onload = function getProjects1() {
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSPurchaseServlet', true);
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
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSPurchaseServlet', true);
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
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSPurchaseServlet', true);

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
	xhr2.open('PUT', 'http://localhost:8080/EMS2/EMSPurchaseServlet', true);
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


	var js1 = data.filter(x => x.count == deleteId1)

	document.getElementById(`tr${js1[0].count}`).remove();

	data.splice(deleteId1 - 1, 1);

	flag = false;

});



let editValue = "";
function updateField(editId) {
	console.log(editId);

	editValue = editId;

	EditId = parseInt(editValue.substring(4));
	var js1 = data.filter(x => x.count == EditId)
	console.log(js1)
	document.getElementById('orderQuan-id').value = js1[0].quantity;
	document.getElementById('category-id').value = js1[0].category,
		document.getElementById('grade-id').value = js1[0].grade;
	document.getElementById('size-id').value = js1[0].size;
	console.log(data)
	appendFunc()
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
	count++;
	if (count <= 1) {
		originalAmount = parseFloat(document.getElementById('Totalamount-id').value);
	}
	var radioButton = document.getElementsByName('chks');
	for (var i = 0; i < radioButton.length; i++) {
		if (radioButton[i].checked) {
			gstAmount = originalAmount * (parseFloat(radioButton[i].value) / 100)
			gst = parseFloat(radioButton[i].value)
			break;
		}
	}
	document.getElementById('Totalamount-id').value = gstAmount + originalAmount;
}

var submitData = []

function submitForm() {

	var js1 = data.filter(x => x.count == EditId)

	js1[0].TotalAmount = document.getElementById('Totalamount-id').value;
	js1[0].vendorName = document.getElementById('vendor-id').value;
	appendFunc()
	
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
		PaymentTerms:document.getElementById('PaymentTermsId').value
	}

	submitData.push(json)
	console.log(submitData)
	
	totalAmountAppended+=parseFloat(document.getElementById('Totalamount-id').value)
    percent=(totalAmountAppended*100)/givenQuote
    console.log(percent)
    progressBar.style.width = percent + '%';
    progressBar.setAttribute('aria-valuenow', percent);
    change()
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
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSPurchaseServlet', true);

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
		  }
    	else if (percent >= 80 && percent < 85) {
			document.getElementById("progressBarId").style.backgroundColor = "yellow";
      		progressBar.style.backgroundColor = 'yellow';
      		document.getElementById('profitAlert').innerHTML="You are Iching forward to make the loss"
      		document.getElementById('profitAlert').style.color="white"
      		exampleElement.classList.remove('bg-danger');
      		exampleElement.classList.remove('bg-gradient-success');
      		exampleElement.classList.add('bg-gradient-warning');
    	}
    	else if (percent >= 85 && percent < 100) {
      		progressBar.style.backgroundColor = 'Red';
      		exampleElement.classList.remove('bg-danger');
      		exampleElement.classList.remove('bg-gradient-success');
      		exampleElement.classList.add('bg-gradient-warning');
      		document.getElementById('profitAlert').innerHTML="You are Iching forward to make the loss"
      		document.getElementById('profitAlert').style.color="red"
    	}
    	else if(percent >= 100)
    	{
			progressBar.style.backgroundColor = 'Red';
			document.getElementById('profitAlert').innerHTML="You are In totally loss"
      		document.getElementById('profitAlert').style.color="red"
      		exampleElement.classList.remove('bg-gradient-warning');
      		exampleElement.classList.remove('bg-gradient-success');
      		exampleElement.classList.add('bg-danger');
		}
    	document.getElementById('totalNumber').innerHTML=percent+"%"
    	document.getElementById('progressDescOfAvailable').innerHTML="So You Have now total : "+(parseFloat(givenQuote)-parseFloat(totalAmountAppended))+" Amount Left <br> and the Purchase Amount till now is: "+parseFloat(totalAmountAppended)
}


window.addEventListener("beforeunload", function(event) {
	event.preventDefault();
	document.cookie = "myCookie1=".concat(JSON.stringify(data));
	event.returnValue = "Are you sure you want to leave this page?"
});



