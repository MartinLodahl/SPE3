
    var url = "http://localhost:8084/Persons2/api/person";
    alert(url);
    var conf = {method: 'get'};
    var promise = fetch(url, conf);
    promise.then(function (response) {
        alert("HEY");
        return response.json();
    }).then(function (json) {
        alert("2");
        originalJson = json;
        var html;
        for (var i = 0; i < json.length; i++) {
            alert("3");
            html += "<tr><td>" + json[i].id + "</td><td>" + json[i].fName + "</td><td>" + json[i].lName + "</td><td>" + json[i].phone + "</td></tr>";
        }
        document.getElementById("tblbody").innerHTML = html;
    });
    
    
    document.getElementById("tblbody").innerHTML = "YOLO";