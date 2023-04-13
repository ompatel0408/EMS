

function AddHeaders(){
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            console.log(xhr.responseText);            
        }
    }
  	xhr.open('GET', 'http://localhost:8080/EMS2/EMSItemServlet', true);
 	xhr.send();
};

