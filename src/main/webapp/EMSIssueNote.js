var data = [];
var search = [];
var percent;
document.getElementById("input-form").addEventListener("change", () => {

	let value = document.getElementById("input-form").value;
	console.log(value);
	if (value == "select") {
		document.getElementById("input-form").style.borderColor = "red";
		document.getElementById("select-error").innerText = "Please, Select any one optoins.";
		document.getElementById("select-error").style.color = "red";
		document.getElementById("hide-text").style.display = "none";
		document.querySelector("#input-update").classList.add("disabled");
		document.getElementById("category-id-model").style.display = "none";
		document.getElementById("grade-id-model").style.display = "none";
		document.getElementById("size-id-model").style.display = "none";
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
	}
	else {
		document.querySelector("#input-update").classList.remove("disabled");
		document.getElementById("hide-text").style.display = "block";
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

function submitForm(event) {
	event.preventDefault();
	if (document.getElementById('MyTable').hasChildNodes() != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	}

	var json =
	{
		ProjectId: document.getElementById('ProjectId1').value,
		category: document.getElementById('categoryId').value,
		grade: document.getElementById('gradeId').value,
		size: document.getElementById('sizeId').value, 
		quantity: document.getElementById('Quantity').value,
		unit: document.getElementById('unit-id').value,
		remark: document.getElementById('remarkId').value,
		issuePer: document.getElementById('issue-per-id').value,
		contracter: document.getElementById('contracter-id').value,
	}
	data.push(json);
	console.log(json);
	appendFunc();
}

document.getElementById("ProjectId1").addEventListener("change", () => {

	document.getElementById('ProjectId1').disabled = true;
	document.getElementById('categoryId').disabled = false;
	document.getElementById('gradeId').disabled = false;
	document.getElementById('sizeId').disabled = false;
	document.getElementById('Quantity').disabled = false;
	document.getElementById('remarkId').disabled = false;
	document.getElementById('unit-id').disabled = false;
	document.getElementById('issue-per-id').disabled = false;
	document.getElementById('contracter-id').disabled = false;
	document.getElementById('submit-item-btn').disabled = false;
})

function appendFunc() {
	document.getElementById("MyTable").innerHTML = "";
	var table = document.getElementById("MyTable");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML = `
                    <td id="${i + 1}">${i + 1}</td>
                    <td><a id="materialDisc-${i + 1}">${data[i].category}</a> <br></td>
                    <td><a id="category-app-${i + 1}">${data[i].grade}</a> <br></td>
                    <td><a id="quantity-app-${i + 1}"> ${data[i].size} </a> <br></td>
                    <td><a id="materialDisc-${i + 1}">${data[i].quantity}</a> <br></td>
                    <td><a id="category-app-${i + 1}">${data[i].unit}</a> <br></td>
                    <td><a id="issuePer-app-${i + 1}"> ${data[i].issuePer} </a> <br></td>
                    <td><a id="contracter-app-${i + 1}"> ${data[i].contracter} </a> <br></td>
                    <td class="project-actions text-right">
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modal-editItem" onclick="editFinction(this.id)" id="Edit${i + 1}">
                            <i class="fas fa-pencil-alt"></i>
                    </button>
                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-projectDelete" onclick="deleteItem(this.id)" id="Delete${i + 1}">
                            <i class="fas fa-trash"></i>
                    </button>
                    </td>`;
		table.appendChild(newRow);
	}
}

var deleteValue = "";
function deleteItem(deleteId) {
	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {
	data.splice(((deleteValue.substring(6)) - 1), 1);
	appendFunc();
	//$('#modal-projectDelete').modal('hide');
	document.getElementById("modal-projectDelete").click();
});

let editValue;
function editFinction(editId) {
	console.log(editId);
	editValue = parseInt(editId.substring(4));
}

function updateField(event) {
	event.preventDefault();
	console.log("edited id : " + editValue);
	var fieldToChange = document.getElementById('input-form').value;

	console.log("fieldToChange ", fieldToChange);

	switch (fieldToChange) {
		case "mtd": data[editValue - 1].materialDisc = document.getElementById("materialId-mdl").value;
			break;

		case "remark": data[editValue - 1].remark = document.getElementById("materialId-mdl").value;
			break;

		case "contracter": data[editValue - 1].contracter = document.getElementById("model-text").value;
			break;

		case "issueper": data[editValue - 1].issuePer = document.getElementById("model-text").value;
			break;

		case "quantity": data[editValue - 1].quantity = document.getElementById("model-num").value;
			break;
	}
	console.log(data)
	appendFunc();
	$('#modal-editItem').modal('hide');
	XhrRequestForSendData(event)
}

function XhrRequestForSendData(event)
{
	alert('hello')
	event.preventDefault();
	
	var xhr = new XMLHttpRequest;
	xhr.open('POST','http://localhost:8080/EMS/EMSIssueNoteServlet',true);
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
	document.getElementById("MyTable").innerHTML = "";
	data=[]
}

window.onload = function getProjects() {
	let dataText;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSIssueNoteServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			dataText = JSON.parse(xhr.responseText);
			console.log("))))))))))")
			console.log(dataText);
			appendProjects(dataText);
		}
	}
	var data1 = { token: 'Projects' }
	xhr.send(JSON.stringify(data1));
};

function appendProjects(projects) {

	var projectsSelect = document.getElementById("ProjectId1");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select category"selected>Select Project</option>`;
	for (let i = 0; i < projects.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		console.log(projects[i]);
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById("ProjectId1").addEventListener("change", () => {
	console.log("in blur of catagory ");
	var categoryData;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS/EMSIssueNoteServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			categoryData = xhr.responseText;
			appendCategory(JSON.parse(categoryData));
		}
	}
	var data1 = {token: 'catagory'}
	xhr.send(JSON.stringify(data1));
});

function appendCategory(dataCategory) {
	console.log(dataCategory);
	var projectsSelect = document.getElementById("categoryId");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select category"selected>Select Category</option>`;
	for (let i = 0; i < dataCategory.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = dataCategory[i];
		createdAt.innerHTML = dataCategory[i];
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById("categoryId").addEventListener("change", () => {
	console.log("in blur of grade ");
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSIssueNoteServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data);
			appendGrade(Data)
		}
	}
	var data = { category: document.getElementById('categoryId').value,token:"grade"}
	xhr.send(JSON.stringify(data));
});

function appendGrade(dataCategory) {
	var projectsSelect = document.getElementById("gradeId");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select Grade"selected>Select Grade</option>`;
	for (let i = 0; i < dataCategory.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = dataCategory[i];
		createdAt.innerHTML = dataCategory[i];
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById("gradeId").addEventListener("change", () => {
	console.log("in blur of grade ");
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSIssueNoteServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data);
			appendSize(Data)
		}
	}
	var data1 = { token: 'size', category: document.getElementById("categoryId").value, grade: document.getElementById("gradeId").value }
	xhr.send(JSON.stringify(data1));
});

function appendSize(dataCategory) {
	var projectsSelect = document.getElementById("sizeId");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select Grade"selected>Select size</option>`;
	for (let i = 0; i < dataCategory.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = dataCategory[i];
		createdAt.innerHTML = dataCategory[i];
		projectsSelect.appendChild(createdAt);
	}
}
var quantity=0
document.getElementById('sizeId').addEventListener('change',()=>{
	console.log("for quanity");
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSIssueNoteServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			quantity = JSON.parse(xhr.responseText);
			console.log(parseInt(quantity));
			document.getElementById('quant').innerHTML="Available Quantity is: "+quantity
			document.getElementById('quant').style.color='red'
		}
	}
	var data1 = {catagory:document.getElementById('categoryId').value,grade:document.getElementById('gradeId').value,size:document.getElementById('sizeId').value,token:"quantity"}
	xhr.send(JSON.stringify(data1));	
})
document.getElementById('Quantity').addEventListener('keydown',(event)=>{
	console.log('quanityt')
	if (parseInt(event.key) >= 0 && parseInt(event.key) <= 9) { // Check if the key pressed is a number
    const newQuantity = parseInt(document.getElementById('Quantity').value + event.key);
    if (newQuantity > quantity) {
      event.preventDefault();
      document.getElementById('Quantity').style.borderWidth='1px'
      document.getElementById('Quantity').style.borderColor='red'
      document.getElementById('Quantity').style.borderStyle='solid'
      yourApiFunction();
    }
    else if (newQuantity <= quantity){
		document.getElementById('Quantity').style.borderWidth='1px'
      document.getElementById('Quantity').style.borderColor='grey'
      document.getElementById('Quantity').style.borderStyle='solid'
	}
  }
})

function yourApiFunction(){
  
  //Do your api calls, the "error" variable is only set to demonstrate the use of the success/error message
  let error = false;
  
  if(error){
    showToast("Please Enter the Quantity less than or equal to "+quantity);
  }else{
      showToast("Please Enter the Quantity less than or equal to "+quantity);
  }
}

function showToast(content = "Unknown error") { //You can change the default value
  // Get the snackbar DIV
  var x = document.getElementById("snackbar");
  
  //Change the text (not mandatory, but I think you might be willing to do it)
  x.innerHTML = content;

  // Add the "show" class to DIV
  x.className = "show";

  // After 3 seconds, remove the show class from DIV
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}