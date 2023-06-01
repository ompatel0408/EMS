
window.onload = () =>{
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSPurchaseServlet', true);
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
	document.querySelector('#add-store').classList.remove('disabled')
	document.querySelector('#processTo').classList.remove('disabled')
	
}

function submit(){
	
	appendFunction()
}

function appendFunction(){
	
	console.log("Hiiiiii........")
	
	document.getElementById("MyTable16").innerHTML = "";
	
	var table = document.getElementById("MyTable16");
	
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("tr");
		newRow.innerHTML =
			`<td id="${i + 1}">${i + 1}</td>
                <td><a id="OfferName${i + 1}">${data[i]}</a> <br></td>
                <td><a id="catagory${i+1}">${data[i]}</a> <br></td>
                <td><a id="grade${i+1}">${data[i]}</a> <br></td>
                <td><a id="size${i+1}">${data[i]}</a> <br></td>
                <td><a id="quantity${i+1}">${data[i]}</a> <br></td>
                <td><a id="unit${i+1}">${data[i]}</a> <br></td>
                <td><a id="waight${i+1}">${data[i]}</a> <br></td>
                <td><a id="price${i+1}">${data[i]}</a> <br></td>
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


