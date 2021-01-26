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


    $("#m").text(diamantes);
    $("#nav_username").text(username);

    var partida = "NULL";

    var cantidad1 = parseInt($("#numero1").text());
    var cantidad2 = parseInt($("#numero2").text());
    var cantidad3 = parseInt($("#numero3").text());
    var cantidad4 = parseInt($("#numero4").text());
    var cantidad5 = parseInt($("#numero5").text());
    var cantidad6 = parseInt($("#numero6").text());
    var cantidad7 = parseInt($("#numero7").text());
    var cantidad8 = parseInt($("#numero8").text());
    var cantidad9 = parseInt($("#numero9").text());

    $.ajax({
            url: BASE_URI.concat("/user/"+username+"/game"),
            success: function(respuesta) {
                console.log(respuesta);
                $("#LA1").text(respuesta[0].dateLast);
                $("#CD1").text(respuesta[0].dateStart);
                $("#scr1").text(respuesta[0].score);
                $("#cns1").text(respuesta[0].coins);

                $("#LA2").text(respuesta[1].dateLast);
                $("#CD2").text(respuesta[1].dateStart);
                $("#scr2").text(respuesta[1].score);
                $("#cns2").text(respuesta[1].coins);
                var partida2 = respuesta[1].id;

                $("#LA3").text(respuesta[2].dateLast);
                $("#CD3").text(respuesta[2].dateStart);
                $("#scr3").text(respuesta[2].score);
                $("#cns3").text(respuesta[2].coins);
                var partida3 = respuesta[2].id;
            }
    });


    $.ajax({
                url: BASE_URI.concat("/game/"+2+"/inventory"),
                success: function(respuesta) {
                    console.log(respuesta);
                    $("#idItem").text(respuesta[0].id_item);
                    $("#cant1").text(respuesta[0].quantity);


                }
        });



    $.ajax({
            url: BASE_URI.concat("/shop"),

            success: function(respuesta) {
                //Obtenemos y colocamos el nombre de cada objeto
                $("#item_1_name").text(respuesta[0].name);
                $("#item_2_name").text(respuesta[1].name);
                $("#item_3_name").text(respuesta[2].name);
                $("#item_4_name").text(respuesta[3].name);
                $("#item_5_name").text(respuesta[4].name);
                $("#item_6_name").text(respuesta[5].name);
                $("#item_7_name").text(respuesta[6].name);
                $("#item_8_name").text(respuesta[7].name);
                $("#item_9_name").text(respuesta[8].name);

                //Obtenemos y colocamos la descripcion de cada objeto
                $("#item_1_description").text(respuesta[0].description);
                $("#item_2_description").text(respuesta[1].description);
                $("#item_3_description").text(respuesta[2].description);
                $("#item_4_description").text(respuesta[3].description);
                $("#item_5_description").text(respuesta[4].description);
                $("#item_6_description").text(respuesta[5].description);
                $("#item_7_description").text(respuesta[6].description);
                $("#item_8_description").text(respuesta[7].description);
                $("#item_9_description").text(respuesta[8].description);

                //Obtenemos y colocamos el precio de cada objeto
                $("#precio_1").text("precio: ".concat(respuesta[0].price.toString()));
                $("#precio_2").text("precio: ".concat(respuesta[1].price.toString()));
                $("#precio_3").text("precio: ".concat(respuesta[2].price.toString()));
                $("#precio_4").text("precio: ".concat(respuesta[3].price.toString()));
                $("#precio_5").text("precio: ".concat(respuesta[4].price.toString()));
                $("#precio_6").text("precio: ".concat(respuesta[5].price.toString()));
                $("#precio_7").text("precio: ".concat(respuesta[6].price.toString()));
                $("#precio_8").text("precio: ".concat(respuesta[7].price.toString()));
                $("#precio_9").text("precio: ".concat(respuesta[8].price.toString()));
            },

            error: function() {
                console.log("No se ha podido obtener la información");
            }
    });


    $('#m').click(function () {
        $.ajax({
            url: BASE_URI.concat("/shop/"),
            success: function(respuesta) {
                console.log(respuesta);
                var id_username = respuesta;
            }
        })
    })


    $('#s1').click(function (){
        cantidad1 = cantidad1 + 1;
        $("#numero1").text(cantidad1);
    })
    $('#r1').click(function () {
        if(cantidad1 != 1) cantidad1 = cantidad1 - 1;
        $("#numero1").text(cantidad1);
    })


    $('#s2').click(function (){
        cantidad2= cantidad2 + 1;
        $("#numero2").text(cantidad2);
    })
    $('#r2').click(function () {
        if(cantidad2 != 1) cantidad2 = cantidad2 - 1;
        $("#numero2").text(cantidad2);
    })


    $('#s3').click(function (){
        cantidad3= cantidad3 + 1;
        $("#numero3").text(cantidad3);
    })
    $('#r3').click(function () {
        if(cantidad3 != 1) cantidad3 = cantidad3 - 1;
        $("#numero3").text(cantidad3);
    })


    $('#s4').click(function (){
        cantidad4= cantidad4 + 1;
        $("#numero4").text(cantidad4);
    })
    $('#r4').click(function () {
        if(cantidad4 != 1) cantidad4 = cantidad4 - 1;
        $("#numero4").text(cantidad4);
    })


    $('#s5').click(function (){
        cantidad5= cantidad5 + 1;
        $("#numero5").text(cantidad5);
    })
    $('#r5').click(function () {
        if(cantidad5 != 1) cantidad5 = cantidad5 - 1;
        $("#numero5").text(cantidad5);
    })


    $('#s6').click(function (){
        cantidad6= cantidad6 + 1;
        $("#numero6").text(cantidad6);
    })
    $('#r6').click(function () {
        if(cantidad6 != 1) cantidad6 = cantidad6 - 1;
        $("#numero6").text(cantidad6);
    })


    $('#s7').click(function (){
        cantidad7= cantidad7 + 1;
        $("#numero7").text(cantidad7);
    })
    $('#r7').click(function () {
        if(cantidad7 != 1) cantidad7 = cantidad7 - 1;
        $("#numero7").text(cantidad7);
    })


    $('#s8').click(function (){
        cantidad8= cantidad8 + 1;
        $("#numero8").text(cantidad8);
    })
    $('#r8').click(function () {
        if(cantidad8 != 1) cantidad8 = cantidad8 - 1;
        $("#numero8").text(cantidad8);
    })


    $('#s9').click(function (){
        cantidad9= cantidad9 + 1;
        $("#numero9").text(cantidad9);
    })
    $('#r9').click(function () {
        if(cantidad9 != 1) cantidad9 = cantidad9 - 1;
        $("#numero9").text(cantidad9);
    })



    //Seleccionamos Partida
    $('#seleccionarPartida1').click(function (){
        $.ajax({
            url: BASE_URI.concat("/user/"+username+"/game"),
            success: function(respuesta) {
                partida = respuesta[0].id;
                console.log(partida);
            }
        })
    });

    $('#seleccionarPartida2').click(function (){
        $.ajax({
            url: BASE_URI.concat("/user/"+username+"/game"),
            success: function(respuesta) {
                partida = respuesta[1].id;
                console.log(partida);
            }
        })
    })

    $('#seleccionarPartida3').click(function (){
        $.ajax({
            url: BASE_URI.concat("/user/"+username+"/game"),
            success: function(respuesta) {
                partida = respuesta[2].id;
                console.log(partida);
            }
        })
    })



    $('#aloevera').click(function () {

        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/Aloe%20vera/buy?id_game=" + partida + "&quantity=" + cantidad1),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#venda').click(function () {
         if (partida == "NULL")
         {
             alert("Primero selecciona una partida")
         }
         else
         {
             $.ajax({
                 type: 'POST',
                 url: BASE_URI.concat("/shop/venda/buy?id_game=" + partida + "&quantity=" + cantidad2),
                 success: function(respuesta) {
                         alert("Has realizado la compra correctamente\n");
                         var url = "shop.html";
                         window.open(url, "_self");
                 }
             });
         }
    });

    $('#agua').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/agua/buy?id_game=" + partida + "&quantity=" + cantidad3),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#escudo').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/escudo/buy?id_game=" + partida + "&quantity=" + cantidad4),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#mascara').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/mascara/buy?id_game=" + partida + "&quantity=" + cantidad5),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#pastilla').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/pastilla%20magica/buy?id_game=" + partida + "&quantity=" + cantidad6),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#extintor').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/extintor/buy?id_game=" + partida + "&quantity=" + cantidad7),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#manguera').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/manguera/buy?id_game=" + partida + "&quantity=" + cantidad8),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });

    $('#hacha').click(function () {
        if (partida == "NULL")
        {
            alert("Primero selecciona una partida")
        }
        else
        {
            $.ajax({
                type: 'POST',
                url: BASE_URI.concat("/shop/hacha/buy?id_game=" + partida + "&quantity=" + cantidad9),
                success: function(respuesta) {
                        alert("Has realizado la compra correctamente\n");
                        var url = "shop.html";
                        window.open(url, "_self");
                }
            });
        }
    });
})