<%-- 
    Document   : fichaArticulo
    Created on : Apr 2, 2019, 4:30:47 PM
    Author     : alruiz_o
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
    <title>${art.getNombre()}</title>
    <meta name="description" content="${art.getDescripcion()}" lang="es-ES">
    <meta name="keywords" content="${art.getFabricante()} ${art.getTipo()}" lang="es-ES">
    <meta name="language" content="es-ES">
    <meta name="robots" content="index,follow">

    <link href="css/electrosa.css" rel="stylesheet" media="all" type="text/css">
    <link href="css/fichaProducto.css" rel="stylesheet" media="all" type="text/css">
  </head>

  <body>
    <%@include file="cabecera.html" %> 

    <div class="sombra">
      <div class="nucleo">
        <div id="migas">
          <a href="index.html" title="Inicio" >Inicio</a>&nbsp; | &nbsp; 
          <a href="${anterior}" title="Hojear catalogo">Hojear catálogo</a>&nbsp; | &nbsp; ${art.codigo}	
        </div>

        <div class="contenido">
          <h1>Ficha t&eacute;cnica de ${art.codigo}</h1>
          <div class="fotoDetalle">
            <img src="img/fotosElectr/${art.foto}" alt="${art.codigo}" longdesc="${art.nombre}">
          </div>
          <div class="datosDetalle">
            <h2>${art.nombre}</h2>
            <p>${art.descripcion}</p>
            <div class="precio">
              <span>Precio: ${art.pvp} &euro;</span>	
            </div>
            <div class="carroDetalle" >
              <img src="img/AddCart2-50.png" title="Añadir a mi pedido en realización">
            </div>
          </div>
          <div class="clear"></div>
        </div>
      </div>
            
      <div class="separa"></div>

      <%@include file="pie.html" %>

    </div>
  </body>
</html>