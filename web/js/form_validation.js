var http = new XMLHttpRequest();
(function () {
    'use strict'
    
    var forms = document.querySelectorAll('.needs-validation');
    
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {            
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }else{
                if(validar_contrasena()){
                    enviar_datos();
                    event.preventDefault();
                }else{
                    event.preventDefault();
                    event.stopPropagation();
                }
            }
            form.classList.add('was-validated');
        }, false);
    });       
})();

function validar_contrasena(){
    var estado = true;
    if(document.getElementById("contrasena").value!=document.getElementById("rcontrasena").value){
        document.getElementById("alerta").innerHTML = "Las contraseñas no coinciden";
        estado = false;
    }
    return estado;
}

function enviar_datos(){
    var idetificacionIN = document.getElementById("identificacion").value;
    var nombresIN  = document.getElementById("nombres").value;
    var apellidosIN = document.getElementById("apellidos").value;
    var fechanacimientoIN = document.getElementById("fechanacimiento").value;
    var sexoIN = document.getElementById("sexo").value;
    var contrasenaIN    = document.getElementById("contrasena").value;

    http.open("POST","http://localhost:8080/APIRESTfull/resources/services/capstore/registroUsuarioCliente", true);
    http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    http.send(JSON.stringify({"ID_usuario":idetificacionIN,"ID_rol":1,"password":contrasenaIN,"nombres":nombresIN,"apellidos":apellidosIN,"fecha_nacimiento":fechanacimientoIN,"genero":sexoIN}));
    console.log("Enviando datos al servidor...");
}

http.onreadystatechange = function() {
    if(http.readyState == 4) {
        if(http.status == 200){
            alert("Registro Exitoso");
            location.reload();
        }else{
           alert("Su Registro Fue Fallido");
        }
    }
};