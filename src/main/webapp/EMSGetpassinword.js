window.onload = () => {
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSGetpassinwordServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendVendors(Data)
		}
	}
	xhr.send(JSON.stringify({ Token: "Vendors" }));
}


function appendVendors(vendor)
{
	var vendorSelect = document.getElementById("vendorId");
	console.log(vendorSelect)
	vendorSelect.innerHTML = `<option value="select vendor"selected>Select Vendor</option>`;
	for(let i=0; i<vendor.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = vendor[i];
		createdAt.innerHTML = vendor[i];
		vendorSelect.appendChild(createdAt);
	}
}

document.getElementById("vendorId").addEventListener("change",() => {
	
	var xhr = new XMLHttpRequest();
	let Data;
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSGetpassinwordServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data);
			appendItems(Data)
		}
	}
	xhr.send(JSON.stringify({ Token: "Items", Vendor : document.getElementById("vendorId").value}));
})

function appendItems(item)
{
	var itemSelect = document.getElementById("itemsId");
	console.log(itemSelect)
	itemSelect.innerHTML = `<option value="select item"selected>Select Items</option>`;
	for(let i=0; i<item.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = item[i];
		createdAt.innerHTML = item[i];
		itemSelect.appendChild(createdAt);
	}
}














