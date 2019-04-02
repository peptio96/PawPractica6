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
public class Login extends HttpServlet {

    private static GestorBD gbd = new GestorBD();

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("entrada.jsp");
        rd.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario, contrasena, alerta;
        request.setCharacterEncoding("UTF-8");
        usuario = request.getParameter("usuario");
        contrasena = request.getParameter("contrasena");
        alerta = "";
        try {
            if ((UtilesString.isVacia(usuario)) || (UtilesString.isVacia(contrasena))) {
                alerta = "No has introducido el nombre o la contraseña";
            } else {
                HttpSession sesion = request.getSession();
                if (gbd.comprobarLogin(usuario, contrasena)) {
                    sesion.setAttribute("cliente", gbd.getClienteByUserName(usuario));
                    String returnURL = (String) sesion.getAttribute("returnURL");
                    if (returnURL != null) {
                        sesion.removeAttribute("returnURL"); // Quitarla de la sesión
                        response.sendRedirect(returnURL);
                        return;
                    } else {
                        // Dirección por defecto
                        response.sendRedirect("clientes/AreaCliente");
                        return;
                    }
                } else {
                    sesion.invalidate();
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }
            request.setAttribute("alerta", alerta);
            request.setAttribute("usuario", usuario);
            RequestDispatcher rd = request.getRequestDispatcher("entrada.jsp");
            rd.forward(request, response);
        } catch (ExcepcionDeAplicacion ex) {
            Logger.getLogger(BuscarArticulos.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }
}
