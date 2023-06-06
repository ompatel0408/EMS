

function AddHeaders(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            console.log(xhr.responseText);            
        }
    }
  	xhr.open('GET', 'http://192.168.1.9:8080/EMS/EMSItemServlet', true);
 	xhr.send();
};

