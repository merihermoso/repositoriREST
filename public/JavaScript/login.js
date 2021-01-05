var BASE_URI="http://147.83.7.207:8080/dsaApp/";

$(document).ready(function() {

    $('#loginbtn').click(function () {
        var username = $("#loginName").val();
        var password = $("#loginPassword").val();

        var user = {"username": username, "password": password};

        $.ajax({
            type: 'POST',
            url: BASE_URI.concat("user/login"),
            headers: {'content-type': 'application/json', "x-kii-appid": "XXXXX", "x-kii-appkey": "XXXXX"},
            statusCode: {
                201: function() {
                    alert("Has iniciado sesión correctamente\n");
                    window.localStorage.setItem("username", username);
                },
                601: function() {
                    alert("Debes escribir un nombre de usuario\n");
                },
                602: function() {
                    alert("Debes escribir una contraseña\n");
                },
                603: function() {
                    alert("Contraseña incorrecta\n");
                },
                250: function() {
                    alert("No existe un usuario con ese nombre\n");
                }
            },
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (data) {

            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });

    $("#registerbtn").click(function(){
                var username = $("#registerName").val();
                var password = $("#registerPassword").val();
                var email = $("#registerMail").val();
                var confirm = $("#registerConfirm").val();
                var birthdate = $("#registerBirth").val();

                if (password == confirm){

                    var user = {"username": username, "password": password, "email":email, "birthdate": birthdate, "score": 0, "level": 0};

                    $.ajax({
                        type: 'POST',
                        url: BASE_URI.concat("user/register"),
                        headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
                        data: JSON.stringify(user),
                        dataType: 'json',
                        statusCode: {
                            201: function() {
                                alert("Te has registrado correctamente\n");
                                window.localStorage.setItem("username", username);
                            },
                            600: function() {
                                alert("Debes escribir un nombre de usuario\n");
                            },
                            601: function() {
                                alert("Debes escribir una contraseña\n");
                            },
                            604: function() {
                                alert("Tu nombre de usuario debe tener entre 4 y 20 caracteres\n");
                            },
                            605: function() {
                                alert("Tu contraseña debe tener entre 4 y 20 caracteres\n");
                            },
                            606: function() {
                                alert("Tu email debe tener entre 4 y 30 caracteres\n");
                            },
                            607: function() {
                                alert("Debes tener más de 14 años para poder jugar\n");
                            },
                            250: function() {
                                alert("Ya existe un usuario con ese nombre\n");
                            }
                        },
                        success: function (data) {
                            window.localStorage.setItem("username", username)
                            var url = "http://147.83.7.207:8080/home.html";
                            window.open(url, "_self");
                        },
                        error: function (e) {
                            // log error in browser
                            console.log(e);
                        }
                    });
                }
                else
                    alert("Las contraseñas son distintas\n");
            });

});