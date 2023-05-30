
document.getElementById('ProjectId1').addEventListener('change', () => {
	document.getElementById('ProjectId1').disabled = true;
	document.getElementById('category-id').disabled = false;
	document.getElementById('grade-id').disabled = false;
	document.getElementById('size-id').disabled = false;
	document.getElementById('item-id').disabled = false;
	document.getElementById('Quantity').disabled = false;
	document.getElementById('remark-id').disabled = false;
	document.querySelector('#add-indent').classList.remove('disabled');
});

function Demo(tt) {
	EditId = parseInt(tt.slice(4));
	console.log(EditId)
}

var deleteValue = "";
function deleteItem(deleteId) {

	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {
	data.splice(((deleteValue.substring(6)) - 1), 1);
	document.getElementById("MyTable11").innerHTML = "";
	var table = document.getElementById("MyTable11");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML = `
		<td id="${i + 1}">${i + 1}</td>
		<td><a id="projectApp${i + 1}"></a> <br></td>
		<td><a id="categoryApp${i + 1}"></a> <br></td>
		<td><a id="gradeApp${i + 1}"></a> <br></td>
		<td><a id="sizeApp${i + 1}"></a> <br></td>
		<td><a id="itemApp${i + 1}"></a> <br></td>
		<td><a id="qtyApp${i + 1}"></a> <br></td>
		<td><a id="remarkApp${i + 1}"></a> <br></td>
		<td><a id="DelivaryDate${i + 1}"></a> <br></td>
		 <td class="project-actions text-right">

<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-projectDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
<i class="fas fa-trash"></i>
</button>
</td>`;
		table.appendChild(newRow);
		document.getElementById(`projectApp${i + 1}`).innerHTML = data[i].ProjectId;
		document.getElementById(`categoryApp${i + 1}`).innerHTML = data[i].category;
		document.getElementById(`gradeApp${i + 1}`).innerHTML = data[i].grade;
		document.getElementById(`sizeApp${i + 1}`).innerHTML = data[i].size;
		document.getElementById(`itemApp${i + 1}`).innerHTML = data[i].item;
		document.getElementById(`qtyApp${i + 1}`).innerHTML = data[i].quantity;
		document.getElementById(`remarkApp${i + 1}`).innerHTML = data[i].remark;
	}
	$('#modal-projectDelete').modal('hide');
});

function updateField() {
	var fieldToChange = document.getElementById('input-form').value;
	var newValue = document.getElementById('placeholderChange').value;
	console.log("fieldToChange " + fieldToChange)
	console.log("newValue " + newValue)
	if (fieldToChange == "ItemName") {
		data[EditId - 1].ItemName = newValue;
	} else if (fieldToChange == "tagNo") {
		data[EditId - 1].tagNo = newValue;
	} else if (fieldToChange == "quantity") {
		data[EditId - 1].quantity = newValue;
	} else {
		var Value = document.getElementById('placeholderChange1').value;
		data[EditId - 1].delivaryDate = Value;
	}
	console.log(data)
	for (var i = 0; i < data.length; i++) {
		document.getElementById(`ItemName${i + 1}`).innerHTML = data[i].ItemName;
		document.getElementById(`PricePerUnit${i + 1}`).innerHTML = data[i].pricePerUnit;
		document.getElementById(`TagNo${i + 1}`).innerHTML = data[i].tagNo;
		document.getElementById(`Quantity${i + 1}`).innerHTML = data[i].quantity;
		document.getElementById(`DelivaryDate${i + 1}`).innerHTML = data[i].delivaryDate;
		document.getElementById(`TotalPrice${i + 1}`).innerHTML = data[i].TotalPrice;
	}
}

function getGrade(){
	console.log("gg");
	let gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/IndentServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			gradeData = JSON.parse(xhr.responseText);
			appendGrade(gradeData);
		}
	}
	var data = { category: document.getElementById('category-id').value,token:"category"}
	xhr.send(JSON.stringify(data));
}
function getItemNames(){
	let gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/IndentServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			gradeData = JSON.parse(xhr.responseText);
			appendItemName(gradeData);
		}
	}
	var data = { category: document.getElementById('ProjectId1').value,token:"indent"}
	xhr.send(JSON.stringify(data));
}
function appendItemName(nameData){	
	console.log(nameData);
	var itemSelect = document.getElementById("itemName");
	
	itemSelect.innerHTML = `<option value="Select Orders">Select Orders</option>`;
	for(let i=0; i<nameData.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = nameData[i];
		createdAt.innerHTML = nameData[i];
		itemSelect.appendChild(createdAt);
	}
}
var gradeId;
function appendGrade(gradeData){
	
	console.log(gradeData);
	var gradeSelect = document.getElementById("grade-id");
	console.log(gradeSelect.value)
	gradeSelect.innerHTML = `<option value="Select Grade">Select Grade</option>`;
	for(let i=0; i<gradeData.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = gradeData[i].grade;
		createdAt.innerHTML = gradeData[i].grade;
		gradeSelect.appendChild(createdAt);
	}
}
function getSize(){
	console.log("gg");
	let sizeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/IndentServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
		sizeData = JSON.parse(xhr.responseText);
			appendSize(sizeData);
		}
	}
	var data = { grade: document.getElementById('grade-id').value,token:"grade"}
	xhr.send(JSON.stringify(data));	
}
function appendSize(sizeData){	
	console.log(sizeData);
	var sizeSelect = document.getElementById("size-id");
	console.log(sizeSelect)
	sizeSelect.innerHTML = `<option value="Select Size">Select Size</option>`;
	for(let i=0; i<sizeData.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = sizeData[i].size;
		createdAt.innerHTML = sizeData[i].size;
		sizeSelect.appendChild(createdAt);
	}
	
	
}
function submitForm1(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/EMS/IndentServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.status == 200) {
			 console.log(xhr.responseText);
			 window.location.href = "ListIndentServlet?token=1&projectid=0&itemcode=0";
		}
	}
	xhr.send(JSON.stringify(data));
}
function XHRRequestForItem() {
	document.getElementById('ProjectId1').disabled = false;
	document.getElementById('ItemName').disabled = true;
	document.getElementById('PricePerUnit').disabled = true;
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
			var response = xhr.responseText;
			console.log(response);
		}
	} 	
	// send the request
	xhr.send(JSON.stringify(data));
}
function getCount(){
	//alert('Called!')
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/IndentServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
		let countSize = JSON.parse(xhr.responseText);
		console.log("total count size : " + countSize);
		document.getElementById('quantity').innerHTML="Available Stock in Store is :"+parseInt(countSize)
		document.getElementById('quantity').style.color="red"
		}
	}
	var data = {category: document.getElementById('category-id').value,
				grade: document.getElementById('grade-id').value,
				size: document.getElementById('size-id').value,token:"quantity"}
	xhr.send(JSON.stringify(data));	
	
}
var EditId;
var data = [];
function submitForm(event) {
	event.preventDefault();
	if (document.getElementById('MyTable11').hasChildNodes() != null) {
		document.querySelector('#ProcessId').classList.remove('disabled')
	}
	var json =
	{
		ProjectId: document.getElementById('ProjectId1').value,
		category: document.getElementById('category-id').value,
		grade: document.getElementById('grade-id').value,
		size: document.getElementById('size-id').value,
		item: document.getElementById('itemName').value,
		quantity: document.getElementById('Quantity').value,
		remark: document.getElementById('remark-id').value
	}
	data.push(json)
	console.log(json)
	console.log(data)
	document.getElementById("MyTable11").innerHTML = "";
	var table = document.getElementById("MyTable11");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML = `
		<td id="${i + 1}">${i + 1}</td>
		<td><a id="projectApp${i + 1}"></a> <br></td>
		<td><a id="categoryApp${i + 1}"></a> <br></td>
		<td><a id="gradeApp${i + 1}"></a> <br></td>
		<td><a id="sizeApp${i + 1}"></a> <br></td>
		<td><a id="itemApp${i + 1}"></a> <br></td>
		<td><a id="qtyApp${i + 1}"></a> <br></td>
		<td><a id="remarkApp${i + 1}"></a> <br></td>
		<td><a id="DelivaryDate${i + 1}"></a> <br></td>
		 <td class="project-actions text-right">

<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-projectDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
<i class="fas fa-trash"></i>
</button>
</td>`;
		table.appendChild(newRow);
		document.getElementById(`projectApp${i + 1}`).innerHTML = data[i].ProjectId;
		document.getElementById(`categoryApp${i + 1}`).innerHTML = data[i].category;
		document.getElementById(`gradeApp${i + 1}`).innerHTML = data[i].grade;
		document.getElementById(`sizeApp${i + 1}`).innerHTML = data[i].size;
		document.getElementById(`itemApp${i + 1}`).innerHTML = data[i].item;
		document.getElementById(`qtyApp${i + 1}`).innerHTML = data[i].quantity;
		document.getElementById(`remarkApp${i + 1}`).innerHTML = data[i].remark;
	}
}
window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie1=".concat(JSON.stringify(data));
  event.returnValue = "Are you sure you want to leave this page?"
});

function getGivenQuantity(){
	
	var xhr1 = new XMLHttpRequest();
	xhr1.open('PUT', 'http://localhost:8080/EMS/IndentServlet',true);
	xhr1.setRequestHeader('Content-type', 'application/json');
	xhr1.onload = function() {
		if (xhr1.status === 200) {
		let countSize = JSON.parse(xhr1.responseText);
		console.log("total count size : " + countSize);
		document.getElementById('givenQuantity').innerHTML="Your need to request for "+parseInt(countSize)+" quantity to store."
		document.getElementById('givenQuantity').style.color="red"
		}
	}
	var data = {category: document.getElementById('category-id').value,
				grade: document.getElementById('grade-id').value,
				size: document.getElementById('size-id').value,token:"givenQuantity",itemName: document.getElementById('itemName').value}
	xhr1.send(JSON.stringify(data));
}



