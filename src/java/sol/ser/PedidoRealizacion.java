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
import paw.bd.GestorBDPedidos;
import paw.model.Cliente;
import paw.model.ExcepcionDeAplicacion;
import paw.model.PedidoEnRealizacion;

/**
 *
 * @author alruiz_o
 */
public class PedidoRealizacion extends HttpServlet {

    private GestorBDPedidos gbdP = new GestorBDPedidos();

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
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        PedidoEnRealizacion pedidoRealizacion = (PedidoEnRealizacion) sesion.getAttribute("pedidoRealizacion");
        if (pedidoRealizacion == null) {
            try {
                pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                if (pedidoRealizacion != null) {
                    sesion.setAttribute("pedidoRealizacion", pedidoRealizacion);
                }
            } catch (ExcepcionDeAplicacion ex) {
                Logger.getLogger(PedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("../clientes/pedidoRealizacion.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
