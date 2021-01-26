var BASE_URI="/api";


$(document).ready(function() {
    var username = localStorage.getItem("username");
    $("#nav_username").text(username);
    
    $('#getPlayersbtn').click(function () { 

        $.ajax({
            url: BASE_URI.concat("/user/ranking"),
                success: function(respuesta) {

                    $("#nam1").text(respuesta[0].username);
                    $("#scr1").text(respuesta[0].score);

                    $("#nam2").text(respuesta[1].username);
                    $("#scr2").text(respuesta[1].score);

                    $("#nam3").text(respuesta[2].username);
                    $("#scr3").text(respuesta[2].score);

                    $("#nam4").text(respuesta[3].username);
                    $("#scr4").text(respuesta[3].score);

                    $("#nam5").text(respuesta[4].username);
                    $("#scr5").text(respuesta[4].score);

                    $("#nam6").text(respuesta[5].username);
                    $("#scr6").text(respuesta[5].score);

                    $("#nam7").text(respuesta[6].username);
                    $("#scr7").text(respuesta[6].score);

                    $("#nam8").text(respuesta[7].username);
                    $("#scr8").text(respuesta[7].score);

                    $("#nam9").text(respuesta[8].username);
                    $("#scr9").text(respuesta[8].score);

                    $("#nam10").text(respuesta[9].username);
                    $("#scr10").text(respuesta[9].score);

                    $("#nam11").text(respuesta[10].username);
                    $("#scr11").text(respuesta[10].score);

                    $("#nam12").text(respuesta[11].username);
                    $("#scr12").text(respuesta[11].score);

                    $("#nam13").text(respuesta[12].username);
                    $("#scr13").text(respuesta[12].score);

                    $("#nam14").text(respuesta[13].username);
                    $("#scr14").text(respuesta[13].score);

                    $("#nam15").text(respuesta[14].username);
                    $("#scr15").text(respuesta[14].score);

                    $("#nam16").text(respuesta[15].username);
                    $("#scr16").text(respuesta[15].score);

                    $("#nam17").text(respuesta[16].username);
                    $("#scr17").text(respuesta[16].score);

                    $("#nam18").text(respuesta[17].username);
                    $("#scr18").text(respuesta[17].score);

                    $("#nam19").text(respuesta[18].username);
                    $("#scr19").text(respuesta[18].score);

                    $("#nam20").text(respuesta[19].username);
                    $("#scr20").text(respuesta[19].score);
                }
        })
    });

    $('#getPositionbtn').click(function () {

        $.ajax({
        url: BASE_URI.concat("/user/"+username+"/ranking"),
            success: function(respuesta) 
            {
            console.log(respuesta);
            $("#pos").text(respuesta.position)
            }
        });
    })

})