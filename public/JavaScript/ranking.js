var BASE_URI="/api";

$(document).ready(function() {

    $('#getPlayersbtn').click(function () {
        var username = $("#loginName").val();

        var user = {"username": username};

        $.ajax({
            type: 'GET',
            url: BASE_URI.concat("/user/ranking"),
            data: JSON.stringify(user),
            dataType: 'json',
            headers: {'content-type': 'application/json'},
            statusCode: {
                200: function() {
                alert(user);

                    window.localStorage.setItem("username", username);
                    window.open(url, "_self");

                            var Json = JSON.parse(JSON.stringify(json));
                            console.log(Json.user.length);
                            $("#Table").append('<tr><td>username</td>'+
                            '<td>score</td>' +
                            '<td>position</td>');
                            for (i = 0; i < Json.user.length; i++){

                         $("#Table").append('<tr>' +
                            '<td align="center" style="display: none;">' + Json.user[i].username + '</td>'+
                            '<td align="center" style="display: none;">' + Json.user[i].score + '</td>'+
                            '<td align="center" style="display: none;">' + Json.user[i].position + '</td>'+'</tr>');
                            }
                }
            }
        });
    });


    $('#getPositionbtn').click(function () {
            var username = $("#loginName").val();

            var user = {"username": username};

            $.ajax({
                type: 'GET',
                url: BASE_URI.concat("/user/{username}/ranking"),
                data: JSON.stringify(user),
                dataType: 'json',
                headers: {'content-type': 'application/json'},
                statusCode: {
                    200: function() {
                    alert(user);

                        window.localStorage.setItem("username", username);
                        window.open(url, "_self");

                                var Json = JSON.parse(JSON.stringify(json));
                                console.log(Json.user.length);
                                $("#Table").append('<tr><td>username</td>'+
                                '<td>score</td>' +
                                '<td>position</td>');
                                for (i = 0; i < Json.user.length; i++){

                             $("#Table").append('<tr>' +
                                '<td align="center" style="display: none;">' + Json.user[i].username + '</td>'+
                                '<td align="center" style="display: none;">' + Json.user[i].score + '</td>'+
                                '<td align="center" style="display: none;">' + Json.user[i].position + '</td>'+'</tr>');
                                }
                    }
                }
            });
        });








});