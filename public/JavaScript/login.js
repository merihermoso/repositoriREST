var BASE_URI="http://localhost:8080/dsaApp/auth";

/*$(document).ready(function() {
    console.log("login/register");
    $('#loginbtn').click(function () {
        var nombre = $("#loginName").val();
        var password = $("#loginPassword").val();
        var user = {"nombre": nombre, "password": password};
        console.log(user);

        $.ajax({
            type: 'POST',
            url: BASE_URI.concat("/login"),
            headers: {'content-type': 'application/json', "x-kii-appid": "XXXXX", "x-kii-appkey": "XXXXX"},
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (data) {
                console.log("hola1")
                var token = data.token;
                var diamantes = data.diamantes;
                console.log(token);
                window.sessionStorage.setItem("token", token)
                window.sessionStorage.setItem("diamantes", diamantes)
                var url = "http://localhost:8080/shop.html";
                window.open(url, "_self");
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    });
    $("#registerbtn").click(function(){
            var nombre = $("#registerName").val();
            var mail = $("#registerMail").val();
            var password = $("#registerPassword").val();
            var confirm = $("#registerConfirm").val();
            var birthdate = $("#registerBirth").val();
            console.log("Carlos");
            if (password == confirm){
                var user = {"nombre": nombre, "mail":mail, "password": password, "confirm":confirm, "birthdate": birthdate};
                console.log(user);
                $.ajax({
                    type: 'POST',
                    url: BASE_URI.concat("/user/registerUser"),
                    headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
                    data: JSON.stringify(user),
                    dataType: 'json',
                    success: function (data) {
                        var token = data.token;
                        var diamantes = data.diamantes;
                        window.sessionStorage.setItem("token", token)
                        window.sessionStorage.setItem("diamantes", diamantes)
                        var url = "http://localhost:8080/shop.html";
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
})*/