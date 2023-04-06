
document.getElementById('ProjectId1').addEventListener('change', () => {

	document.getElementById('ProjectId1').disabled = true;
	document.getElementById('ItemName').disabled = false;
	document.getElementById('Remarks').disabled = false;
	document.getElementById('Quantity').disabled = false;
	//document.getElementById('DelivaryDate').disabled = false;
	document.querySelector('#AddItemBtn').classList.remove('disabled');
});

var data = []
function submitForm() {
	if (document.getElementById('MyTable2').hasChildNodes) {
		document.querySelector('#ProcessId').classList.remove('disabled')
	}
	
	var json =
	{
		ClientId: document.getElementById('ProjectId1').value,
		ItemName: document.getElementById('ItemName').value,
		remarks: document.getElementById('Remarks').value,
		quantity: document.getElementById('Quantity').value,
		TotalPrice:0,
		//delivaryDate: document.getElementById('DelivaryDate').value,
	}
	data.push(json);
	console.log(json)
	console.log(data);
	appendFunc();
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
	alert("asdfdsaf");
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

	if (fieldToChange == "ItemName") {
		data[editValue - 1].ItemName = document.getElementById("size-id-select").value;
	} else if (fieldToChange == "quantity") {
		data[editValue - 1].size = document.getElementById("size-id-select").value;
	} else if (fieldToChange == "delivaryDate") {
		data[editValue - 1].size = document.getElementById("size-id-select").value;
	} 
	console.log(data)
	appendFunc();
}

function appendFunc() 
{
	document.getElementById("MyTable2").innerHTML = "";
	var table = document.getElementById("MyTable2");
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");

		newRow.innerHTML = `
    		<td id="${i + 1}">${i + 1}</td>
    		<td><a id="ClientName${i + 1}">${data[i].ClientId}</a> <br></td>
    		<td><a id="ItemName${i + 1}">${data[i].ItemName}</a> <br></td>
    		<td><a id="Quantity${i + 1}">${data[i].quantity}</a> <br></td>
    		 <td class="project-actions text-right">
                  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modal-editItem" onclick="editFinction(this.id)" id="Edit${i+1}">
                        <i class="fas fa-pencil-alt"></i>
                  </button>
                  <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#modal-itemDelete" id="Delete${i+1}" onclick="deleteItem(this.id)">
                        <i class="fas fa-trash"></i>
                  </button>
              </td>
    		`;
		table.appendChild(newRow);
	}
}

function XHRRequestForOffer(){
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/EMS2/EMSOffersServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
    		//countSize = JSON.parse(xhr.responseText);
    		console.log(xhr.responseText)
    		window.location.href = "EMSQuotationPerItem.jsp";
  		}
	}
	xhr.send(JSON.stringify(data));
	document.querySelector('#ProcessId').classList.add('disabled')
	document.getElementById("MyTable2").innerHTML = "";
}

window.addEventListener("beforeunload", function (event) {
  event.preventDefault();
  document.cookie = "myCookie2=".concat(JSON.stringify(data));
  event.returnValue = "Are you sure you want to leave this page?"
});



