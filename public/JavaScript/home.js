


$(document).ready(function() {
var username = localStorage.getItem("username");
console.log(username);
    if (username == ""){
        var url = "index.html";
        window.open(url, "_self");
    }
    
    console.log(username);

    $("#nav_username").text(username);
    
})