/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.ser;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paw.bd.GestorBD;
import paw.bd.GestorBDPedidos;
import paw.model.ExcepcionDeAplicacion;
import paw.model.Pedido;

/**
 *
 * @author alruiz_o
 */
public class VerPedido extends HttpServlet {
    private static GestorBD gbd = new GestorBD();
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
        String codigoPedido = (String) request.getParameter("cp");
        HttpSession sesion = request.getSession();
        if(codigoPedido == null){
            response.sendRedirect("../clientes/PedidosCliente");
            return;
        }
        try {
            Pedido pedido = gbdP.getPedido(codigoPedido);
            if(pedido != null){
                if(pedido.getCliente().equals(sesion.getAttribute("cliente"))){
                    request.setAttribute("pedido", pedido);
                    request.setAttribute("lineas", pedido.getLineas());
                    RequestDispatcher rd = request.getRequestDispatcher("../clientes/verPedido.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("link", "../Salir");
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usted no está autorizado para consultar esta información.");
                }
            } else {
                request.setAttribute("link", "AreaCliente");
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Código de pedido inválido.");
            }
        } catch (ExcepcionDeAplicacion ex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Código de pedido inválido.");
        }
    }
}
