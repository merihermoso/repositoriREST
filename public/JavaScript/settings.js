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

    $('#updatePasswordbtn').click(function () {
            var username = $("#loginName").val();
            var password = $("#newPassword").val();

            var user = {"username": username, "password": password};

            console.log(user);
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/user/{username}/{parameter}"),
                data: JSON.stringify(user),
                dataType: 'json',
                headers: {'content-type': 'application/json'},
                statusCode: {
                    200: function() {
                        alert("Has modificado la contraseña correctamente\n");
                        window.localStorage.setItem("username", username);
                        var url = "settings.html";
                        window.open(url, "_self");
                    },
                    404: function() {
                        alert("Usuario no encontrado\n");
                    },
                    603: function() {
                        alert("Parámetro no encontrado\n");
                    },
                    604: function() {
                        alert("No has introducido un nuevo valor\n");
                    }
                }
            });
        });

    $('#updateEmailbtn').click(function () {
            var username = $("#loginName").val();
            var password = $("#newEmail").val();

            var user = {"username": username, "newEmail": newEmail};

            console.log(user);
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/user/{username}/{parameter}"),
                data: JSON.stringify(user),
                dataType: 'json',
                headers: {'content-type': 'application/json'},
                statusCode: {
                    200: function() {
                        alert("Has modificado el correo correctamente\n");
                        window.localStorage.setItem("username", username);
                        var url = "settings.html";
                        window.open(url, "_self");
                    },
                    404: function() {
                        alert("Usuario no encontrado\n");
                    },
                    603: function() {
                        alert("Parámetro no encontrado\n");
                    },
                    604: function() {
                        alert("No has introducido un nuevo valor\n");
                    }
                }
            });
        });


 $('#getUserProfilebtn').click(function () {
        $.ajax({
            url: BASE_URI.concat("/user/"+username+"/profile"),
                success: function(respuesta) {
                    console.log(respuesta);

                    $("#username").text(respuesta[0].username);
                    $("#email").text(respuesta[0].email);
                    $("#score").text(respuesta[0].score).toString();
                    $("#birthdate").text(respuesta[0].birthdate).toString();

                    alert("quiero obtener el perfil");
    });
})

})



