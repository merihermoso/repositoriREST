var BASE_URI="/api";
var username = localStorage.getItem("username");
var diamantes = localStorage.getItem("diamantes");

if (username == "null"){
    var url = "login.html";
    window.open(url, "_self");
}

$(document).ready(function() {
    console.log(username);
    console.log(diamantes);

    $("#set_username").text("¡Tu id de usuario es : ".concat(username));
    $("#nav_username").text(username);
    
    $('#cerrar_session').click(function () {
        window.localStorage.setItem("username", "null");
        console.log(username);
        window.open("login.html", "_self");
    })

    $('#eliminar_usuario').click(function () {
        $.ajax({
            url: BASE_URI.concat("/user/"username),
            success: function(respuesta) {

            }
        }
    })
})



