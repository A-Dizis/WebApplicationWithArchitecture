

function send() {
	var inp = document.getElementById("inp");
	var xmlhttp = new XMLHttpRequest();



	URL = "http://localhost:9080/platformaDeTesting/rest/testservice/user/" + inp.value; //Your URL

	xmlhttp.open("GET", URL, true);
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send();

}

var button = document.getElementById("spinner");
button.addEventListener("readystatechange", function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		document.getElementById("spinner").classpath = "";
		document.getElementById("spinner").innerHTML = "call";
		document.getElementById("out").innerHTML = xhe.response;
	};
});