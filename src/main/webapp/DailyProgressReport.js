window.onload = function getProject1() {
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
	
	
	let categoryData;
	var xhr1 = new XMLHttpRequest();
	xhr1.open('GET', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet', true);
	xhr1.onload = function() {
		if (xhr1.status === 200) {
			categoryData = JSON.parse(xhr1.responseText);
			appendCategory(categoryData);
		}
	}
	xhr1.send();
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

function appendCategory(categoryData) {
	console.log(categoryData);
	var categorySelect = document.getElementById("categoryId");
	console.log(categorySelect)
	categorySelect.innerHTML = `<option value="select" selected>Select catagory</option>`;
	for (let i = 0; i < categoryData.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = categoryData[i];
		createdAt.innerHTML = categoryData[i];
		categorySelect.appendChild(createdAt);
	}
}
document.getElementById("categoryId").addEventListener("change", () => {
	let gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			gradeData = JSON.parse(xhr.responseText);
			appendGrade(gradeData);
		}
	}
	var data = { category: document.getElementById('categoryId').value, token: "category" }
	xhr.send(JSON.stringify(data));
});
function appendGrade(gradeData) {
	console.log(gradeData);
	var gradeSelect = document.getElementById("gradeId");
	console.log(gradeSelect)
	gradeSelect.innerHTML = `<option value="select" selected>Select Grade</option>`;
	for (let i = 0; i < gradeData.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = gradeData[i];
		createdAt.innerHTML = gradeData[i];
		gradeSelect.appendChild(createdAt);
	}
}
document.getElementById("gradeId").addEventListener("change", () => {
	let sizeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			sizeData = JSON.parse(xhr.responseText);
			appendSize(sizeData);
		}
	}
	var data = { grade: document.getElementById('gradeId').value, token: "grade" }
	xhr.send(JSON.stringify(data));
});
//var sizeArray;
function appendSize(sizeData) {
	sizeArray = sizeData;
	console.log(sizeData);
	var sizeSelect = document.getElementById("sizeId");
	console.log(sizeSelect)
	sizeSelect.innerHTML = `<option value="select" selected>Select size</option>`;
	for (let i = 0; i < sizeData.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = sizeData[i];
		createdAt.innerHTML = sizeData[i];
		sizeSelect.appendChild(createdAt);
	}
}
var data = [];
function submitForm() {
	if (document.getElementById('MyTable').hasChildNodes) {
		document.querySelector('#processTo').classList.remove('disabled')
	}
	var json =
	{
		project: document.getElementById('projectId').value,
		item: document.getElementById('ItemCode').value,
		subItem: document.getElementById('subItemId').value,
		catagory: document.getElementById('categoryId').value,
		grade: document.getElementById('gradeId').value,
		size: document.getElementById('sizeId').value,
		quantity: document.getElementById('Quantity').value,
		unit: document.getElementById('unit-id').value,
		date: document.getElementById('DateId').value,
	}
	data.push(json)
	console.log(data)
	appendFunc();
}
function appendFunc() {
	document.getElementById("MyTable").innerHTML = "";
	var table = document.getElementById("MyTable");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML =
			`<td id="${i + 1}">${i + 1}</td>
 <td><a id="project${i + 1}">${data[i].project}</a> <br></td>
 <td><a id="item${i + 1}">${data[i].item}</a> <br></td>
 <td><a id="subItem${i + 1}">${data[i].subItem}</a> <br></td>
 <td><a id="catagory${i + 1}">${data[i].catagory}</a> <br></td>
 <td><a id="grade${i + 1}">${data[i].grade}</a> <br></td>
 <td><a id="size${i + 1}">${data[i].size}</a> <br></td>
 <td><a id="quantity${i + 1}">${data[i].quantity}</a> <br></td>
 <td><a id="unit${i + 1}">${data[i].unit}</a> <br></td>
 <td><a id="date${i + 1}">${data[i].date}</a> <br></td>
 <td class="project-actions text-right">
 <button type="button" class="btn btn-sm btn-danger toastrDefaultError" data-toggle="modal" data-target="#modal-itemDelete" onclick="deleteItem(this.id)" id="Delete${i + 1}">
 <i class="fas fa-trash"></i>
 </button>
 </td>`;
		table.appendChild(newRow);
	}
}
vardeleteValue = "";
function deleteItem(deleteId) {
	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {
	data.splice(((deleteValue.substring(6)) - 1), 1);
	appendFunc();
	document.getElementById("modal-itemDelete").click();
});
function submitForm1() {
	console.log(data)
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/EMS/DailyProgressReportServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function() {
		if (xhr.status == 200) {
			console.log(xhr.responseText);
			window.location.href = "DPRList.jsp";
		}
	}
	xhr.send(JSON.stringify(data));
}
