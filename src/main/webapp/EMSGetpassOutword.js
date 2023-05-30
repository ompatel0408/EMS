window.onload = () => {
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS/EMSAddPersonServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			appendVendors(Data)
		}
	}
	xhr.send(JSON.stringify({ Token: "Persons" }));
}

function appendVendors(vendor)
{
	var vendorSelect = document.getElementById("vendorId");
	console.log(vendorSelect)
	vendorSelect.innerHTML = `<option value="select vendor"selected>Select Persons</option>`;
	for(let i=0; i<vendor.length; i++)
	{
		let createdAt = document.createElement("option");
		createdAt.value = vendor[i];
		createdAt.innerHTML = vendor[i];
		vendorSelect.appendChild(createdAt);
	}
}