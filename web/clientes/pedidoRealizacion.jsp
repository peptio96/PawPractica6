<%-- 
    Document   : PedidoRealizacion
    Created on : Apr 8, 2019, 6:43:11 PM
    Author     : alruiz_o
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
        <title>Electrosa >> Pedido en realización</title>
        <meta name="description" content="Aplicación de prueba de Programación de aplicaciones Web; Grado en informática; Universidad de La Rioja." lang="es-ES">
        <meta name="keywords" content="electrodomesticos" lang="es-ES">
        <meta name="language" content="es-ES">
        <meta name="robots" content="index,follow">

        <link href="../css/electrosa.css" rel="stylesheet" media="all" type="text/css">
        <link href="../css/clientes.css" rel="stylesheet" media="all" type="text/css">
        <link href="../css/pedidoR.css" rel="stylesheet" media="all" type="text/css">
    </head>

    <body >
        <%@include file="cabeceraRegistrado.html" %>

        <div class="sombra">
            <div class="nucleo">
                <div id="migas">
                    <a href="../index.html" title="Inicio" >Inicio</a> &nbsp; | &nbsp; 
                    <a href="AreaCliente" title="Área de cliente">Área de cliente</a>
                </div>
                <div id="cliente">
                    Bienvenido, ${cliente.nombre}
                </div>
                <div class="clear"></div>
                <div class="contenido">
                    <h1>Contenido de su  pedido    </h1>
                    <c:if test="${pedidoRealizacion == null}">
                        <p>Actualmente no está realizando ninguna compra en Electrosa. Use el enlace "Comprar" en la barra de menú.</p>
                    </c:if>
                    <c:if test="${pedidoRealizacion != null}">
                    
                        <p>Pedido iniciado el <fmt:formatDate pattern="dd/MM/YYYY" value="${pedidoRealizacion.inicio.time}"/> a las <fmt:formatDate pattern="HH:mm" value="${pedidoRealizacion.inicio.time}"/>.</p>
                    
                    <form action="GestionaPedido" method="post">
                        <table width="95%" cellspacing="0">
                            <tr>
                                <td colspan="2"><img src="../img/AddCartb.png" style="vertical-align:middle;margin-bottom:3px; margin-left:15px">&nbsp; Art&iacute;culos del pedido</td>
                                <td width="10%">P.V.P.</td>
                                <td width="10%">Cantidad</td>
                                <td width="16%">Fecha de entrega (dd/mm/yyyy)</td>
                            </tr>
                            <c:forEach var="lineaRealizacion" items="${pedidoRealizacion.lineas}" varStatus="contad">
                            <tr >
                                <td width="6%" style="text-align:center"><a href="GestionaPedido?accion=Quitar&cl=${lineaRealizacion.codigo}"><img src="../img/cancel.png" alt="Quitar del pedido" border="0" title="Quitar del pedido"></a></td>
                                <td width="58%"><span class="codigo">${lineaRealizacion.articulo.codigo}</span> - <br/><span class="descr">${lineaRealizacion.articulo.nombre}</span></td>
                                <td><fmt:formatNumber type="currency" value="${lineaRealizacion.articulo.pvp}"/></td>
                                <td>
                                    <input class="cantidad" type="text" name="C_${lineaRealizacion.codigo}" size="3" value="${lineaRealizacion.cantidad}">
                                </td>
                                <td>
                                    <input type="text" name="F_${lineaRealizacion.codigo}" size="10" value="<fmt:formatDate pattern="dd/MM/YYYY" value="${lineaRealizacion.fechaEntregaDeseada.time}"/>">			  
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                        <input class="submitb" type="submit" name="accion" value="Seguir comprando">
                        <input class="submitb" type="submit" name="accion" value="Guardar pedido">
                        <input class="submitb cerrar" type="submit" name="accion" value="Cerrar pedido">	
                        <input class="submitb cancelar" type="submit" name="accion" value="Cancelar">
                    </form>
                    </c:if>
                </div>
                <div class="clear"></div>
            </div>

            <div class="separa"></div>

            <%@include file="../pie.html" %>

        </div>
    </body>
</html>