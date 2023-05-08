var data = [];
var EditId;
let editValue = "";
var myMap = new Map();
var myArray = []

function getProjects() {
document.querySelector('#add_Quatation').classList.remove('disabled');	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS/EMSGRNApprovalPending', true);
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

	if (document.getElementById("MaterialCat").value == 'projectDependent') {
		document.getElementById('projectId').disabled = false;
		document.getElementById('Quantity').disabled = false;
		var projectsSelect = document.getElementById("projectId");
		console.log(projectsSelect)
		projectsSelect.innerHTML = `<option value="select category"selected>Select Project</option>`;
		for (let i = 0; i < projects.length; i++) {
			let createdAt = document.createElement("option");
			createdAt.value = projects[i];
			createdAt.innerHTML = projects[i];
			projectsSelect.appendChild(createdAt);
		}
	}else{
		document.getElementById('category-id').disabled = false;
		document.getElementById('grade-id').disabled = false;
		document.getElementById('size-id').disabled = false;
		document.getElementById('Quantity').disabled = false;
		document.getElementById('unit-id').disabled = false;
	}
}

function getAllPurchaseDetails()
{
		
	let Data;
	var xhr = new XMLHttpRequest();
		xhr.open('PUT', 'http://localhost:8080/EMS/EMSGRNApprovalPending', true);
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data)
			appendJsonData(Data)
		}
	}
	if(localStorage.getItem("isReceived") == 'true'){
		localStorage.setItem("isReceived",false)	
		xhr.send(JSON.stringify({projectId:document.getElementById('projectId').value,Token:'AllDetailsFromGRNApproval'}));
		
	}else{
		xhr.send(JSON.stringify({projectId:document.getElementById('projectId').value,Token:'AllDetails'}));	
	}
}


function appendJsonData(response) {
	
	let counter = 0;
	for (let i = 0; i < response.length; i++) {

		var json = {
			Category:response[i].categoryName,
			Grade:response[i].gradeName,
			size:response[i].size,
			originalQuantity:response[i].quantity,
			Quantity:response[i].quantity,
			units:response[i].units,
			count: ++counter,
		}
		data.push(json)
		console.log(data)
	}

	appendFunc()
}
function appendFunc() {

	if (document.getElementById('MyTable18').hasChildNodes != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	}

	document.getElementById("MyTable18").innerHTML = "";
	var table = document.getElementById("MyTable18");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.setAttribute('id', `tr${i + 1}`);
		myMap.set(i+1,data[i])
		myArray.push(i+1);
		newRow.innerHTML = `
                              <td id="${i + 1}">${i + 1}</td>
                              <td><a id="category1-${i + 1}"> ${data[i].Category} </a> <br></td>
                              <td><a id="grade1-${i + 1}"> ${data[i].Grade} </a> <br></td>
                              <td><a id="size1-${i + 1}"> ${data[i].size} </a> <br></td>
                              <td><a id="quantity1-${i + 1}"> ${data[i].originalQuantity} </a> <br></td>
                              <td><a id="units1-${i + 1}"> ${data[i].units} </a> <br></td>                       
                              <td class="project-actions text-right">
                               <button type="button" class="btn btn-success btn-sm" onclick = "updateField(this.id)" id="Edit${i + 1}">
                                      <i class="fas fa-pencil-alt"></i>
                              </button>
                              <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" id="Delete${i + 1}" onclick="deleteItem(this.id)">
                                      <i class="fas fa-trash"></i>
                              </button>
								</td>   
                              `;
		table.appendChild(newRow);
	}
}

function updateField(editId) {
	editValue = editId;
	
	EditId = parseInt(editValue.substring(4));
	
	console.log(data)	
	
	var js1 = data.filter(x => x.count == EditId)
	
	document.getElementById('category-id').value = js1[0].Category;
	document.getElementById('grade-id').value = js1[0].Grade,
	document.getElementById('size-id').value = js1[0].size;
	document.getElementById('Quantity').value = js1[0].originalQuantity;
	document.getElementById('unit-id').value = js1[0].units;
	document.getElementById('quantity1').value = js1[0].Quantity
	qty = js1[0].originalQuantity;
}



var submitData = []
var qty = 0;
function submitForm() {
	
	console.log("Edit Id :"+EditId)
	
	var js1 = data.filter(x => x.count == EditId)
	
	console.log(js1)
	
	js1[0].Quantity = document.getElementById('Quantity').value;
	
	var json =
	{
			projectId:document.getElementById('projectId').value,
			MaterialCategory:document.getElementById('MaterialCat').value,
			Category:document.getElementById('category-id').value,
			Grade:document.getElementById('grade-id').value,
			size:document.getElementById('size-id').value,
			Quantity:document.getElementById('Quantity').value,
			originalQuantity: JSON.stringify(parseInt(document.getElementById('quantity1').value)  - parseInt(document.getElementById('Quantity').value)),
			units:document.getElementById('unit-id').value
	}
	
	submitData.push(json)
	console.log(submitData)
	appendMap();
	
	
}



function appendMap(){
	
	
	if (document.getElementById('MyTable18').hasChildNodes != null) {
		document.querySelector('#processTo').classList.remove('disabled');
	}

	document.getElementById("MyTable18").innerHTML = "";	
	console.log(myMap)
	
	var table = document.getElementById("MyTable18");
	for (var i = 0; i < myArray.length; i++) {
		var newRow = document.createElement("tr");
		newRow.setAttribute('id', `tr${myArray[i]}`);
		newRow.innerHTML = `<td id="${myArray[i]}">${myArray[i]}</td>
                              <td><a id="category1-${myArray[i]}">${myMap.get(myArray[i]).Category}</a> <br></td>
                              <td><a id="grade1-${myArray[i]}"> ${myMap.get(myArray[i]).Grade} </a> <br></td>
                              <td><a id="size1-${myArray[i]}"> ${myMap.get(myArray[i]).size} </a> <br></td>
                              <td><a id="quantity1-${myArray[i]}"> ${myMap.get(myArray[i]).Quantity} </a> <br></td>
                              <td><a id="units1-${myArray[i]}"> ${myMap.get(myArray[i]).units} </a> <br></td>                       
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

var deleteValue = "";
function deleteItem(deleteId) {
	deleteValue = deleteId;
}
document.getElementById("deleteClicked").addEventListener("click", () => {


	var deleteId1 = parseInt(deleteValue.substring(6));
	var js1 = data.filter(x=>x.count == deleteId1)
	
	document.getElementById(`tr${js1[0].count}`).remove();
	
	
	data = data.filter(x=>x.count !== deleteId1)
	myArray = myArray.filter(x=>x !== deleteId1)
	
	document.getElementById("closeMdl").click();
	
});

function submitFormToServlet()
{
	
	document.getElementById('projectId').disabled = true;
	document.getElementById('category-id').disabled = true;
	document.getElementById('grade-id').disabled = true;
	document.getElementById('size-id').disabled = true;
	document.getElementById('Quantity').disabled = true;
	document.getElementById('unit-id').disabled = true;
	document.querySelector('#add_Quatation').classList.add('disabled');
	document.querySelector('#processTo').classList.add('disabled');

	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS/EMSGRNApprovalPending', true);
	
	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response
	xhr.onload = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			console.log(response);
			window.location.href = "EMSDirectorsDashboard.jsp"
			localStorage.setItem("isReceived",true);
		}
	}
	// send the request
	xhr.send(JSON.stringify(submitData));
}

document.getElementById('Quantity').addEventListener('keydown',(event)=>{
	console.log('quanityt' + qty)
	if (parseInt(event.key) >= 0 && parseInt(event.key) <= 9) { // Check if the key pressed is a number
    const newQuantity = parseInt(document.getElementById('Quantity').value + event.key);
    
    if (newQuantity > qty) {
      event.preventDefault();
      document.getElementById('Quantity').style.borderWidth='1px'
      document.getElementById('Quantity').style.borderColor='red'
      document.getElementById('Quantity').style.borderStyle='solid'
    }
    else if (newQuantity <= qty){
		document.getElementById('Quantity').style.borderWidth='1px'
      document.getElementById('Quantity').style.borderColor='grey'
      document.getElementById('Quantity').style.borderStyle='solid'
	}
  }
})
