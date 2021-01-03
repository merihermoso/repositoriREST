var BASE_URI="http://147.83.7.207:8080/dsaApp/";

$(document).ready(function() {

    $('#loginbtn').click(function () {
        var username = $("#loginName").val();
        var password = $("#loginPassword").val();
        var user = {"username": username, "password": password};
        //alert("Usuario creado logeado correctamente");

        $.ajax({
            type: 'POST',
            url: BASE_URI.concat("user/login"),
            headers: {'content-type': 'application/json', "x-kii-appid": "XXXXX", "x-kii-appkey": "XXXXX"},
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (data) {
                window.localStorage.setItem("username", username)
                var url = "http://147.83.7.207:8080/home.html";
                window.open(url, "_self");
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

                    var user = { "id": 0, "username": username, "password": password, "email":email, "birthdate": birthdate, "score": 0, "level": 0};

                    $.ajax({
                        type: 'POST',
                        url: BASE_URI.concat("user/register"),
                        headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
                        data: JSON.stringify(user),
                        dataType: 'json',
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