<%-- 
    Document   : pedidosCliente.jsp
    Created on : Apr 8, 2019, 12:43:08 PM
    Author     : alruiz_o
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
        <title>Electrosa >> Pedidos del cliente</title>
        <meta name="description" content="Aplicación de prueba de Programación de aplicaciones Web; Grado en informática; Universidad de La Rioja." lang="es-ES">
        <meta name="keywords" content="electrodomesticos" lang="es-ES">
        <meta name="language" content="es-ES">
        <meta name="robots" content="index,follow">

        <link href="../css/electrosa.css" rel="stylesheet" media="all" type="text/css">
        <link href="../css/clientes.css" rel="stylesheet" media="all" type="text/css">
        <link href="../css/listado.css" rel="stylesheet" media="all" type="text/css">
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
                    Bienvenido ${cliente.nombre}
                </div>
                <div class="clear"></div>
                <div class="contenido">
                    <h1>Sus pedidos    </h1>
                    <a name="inicio"></a>
                    <p>Estos son sus pedidos. </p>
                    <p>Actualmente, disponible de un <a href="../clientes/PedidoRealizacion">pedido en realización</a></p>
                    <p>&nbsp;<span class="atajo"><a href="#comp">Completados</a> &nbsp; | &nbsp; <a href="#anul">Anulados</a></span></p>

                    <table width="95%">
                        <colgroup>
                            <col width="5%">
                            <col width="5%">
                            <col width="14%">
                            <col width="14%">
                            <col width="51%">
                            <col width="11%">
                        </colgroup>
                        <tr>
                            <td colspan="6">Listado de pedidos pendientes </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>C&oacute;digo </td>
                            <td>Fecha </td>
                            <td>Direcci&oacute;n de entrega </td>
                            <td>Importe </td>
                        </tr>            
                        <c:forEach var="pedidoPendiente" items="${pedidosPendientes}" varStatus="contad">
                            <c:if test="${contad.count%2 == 0}"><tr class="par"></c:if>
                            <c:if test="${contad.count%2 != 0}"><tr></c:if>
                                <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                                <td style="text-align: center"><a href="VerPedido?cp=${pedidoPendiente.codigo}"><img src="../img/cancel.png" title="Cancelar el pedido"/></td>
                                <td><a href="VerPedido?cp=${pedidoPendiente.codigo}">${pedidoPendiente.codigo}</a></td>
                                <td><fmt:formatDate value="${pedidoPendiente.fechaCierre.time}"/></td>
                                <td>${pedidoPendiente.dirEntrega}</td>
                                <td style="text-align: right"><fmt:formatNumber type="currency" value="${pedidoPendiente.importe}"/></td>
                            </tr>
                        </c:forEach>
                    </table>

                    <span class="atajo"><a href="#inicio">Inicio</a></span>

                    <p>&nbsp;</p>          
                    <a name="comp"></a>
                    <table width="95%">
                        <colgroup>
                            <col width="5%">
                            <col width="14%">
                            <col width="14%">
                            <col width="56%">
                            <col width="11%">
                        </colgroup>
                        <tr>
                            <td colspan="5">Listado de pedidos Completados</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>C&oacute;digo </td>
                            <td>Fecha </td>
                            <td>Direcci&oacute;n de entrega </td>
                            <td>Importe </td>
                        </tr>
                        <c:forEach var="pedidoCompletado" items="${pedidosCompletados}" varStatus="contador">
                            <c:if test="${contador.count%2 == 0}"><tr class="par"></c:if>
                            <c:if test="${contador.count%2 != 0}"><tr></c:if>
                                <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                                <td><a href="VerPedido?cp=${pedidoCompletado.codigo}">${pedidoCompletado.codigo}</a></td>
                                <td><fmt:formatDate value="${pedidoCompletado.fechaCierre.time}"/></td>
                                <td>${pedidoCompletado.dirEntrega}</td>
                                <td style="text-align: right"><fmt:formatNumber type="currency" value="${pedidoCompletado.importe}"/></td>
                            </tr>
                        </c:forEach>
                    </table>

                    <span class="atajo"><a href="#inicio">Inicio</a></span>

                    <p>&nbsp;</p>
                    <a name="anul"></a>
                    <table width="55%">
                        <colgroup>
                            <col width="10%">
                            <col width="26%">
                            <col width="32%">
                            <col width="32%">
                        </colgroup>
                        <tr>
                            <td colspan="4">Listado de pedidos anulados </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>C&oacute;digo</td>
                            <td>Fecha cierre</td>
                            <td>Fecha anulación</td>
                        </tr>            
                        <c:forEach var="pedidoAnulado" items="${pedidosAnulados}" varStatus="cuenta">
                            <c:if test="${cuenta.count%2 == 0}"><tr class="par"></c:if>
                            <c:if test="${cuenta.count%2 != 0}"><tr></c:if>
                                <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                                <td><a href="VerPedidoAnulado?cp=${pedidoAnulado.codigo}">${pedidoAnulado.codigo}</a></td>
                                <td><fmt:formatDate value="${pedidoAnulado.fechaCierre.time}"/></td>
                                <td><fmt:formatDate value="${pedidoAnulado.fechaAnulacion.time}"/></td>
                            </tr>
                        </c:forEach>
                    </table>

                    <span class="atajo"><a href="#inicio">Inicio</a></span>
                </div>

                <div class="clear"></div>
            </div>

            <div class="separa"></div>

            <%@include file="../pie.html" %>

        </div>
    </body>
</html>
