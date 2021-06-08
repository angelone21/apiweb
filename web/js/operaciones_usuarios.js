/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var usuario_u;

window.addEventListener('load',pullUsuarios,false); 

document.getElementById('actualizar_tabla_usuarios').addEventListener('click',pullUsuarios,false);
document.getElementById('registrar_usuario_panel').addEventListener('click',rigistrar_usuario,false);

function rigistrar_usuario(){
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {
            if(http.status == 200){
                alert("Registro Exitoso");  
                document.getElementById("identificacion").value = "";
                document.getElementById("nombres").value = "";
                document.getElementById("apellidos").value = "";
                document.getElementById("fechanacimiento").value = "2000-01-01";
                document.getElementById("sexo").value = "";
                document.getElementById("contrasena").value="";
            }else{
               alert("Su Registro Fue Fallido");
            }
        }
    };
    
    var idetificacionIN = document.getElementById("identificacion").value;
    var nombresIN  = document.getElementById("nombres").value;
    var apellidosIN = document.getElementById("apellidos").value;
    var fechanacimientoIN = document.getElementById("fechanacimiento").value;
    var sexoIN = document.getElementById("sexo").value;
    var contrasenaIN    = document.getElementById("contrasena").value;

    http.open("POST","http://localhost:8080/APIRESTfull/resources/services/capstore/registroUsuarioCliente", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"ID_usuario":idetificacionIN,"ID_rol":2,"password":contrasenaIN,"nombres":nombresIN,"apellidos":apellidosIN,"fecha_nacimiento":fechanacimientoIN,"genero":sexoIN}));
    console.log("Enviando datos al servidor...");
}

function desplegar_datos_informes_usuarios(listajson){
    document.getElementById('tabla_informes_usuarios').removeChild(document.getElementById("cuerpo_tabla_informes_usuarios"));
    var TBODY = document.createElement("tbody");
        TBODY.setAttribute("id", "cuerpo_tabla_informes_usuarios");
        
    for(let i=0; i<listajson.length; i++){
        var row = document.createElement("tr");
        var column_1 = document.createElement("td");
        var column_2 = document.createElement("td");
        var column_3 = document.createElement("td");
        var column_4 = document.createElement("td");
        var column_5 = document.createElement("td");
        
        column_1.innerHTML = listajson[i].ID_usuario;
        column_2.innerHTML = listajson[i].nombres;
        column_3.innerHTML = listajson[i].apellidos;
        column_4.innerHTML = listajson[i].fecha_nacimiento;
        column_5.innerHTML = listajson[i].genero;
        
        row.appendChild(column_1);
        row.appendChild(column_2);
        row.appendChild(column_3);
        row.appendChild(column_4);
        row.appendChild(column_5);
        
        TBODY.appendChild(row);
    }
    document.getElementById("tabla_informes_usuarios").appendChild(TBODY);
}

function desplegar_datos_usuarios(listajson){
    document.getElementById("tabla_usuarios").removeChild(document.getElementById("cuerpo_tabla_usuarios"));
    var TBODY = document.createElement("tbody");
        TBODY.setAttribute("id", "cuerpo_tabla_usuarios");
    
    for(let i=0; i<listajson.length; i++){
        var row = document.createElement("tr");
        var column_1 = document.createElement("td");
        var column_2 = document.createElement("td");
        var column_3 = document.createElement("td");
        var column_4 = document.createElement("td");
        var column_5 = document.createElement("td");
        var column_6 = document.createElement("td");
        
        column_1.setAttribute("class", "cc");
        column_2.setAttribute("class", "nombres");
        column_3.setAttribute("class", "apellidos");
        column_4.setAttribute("class", "fecha_nacimiento");
        column_5.setAttribute("class", "sexo");
        
        var accion_1 = document.createElement("button");
        var accion_2 = document.createElement("button");
        
        accion_1.append("Editar");
        accion_2.append("Eliminar");
        
        accion_1.setAttribute("type", "button");
        accion_1.style.margin = "2px";
        accion_1.setAttribute("class", "btn btn-secondary btn-sm");
        accion_2.setAttribute("type", "button");
        accion_2.style.margin = "2px";
        accion_2.setAttribute("class", "btn btn-secondary btn-sm");
        
        accion_1.addEventListener("click",ventana_edit_cliente,false);
        accion_2.addEventListener("click",ventana_delete_cliente,false);
        
        column_1.innerHTML = listajson[i].ID_usuario;
        column_2.innerHTML = listajson[i].nombres;
        column_3.innerHTML = listajson[i].apellidos;
        column_4.innerHTML = listajson[i].fecha_nacimiento;
        column_5.innerHTML = listajson[i].genero;
        column_6.appendChild(accion_1);
        column_6.appendChild(accion_2);
        
        row.appendChild(column_1);
        row.appendChild(column_2);
        row.appendChild(column_3);
        row.appendChild(column_4);
        row.appendChild(column_5);
        row.appendChild(column_6);
        
        TBODY.appendChild(row);
    }
    document.getElementById("tabla_usuarios").appendChild(TBODY);
}

function pullUsuarios(){   
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                var listaUsuarios = JSON.parse(http.responseText);
                desplegar_datos_usuarios(listaUsuarios);
                console.log("Se resivieron los clientes ");
            }else{
                console.log("Error al obtener la lista de cliuentes");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/listarusuarios", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud");
}

function pullInformesUsuarios(){   
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                var listaUsuarios = JSON.parse(http.responseText);
                desplegar_datos_informes_usuarios(listaUsuarios);
                console.log("Se resivieron el informe de clientes ");
            }else{
                console.log("Error al obtener el informe de clientes");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/listarusuarios", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud");
}

function eliminarUsuarios(identificacion){   
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                pullUsuarios();
                console.log("Se elimino un usuario");
            }else{
                console.log("Error al eliminar");
            }
        }
    };
    
    http.open("POST","/APIRESTfull/resources/services/capstore/eliminarusuarios", true);
    http.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    http.send(identificacion);
    console.log("Se envio la solicitud paraa eliminar");
}

function editarUsuarios(id_u,id_r,nombre,apellido,fecha_n,genero,password){   
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  // Cuando readyState es igual a 4 la respuesta esta lista
            if(http.status == 200){ // Cuando el status es 200 la respuesta llega sin problemas
                document.getElementById("modal_c_edit").style.display='none';
                pullUsuarios();
                console.log("Se actualizo el usuario");
            }else{
                console.log("Error al actaulizar el usuario");
            }
        }
    };
    http.open("POST","/APIRESTfull/resources/services/capstore/actualizarusuario", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"ID_usuario":id_u,"ID_rol":id_r,"password":password,"nombres":nombre,"apellidos":apellido,"fecha_nacimiento":fecha_n,"genero":genero}));
    console.log("Se envio la solicitud para editar");
}

function ventana_edit_cliente(Event){
    document.getElementById("modal_c_edit").style.display = 'block';
    document.getElementById('cerrar_c_edit').addEventListener("click",function(){
            document.getElementById("modal_c_edit").style.display='none';
    },false);

    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function() {
        if(http.readyState == 4) {  
            if(http.status == 200){ 
                var usuario = JSON.parse(http.responseText);
                usuario_u = usuario;
                document.getElementById("edit_c_nombre").value = usuario.nombres;
                document.getElementById("edit_c_apellido").value = usuario.apellidos;
                document.getElementById("edit_c_fecha").value = usuario.fecha_nacimiento;
                document.getElementById("edit_c_sexo").value = usuario.genero;
                document.getElementById("edit_c_contrasena_a").value = usuario.password;
                console.log("Se resivio el usuario para editar");
            }else{
                console.log("Error al buscar el usuario para editar");
            }
        }
    };
    
    http.open("GET","/APIRESTfull/resources/services/capstore/buscarUsuario?id="+Event.target.parentNode.parentNode.getElementsByClassName("cc")[0].innerText, true);
    http.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    http.send();
    console.log("Se envio la solicitud para editar");
}

document.getElementById("btn_guardar_cliente_edit").addEventListener('click',function(){
    var nombre_out = document.getElementById("edit_c_nombre").value;
    var apellido_out = document.getElementById("edit_c_apellido").value;
    var fecha_nacimiento_out = document.getElementById("edit_c_fecha").value;
    var genero_out = document.getElementById("edit_c_sexo").value;
    var password = document.getElementById("edit_c_contrasena_a").value;
    editarUsuarios(usuario_u.ID_usuario,usuario_u.ID_rol,nombre_out,apellido_out,fecha_nacimiento_out,genero_out,password);
},false);

function ventana_delete_cliente(Event){
    document.getElementById('modal_c_delete').style.display = 'block';
    
    document.getElementById('btn1_modal_c_delete').addEventListener('click',function(){
        document.getElementById("modal_c_delete").style.display='none';
        eliminarUsuarios(Event.target.parentNode.parentNode.getElementsByClassName("cc")[0].innerText);
    },false);
    
    document.getElementById('btn2_modal_c_delete').addEventListener('click',function(){
        document.getElementById("modal_c_delete").style.display='none';
    },false);
}
