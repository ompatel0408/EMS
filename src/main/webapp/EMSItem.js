

document.getElementById("input-form").addEventListener("change", () => {

               let value = document.getElementById("input-form").value;
               console.log(value);
               if (value == "Select option you want to update..") {
                   document.getElementById("input-form").style.borderColor = "red";
                   document.getElementById("select-error").innerText = "Please, Select any one optoins.";
                   document.getElementById("select-error").style.color = "red";
                   document.getElementById("hide-text").style.display = "none";
                   document.querySelector("#input-update").classList.add("disabled");
               }
               else if(value == "delivaryDate")
               {
				   document.querySelector("#input-update").classList.remove("disabled");
				   document.querySelector("#hide-text").classList.remove("disabled");
				   document.getElementById("hide-date").style.display = "block";
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
                    document.getElementById("hide-date").style.display = "none";
                   document.getElementById("lableName").innerHTML = document.getElementById("input-form").value;
                   document.getElementById("placeholderChange").setAttribute("placeholder", "Enter New value to that element");
                   document.getElementById("input-form").style.borderColor = "blue";
                   document.getElementById("select-error").innerText = "";
                   document.getElementById("select-error").style.color = "red";
               }
 })

document.getElementById('ProjectId1').addEventListener('change', () => {

	document.getElementById('ProjectId1').disabled = true;
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
function submitForm() {
	console.log(document.getElementById('MyTable').hasChildNodes);
	if (document.getElementById('MyTable').hasChildNodes != null) {
		document.querySelector('#ProcessId').classList.remove('disabled');
	}

	var json =
	{
		ProjectId: document.getElementById('ProjectId1').value,
		ItemName: document.getElementById('ItemName').value,
		tagNo: document.getElementById('TagNo').value,
		remarks: document.getElementById('Remarks').value,
		quantity: document.getElementById('Quantity').value,
		delivaryDate: document.getElementById('DelivaryDate').value,
		TotalPrice: JSON.stringify(parseInt(document.getElementById('Quantity').value) * 0)
	}
	data.push(json);
	console.log(json);
	console.log(data);
	appendFunc();
	
	
}

window.onload = function getProjects(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSItemServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendProjects(Data)
  		}
	}
  	var data = { token:"Projects" }
	xhr.send(JSON.stringify(data));	
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
/*
function Demo(tt){
	EditId=parseInt(tt.slice(4));
	console.log("Edit Id :"+EditId)
}

function Demo1(tt){
	deleteId=parseInt(tt.slice(6));
	console.log("Delete Id :"+deleteId)
}

function updateField(){
	var fieldToChange = document.getElementById('input-form').value;
	var newValue = document.getElementById('placeholderChange').value;
	
	console.log("fieldToChange "+fieldToChange)
	console.log("newValue  "+newValue)
	
	if(fieldToChange == "ItemName"){
		data[EditId-1].ItemName = newValue;
	}else if(fieldToChange == "tagNo"){
		data[EditId-1].tagNo = newValue;
	}else if(fieldToChange == "quantity"){	
		data[EditId-1].quantity = newValue;
	}else{
		var Value = document.getElementById('placeholderChange1').value;
		data[EditId-1].delivaryDate = Value;
	}
	console.log(data)
	for (var i = 0; i < data.length; i++) {
		document.getElementById(`ItemName${i + 1}`).innerHTML = data[i].ItemName;

		document.getElementById(`TagNo${i + 1}`).innerHTML = data[i].tagNo;

		document.getElementById(`Quantity${i + 1}`).innerHTML = data[i].quantity;

		document.getElementById(`DelivaryDate${i + 1}`).innerHTML = data[i].delivaryDate;


	}
	
}


function deleteItem(){
	console.log("delete Id -------->"+deleteId)
	data.splice((deleteId-1),1);
	var parent = document.getElementById('MyTable')
	//var child = parent.querySelector('tr')
	//parent.removeChild(child);
	document.getElementById(`Edit${deleteId}`).removeChild('tr')
	parent.innerHTML = "";
	for (var i = 0; i < data.length; i++) {	
		document.getElementById(`ItemName${i + 1}`).innerHTML = data[i].ItemName;

		document.getElementById(`TagNo${i + 1}`).innerHTML = data[i].tagNo;

		document.getElementById(`Quantity${i + 1}`).innerHTML = data[i].quantity;

		document.getElementById(`DelivaryDate${i + 1}`).innerHTML = data[i].delivaryDate;

	}
	console.log(data)
}

*/




function XHRRequestForItem() {

	document.getElementById('ProjectId1').disabled = false;
	document.getElementById('ItemName').disabled = true;
	document.getElementById('TagNo').disabled = true;
	document.getElementById('Remarks').disabled = true;
	document.getElementById('Quantity').disabled = true;
	document.getElementById('DelivaryDate').disabled = true;
	document.querySelector('#AddItemBtn').classList.add('disabled');
	document.querySelector('#ProcessId').classList.add('disabled')
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSItemServlet', true);

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







var deleteValue = "";
function deleteItem(deleteId) {
	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {
	data.splice(((deleteValue.substring(6)) - 1), 1);
	appendFunc();
});

let editValue = "";
function editFinction(editId) {
	console.log(editId);
	editValue = editId;
}

function updateField() {
	EditId = parseInt(editValue.substring(4));
	var fieldToChange = document.getElementById('input-form').value;
	var newValue = document.getElementById('placeholderChange').value;
	console.log("fieldToChange "+fieldToChange)
	console.log("newValue  "+newValue)
	console.log("hiiiii"+editValue)
	if(fieldToChange == "ItemName"){
		data[EditId-1].ItemName =newValue;
	}else if(fieldToChange == "tagNo"){
		data[EditId-1].tagNo = newValue;
	}else if(fieldToChange == "quantity"){	
		data[EditId-1].quantity = newValue;
	}else{
		var Value = document.getElementById('placeholderChange1').value;
		data[EditId-1].delivaryDate = Value;
	}
	console.log(data)
	appendFunc();
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
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
	appendFunc();*/
}

function appendFunc() {
	
	document.getElementById("MyTable").innerHTML = "";
	var table = document.getElementById("MyTable");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");

		newRow.innerHTML = `
    		<td id="${i + 1}">${i + 1}</td>
    		<td><a id="ItemName${i + 1}">${data[i].ItemName}</a> <br></td>
    		<td><a id="Quantity${i + 1}">${data[i].quantity}</a> <br></td>
    		<td><a id="TagNo${i + 1}">${data[i].tagNo}</a> <br></td>
    		<td><a id="DelivaryDate${i + 1}">${data[i].delivaryDate}</a> <br></td>
    		 <td class="project-actions text-right">
                  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modal-editItem" onclick="editFinction(this.id)" id="Edit${i+1}">
                        <i class="fas fa-pencil-alt"></i>
                  </button>
                  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" onclick="deleteItem(this.id)" id="Delete${i+1}">
                        <i class="fas fa-trash"></i>
                  </button>
              </td>
    		`;
		table.appendChild(newRow);
	}
}














