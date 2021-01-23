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
        url: BASE_URI.concat("/shop"),

        success: function(respuesta) {
            console.log(respuesta);
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


    $('#s1').click(function (){
        cantidad1 = cantidad1 + 1;
        $("#numero1").text(cantidad1);
    })
    $('#r1').click(function () {
        if(cantidad1 != 1) cantidad1 = cantidad1 - 1;
        else alert("Noo");
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




    /*$('#aloevera').click(function () {

        url: BASE_URI.concat("/shop/"username),
        success: function(respuesta) {
            console.log(respuesta);
            var id_username = respuesta;
        }

        url: BASE_URI.concat("/shop/"),
        success: function(respuesta) {
            console.log(respuesta);
            var price = respuesta[0];
        }

        var inventario = {"id:": 1, "id_user:": respuesta, "id_item": 0, "quantity": cantidad1, "orderDate": 0, "OrderTime": 0};

        console.log(inventario);

        diamantes = diamantes - price * cantidad2;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero2").text(1);
        cantidad2 = 1;
    });*/

    $('#venda').click(function () {
        var inventario = {"idObjeto": 2, "cantidad": cantidad2, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 150*cantidad2;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero2").text(1);
        cantidad2 = 1;
    });

    $('#agua').click(function () {
        var inventario = {"idObjeto": 3, "cantidad": cantidad3, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 10*cantidad3;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero3").text(1);
        cantidad3 = 1;
    });

    $('#escudo').click(function () {
        var inventario = {"idObjeto": 4, "cantidad": cantidad4, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 20*cantidad4;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero4").text(1);
        cantidad4 = 1;
    });

    $('#mascara').click(function () {
        var inventario = {"idObjeto": 5, "cantidad": cantidad5, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 100*cantidad5;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero5").text(1);
        cantidad5 = 1;
    });

    $('#pastilla').click(function () {
        var inventario = {"idObjeto": 6, "cantidad": cantidad6, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 25 * cantidad6;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero6").text(1)
        cantidad6 = 1;
    });

    $('#extintor').click(function () {
        var inventario = {"idObjeto": 4, "cantidad": cantidad4, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 20*cantidad4;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero4").text(1);
        cantidad4 = 1;
    });

    $('#mangera').click(function () {
        var inventario = {"idObjeto": 5, "cantidad": cantidad5, "idJugador": idJugador};
        console.log(inventario);
        compraObjeto(inventario);
        diamantes = diamantes - 100*cantidad5;
        window.sessionStorage.setItem("diamantes", diamantes);
        $("#m").text(diamantes);
        $("#numero5").text(1);
        cantidad5 = 1;
    });

    $('#hacha').click(function () {
        var inventario = {"idObjeto": 6, "cantidad": cantidad6, "idJugador": idJugador};
        compraObjeto(inventario);
        //diamantes = diamantes - 25 * cantidad6;
        //window.sessionStorage.setItem("diamantes", diamantes);
        //$("#m").text(diamantes);
        $("#numero6").text("1");
        cantidad6 = 1;
    });


    $('#cerrar_sesion').click(function () {
        window.localStorage.setItem("", username);
    });


    compraObjeto(inventario) 
    {
        console.log("comprando objeto", inventario);
        $.ajax({
            type: 'POST',
            url: BASE_URI.concat("/user/register"), //Modificar antes de copiar en cada funcion
            headers: {'content-type': 'application/json'},
            data: JSON.stringify(inventario),
            dataType: 'json',
            statusCode: {
                201: function() {
                    alert("Comprado correctamente\n");
                    //window.localStorage.setItem("username", username);
                }
            }
        })
    }
    
    /*function compraObjeto(inventario) {
        console.log("comprando objeto",inventario);
        $.ajax({
            type: 'POST',
            url: BASE_URI.concat("/compra"),
            headers: {'content-type': 'application/json', "x-kii-appid": "XXXXX", "x-kii-appkey": "XXXXX"},
            data: JSON.stringify(inventario),
            //dataType: 'json',
            success: function () {
                alert("Objeto comprado con exito")
            },
            error: function (e) {
                console.log(e.message);
            }
        });
    }*/
});
