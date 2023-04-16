
var data = [];
var search = [];
var percent;


        

function submitForm(){
	if (document.getElementById('MyTable').hasChildNodes) {
		document.querySelector('#processTo').classList.remove('disabled')
	}
	
	
	var json =
	{
		OfferName: document.getElementById('itemId').value,
		catagory: document.getElementById('categoryId').value,
		grade: document.getElementById('gradeId').value,
		size: document.getElementById('sizeId').value,
		quantity: parseInt(document.getElementById('Quantity').value),
		waight: parseFloat(document.getElementById('waight-id').value),
		unit:document.getElementById('unit-id').value,
		price:parseFloat(document.getElementById('price-id').value),
		percentage:document.getElementById('profit-id').value
	}
	data.push(json)
	search.push(json)
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
                <td><a id="OfferName${i + 1}">${data[i].OfferName}</a> <br></td>
                <td><a id="catagory${i+1}">${data[i].catagory}</a> <br></td>
                <td><a id="grade${i+1}">${data[i].grade}</a> <br></td>
                <td><a id="size${i+1}">${data[i].size}</a> <br></td>
                <td><a id="quantity${i+1}">${data[i].quantity}</a> <br></td>
                <td><a id="unit${i+1}">${data[i].waight}</a> <br></td>
                <td><a id="waight${i+1}">${data[i].unit}</a> <br></td>
                <td><a id="price${i+1}">${data[i].price}</a> <br></td>
                <td class="project-actions text-right">
                      <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#modal-editItem" id="Edit${i+1}" onclick="editFinction(this.id)">
                              <i class="fas fa-pencil-alt"></i>
                       </button>
                       <button type="button" class="btn btn-sm btn-danger toastrDefaultError" data-toggle="modal" data-target="#modal-itemDelete" onclick="deleteItem(this.id)" id="Delete${i+1}">
                               <i class="fas fa-trash"></i>
                       </button>
                 </td>`;
		table.appendChild(newRow);
	}
}

function disableLogic(){
	document.getElementById('itemId').disabled = true;
	document.getElementById('categoryId').disabled = false;
	document.getElementById('gradeId').disabled = false;
	document.getElementById('sizeId').disabled = false;
	document.getElementById('Quantity').disabled = false;
	document.getElementById('waight-id').disabled = false;
	document.getElementById('unit-id').disabled = false;
	document.getElementById('price-id').disabled = false;
	document.getElementById('profit-id').disabled = false;
	document.querySelector('#add_Quatation').classList.remove('disabled');
	if(search.some(item => item.OfferName === document.getElementById('itemId').value)){
		document.getElementById('profit-id').disabled = true;
		document.getElementById('profit-id').value = search.filter(x => x.OfferName === document.getElementById('itemId').value)[0].percentage; 
	}else{
		document.getElementById('profit-id').disabled = false;	
	}
}




function getXHRRequestToQuotationPerItemCatagory(){
	let categoryData;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet',true);
	xhr.onload = function() {
  		if (xhr.status === 200) {	  
    		categoryData = JSON.parse(xhr.responseText);
			appendCategory(categoryData);
  		}
	}
	
	xhr.send();
	
	
}

function appendCategory(categoryData){
	
	console.log(categoryData);
	var categorySelect = document.getElementById("categoryId");
	console.log(categorySelect)

	categorySelect.innerHTML = `<option value="select" selected>Select catagory</option>`;
	for(let i=0; i<categoryData.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = categoryData[i];
		createdAt.innerHTML = categoryData[i];
		categorySelect.appendChild(createdAt);
	}
	
}

function DisablePercentage(){
	document.getElementById('profit-id').disabled = true;
}


function getXHRRequestToQuotationPerItemGrade(){
	
	let gradeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		gradeData = JSON.parse(xhr.responseText);
			appendGrade(gradeData);
  		}
	}
	var data = { category: document.getElementById('categoryId').value,token:"category"}
	xhr.send(JSON.stringify(data));
}

function appendGrade(gradeData){
	
	console.log(gradeData);
	
	var gradeSelect = document.getElementById("gradeId");
	console.log(gradeSelect)
	gradeSelect.innerHTML = `<option value="select" selected>Select grade</option>`;
	for(let i=0; i<gradeData.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = gradeData[i];
		createdAt.innerHTML = gradeData[i];
		gradeSelect.appendChild(createdAt);
	}
}


window.onload = function getOffers(){
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		Data = JSON.parse(xhr.responseText);
    		appendOffers(Data)
  		}
	}
  	var data = { token:"Offers" }
	xhr.send(JSON.stringify(data));	
	
};

function appendOffers(offers){
	console.log(offers);
	var offerSelect = document.getElementById("itemId");
	console.log(offerSelect)
	offerSelect.innerHTML = `<option value="select" selected>Select Offers</option>`;
	for(let i=0; i<offers.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = offers[i];
		createdAt.innerHTML = offers[i];
		offerSelect.appendChild(createdAt);
	}
}



function getXHRRequestToQuotationPerItemSize(){
	let sizeData;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		sizeData = JSON.parse(xhr.responseText);
			appendSize(sizeData);
  		}
	}
	var data = {grade: document.getElementById('gradeId').value,token:"grade"}
	xhr.send(JSON.stringify(data));
}
var sizeArray;
function appendSize(sizeData){
	sizeArray = sizeData;
	console.log(sizeData);
	var sizeSelect = document.getElementById("sizeId");
	console.log(sizeSelect)
	sizeSelect.innerHTML = `<option value="select" selected>Select size</option>`;
	for(let i=0; i<sizeData.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = sizeData[i];
		createdAt.innerHTML = sizeData[i];
		sizeSelect.appendChild(createdAt);
	}
}


window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie=".concat(JSON.stringify(data));
});


function XHRRequestForQuotationPerItem(){
	
	document.getElementById('itemId').disabled = false;
	document.getElementById('categoryId').disabled = true;
	document.getElementById('gradeId').disabled = true;
	document.getElementById('sizeId').disabled = true;
	document.getElementById('Quantity').disabled = true;
	document.getElementById('waight-id').disabled = true;
	document.getElementById('unit-id').disabled = true;
	document.getElementById('price-id').disabled = true;
	document.getElementById('profit-id').disabled = true;
	document.querySelector('#add_Quatation').classList.add('disabled');
	document.querySelector('#processTo').classList.add('disabled');
	
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS/EMSQuotationPerItemServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			console.log(response);
			window.location.href = "QuotationPerItemListServlet?offer=1"
		}
	}
	// send the request
	xhr.send(JSON.stringify(data));
	document.getElementById("MyTable").innerHTML = "";
	data=[]
}


var deleteValue = "";
function deleteItem(deleteId) {
	deleteValue = deleteId;
}

document.getElementById("deleteClicked").addEventListener("click", () => {
	data.splice(((deleteValue.substring(6)) - 1), 1);
	appendFunc();
	document.getElementById("modal-itemDelete").click();
});

let editValue = "";
function editFinction(editId) {
	console.log(editId);
	editValue = editId;
	editValue = parseInt(editId.substring(4));
	console.log(editValue);
}

function updateField() {
	
	console.log("edited id : " + editValue);
	var fieldToChange = document.getElementById('input-form').value;

	console.log("fieldToChange ", fieldToChange);

	switch(fieldToChange)
	{
		case "unit" : data[editValue - 1].unit = document.getElementById("placeholderChange").value;
		break;
		
		case "waight" : data[editValue - 1].waight = document.getElementById("placeholderChange1").value;
		break;
		
		case "price" : data[editValue - 1].price = document.getElementById("placeholderChange1").value;
		break;
		
		case "quantity" : data[editValue - 1].quantity = document.getElementById("placeholderChange1").value;
		break;
	}

	console.log(data)
	appendFunc();
	document.getElementById("modal-editItem").click();
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
                document.getElementById("hide-num").style.display = "none";
            }
            else if(value == "size") {
				console.log("in size");
				
				document.querySelector("#input-update").classList.remove("disabled");
                document.getElementById("hide-text").style.display = "none";
                document.getElementById("lableName1").innerHTML = document.getElementById("input-form").value;
                document.getElementById("placeholderChange1").setAttribute("placeholder", "Enter New value to that element");
                document.getElementById("input-form").style.borderColor = "blue";
                document.getElementById("select-error").innerText = "";
                document.getElementById("select-error").style.color = "red";
                document.getElementById("hide-num").style.display = "none";
                let sizeSelect = document.getElementById("size-id-select");
				console.log(sizeSelect)
				sizeSelect.innerHTML = `<option value="select" selected>Select size</option>`;
				for(let i=0; i<sizeArray.length; i++)
				{
					let createdAt = document.createElement("option");
					createdAt.value = sizeArray[i];
					createdAt.innerHTML = sizeArray[i];
					sizeSelect.appendChild(createdAt);
				}
			}
            else if (value == "unit") {
				console.log("in units");
                document.querySelector("#input-update").classList.remove("disabled");
                document.getElementById("hide-text").style.display = "block";
                document.getElementById("lableName").innerHTML = document.getElementById("input-form").value;
                document.getElementById("placeholderChange1").setAttribute("placeholder", "Enter New value to that element");
                document.getElementById("input-form").style.borderColor = "blue";
                document.getElementById("select-error").innerText = "";
                document.getElementById("select-error").style.color = "red";
                document.getElementById("hide-num").style.display = "none";
            }
            else {
				console.log("in else");
                document.querySelector("#input-update").classList.remove("disabled");
                document.getElementById("hide-text").style.display = "none";
                document.getElementById("hide-num").style.display = "block";
                document.getElementById("lableName1").innerHTML = document.getElementById("input-form").value;
                document.getElementById("placeholderChange").setAttribute("placeholder", "Enter New value to that element");
                document.getElementById("input-form").style.borderColor = "blue";
                document.getElementById("select-error").innerText = "";
                document.getElementById("select-error").style.color = "red";
            }
    })

window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie2=".concat(JSON.stringify(data));
  event.returnValue = "Are you sure you want to leave this page?"
});

