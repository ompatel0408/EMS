
window.onload = () =>{
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSPurchaseServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendVendors(Data)
		}
	}
	xhr.send(JSON.stringify({ Token: "Vendors" }));
}

function appendVendors(projects) {

	var projectsSelect = document.getElementById("vendor-id");
	console.log(projectsSelect)
	
	projectsSelect.innerHTML = `<option value="select Vendor"selected>Select vendor</option>`;
	
	for (let i = 0; i < projects.length; i++) {
		let createdAt = document.createElement("option");
		createdAt.value = projects[i];
		createdAt.innerHTML = projects[i];
		projectsSelect.appendChild(createdAt);
	}
}

function enableFields(){
	
	document.getElementById("rate-id").disabled = false;
	document.getElementById("remark-Id").disabled = false;
	document.getElementById("Quantity-id").disabled = false;
	document.getElementById("vendor-id").disabled = false;
	document.getElementById("dateid").disabled = false;
	document.getElementById("itemName").disabled = false;
	document.getElementById("DiscountAmount").disabled = false;
	document.getElementById("DiscountPercentage").disabled = false;
	document.querySelector('#add-store').classList.remove('disabled');
	document.querySelector('#processTo').classList.remove('disabled')
}

var amount;
function calculateDiscountAmount() {
	
	amount = parseFloat(document.getElementById('rate-id').value) * parseInt(document.getElementById("Quantity-id").value)
	document.getElementById('DiscountAmount').disabled = true;
	document.getElementById('DiscountAmount').value = parseFloat(amount) * (parseFloat(document.getElementById('DiscountPercentage').value) / 100);
	document.getElementById('Totalamount-id').value = parseFloat(amount) - (parseFloat(amount) * (parseFloat(document.getElementById('DiscountPercentage').value) / 100));
}

function calculateDiscountPercentage() {
	document.getElementById('DiscountPercentage').disabled = true;
	document.getElementById('DiscountPercentage').value = (parseFloat(document.getElementById('DiscountAmount').value) / parseFloat(amount)) * 100;
	document.getElementById('Totalamount-id').value = parseFloat(amount) - parseFloat(document.getElementById('DiscountAmount').value)
}


var gstAmount;
var gst;
var count = 0;
var originalAmount;
function clickOfRadioButton() {
	count++;
	if(count <=1){
		originalAmount = parseFloat(document.getElementById('Totalamount-id').value);	
	}
	
	console.log("originalAmount :"+originalAmount)
	var radioButton = document.getElementsByName('chks');
	for (var i = 0; i < radioButton.length; i++) {
		if (radioButton[i].checked) {
			gstAmount = originalAmount * (parseFloat(radioButton[i].value) / 100)
			gst = parseFloat(radioButton[i].value)
			break;
		}
	}
	console.log("GST :"+gst)
	console.log("GST Amount :"+gstAmount)
	console.log("Total Amount :"+(gstAmount + originalAmount))
	document.getElementById('Totalamount-id').value = gstAmount + originalAmount;
}

var data = []

function submitForm(){
	
	var json = {
		itemName:document.getElementById("itemName").value,
		category:document.getElementById("CategoryName").value,
		rate:document.getElementById("rate-id").value,
		quantity:document.getElementById("Quantity-id").value,
		remarks:document.getElementById("remark-Id").value,
		vendor:document.getElementById("vendor-id").value,
		currentDate:document.getElementById("dateid").value,
		totalAmount:document.getElementById('Totalamount-id').value,
		discountAmount:document.getElementById('DiscountAmount').value,
		GST:gst
	}
	
	data.push(json);
	appendFunction(data)
	count = 0;
}

function appendFunction(data){
	
	document.getElementById("MyTable16").innerHTML = "";
	
	var table = document.getElementById("MyTable16");
	
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML =
			`<td id="${i + 1}">${i + 1}</td>
                <td><a id="itemName${i + 1}">${data[i].itemName}</a> <br></td>
                <td><a id="catagory${i+1}">${data[i].category}</a> <br></td>
                <td><a id="rate${i+1}">${data[i].rate}</a> <br></td>
                <td><a id="quantity${i+1}">${data[i].quantity}</a> <br></td>
                <td><a id="vendor${i+1}">${data[i].vendor}</a> <br></td>
                <td><a id="totalPrice${i+1}">${data[i].totalAmount}</a> <br></td>
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


function submitData(){
	
	var xhr = new XMLHttpRequest();

	// specify the servlet URL and HTTP method
	xhr.open('POST', 'http://192.168.1.9:8080/EMS/EMSConsumablePurchase', true);

	// set headers
	xhr.setRequestHeader('Content-type', 'application/json');

	// handle the response
	xhr.onload = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var response = xhr.responseText;
			console.log(response);
			window.location.href = "EMSDirectorsDashboard.jsp";
		}
	}
	// send the request
	xhr.send(JSON.stringify(data));
}


