var BASE_URI="/api";
var username = localStorage.getItem("username");

$(document).ready(function() {

    $('#getPlayersbtn').click(function () {


        /*$.ajax({
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
                            //console.log(Json.user.length);
                            $("#Table").append(
                            '<tr><td>username</td>'+
                            '<td>score</td>' +
                            '<td>position</td>');

                            for (i = 0; i < Json.user.length; i++){

                         $("#Table").append('<tr>' +
                            '<td align="center" style="display: none;">' + Json.user[i].username + '</td>'+
                            '<td align="center" style="display: none;">' + Json.user[i].score + '</td>'+
                            '<td align="center" style="display: none;">' + Json.user[i].position + '</td>'+
                            '</tr>');
                         }

                         $("#excel").append(
                         '<tr><td>username</td>'+
                         '<td>score</td>' +
                         '<td>position</td></tr>');
                }
            }
        });*/

        $.ajax({
            url: BASE_URI.concat("/user/ranking"),
                success: function(respuesta) {
                    console.log(respuesta);

                    $("#nam1").text(respuesta[0].username);
                    $("#nam2").text(respuesta[1].username);
                    $("#nam3").text(respuesta[2].username);
                    $("#nam4").text(respuesta[3].username);
                    $("#nam5").text(respuesta[4].username);
                    $("#nam6").text(respuesta[5].username);
                    $("#nam7").text(respuesta[6].username);
                    $("#nam8").text(respuesta[7].username);
                    $("#nam9").text(respuesta[8].username);
                    $("#nam10").text(respuesta[9].username);
                    $("#nam11").text(respuesta[10].username);
                    $("#nam12").text(respuesta[11].username);
                    $("#nam13").text(respuesta[12].username);
                    $("#nam14").text(respuesta[13].username);
                    $("#nam15").text(respuesta[14].username);
                    $("#nam16").text(respuesta[15].username);
                    $("#nam17").text(respuesta[16].username);
                    $("#nam18").text(respuesta[17].username);
                    $("#nam19").text(respuesta[18].username);
                    $("#nam20").text(respuesta[19].username);

                    $("#scr1").text(respuesta[0].score);
                    $("#scr2").text(respuesta[1].score);
                    $("#scr3").text(respuesta[2].score);
                    $("#scr4").text(respuesta[3].score);
                    $("#scr5").text(respuesta[4].score);
                    $("#scr6").text(respuesta[5].score);
                    $("#scr7").text(respuesta[6].score);
                    $("#scr8").text(respuesta[7].score);
                    $("#scr9").text(respuesta[8].score);
                    $("#scr10").text(respuesta[9].score);
                    $("#scr11").text(respuesta[10].score);
                    $("#scr12").text(respuesta[11].score);
                    $("#scr13").text(respuesta[12].score);
                    $("#scr14").text(respuesta[13].score);
                    $("#scr15").text(respuesta[14].score);
                    $("#scr16").text(respuesta[15].score);
                    $("#scr17").text(respuesta[16].score);
                    $("#scr18").text(respuesta[17].score);
                    $("#scr19").text(respuesta[18].score);
                    $("#scr20").text(respuesta[19].score);
                }

                /*for (var i=0; i<=10; i++){
                    var text = '<tr><th scope="row">'+respuesta.position[i].toString()+'</th><td>'+respuesto.username[i]+'</td><td>'+respuesta.score[i].toString()+'</td></tr>';
                    $('#excel').html(text);
                }*/
    });
})

    $('#getPositionbtn').click(function () {
            //var username = $("#loginName").val();

            //var user = {"username": username};

            $.ajax({
            url: BASE_URI.concat("/user/"+username+"/ranking"),
                success: function(respuesta) {
                console.log(respuesta);
                $("#pos").text(respuesta.position).toString();
            }
        });
    });
});
