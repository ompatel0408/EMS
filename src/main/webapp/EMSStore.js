var data = []
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
	console.log(document.getElementById('MyTable').hasChildNodes);
	if (document.getElementById('MyTable').hasChildNodes != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	}

	var json =
	{
		category: document.getElementById('category-id').value,
		grade: document.getElementById('grade-id').value,
		size: document.getElementById('size-id').value,
		quantity: document.getElementById('quantaty-id').value,
	}
	data.push(json);
	console.log(json);
	console.log(data);
	appendFunc();
}

function appendFunc() {
	document.getElementById("MyTable").innerHTML = "";
	var table = document.getElementById("MyTable");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML =
			`<td id="${i + 1}">${i + 1}</td>
   
             <td><a id="category-${i + 1}">${data[i].category}</a> <br></td>
             <td><a id="grade-${i + 1}"> ${data[i].grade} </a> <br></td>
             <td><a id="size-${i + 1}"> ${data[i].size} </a> <br></td>
             <td><a id="quantity-${i + 1}"> ${data[i].quantity} </a> <br></td>
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
});

/*let editValue = "";
function editFinction(editId) {
	console.log(editId);
	editValue = editId;
}

function updateField(event) {
	event.preventDefault();
	alert(editValue)
	editValue = editValue.substring(4)
	console.log("edited id : " + editValue);
	var fieldToChange = document.getElementById('input-form').value;
	var newValue = document.getElementById('placeholderChange').value;

	console.log("fieldToChange ", fieldToChange);
	console.log("newValue  ", newValue);

	if (fieldToChange == "category") {
		data[editValue - 1].category = document.getElementById("category-id-select").value;
		data[editValue - 1].grade = document.getElementById("grade-id-select").value;
		data[editValue - 1].size = document.getElementById("size-id-select").value;
	} else if (fieldToChange == "grade") {
		data[editValue - 1].grade = document.getElementById("grade-id-select").value;
		data[editValue - 1].size = document.getElementById("size-id-select").value;
	} else if (fieldToChange == "size") {
		data[editValue - 1].size = document.getElementById("size-id-select").value;
	} else if (fieldToChange == "quantity") {
		var Value = document.getElementById('placeholderChange').value;
		data[editValue - 1].quantity = Value;
	}
	console.log(data)
	appendFunc();
}
*/
function XHRRequestForStore() {
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSStoreServlet', true);

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
	console.log(JSON.stringify(data));
	document.getElementById("MyTable").innerHTML = "";
	data = []
	document.getElementById('category-id').disabled = true;
	document.getElementById('grade-id').disabled = true;
	document.getElementById('size-id').disabled = true;
	document.getElementById('quantaty-id').disabled = true;
	document.querySelector('#processTo').classList.add('disabled');

}


/*window.onload = function getProjects() {
	let dataText;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSStoreServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			dataText = JSON.parse(xhr.responseText);
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
		projectsSelect.appendChild(createdAt);
	}
}*/

window.onload = () => {
	console.log("in blur of catagory ");
	var categoryData;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSStoreServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {

			categoryData = xhr.responseText;
			//console.log(JSON.parse(categoryData));
			appendCategory(JSON.parse(categoryData));
		}
	}
	xhr.send();
}

function appendCategory(dataCategory) {
	console.log(dataCategory);
	var projectsSelect = document.getElementById("category-id");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select category"selected>Select Category</option>`;
	for (let i = 0; i < dataCategory.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = dataCategory[i];
		createdAt.innerHTML = dataCategory[i];
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById("category-id").addEventListener("change", () => {
	console.log("in blur of grade ");
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSStoreServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data);
			appendGrade(Data)
		}
	}
	var data1 = { token: 'grade', category: document.getElementById("category-id").value }
	xhr.send(JSON.stringify(data1));
});

function appendGrade(dataCategory) {
	var projectsSelect = document.getElementById("grade-id");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select Grade"selected>Select Grade</option>`;
	for (let i = 0; i < dataCategory.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = dataCategory[i];
		createdAt.innerHTML = dataCategory[i];
		projectsSelect.appendChild(createdAt);
	}
}

document.getElementById("grade-id").addEventListener("change", () => {
	console.log("in blur of grade ");
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSStoreServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data);
			appendSize(Data)
		}
	}
	var data1 = { token: 'size', category: document.getElementById("category-id").value, grade: document.getElementById("grade-id").value }
	xhr.send(JSON.stringify(data1));
});

function appendSize(dataCategory) {
	var projectsSelect = document.getElementById("size-id");
	console.log(projectsSelect)
	projectsSelect.innerHTML = `<option value="select Grade"selected>Select size</option>`;
	for (let i = 0; i < dataCategory.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = dataCategory[i];
		createdAt.innerHTML = dataCategory[i];
		projectsSelect.appendChild(createdAt);
	}
}