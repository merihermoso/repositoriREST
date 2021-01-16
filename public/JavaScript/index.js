var username = localStorage.getItem("username")
if (username == null){
    var url = "login.html";
    window.open(url, "_self");
}

$(document).ready(function() {
    console.log(username);

    
    
    $("#nav_username").text(username);
    
})