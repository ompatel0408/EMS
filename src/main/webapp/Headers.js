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

function getNotificationForPayment(){
	var data1;
	var xhr1 = new XMLHttpRequest();
	xhr1.open('PUT', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet', true);
	xhr1.onload = function() {
		if (xhr1.status === 200) {
			data1 = JSON.parse(xhr1.responseText);
			console.log(data1)
			appendNotifications1(data1);
		}
	}
	xhr1.send(JSON.stringify({ Token: "Notify1" }));
}


function appendNotifications(data) {
	
	document.getElementById("Notification").innerHTML = "";
	var table = document.getElementById("Notification");
	table.innerHTML = `<span class='dropdown-item dropdown-header'>${data.length} Notifications</span>`;
	for (var i = 0; i < data.length; i++) {
			var newRow = document.createElement("span");
			newRow.innerHTML =
			`<div class="dropdown-item">
				You need to pay ${data[i].sumTotalAmount} INR To ${data[i].vendorName}
				<button type="button" class="btn btn-danger btn-xs float-right" id="${data[i].vendorName}" onclick="deleteNotification(this.id)">Paid</button>
			</div>`;		
			table.appendChild(newRow);
	}
}



function appendNotifications1(data) {
	
	document.getElementById("Notification1").innerHTML = "";
	var table = document.getElementById("Notification1");
	table.innerHTML = `<span class='dropdown-item dropdown-header'>${data.length} Notifications</span>`;
	for (var i = 0; i < data.length; i++) {
		var newRow = document.createElement("span");
		
		newRow.innerHTML =
			`<div class="dropdown-item">
				we need to have a conversation with our client regarding the payment for the project ${data[i].projectId}.<br>
				We have to request for a payment of ${data[i].remainingAmount} INR.				
				<button type="button" class="btn btn-primary btn-xs float-right" id="${data[i].projectId}" onclick="deleteNotification1(this.id)">Paid</button>
			</div>`;
		table.appendChild(newRow);	
		
	}
}


function deleteNotification1(projectId){
	console.log("Project Id :"+projectId)
	var data1;
	var xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'http://localhost:8080/EMS2/EMSDirectorsDashboardServlet', true);
	xhr.onload = function() {
		if (xhr.status === 200) {
			data1 = JSON.parse(xhr.responseText);
			window.location.href = "EMSDirectorsDashboard.jsp";
		}
	}
	xhr.send(JSON.stringify({name:projectId,Token:"Hello"}));
}

