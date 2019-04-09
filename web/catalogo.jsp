<%-- 
    Document   : catalogo
    Created on : Apr 2, 2019, 4:28:05 PM
    Author     : alruiz_o
--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
        <title>Electrosa >> cat&aacute;logo</title>
        <meta name="description" content="Aplicación de prueba de Programación de aplicaciones Web; Grado en informática; Universidad de La Rioja." lang="es-ES">
        <meta name="keywords" content="electrodomesticos" lang="es-ES">
        <meta name="language" content="es-ES">
        <meta name="robots" content="index,follow">

        <link href="css/electrosa.css" rel="stylesheet" media="all" type="text/css">
        <link href="css/catalogoMosaico.css" rel="stylesheet" media="all" type="text/css">
        <!--    <link href="css/catalogoLista.css" rel="stylesheet" media="all" type="text/css"> -->
    </head>
    <body>
        <c:if test="${cliente==null}"><jsp:include page="cabecera.html"/></c:if>
        <c:if test="${cliente!=null}"><jsp:include page="clientes/cabeceraRegistrado.html"/></c:if>
    <div class="sombra">
        <div class="nucleo">

            <div id="migas">
                <a href="index.html" title="Inicio" >Inicio</a>&nbsp; | &nbsp; 
                <a href="generica.html" title="Hojear catalogo">Hojear catalogo</a>	<!-- &nbsp; | &nbsp; 
                <a href="..." title="Otra cosa">Otra cosa</a>   -->	
            </div>
            <div class="contenido">
                <h1>Nuestros productos</h1>
                <p>Puede buscar los productos que necesite en nuestro cat&aacute;logo. Lo hemos organizado por marcas, tipo de electrodom&eacute;stico y rango de precios. Lo precios indicados en rojo corresponden a ofertas. </p>
                <div class="filtroCatalogo">
                    <form name="filtroCatalogo" id="filtroCatalogo" action="BuscarArticulos">

                        <label for="tipo">Tipo: </label>
                        <select name="tipo" id="tipo">
                            <option value="-1">- Cualquiera -</option> 
                            <c:forEach var="t" items="${tiposArticulos}">
                                <option value="${t}" ${(t == tipo ? 'selected' : '')}>${t}</option>
                            </c:forEach>
                        </select>

                        <label for="fabricante">Fabricante: </label>
                        <select name="fabricante" id="fabricante">
                            <option value="-1">- Cualquiera -</option> 
                            <c:forEach var="f" items="${fabricanteArticulos}">
                                <option value="${f}" ${(f == fabricante ? 'selected' : '')}>${f}</option>
                            </c:forEach>
                        </select>

                        <label for="precio">Precio: </label>
                        <select name="precio" id="precio">
                            <option value="-1">- Cualquiera -</option>
                            <option value="10-50">10-50 &euro;</option>
                            <option value="50-100">50-100 &euro;</option>
                            <option value="100-200">100-200 &euro;</option>
                            <option value="200-500">200-500 &euro;</option>
                            <option value="500-1000">500-1000 &euro;</option>
                            <option value="1000">Mas de 1000 &euro;</option>
                        </select>
                        <label for="nombre">Nombre: </label>
                        <input type="text" id="nombre" name="nombre" value="${nombre}">
                        <label for="codigo">Código </label>
                        <input type="text" id="codigo" name="codigo" value="${codigo}">

                        <input href="/BuscarArticulos" name="buscar" id="buscar" type="image" title="Buscar" src="img/search25.png" alt="Buscar">

                    </form>

                    <div class="modovisual">
                        <a href="catalogo.html">Mosaico</a> &nbsp; | &nbsp; <a href="catalogo.html">Lista</a>
                    </div>
                    <div class="clear"></div>
                </div>
                <c:if test="${paginador != null}">  
                    <c:if test="${not empty articulos}">
                        <div class="resumResul redondeo">
                            Encontrados ${numRegs} artículos. Mostrando página ${pagina} de ${numPags}.
                            <span class="paginador">
                                <c:if test="${pagina != 1}">
                                    <a href="BuscarArticulos?p=${paginador.anterior(pagina)}<c:if test="${tipo != null}">&tipo=${tipo}</c:if><c:if test="${fabricante != null}">&fabricante=${fabricante}</c:if><c:if test="${precio != null}">&precio=${precio}</c:if><c:if test="${nombre != null}">&nombre=${nombre}</c:if><c:if test="${codigo != null}">&codigo=${codigo}</c:if>">Anterior</a>              
                                </c:if>
                                <c:forEach var="t" items="${adyacentes}">
                                    <c:if test="${pagina == t}">
                                        <span>${t}</span>
                                    </c:if>
                                    <c:if test="${pagina != t}">
                                        <a href="BuscarArticulos?p=${t}<c:if test="${tipo != null}">&tipo=${tipo}</c:if><c:if test="${fabricante != null}">&fabricante=${fabricante}</c:if><c:if test="${precio != null}">&precio=${precio}</c:if><c:if test="${nombre != null}">&nombre=${nombre}</c:if><c:if test="${codigo != null}">&codigo=${codigo}</c:if>">${t}</a>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${pagina != numPags}">
                                    <a href="BuscarArticulos?p=${paginador.siguiente(pagina)}<c:if test="${tipo != null}">&tipo=${tipo}</c:if><c:if test="${fabricante != null}">&fabricante=${fabricante}</c:if><c:if test="${precio != null}">&precio=${precio}</c:if><c:if test="${nombre != null}">&nombre=${nombre}</c:if><c:if test="${codigo != null}">&codigo=${codigo}</c:if>">Siguiente</a>               
                                </c:if>

                            </span>
                        </div>
                    </c:if>
                    <c:if test="${empty articulos}">
                        <div class="resumResul redondeo">
                            Encontrados 0 artículos.
                        </div>
                    </c:if>
                </c:if>

                <ul class="resultBusqueda">
                    <c:forEach var="a" items="${articulos}">
                        <li class="item redondeo">
                            <div class="foto">
                                <a href="../pr6/FichaArticulo?cart=${a.codigo}"><img src="img/fotosElectr/${a.foto}" alt="${a.nombre}" longdesc="${a.descripcion}" width="80"></a>
                            </div>
                            <div class="datos">
                                <span>${a.nombre}</span>
                                <div class="precio">
                                    <span class="oferta">${a.pvp} &euro;</span>
                                </div>
                                <div class="carro">
                                    <img src="img/shopcartadd_16x16.png" title="Añadir a mi carro de la compra">
                                </div>
                            </div>			  
                            <div class="codigo"><a href="../pr6/FichaArticulo?cart=${a.nombre}">${a.codigo}</a></div>

                        </li>			
                    </c:forEach>
                </ul>			
                <div class="clear"></div>

            </div>
        </div>

        <div class="separa"></div>

        <jsp:include page="pie.html"/>

    </div>
</body>
</html>