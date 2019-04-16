/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.ser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paw.bd.GestorBDPedidos;
import paw.model.ExcepcionDeAplicacion;
import paw.model.PedidoEnRealizacion;

/**
 *
 * @author alruiz_o
 */
public class AnulaPedidoRealizacion extends HttpServlet {
    private static GestorBDPedidos gbdP = new GestorBDPedidos();
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
        HttpSession sesion = request.getSession();
        PedidoEnRealizacion pedidoACancelar = (PedidoEnRealizacion) sesion.getAttribute("pedidoACancelar");
        if (pedidoACancelar == null) {
            request.setAttribute("link", "AreaCliente");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La aplicación no puede determinar el pedido a anular.");
        } else {
            sesion.removeAttribute("pedidoACancelar");
            String accion = (String) request.getParameter("accion");
            if (accion.equals("anular")) {
                try {
                    gbdP.anulaPedido(pedidoACancelar);
                    sesion.removeAttribute("pedidoRealizacion");
                    response.sendRedirect("AreaCliente");
                } catch (ExcepcionDeAplicacion ex) {
                    Logger.getLogger(AnulaPedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("PedidoRealizacion");
            }
        }
    }
}
