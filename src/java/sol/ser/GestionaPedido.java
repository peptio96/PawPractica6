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
import paw.bd.GestorBDPedidos;
import paw.model.Articulo;
import paw.model.Cliente;
import paw.model.ExcepcionDeAplicacion;
import paw.model.PedidoEnRealizacion;

/**
 *
 * @author alruiz_o
 */
public class GestionaPedido extends HttpServlet {

    private static GestorBDPedidos gbdP = new GestorBDPedidos();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String valorAccion = request.getParameter("accion");

        HttpSession sesion = request.getSession();
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        PedidoEnRealizacion pedidoRealizacion = (PedidoEnRealizacion) sesion.getAttribute("pedidoRealizacion");
        switch (valorAccion) {
            case "Comprar":
                String codigoArticulo = request.getParameter("ca");
                if (pedidoRealizacion == null) {
                    try {
                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            pedidoRealizacion.addLinea(gbd.getArticulo(codigoArticulo));

                        } else {
                            pedidoRealizacion = new PedidoEnRealizacion(cliente);
                            pedidoRealizacion.addLinea(gbd.getArticulo(codigoArticulo));
                        }
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(PedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sesion.setAttribute("pedidoRealizacion", pedidoRealizacion);
                } else {
                    try {
                        pedidoRealizacion.addLinea(gbd.getArticulo(codigoArticulo));
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(GestionaPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher("/clientes/pedidoRealizacion.jsp");
                rd.forward(request, response);
                break;
            case "Seguir comprando":
                break;
            case "Guardar pedido":
                break;
            case "Quitar":
                String codigoLinea = request.getParameter("cl");
                if (pedidoRealizacion == null) {
                    try {
                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            pedidoRealizacion.removeLinea(codigoLinea);
                            if (!pedidoRealizacion.getLineas().isEmpty()) {
                                sesion.setAttribute("pedidoRealizacion", pedidoRealizacion);
                            }
                        }
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(PedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    pedidoRealizacion.removeLinea(codigoLinea);
                    if (!pedidoRealizacion.getLineas().isEmpty()) {
                        sesion.setAttribute("pedidoRealizacion", pedidoRealizacion);
                    }
                }
                RequestDispatcher reqd = request.getRequestDispatcher("/clientes/pedidoRealizacion.jsp");
                reqd.forward(request, response);
                break;
            case "Cerrar pedido":
                break;
            case "Cancelar":
                if (pedidoRealizacion == null) {
                    try {
                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            gbdP.anulaPedido(pedidoRealizacion);
                            sesion.setAttribute("pedidoRealizacion", null);
                        }
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(PedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        gbdP.anulaPedido(pedidoRealizacion);
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(GestionaPedido.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sesion.setAttribute("pedidoRealizacion", null);
                }
                RequestDispatcher reqdis = request.getRequestDispatcher("/clientes/pedidoRealizacion.jsp");
                reqdis.forward(request, response);
                break;
            default:
                break;
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
        doGet(request, response);
    }
}
