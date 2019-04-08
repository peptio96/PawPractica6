<%-- 
    Document   : pedidosCliente.jsp
    Created on : Apr 8, 2019, 12:43:08 PM
    Author     : alruiz_o
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <div class="logo"><a href="../index.html"><img src="../img/LogoElectrosa200.png" border="0"></a></div>


        <div class="sombra">
            <div class="nucleo">
                <div id="lang">
                    <a href="index.html">Español</a> &nbsp; | &nbsp; <a href="index.html">English</a>
                </div>
            </div>
        </div>  

        <div class="barra_menus">
            <div class="pestanias">
                <div class="grupoPestanias">
                    <div class="pestaniaSel">Para usuarios</div>
                    <div class="pestaniaNoSel">Intranet</div>
                </div>
            </div>

            <div id="menu">
                <ul>
                    <li>
                        <a href="../BuscarArticulos">Comprar </a>
                    </li>
                    <li>
                        <a href="PedidosCliente">Mis pedidos </a>
                    </li>
                    <li>
                        <a href="EditaCliente">Cambiar datos personales </a>	
                    </li>
                    <li>
                        <a href="../Salir">Cerrar sesión </a>	
                    </li>
                </ul>
                <div style="clear: left;"></div>
            </div>
        </div> 

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
                        <c:forEach var="pedido" items="${pedidosPendientes}">
                            <tr class="par">
                                <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                                <td style="text-align: center"><img src="../img/cancel.png" title="Cancelar el pedido"/></td>
                                <td>${pedido.codigo}</td>
                                <td><fmt:formatDate value="${pedido.fechaCierre.time}"/>:</td>
                            <td>${pedido.dirEntrega}</td>
                            <td style="text-align: right"><fmt:formatNumber type="currency" value="${pedido.importe}"/> &euro;</td>
                            </tr>
                        </c:forEach>
                        <tr >
                            <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                            <td style="text-align: center"><img src="../img/cancel.png" title="Cancelar el pedido"/></td>
                            <td>P000147-12</td>
                            <td>24-feb-2012</td>
                            <td>Jorge Vigón 23, Logroño.  26004- La Rioja </td>
                            <td style="text-align: right">6.564 &euro;</td>
                        </tr>
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
                        <tr class="par">
                            <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                            <td>P000004-08</td>
                            <td>11-dic-2008</td>
                            <td>Jorge Vigón 23, Logroño.  26004- La Rioja </td>
                            <td style="text-align: right">11.324 &euro;</td>
                        </tr>

                        <tr>
                            <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                            <td>P000005-08</td>
                            <td>03-abr-2008</td>
                            <td>Jorge Vigón 23, Logroño.  26004- La Rioja </td>
                            <td style="text-align: right">20.305 &euro;</td>
                        </tr>
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
                        <tr class="par">
                            <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                            <td>P000053-10</td>
                            <td>23-feb-2010</td>
                            <td>24-feb-2012</td>
                        </tr>

                        <tr >
                            <td style="text-align: center"><img src="../img/pdf.gif" title="Descargar en PDF"/></td>
                            <td>P000054-10</td>
                            <td>13-dic-2010</td>
                            <td>24-feb-2012</td>
                        </tr>
                    </table>

                    <span class="atajo"><a href="#inicio">Inicio</a></span>
                </div>

                <div class="clear"></div>
            </div>

            <div class="separa"></div>

            <div class="pie">
                <span class="pie_izda">
                    <a href="mailto:francisco.garcia@unirioja.es">Contacto</a> &nbsp; | &nbsp; <a href="../mapa.html">Mapa</a> </span>
                <span class="pie_dcha">
                    &copy; 2012 Francisco J. García Izquierdo  </span>
                <div class="clear"></div>  
            </div>

        </div>
    </body>
</html>
