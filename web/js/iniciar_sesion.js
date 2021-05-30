/*
 *  JavaScript File: iniciar_sesion.js
 *  version: 1.0 
 *  Sincelejo: 20/05/2021
 *  Fecha de Modificación: 
 *  autor: Osnayder Conde Rodriguez
 */

var http = new XMLHttpRequest();
var id,contrasena;

http.onreadystatechange = function() {
    if(http.readyState == 4) {
        if(http.status == 200){
            if(http.responseText!="ERRORX1"){
                window.location.href = "http://localhost:8080/APIRESTfull/panel.html";
            }else{
                alert("Usted no está registrado");
            }
        }else{
            console.log("Error con el servidor");
        }
    }
};

document.getElementById('bt_iniciarsesion').addEventListener('click', function (e) {
    id         = document.getElementById("idusuario").value;
    contrasena = document.getElementById("contrasena").value;

    http.open("POST","http://localhost:8080/APIRESTfull/resources/services/capstore/sesion", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"id":id,"password":contrasena}));
},false);

