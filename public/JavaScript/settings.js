var BASE_URI="/api";
var username = localStorage.getItem("username");
var diamantes = localStorage.getItem("diamantes");

if (username == null){
    var url = "login.html";
    window.open(url, "_self");
}

$(document).ready(function() {
    console.log(username);
    console.log(diamantes);

    $("#set_username").text("¡Tu id de usuario es : ".concat(username));
})