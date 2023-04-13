
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
		remark: document.getElementById('Remarks').value,
		quantity: document.getElementById('Quantity').value,
		TotalPrice:0,
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
	editValue = editValue.substring(4)
	console.log("edited id : " + editValue);
	var fieldToChange = document.getElementById('input-form').value;
	
	console.log("fieldToChange ", fieldToChange);
	
	if (fieldToChange == "ItemName") {
		data[editValue - 1].ItemName = document.getElementById("placeholderChange").value;
	} else if (fieldToChange == "quantity") {
		data[editValue - 1].quantity = document.getElementById("placeholderChange").value;
	} else if (fieldToChange == "remark") {
		data[editValue - 1].remark = document.getElementById("RemarkModel").value;
	} 
	console.log(data)
	appendFunc();
	document.getElementById("modal-editItem").click();
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
    		<td><a id="Quantity${i + 1}">${data[i].remark.length >= 25 ? data[i].remark.substring(0,23).concat("...") : data[i].remark}</a> <br></td>
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
	console.log("------------>")
	console.log(data)
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://192.168.1.130:8080/EMS2/EMSOffersServlet',true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
  		if (xhr.status === 200) {
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

document.getElementById("input-form").addEventListener("change", () => {

	let value = document.getElementById("input-form").value;
	console.log(value);
	if (value == "selected") {
		document.getElementById("input-form").style.borderColor = "red";
		document.getElementById("select-error").innerText = "Please, Select any one optoins.";
		document.getElementById("select-error").style.color = "red";
		document.getElementById("hide-text").style.display = "none";
		document.querySelector("#input-update").classList.add("disabled");
		document.getElementById("hide-area").style.display = "none  ";
	}
	else if (value == "remark") {
		document.querySelector("#input-update").classList.remove("disabled");
		document.getElementById("hide-area").style.display = "block";
		document.getElementById("hide-text").style.display = "none";
		//document.getElementById("lableName1").innerHTML = document.getElementById("input-form").value;
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
		document.getElementById("hide-area").style.display = "none";
	}
})



