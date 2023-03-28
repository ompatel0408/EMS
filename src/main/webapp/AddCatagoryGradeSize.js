let target = document.getElementById("append-here");
let appendFlag = false;
var dataFromServer = document.querySelector('#data-from-server').value;
console.log(dataFromServer); // outputs "Hello from the server!"
document.getElementById("add-category").addEventListener("click", () => {
	if (target.hasChildNodes() == true) {
		target.removeChild(target.lastElementChild);
		appendFlag = false;
	}
	if (!appendFlag) {
		let value = document.createElement('div');
		value.classList.add("form-group");
		value.innerHTML = `
                        <div class="w-50">
                            <label>Category</label>
                            <input type="text" class="form-control" id="catagoryName" placeholder="Enter Category" name="catagoryName">
                        </div>`
		target.append(value);
		appendFlag = true;
	}
});

document.getElementById("add-grade").addEventListener("click", () => {
	var dataFromServer = document.querySelector('#data-from-server').value.replace("[", "").replace("]", "");
	var dataFromServerId = document.querySelector('#data-from-serverId').value.replace("[", "").replace("]", "");
	console.log(dataFromServer); // outputs "Hello from the server!"
	let newData = dataFromServer.split(",");
	let id = dataFromServerId.split(",");
	console.log(newData);
	if (target.hasChildNodes() == true) {
		target.removeChild(target.lastElementChild);
		appendFlag = false;
	}
	if (!appendFlag) {
		let value = document.createElement('div');
		value.classList.add("form-group");
		value.innerHTML = `
                        <label for="item-id">Category</label>
                        <select type="text" id="item-id" class="form-control w-50" required name="selectField">
                            <option value="null" selected>Select Category</option>
                        </select>
                        <div class=" w-50">
                            <label>Grade</label>
                            <input type="text" class="form-control" id="" placeholder="Enter Grade" name="GradeName">
                        </div>`
		target.append(value);
		appendFlag = true;

		for (let i = 0; i < newData.length; i++) {
			let opt = document.createElement("option");
			opt.innerHTML = newData[i];
			opt.setAttribute("value", id[i]);
			document.getElementById("item-id").appendChild(opt);
			console.log(opt);
		}
	}
});

document.getElementById("add-size").addEventListener("click", () => {
	if (target.hasChildNodes() == true) {
		target.removeChild(target.lastElementChild);
		appendFlag = false;
	}
	if (!appendFlag) {
		var dataFromServer = document.querySelector('#data-from-server').value.replace("[", "").replace("]", "");
		var dataFromServerId = document.querySelector('#data-from-serverId').value.replace("[", "").replace("]", "");
		console.log(dataFromServer); // outputs "Hello from the server!"
		let id = dataFromServerId.split(",");
		var newData = dataFromServer.split(",");
		console.log(newData);

		let value = document.createElement('div');
		value.classList.add("form-group");
		value.innerHTML = `
							<div>
                            <label for="">Select Category</label>
                            <select  id="size-id1" class="form-control w-50" required name="selectFieldForGrade" onchange="Demo()">
                                <option value="null" selected>Select Category</option>
                            </select>
                             <label for="">Select Grade</label>
                                <select type="text" id="gradeIdApped" class="form-control w-50" required name="GradeFieldName">
                                <option value="null" selected>Select Grade</option>
                            </select>
                            <div class=" w-50">
                                <label>Insert Size</label>
                                <input type="text" class="form-control" id="" placeholder="Enter Size" name="size">
        
                            </div>`
			;



		console.log()
		target.append(value);
		appendFlag = true;

		for (let i = 0; i < newData.length; i++) {
			let opt = document.createElement("option");
			opt.innerHTML = newData[i];
			opt.setAttribute("value", id[i]);
			document.getElementById("size-id1").appendChild(opt);
			console.log(opt);

		}
	}
});
function Demo() {
	// create a new XMLHttpRequest object
	console.log('Reached!!!!')
	let id = document.getElementById('size-id1').value
	const xhr = new XMLHttpRequest();
	
	xhr.open("POST", "http://localhost:8080/EMS2/GetGradeListServlet",true);
	xhr.setRequestHeader("Content-Type", "application/json");
	
	xhr.onload = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				if (xhr.responseText.length > 0) {
					const data = JSON.parse(xhr.responseText);
					// process the data
					console.log(data)
					document.getElementById('gradeIdApped').innerHTML = "";
					for (let i = 0; i < data.length; i++) {
						let opt = document.createElement("option");
						opt.innerHTML = data[i];
						opt.setAttribute("value", data[i]);
						document.getElementById("gradeIdApped").appendChild(opt);
					}
				} else {
					console.error("Response from server is empty");
				}
			} else {
				console.error("Error occurred while making the request");
			}
		}
	}

	xhr.send(JSON.stringify({ query: "SELECT * FROM catagorygrade where catagoryId=" + id }));


}