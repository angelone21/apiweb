<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Server API RESTfull - XML</title>
        <style>
            *{
                margin: 0px;
                padding: 0px;
            }
            header{
                text-align: center;
                padding: 10px;
            }
            section{
                padding: 10px;
            }
            footer{
                padding: 10px;
                color:  #ffffff;
                background-color: #000000;
            }
        </style>
        <script src="JS/jquery-3.5.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <header><h1>Server API RESTfull</h1></header>
        <section>
            <h2><p>FORMULARIO</p></h2><br>
            <form method="POST" >  
                <label for="identificacion">Identificacion: </label>
                <input type="text" name="id" id="id"><br>
                <label for="nombre">Nombre: </label>
                <input type="text" name="nombre" id="nombre"><br>
                <label for="apellido">Apellido: </label>
                <input type="text" name="apellido" id="apellido"><br>
            </form>
            <button id="enviar">Enviar</button>
        </section>
        
        <!--   
            <section>
                <input type="email" id="email" name="user" placeholder="E-mail">
                <input type="password" name="pass" id="pass" placeholder="Password">
                <div class="ico" id="registrar"
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                <a href="#">Register</a>
            </div>

            <div class="ico" id="registrar"
                <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                <a href="#">Register</a>
            </div>

            </section>
        
            <scritp> 
                $('#registrar').click(function(){
                        var data = { email : $('#email').val(), password : $('#pass').val() };
                        $.ajax({
                                url : 'tu_url'
                                data : data, 
                                method : 'post', //en este caso
                                dataType : 'json',
                                success : function(response){
                                       //codigo de exito
                                },
                                error: function(error){
                                       //codigo error
                                }
                        });
                });
            </script>
        -->
        <footer>
            DEVELOPED BY:<br>
            Vincenzo Angelone<br>
            Osnayder Conde<br>
            Jader Arcia<br>
        </footer>
        <script> 
            
            document.getElementById('enviar').addEventListener('click', function (e) {
                var http = new XMLHttpRequest();
                var url = "/APIRESTfull/resources/services/usuarios/agregar";
                var idIN = document.getElementById('id').value;
                var nombreIN = document.getElementById('nombre').value;
                var apellidoIN = document.getElementById('apellido').value;

                http.open("POST", url, true);
                http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                

                http.onreadystatechange = function() {
                    if(http.readyState == 4 && http.status == 200) { 
                       //aqui obtienes la respuesta de tu peticion
                       alert(http.responseText);
                    }
                }
                http.send(JSON.stringify({id: idIN, nombre: nombreIN, apellido: apellidoIN}));
            },false);
            
        </script>
    </body>
    <!-- 
    <script>
        document.addEventListener("DOMContentLoaded", function(e) {

        var miForm = document.getElementById('miForm');
        miForm.onsubmit = function(e) {
          e.preventDefault();
          var formData = new FormData(this);
          var jsonData = {};
          for (var [k, v] of formData) {
            jsonData[k] = v;
          }
          console.log(jsonData);
        }

      });
    </script>
    -->
    
</html>
