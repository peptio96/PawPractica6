/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.ser;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paw.bd.GestorBD;
import paw.model.Cliente;
import paw.model.ExcepcionDeAplicacion;
import paw.util.mail.DatosCorreo;
import paw.util.mail.GestorCorreo;
import paw.util.mail.conf.ConfiguracionCorreo;
import paw.util.mail.conf.URMailConfiguration;

/**
 *
 * @author alruiz_o
 */
public class RecuerdoContrasenia extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();
        GestorBD gbd = new GestorBD();
        try {
            String usuario = request.getParameter("usr");
            Cliente cliente = gbd.getClienteByUserName(usuario);
            if( cliente != null ){
                DatosCorreo datosCorreo = new DatosCorreo(cliente.getEmail());
                datosCorreo.setBody("Usa el siguiente enlace para acceder a una página donde podrás cambiar tu contraseña: \n" +
                                                            "http://"+request.getServerName()+
                                                            ":" + request.getServerPort()+request.getContextPath()+"/CambioContrasenia?cc="+
                                                            uuid);
                datosCorreo.setSubject("Cambio de contraseña en electrosa.com");
                datosCorreo.setMimeType("text/plain");
                datosCorreo.setCharset("UTF-8");
                
                GestorCorreo.envia(datosCorreo, ConfiguracionCorreo.getDefault());
                gbd.insertaRecuerdoContrasenia(usuario, uuid.toString());
                response.sendRedirect("avisoEmail.html");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ExcepcionDeAplicacion ex) {
            Logger.getLogger(RecuerdoContrasenia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(RecuerdoContrasenia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RecuerdoContrasenia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}