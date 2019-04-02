<%-- 
    Document   : error
    Created on : Mar 5, 2019, 5:17:52 PM
    Author     : alruiz_o
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
    <title>Electrosa >> Error de aplicación</title>
    <meta name="description" content="Aplicación de prueba de Programación de aplicaciones Web; Grado en informática; Universidad de La Rioja." lang="es-ES">
    <meta name="keywords" content="electrodomesticos" lang="es-ES">
    <meta name="language" content="es-ES">
    <meta name="robots" content="index,follow">

    <link href="css/electrosa.css" rel="stylesheet" media="all" type="text/css">
  </head>
  <body>
  <jsp:include page="cabecera.html"/>

    <div class="sombra">
      <div class="nucleo">
        <div id="migas">
          <a href="index.html" title="Inicio" >Inicio</a><!-- &nbsp; | &nbsp; 
          <a href="..." title="Otra cosa">Otra cosa</a>   -->	
        </div>

        <div class="contenido">
		  <div style="float:left"><img src="img/alerta.png"></div>
		  <div class="error">
		    <div>Error  ${empty requestScope['javax.servlet.error.status_code'] ? 'Error de aplicación' : requestScope['javax.servlet.error.status_code']} : ${empty requestScope['javax.servlet.error.message'] ? 'Error de aplicación' : requestScope['javax.servlet.error.message']}</div>

		    <div class="errorb"><a href="index.html">Salir de aqui</a></div>
		  </div>
		  <div class="clear"></div>
          
        </div>
      </div>

      <div class="separa"></div>

      <jsp:include page="pie.html"/>

    </div>
  </body>
</html>