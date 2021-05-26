<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Server API RESTfull</title>
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
            <ul>
                <p>PULL</p>
                <a href="/APIRESTfull/resources/services/usuarios/xml"><li>Generar XML de Con Todos Los Usuarios</li></a>
                <a href="/APIRESTfull/xmlid.jsp"><li>Generar XML Con Un Usuario</li></a>
                <a href="/APIRESTfull/resources/services/usuarios/json"><li>Generar JSON de Con Todos Los Usuarios</li></a>
                <a href="/APIRESTfull/jsonid.jsp"><li>Generar JSON Con Un Usuario</li></a>
                <li></li>
            </ul>
            <ul>
                <a href="/APIRESTfull/agregar.jsp"><li>Agregar un Nuevo Usuario</li></a>
            </ul>
        </section>
        </section>
        <footer>
            DEVELOPED BY:<br>
            Vincenzo Angelone<br>
            Osnayder Conde<br>
            Jader Arcia<br>
        </footer>
    </body>
</html>
