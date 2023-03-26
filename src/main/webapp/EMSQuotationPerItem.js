
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
	
	document.getElementById("MyTable").innerHTML = "";
	var table = document.getElementById("MyTable");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");

		newRow.innerHTML = `
		
			<tr>
              	<td id="${i + 1}">${i + 1}</td>
                <td><a id="OfferName${i + 1}"></a> <br></td>
                <td><a id="catagory${i+1}"></a> <br></td>
                <td><a id="grade${i+1}"></a> <br></td>
                <td><a id="size${i+1}"></a> <br></td>
                <td><a id="quantity${i+1}"></a> <br></td>
                <td><a id="unit${i+1}"></a> <br></td>
                <td><a id="waight${i+1}"></a> <br></td>
                <td><a id="price${i+1}"></a> <br></td>
                <td class="project-actions text-right">
                      <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#modal-editItem" id="Edit${i+1}">
                              <i class="fas fa-pencil-alt"></i>
                       </button>
                       <button type="button" class="btn btn-sm btn-danger toastrDefaultError" id="Delete${i+1}">
                               <i class="fas fa-trash"></i>
                       </button>
                 </td>
            </tr>
    		`;
		table.appendChild(newRow);

		document.getElementById(`OfferName${i + 1}`).innerHTML = data[i].OfferName;

		document.getElementById(`catagory${i + 1}`).innerHTML = data[i].catagory;

		document.getElementById(`grade${i + 1}`).innerHTML = data[i].grade;

		document.getElementById(`size${i + 1}`).innerHTML = data[i].size;
		
		document.getElementById(`quantity${i + 1}`).innerHTML = data[i].quantity;

		document.getElementById(`waight${i + 1}`).innerHTML = data[i].waight;
		
		document.getElementById(`unit${i + 1}`).innerHTML = data[i].unit;
		
		document.getElementById(`price${i + 1}`).innerHTML = data[i].price;
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
	console.log("ABC")
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
	xhr.open('GET', 'http://localhost:8080/EMS2/EMSQuotationPerItemServlet',true);
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
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSQuotationPerItemServlet',true);
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
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSQuotationPerItemServlet',true);
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
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSQuotationPerItemServlet',true);
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

function appendSize(sizeData){
	
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
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSQuotationPerItemServlet', true);

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
	document.getElementById("MyTable").innerHTML = "";
	data=[]
}




