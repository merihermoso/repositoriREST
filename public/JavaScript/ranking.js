var BASE_URI="/api";


$(document).ready(function() {
    var username = localStorage.getItem("username");
    $("#nav_username").text(username);
    
    $('#getPlayersbtn').click(function () { 

        $.ajax({
            url: BASE_URI.concat("/user/ranking"),
                success: function(respuesta) {
                    console.log(respuesta);

                    $("#nam1").text(d);
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
            })
    });

    $('#getPositionbtn').click(function () {

        $.ajax({
        url: BASE_URI.concat("/user/"+username+"/ranking"),
            success: function(respuesta) 
            {
            console.log(respuesta);
            $("#pos")
            }
        });
    })

})