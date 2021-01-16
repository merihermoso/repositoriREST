var username = localStorage.getItem("username")


$(document).ready(function() {
    if (username == "null"){
        var url = "login.html";
        window.open(url, "_self");
    }
    
    console.log(username);

    $("#nav_username").text(username);
    
})