
function getNotification() {
	var data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			data = JSON.parse(xhr.responseText);
			appendNotifications(data);
		}
	}
	xhr.send(JSON.stringify({ Token: "Notify" }));
}
function appendNotifications(data) {
	console.log("Hellloooooo")
	document.getElementById("Notification").innerHTML = "";
	var table = document.getElementById("Notification");
	table.innerHTML = `<span class='dropdown-item dropdown-header'>${data.length} Notifications</span>`;
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("span");
		newRow.innerHTML =
			`<div class="dropdown-item">
				You need to pay ${data[i].sumTotalAmount} INR To ${data[i].vendorName}
				<button type="button" class="btn btn-info btn-xs float-right" id="${data[i].vendorName}" onclick="deleteNotification(this.id)">Paid</button>
			</div>`;
		table.appendChild(newRow);
	}
	
}

function deleteNotification(vendorName){
	console.log("vendor Name :"+vendorName)
	var data1;
	var xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			data1 = JSON.parse(xhr.responseText);
			window.location.href = "EMSDirectorsDashboard.jsp";
		}
	}
	xhr.send(JSON.stringify({name:vendorName}));
}