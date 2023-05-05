try {
	window.onload = function() {
		let Data1;
		var xhr1 = new XMLHttpRequest();
		xhr1.open('PUT', 'http://localhost:8080/EMS/EMSItemListServlet', true);
		xhr1.onload = function() {
			if (xhr1.status === 200) {
				Data1 = JSON.parse(xhr1.responseText);
				console.log(Data1)
				if (document.getElementById("liveorders") != null) {
					document.getElementById("liveorders").innerHTML = Data1
				}
			}
		}

		xhr1.send(JSON.stringify({ token: "orderSum" }));

		let Data2;
		var xhr2 = new XMLHttpRequest();
		xhr2.open('PUT', 'http://localhost:8080/EMS/EMSDirectorsDashboardServlet', true);
		xhr2.onload = function() {
			if (xhr2.status === 200) {
				Data2 = JSON.parse(xhr2.responseText);
				document.getElementById('TotalUser').innerHTML = Data2;
			}
		}
		xhr2.send(JSON.stringify({ Token: "Hii" }));

		let proCount = 0;
		var xhr3 = new XMLHttpRequest();
		xhr3.open("PUT", "http://localhost:8080/EMS/EMSDispatchItemsListServlet", true);
		xhr3.onload = () => {
			if (xhr3.status == 200) {
				proCount = JSON.parse(xhr3.responseText);
				console.log(proCount);
				document.getElementById("projectCount").innerText = proCount;
			}
		}
		xhr3.send(JSON.stringify({ Token: "proCount" }));
	}
}catch(e)
{
	console.log(e);
}
