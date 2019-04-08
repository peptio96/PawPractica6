<%-- 
    Document   : modificarDatosCliente
    Created on : Apr 2, 2019, 4:26:17 PM
    Author     : alruiz_o
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" lang="es-ES">
        <title>Registro</title>
        <meta name="description" content="Aplicación de prueba de Programación de aplicaciones Web; Grado en informática; Universidad de La Rioja." lang="es-ES">
        <meta name="keywords" content="electrodomesticos" lang="es-ES">
        <meta name="language" content="es-ES">
        <meta name="robots" content="index,follow">

        <link href="../css/electrosa.css" rel="stylesheet" media="all" type="text/css">
        <link href="../css/clientes.css" rel="stylesheet" media="all" type="text/css">
    </head>

    <body >
        <%@include file="cabeceraRegistrado.html" %>

        <div class="sombra">
            <div class="nucleo">
                <div id="migas">
                    <a href="index.html" title="Inicio" >Inicio</a><!-- &nbsp; | &nbsp; 
                    <a href="..." title="Otra cosa">Otra cosa</a>   -->	
                </div>

                <div class="contenido">

                    <h1>Registro de cliente   </h1>
                    <p>Para poder realizar pedidos on-line a trav&eacute;s de nuestro sistema de pedidos es necesario que se registre. Desde Electrosa le agradecemos su confianza en nosotros. </p>
                    <p>Introduzca los datos solicitados mediante el  siguiente formulario.   </p>
                    <c:if test="${alerta.isEmpty()==false}">
                        <div class="alerta">
                            <c:forEach var="a" items="${alerta}">
                                ${a} <br>
                            </c:forEach>
                        </div>
                    </c:if>
                    <form name="fCliente" id="fCliente" action="EditaCliente" method="post">
                        <fieldset> 
                            <legend>Datos de la empresa </legend> 
                            <div class="field">
                                <label for="nombre">Raz&oacute;n social :<c:if test="${cliente.nombre==null}"><span class="Requerido">Requerido</span></c:if></label>
                                <input type="text" name="nombre" id="nombre" size="63" value="${cliente.nombre}"/>
                            </div>
                            <div class="field">
                                <label for="cif">CIF:<c:if test="${cliente.cif==null}"><span class="Requerido">Requerido</span></c:if></label>
                                <input type="text" name="cif" id="cif" size="15" value="${cliente.cif}" />
                            </div>
                            <div class="field">
                                <label for="calle">Dirección:<c:if test="${cliente.direccion.calle==null}"><span class="Requerido">Requerido</span></c:if></label>
                                <input class="text" type="text" name="calle" id="calle" size="63" value="${cliente.direccion.calle}" />
                            </div>

                            <div>
                                <div class="left">
                                    <div class="field">
                                        <label for="ciudad">Población:<c:if test="${cliente.direccion.ciudad==null}"><span class="Requerido">Requerido</span></c:if></label>
                                        <input class="text" size="15" type="text" name="ciudad" id="ciudad" value="${cliente.direccion.ciudad}" />
                                    </div>
                                    <div class="field">
                                        <label for="cp">C.P.:<c:if test="${cliente.direccion.cp==null}"><span class="Requerido">Requerido</span></c:if></label>
                                        <input class="text" type="text" name="cp" id="cp" size="10" value="${cliente.direccion.cp}" />
                                    </div>
                                </div>					
                                <!--
                                A Coruña,Álava,Albacete,Alicante,Almería,Araba,Asturias,Ávila,Badajoz,Baleares,Barcelona,Bizkaia,Burgos,Cáceres,Cádiz,Cantabria,Castellón,Ceuta,Ciudad Real,Córdoba,Cuenca,Gerona,Gipuzkoa,Girona,Granada,Guadalajara,Huelva,Huesca,Illes Balears,Jaén,La Coruña,La Rioja,Las Palmas,León,Lérida,Lleida,Lugo,Madrid,Málaga,Melilla,Murcia,Navarra,Orense,Ourense,Palencia,Pontevedra,Salamanca,Santa Cruz de Tenerife,Segovia,Sevilla,Soria,Tarragona,Teruel,Toledo,Valencia,Valladolid,Vizcaya,Zamora,Zaragoza
                                -->
                                <div class="right">
                                    <div class="field">
                                        <label for="provincia">Provincia:<c:if test="${cliente.direccion.provincia==null}"><span class="Requerido">Requerido</span></c:if></label>					
                                        <select name="provincia" id="provincia">
                                            <option value="${cliente.direccion.provincia}"><c:if test="${cliente.direccion.provincia == null}">- Elige -</c:if><c:if test="${cliente.direccion.provincia != null}">${cliente.direccion.provincia}</c:if></option>
                                                <option value="A Coruña">A Coruña</option>
                                                <option value="Álava">Álava</option>
                                                <option value="Albacete">Albacete</option>
                                                <option value="Alicante">Alicante</option>
                                                <option value="Almería">Almería</option>
                                                <option value="Araba">Araba</option>
                                                <option value="Asturias">Asturias</option>
                                                <option value="Ávila">Ávila</option>
                                                <option value="Badajoz">Badajoz</option>
                                                <option value="Baleares">Baleares</option>
                                                <option value="Barcelona">Barcelona</option>
                                                <option value="Bizkaia">Bizkaia</option>
                                                <option value="Burgos">Burgos</option>
                                                <option value="Cáceres">Cáceres</option>
                                                <option value="Cádiz">Cádiz</option>
                                                <option value="Cantabria">Cantabria</option>
                                                <option value="Castellón">Castellón</option>
                                                <option value="Ceuta">Ceuta</option>
                                                <option value="Ciudad Real">Ciudad Real</option>
                                                <option value="Córdoba">Córdoba</option>
                                                <option value="Cuenca">Cuenca</option>
                                                <option value="Gerona">Gerona</option>
                                                <option value="Gipuzkoa">Gipuzkoa</option>
                                                <option value="Girona">Girona</option>
                                                <option value="Granada">Granada</option>
                                                <option value="Guadalajara">Guadalajara</option>
                                                <option value="Guipuzcoa">Guipuzcoa</option>
                                                <option value="Huelva">Huelva</option>
                                                <option value="Huesca">Huesca</option>
                                                <option value="Illes Balears">Illes Balears</option>
                                                <option value="Jaén">Jaén</option>
                                                <option value="La Coruña">La Coruña</option>
                                                <option value="La Rioja">La Rioja</option>
                                                <option value="Las Palmas">Las Palmas</option>
                                                <option value="León">León</option>
                                                <option value="Lérida">Lérida</option>
                                                <option value="Lleida">Lleida</option>
                                                <option value="Lugo">Lugo</option>
                                                <option value="Madrid">Madrid</option>
                                                <option value="Málaga">Málaga</option>
                                                <option value="Melilla">Melilla</option>
                                                <option value="Murcia">Murcia</option>
                                                <option value="Navarra">Navarra</option>
                                                <option value="Orense">Orense</option>
                                                <option value="Ourense">Ourense</option>
                                                <option value="Palencia">Palencia</option>
                                                <option value="Pontevedra">Pontevedra</option>
                                                <option value="Salamanca">Salamanca</option>
                                                <option value="Santa Cruz de Tenerife">Santa Cruz de Tenerife</option>
                                                <option value="Segovia">Segovia</option>
                                                <option value="Sevilla">Sevilla</option>
                                                <option value="Soria">Soria</option>
                                                <option value="Tarragona">Tarragona</option>
                                                <option value="Teruel">Teruel</option>
                                                <option value="Toledo">Toledo</option>
                                                <option value="Valencia">Valencia</option>
                                                <option value="Valladolid">Valladolid</option>
                                                <option value="Vizcaya">Vizcaya</option>
                                                <option value="Zamora">Zamora</option>
                                                <option value="Zaragoza">Zaragoza</option>
                                            </select>
                                        </div>
                                        <div class="field">
                                            <label for="tfno">Teléfono:<!--<span class="Requerido">Requerido</span> --></label>
                                                <input class="text" type="text" name="tfno" id="tfno" value="${cliente.tfno}" />
                                    </div>
                                </div>		
                            </div>

                            <div class="field">
                                <label for="email">Email:<c:if test="${cliente.email==null}"><span class="Requerido">Requerido</span></c:if></label>
                                <input class="text" type="text" name="email" id="email" size="40" value="${cliente.email}" />
                            </div>
                        </fieldset>
                        <fieldset class="submit"> 
                            <div class="right">
                                <div class="field">
                                    <input class="submitb" type="submit"  value="Enviar los datos" />  
                                </div>
                            </div>
                        </fieldset>  
                    </form>
                    <!--          <div style="font-size:75%;font-style: italic;line-height: 9pt">
                                <p> En cumplimiento de lo previsto en la Ley Org&aacute;nica 15/1999, de 13 de   diciembre, de Protecci&oacute;n de Datos de Car&aacute;cter Personal y su normativa de   desarrollo, le informamos de que los datos que nos facilita por medio   del presente formulario, junto a los que sean obtenidos por raz&oacute;n de su   condici&oacute;n de Usuario Registrado del Sitio Web, ser&aacute;n incorporados a un   fichero titularidad de Distribuidora de Electrodom&eacute;sticos, S.A. (en adelante, &ldquo;ELECTROSA&rdquo;), con domicilio en la calle Luis de Ulloa, s.n.                 26004 - Logro&ntilde;o, para su   tratamiento con la finalidad de gestionar su alta como Usuario   Registrado. Asimismo, le informamos que mediante el presente registro se   le asigna un nombre de usuario y contrase&ntilde;a que le permitir&aacute; iniciar   sesi&oacute;n, en cualquier momento, como Usuario Registrado del Sitio Web y,   por tanto, acceder a todas las funcionalidades del mismo. </p>
                                <p> Todos los campos que aparecen se&ntilde;alados con el texto &quot;requerido&quot; en el   presente formulario ser&aacute;n de obligada cumplimentaci&oacute;n, de tal modo que   la omisi&oacute;n de alguno de ellos podr&aacute; comportar la imposibilidad de que   podamos atender su solicitud de alta como Usuario Registrado. </p>
                                <p> Usted otorga su consentimiento expreso para que sus datos   puedan ser tratados por ELECTROSA para remitirle informaci&oacute;n acerca de   ofertas y promociones de la empresa. </p>
                                <p> A este respecto, le informamos de que la informaci&oacute;n promocional podr&aacute;   ser remitida tanto por v&iacute;a postal, como por correo electr&oacute;nico, SMS, o   cualquier otro medio de comunicaci&oacute;n electr&oacute;nica equivalente. En este   sentido, le informamos de que podr&aacute; oponerse en todo caso a que sus   datos sean tratados con esta finalidad, en cualquier momento, mediante   el env&iacute;o de un correo electr&oacute;nico a la siguiente direcci&oacute;n <strong>francisco.garcia@unirioja.es</strong> o usando los medios espec&iacute;ficos que se reconozcan en las propias comunicaciones. </p>
                                <p> Le rogamos que nos comunique inmediatamente cualquier modificaci&oacute;n de   sus datos a fin de que la informaci&oacute;n contenida en nuestros ficheros   est&eacute; en todo momento actualizada y no contenga errores. En este sentido,   usted manifiesta que la informaci&oacute;n y los datos que nos ha facilitado   son exactos, actuales y veraces. </p>
                                <p> Usted podr&aacute; ejercitar en cualquier momento su derecho de acceso,   rectificaci&oacute;n, cancelaci&oacute;n y oposici&oacute;n al tratamiento de sus datos, en   los t&eacute;rminos previstos legalmente, dirigi&eacute;ndose a la direcci&oacute;n   anteriormente se&ntilde;alada, as&iacute; como a la   siguiente direcci&oacute;n de correo electr&oacute;nico <strong>francisco.garcia@unirioja.es</strong>, y acompa&ntilde;ando copia de un documento oficial que acredite su identidad. </p>
                              </div>-->
                </div>

            </div>

            <div class="separa"></div>
            <%@include file="../pie.html" %>

        </div>
    </body>
</html>