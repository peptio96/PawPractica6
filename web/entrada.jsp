<%--
    Document   : entrada
    Created on : Mar 19, 2019, 4:24:05 PM
    Author     : alruiz_o
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
        <title>Login</title>
        <meta name="description" content="Aplicación de prueba de Programación de aplicaciones Web; Grado en informática; Universidad de La Rioja." lang="es-ES">
        <meta name="keywords" content="electrodomesticos" lang="es-ES">
        <meta name="language" content="es-ES">
        <meta name="robots" content="index,follow">

        <link href="css/electrosa.css" rel="stylesheet" media="all" type="text/css">
        <link href="css/entrada.css" rel="stylesheet" media="all" type="text/css">
        <script type="text/javascript">
            window.onload = function () {
                document.getElementById("usr").focus();
            };
        </script>
    </head>

    <body >
        <%@include file="cabecera.html" %>

        <div class="sombra">
            <div class="nucleo">
                <div id="migas">
                    <a href="index.html" title="Inicio" >Inicio</a><!-- &nbsp; | &nbsp; 
                    <a href="..." title="Otra cosa">Otra cosa</a>   -->	
                </div>

                <div class="contenido">

                    <h1>Acceso de usuario registrado</h1> 
                    <c:if test="${alerta!=null}">
                        <div class="alerta">
                            ${alerta}
                        </div>    
                    </c:if>
                    <form name="flogin" id="flogin" class="izda" action="Login" method="post">
                        <fieldset> 
                            <div class="field">
                                <label for="usuario">Nombre de usuario :</label>
                                <input type="text" name="usuario" id="usuario" size="10" value="${usuario}"/>
                            </div>

                            <div class="field">
                                <label for="contrasena">Contraseña:</label>
                                <input type="password" name="contrasena" id="contrasena" size="10" />
                            </div>

                            <div class="dcha">
                                <div class="field">
                                    <input class="submitb" type="submit"  value="Acceder" />  
                                </div>
                            </div>
                            <div style="clear:right"></div>
                            <div class="field">
                                <a href="recuerdo.html">&iquest;Olvid&oacute; su contrase&ntilde;a? </a></div>
                        </fieldset>  
                    </form>



                    <div class="nuevo izda">
                        <div>
                            <p style="font-weight:bold; margin-top:5px">Nuevo cliente.</p>
                            <p style="margin-top:5px;line-height:1.4em;">Para poder realizar pedidos on-line a través de nuestro sistema de pedidos es necesario que se registre </p>
                        </div>

                        <div  class="registrob">
                            <a href="NuevoCliente">REGISTRESE</a>
                        </div> 
                    </div>		  

                    <div class="clear"> </div>
                </div>
            </div>

            <%@include file="../pie.html" %>

        </div>
    </body>
</html>