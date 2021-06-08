/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.addEventListener('load',pullCategoria,false); 
window.addEventListener('load',pullCategoria2(),false); 

document.getElementById('tab_informe_productos').addEventListener('click',pullInformeProductos,false);
document.getElementById('actualizar_tabla_categoria').addEventListener('click',pullCategoria,false);
document.getElementById('actualizar_tabla_productos').addEventListener("click",pullProductos,false);
document.getElementById("tab_listar_productos").addEventListener("click",pullProductos,false);
document.getElementById("crear_categoria").addEventListener("click",crear_categoria,false);
document.getElementById("crear_producto").addEventListener("click",crear_producto,false);


function desplegarCategoriasEnFormulario(listajson){
    document.getElementById('control_categoria').removeChild(document.getElementById("r_categoria"));
    
    var select = document.createElement("select");
        select.setAttribute("class", "form-select");
        select.setAttribute("id", "r_categoria");
        
        var option = document.createElement("option");
                option.innerHTML = "Seleccionar";
            select.appendChild(option);
        
        for(let i=0; i<listajson.length; i++){
            var option2 = document.createElement("option");
                option2.setAttribute("value",listajson[i].ID_Categoria);
                option2.innerHTML = listajson[i].categoria;
            select.appendChild(option2);
        }
        
        document.getElementById('control_categoria').appendChild(select);
}


function desplegar_datos_categorias(listajson){
    document.getElementById('tabla_categoria').removeChild(document.getElementById("cuerpo_tabla_categoria"));
    var TBODY = document.createElement("tbody");
        TBODY.setAttribute("id", "cuerpo_tabla_categoria");
        
    for(let i=0; i<listajson.length; i++){
        var row = document.createElement("tr");
        var column_1 = document.createElement("td");
        var column_2 = document.createElement("td");
        
        column_1.innerHTML = listajson[i].ID_Categoria;
        column_2.innerHTML = listajson[i].categoria;
        
        row.appendChild(column_1);
        row.appendChild(column_2);
        
        TBODY.appendChild(row);
    }
    document.getElementById("tabla_categoria").appendChild(TBODY);
}

function desplegar_datos_informes_productos(listajson){
    document.getElementById('tabla_informes_productos').removeChild(document.getElementById("cuerpo_tabla_informes_productos"));
    var TBODY = document.createElement("tbody");
        TBODY.setAttribute("id", "cuerpo_tabla_informes_productos");
        
    for(let i=0; i<listajson.length; i++){
        var row = document.createElement("tr");
        var column_1 = document.createElement("td");
        var column_2 = document.createElement("td");
        var column_3 = document.createElement("td");
        var column_4 = document.createElement("td");
        var column_5 = document.createElement("td");
        
        
        column_1.innerHTML = listajson[i].ID_producto;
        column_2.innerHTML = listajson[i].nombre;
        column_3.innerHTML = listajson[i].marca;
        column_4.innerHTML = listajson[i].total_gastado;
        column_5.innerHTML = listajson[i].total_comprado;
        
        row.appendChild(column_1);
        row.appendChild(column_2);
        row.appendChild(column_3);
        row.appendChild(column_4);
        row.appendChild(column_5);
        
        TBODY.appendChild(row);
    }
    document.getElementById("tabla_informes_productos").appendChild(TBODY);
}

function pullCategoria(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                var listaCategoria = JSON.parse(http.responseText);
                desplegar_datos_categorias(listaCategoria);
            }else{
                console.log("Error al cargar las categoria");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/listarcategorias", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud listar categoria");
}

function pullInformeProductos(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                var informeproducto = JSON.parse(http.responseText);
                desplegar_datos_informes_productos(informeproducto);
                console.log("se recivio el informe de producto");
            }else{
                console.log("Error al cargar el informe producto");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/informeproductos", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud informe producto");
}

function pullCategoria2(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                var listaCategoria = JSON.parse(http.responseText);
                desplegarCategoriasEnFormulario(listaCategoria);
            }else{
                console.log("Error al cargar las categoria");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/listarcategorias", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud listar categoria");
}

function crear_categoria(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                alert("Se creo de manera exitosa la categoria");
                console.log("Ok http");
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
                alert("Se creo de manera exitosa el producto");
            }else{
                console.log("No se pudo crear el producto");
            }
        }
    };
    
    var referencia_p = document.getElementById("referencia_producto").value;
    var r_categoria_p = document.getElementById('r_categoria').value;
    var nombre_p = document.getElementById("nombre_producto").value;
    var talla_p = document.getElementById("talla").value;
    var color_p = document.getElementById("color").value;
    var marca_p = document.getElementById("marca").value;
    var precio_p = document.getElementById("precio").value;
    var stock_p = document.getElementById("stock").value;
    var imagen_p = document.getElementById('link_imagen').value;
    var promocion_p = document.getElementById("promocion").value;
    
    http.open("POST","/APIRESTfull/resources/services/productos/agregar", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"ID_producto":referencia_p,"ID_categoria":r_categoria_p,"nombre":nombre_p,"talla":talla_p,"color":color_p,"marca":marca_p,"precio":precio_p,"stock":stock_p,"imagen":imagen_p,"promocion":promocion_p}));
    console.log("Se envio la solicitud");
}


function desplegar_datos_productos(listajson){
    document.getElementById('tabla_productos').removeChild(document.getElementById("cuerpo_tabla_productos"));
    var TBODY = document.createElement("tbody");
        TBODY.setAttribute("id", "cuerpo_tabla_productos");
        
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
        
        TBODY.appendChild(row);
    }
    document.getElementById("tabla_productos").appendChild(TBODY);
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