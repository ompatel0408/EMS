var data = [];
var search = [];
var percent;

function submitForm(){
	if (document.getElementById('MyTable').hasChildNodes) {
		document.querySelector('#processTo').classList.remove('disabled')
	}
	
	
	var json =
	{
		vendorName: document.getElementById('vendorName').value,
		email: document.getElementById('email').value,
		address: document.getElementById('address').value,
		mobile: document.getElementById('mobile').value
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
                <td><a id="vendorName${i + 1}">${data[i].vendorName}</a> <br></td>
                <td><a id="email${i+1}">${data[i].email}</a> <br></td>
                <td><a id="address${i+1}">${data[i].address}</a> <br></td>
                <td><a id="mobile${i+1}">${data[i].mobile}</a> <br></td>
                <td class="project-actions text-right">
                      <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#modal-editItem" id="Edit${i+1}">
                              <i class="fas fa-pencil-alt"></i>
                       </button>
                       <button type="button" class="btn btn-sm btn-danger toastrDefaultError" id="Delete${i+1}" onclick="deleteItem(this.id)">
                               <i class="fas fa-trash"></i>
                       </button>
                 </td>
            </tr>
    		`;
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
	$('#modal-stockDelete').modal('hide');
});

let editValue;
function editFinction(editId) {
	console.log(editId);
	/editValue = editId;/
	editValue = parseInt(editId.substring(4));
}

function updateField(event) {
	event.preventDefault();
	console.log("edited id : " + editValue);
	var fieldToChange = document.getElementById('input-form').value;

	console.log("fieldToChange ", fieldToChange);

	switch(fieldToChange)
	{
		case "unit" : data[editValue - 1].unit = document.getElementById("placeholderChange").value;
		break;
		
		case "size" : data[editValue - 1].size = document.getElementById("size-id-select").value;
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
	$('#modal-editItem').modal('hide');
}
function XHRRequestForVendor(){
	alert('vendorrrrr')
	document.getElementById('vendorName').disabled = false;
	document.getElementById('email').disabled = true;
	document.getElementById('address').disabled = true;
	document.getElementById('mobile').disabled = true;
	document.querySelector('#add_vendor').classList.add('disabled');

	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSVendorsServlet', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response
	xhr.onload = function() {
		if (xhr.status == 200) {
			var response = xhr.responseText;
			console.log(response);
		}
	}
	// send the request
	xhr.send(JSON.stringify(data));
	document.getElementById("MyTable").innerHTML = "";
	data=[]
}