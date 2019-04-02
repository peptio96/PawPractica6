/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.ser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paw.bd.GestorBD;
import paw.model.ExcepcionDeAplicacion;
import paw.util.UtilesString;

/**
 *
 * @author alruiz_o
 */
public class CambioContrasenia extends HttpServlet {

    private GestorBD gbd = new GestorBD();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recoge codigo de cambio de la peticion
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("cc");
        if (!UtilesString.isVacia(uuid)) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("uuid", uuid);
            RequestDispatcher rd = request.getRequestDispatcher("cambioContrasenia.html");
            rd.forward(request, response);
        } else {
            request.setAttribute("link", "error.jsp");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Petición de cambio de contraseña inválida. Es posible que el código de cambio haya expirado. Vuelva a solicitar el cambio.");
        }
    }

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
        String pwd = request.getParameter("pwd");
        String rpwd = request.getParameter("rpwd");
        if (pwd.equals(rpwd)) {
            HttpSession sesion = request.getSession();
            String uuid = sesion.getAttribute("uuid").toString();
            try {
                gbd.cambiaContrasenia(gbd.getUserNameDeRecuerdo(uuid), pwd);
                response.sendRedirect("clientes/AreaCliente");
            } catch (ExcepcionDeAplicacion ex) {
                Logger.getLogger(CambioContrasenia.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //contraseñas incorrectas
        }

    }
}
