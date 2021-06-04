/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("tab_listar_productos").addEventListener("click",pullProductos,false);
document.getElementById("crear_categoria").addEventListener("click",crear_categoria,false);
document.getElementById("crear_producto").addEventListener("click",crear_producto,false);


function crear_categoria(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                console.log("Ok http "+http.responseText);
            }else{
                console.log("Error al crear la categoria");
            }
        }
    };
    
    var nombre_categoria = document.getElementById("nombre_categoria").value;
    var numero_referencia = document.getElementById("referencia").value;
    
    http.open("POST","/APIRESTfull/resources/services/capstore/crearcategoria", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"ID_Categoria":numero_referencia,"categoria":nombre_categoria}));
    console.log("Se envio la solicitud");
    
}

function crear_producto(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                console.log("Se creo el producto "+http.responseText);
            }else{
                console.log("Error al crear el producto");
            }
        }
    };
    
    var referencia_p = document.getElementById("referencia_producto").value;
    var r_categoria_p = "0";
    var nombre_p = document.getElementById("nombre_producto").value;
    var talla_p = document.getElementById("talla").value;
    var color_p = document.getElementById("color").value;
    var marca_p = document.getElementById("marca").value;
    var precio_p = document.getElementById("precio").value;
    var stock_p = document.getElementById("stock").value;
    var imegen_p = "codigo imagen";
    var promocion_p = document.getElementById("promocion").value;
    
    http.open("POST","/APIRESTfull/resources/services/productos/agregar", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"ID_producto":referencia_p,"ID_categoria":r_categoria_p,"nombre":nombre_p,"talla":talla_p,"color":color_p,"marca":marca_p,"precio":precio_p,"stock":stock_p,"imagen":imegen_p,"promocion":promocion_p}));
    console.log("Se envio la solicitud");
}


function desplegar_datos_productos(listajson){
    for(let i=0; i<listajson.length; i++){
        var row = document.createElement("tr");
        var column_1 = document.createElement("td");
        var column_2 = document.createElement("td");
        var column_3 = document.createElement("td");
        var column_4 = document.createElement("td");
        var column_5 = document.createElement("td");
        
        column_1.innerHTML = listajson[i].ID_producto;
        column_2.innerHTML = listajson[i].nombre;
        column_3.innerHTML = listajson[i].ID_categoria;
        column_4.innerHTML = listajson[i].promocion;
        column_5.innerHTML = "acciones";
        
        row.appendChild(column_1);
        row.appendChild(column_2);
        row.appendChild(column_3);
        row.appendChild(column_4);
        row.appendChild(column_5);
        
        document.getElementById("tabla_productos").appendChild(row);
    }
}

function pullProductos(){   
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                var listaUsuarios = JSON.parse(http.responseText);
                desplegar_datos_productos(listaUsuarios);
                console.log("Se resivieron los productos ");
            }else{
                console.log("Error al obtener la lista de productos");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/listarproductos", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud");
}