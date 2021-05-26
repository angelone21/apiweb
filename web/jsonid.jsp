<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Server API RESTfull - JSON</title>
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
    </head>
    <body>
        <header><h1>Server API RESTfull</h1></header>
        <section>
            <h2><p>FORMULARIO</p></h2><br>
            <form action="/APIRESTfull/resources/services/usuarios/jsonB" method="post">
                <label for="identificacion">Identificacion: </label>
                <input type="text" name="id" id="id"><br><br>
                <input type="submit" value=" Enviar ">
            </form>
        </section>
        <footer>
            DEVELOPED BY:<br>
            Vincenzo Angelone<br>
            Osnayder Conde<br>
            Jader Arcia<br>
        </footer>
    </body>
</html>
