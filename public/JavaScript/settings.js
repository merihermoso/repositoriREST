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

    //Rellenamos la tabla del user
    $.ajax({
        //Colocamso el username
        url: BASE_URI.concat("/user/"+username+"/username"),
            success: function(respuesta) {
                $("#table_username").text(respuesta);
            }
    });

    $.ajax({
        //Colocamso el email
        url: BASE_URI.concat("/user/"+username+"/email"),
            success: function(respuesta) {
                $("#table_email").text(respuesta);
            }
    });

    $.ajax({
        //Colocamso el ranking
        url: BASE_URI.concat("/user/"+username+"/ranking"),
            success: function(respuesta) {
                $("#table_ranking").text(respuesta.position);
            }
    });

    $.ajax({
        //Colocamso el score
        url: BASE_URI.concat("/user/"+username+"/score"),
            success: function(respuesta) {
                $("#table_score").text(respuesta);
            }
    });

    $.ajax({
        //Colocamso el birthdate
        url: BASE_URI.concat("/user/"+username+"/birthdate"),
            success: function(respuesta) {
                $("#table_birthdate").text(respuesta).toString();
            }
    });
    
    $('#cerrar_session').click(function () {
        window.localStorage.setItem("username", "null");
        console.log(username);
        window.open("login.html", "_self");
    })

    $('#eliminar_usuario').click(function () {
        $.ajax({
            url: BASE_URI.concat("/user/"+username),
            success: function(respuesta) {
            }
        })
    })

    $('#updatePasswordbtn').click(function () {

        var password_val = $("#newPassword").val();
        var parameter = {"parameterValue": password_val};

            $.ajax({
                type: 'PUT',
                url: BASE_URI.concat("/user/"+username+"/password"),  //Acabar de completar
                data: JSON.stringify(parameter),
                dataType: 'json',
                headers: {'content-type': 'application/json'},
                statusCode: {
                    200: function() {
                        alert("Has modificado la contraseña correctamente\n");
                    },
                    404: function() {
                        alert("Usuario no encontrado\n");
                    },
                    603: function() {
                        alert("Parameter not found")
                    },
                    604: function() {
                        alert("You must enter a new parameter value\n");
                    }
                }
            })
    });

    $('#updateEmailbtn').click(function () {

        var mail_val = $("#newEmail").val();
        var parameter = {"parameterValue": mail_val};

            $.ajax({
                type: 'PUT',
                url: BASE_URI.concat("/user/"+username+"/email"),  //Acabar de completar
                data: JSON.stringify(parameter),
                dataType: 'json',
                headers: {'content-type': 'application/json'},
                statusCode: {
                    200: function() {
                        alert("Has modificado el correo correctamente\n");
                    },
                    404: function() {
                        alert("Usuario no encontrado\n");
                    },
                    603: function() {
                        alert("Parameter not found")
                    },
                    604: function() {
                        alert("You must enter a new parameter value\n");
                    }
                }
            })
    });
})





