


$(document).ready(function() {
var username = localStorage.getItem("username");
console.log(username);
    if (username != "null"){
        var url = "home.html";
        window.open(url, "_self");
    }
    
    console.log(username);

    $("#nav_username").text(username);
    
})