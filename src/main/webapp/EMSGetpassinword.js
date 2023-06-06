window.onload = () => {
	
	let Data;
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSGetpassinwordServlet', true);
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
	vendorSelect.innerHTML = `<option value="select vendor"selected>Select persons</option>`;
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
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSGetpassinwordServlet', true);
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
var qunatity=0
document.getElementById("itemsId").addEventListener("change",() => {
	console.log("Here")
	console.log(document.getElementById("itemsId").value)
	var xhr = new XMLHttpRequest();
	let Data;
	xhr.open('PUT', 'http://192.168.1.9:8080/EMS/EMSGetpassinwordServlet', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
		if (xhr.status === 200) {
			Data = JSON.parse(xhr.responseText);
			console.log(Data);
			document.getElementById('Quantity').value=Data
			quantity=Data
		}
	}
	xhr.send(JSON.stringify({ Token: "quantity", Vendor : document.getElementById("vendorId").value,items:document.getElementById("itemsId").value}));
})


document.getElementById('Quantity').addEventListener('keydown',(event)=>{
	console.log('quanityt')
	if (parseInt(event.key) >= 0 && parseInt(event.key) <= 9) { // Check if the key pressed is a number
    const newQuantity = parseInt(document.getElementById('Quantity').value + event.key);
    if (newQuantity > quantity) {
      event.preventDefault();
      document.getElementById('Quantity').style.borderWidth='1px'
      document.getElementById('Quantity').style.borderColor='red'
      document.getElementById('Quantity').style.borderStyle='solid'
      yourApiFunction();
    }
    else if (newQuantity <= quantity){
		document.getElementById('Quantity').style.borderWidth='1px'
      document.getElementById('Quantity').style.borderColor='grey'
      document.getElementById('Quantity').style.borderStyle='solid'
	}
  }
})

function yourApiFunction(){
  
  //Do your api calls, the "error" variable is only set to demonstrate the use of the success/error message
  let error = false;
  
  if(error){
    showToast("Please Enter the Quantity less than or equal to "+quantity);
  }else{
      showToast("Please Enter the Quantity less than or equal to "+quantity);
  }
}

function showToast(content = "Unknown error") { //You can change the default value
  // Get the snackbar DIV
  var x = document.getElementById("snackbar");
  
  //Change the text (not mandatory, but I think you might be willing to do it)
  x.innerHTML = content;

  // Add the "show" class to DIV
  x.className = "show";

  // After 3 seconds, remove the show class from DIV
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}














