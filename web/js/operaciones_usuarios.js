/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function(){ pullUsuarios();};

document.getElementById('actualizar_tabla_usuarios').addEventListener('click',pullUsuarios,false);

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

function ventana_edit_cliente(Event){
	document.getElementById("modal_c_edit").style.display = 'block';
	document.getElementById('cerrar_c_edit').addEventListener("click",function(event){
		document.getElementById("modal_c_edit").style.display='none';
	},false);
	
	document.getElementById("edit_c_nombre").value = Event.target.parentNode.parentNode.getElementsByClassName("nombres")[0].innerText;
        document.getElementById("edit_c_apellido").value = Event.target.parentNode.parentNode.getElementsByClassName("apellidos")[0].innerText;
        document.getElementById("edit_c_fecha").value = Event.target.parentNode.parentNode.getElementsByClassName("fecha_nacimiento")[0].innerText;
        document.getElementById("edit_c_sexo").value = Event.target.parentNode.parentNode.getElementsByClassName("sexo")[0].innerText;
	
}
