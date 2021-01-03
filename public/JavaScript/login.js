var BASE_URI="http://localhost:8080/dsaApp/";

$(document).ready(function() {
    console.log("login/register");
    $('#loginbtn').click(function () {
        var username = $("#loginName").val();
        console.log(username);
        var password = $("#loginPassword").val();
        console.log(password);
        var user = {"id": 0, "username": username, "password": password, "score": 0, "level": 0};
        console.log(user);

        $.ajax({
            type: 'POST',
            url: BASE_URI.concat("user/login"),
            headers: {'content-type': 'application/json', "x-kii-appid": "XXXXX", "x-kii-appkey": "XXXXX"},
            data: JSON.stringify(user),
            dataType: 'json',
            success: function (data) {
                //var token = data.token;
                //var diamantes = data.diamantes;
                //console.log(token);
                //window.sessionStorage.setItem("token", token)
                window.localStorage.setItem("username", username)
                var url = "http://localhost:8080/home.html";
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
            console.log("Carlos");
            if (password == confirm){
                var user = { "id": 0, "username": username, "password": password, "email":email, "birthdate": birthdate, "score": 0, "level": 0};
                console.log(user);
                $.ajax({
                    type: 'POST',
                    url: BASE_URI.concat("user/register"),
                    headers: { 'content-type': 'application/json',"x-kii-appid": "XXXXX","x-kii-appkey":"XXXXX" },
                    data: JSON.stringify(user),
                    dataType: 'json',
                    success: function (data) {
                        //var token = data.token;
                        //var diamantes = data.diamantes;
                        //window.sessionStorage.setItem("token", token)
                        window.localStorage.setItem("username", username);
                        var url = "http://localhost:8080/login.html";
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
})