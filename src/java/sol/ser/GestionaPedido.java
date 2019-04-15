/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.ser;

import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
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
import paw.model.Cliente;
import paw.model.ExcepcionDeAplicacion;
import paw.model.LineaEnRealizacion;
import paw.model.PedidoEnRealizacion;
import paw.util.servlet.ParameterParser;

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
                String urlAnterior = request.getHeader("Referer");
                if (urlAnterior.contains("BuscarArticulos")) {
                    sesion.setAttribute("urlAnterior", urlAnterior);
                }
                response.sendRedirect("PedidoRealizacion");
                break;
            case "Seguir comprando":
                String anterior = (String) sesion.getAttribute("urlAnterior");
                if (pedidoRealizacion == null) {
                    try {
                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            procesaParams(pedidoRealizacion, request);
                        }
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(PedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    procesaParams(pedidoRealizacion, request);
                }
                if (anterior == null) {
                    response.sendRedirect(request.getContextPath() + "/BuscarArticulos");
                } else {
                    response.sendRedirect(anterior);
                }
                break;
            case "Guardar pedido":
                try {
                    if (pedidoRealizacion == null) {

                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            procesaParams(pedidoRealizacion, request);
                        }
                    } else {
                        procesaParams(pedidoRealizacion, request);
                    }
                    gbdP.grabaPedidoEnRealizacion(pedidoRealizacion);
                } catch (ExcepcionDeAplicacion ex) {
                    Logger.getLogger(GestionaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect(request.getContextPath() + "/clientes/AreaCliente");
                break;

            case "Quitar":
                String codigoLinea = request.getParameter("cl");
                if (pedidoRealizacion == null) {
                    try {
                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            procesaParams(pedidoRealizacion, request);
                            pedidoRealizacion.removeLinea(codigoLinea);
                            if (!pedidoRealizacion.getLineas().isEmpty()) {
                                sesion.setAttribute("pedidoRealizacion", pedidoRealizacion);
                            }
                        }
                    } catch (ExcepcionDeAplicacion ex) {
                        Logger.getLogger(PedidoRealizacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    procesaParams(pedidoRealizacion, request);
                    pedidoRealizacion.removeLinea(codigoLinea);
                    if (!pedidoRealizacion.getLineas().isEmpty()) {
                        sesion.setAttribute("pedidoRealizacion", pedidoRealizacion);
                    }
                }
                RequestDispatcher reqd = request.getRequestDispatcher("/clientes/pedidoRealizacion.jsp");
                reqd.forward(request, response);
                break;
            case "Cerrar pedido": {
                try {
                    if (pedidoRealizacion == null) {
                        pedidoRealizacion = gbdP.getPedidoEnRealizacion(cliente.getCodigo());
                        if (pedidoRealizacion != null) {
                            procesaParams(pedidoRealizacion, request);
                        }
                    } else {
                        procesaParams(pedidoRealizacion, request);
                    }
                    //gbdP.grabaPedidoEnRealizacion(pedidoRealizacion);
                    sesion.setAttribute("pedidoACerrar", pedidoRealizacion);
                    request.setAttribute("msg", "Se va a proceder a cerrar su pedido en realización. ¿Está usted seguro?");
                    request.setAttribute("siLink", "CierraPedido?accion=cerrar");
                    request.setAttribute("noLink", "CierraPedido?accion=cancelar");
                    RequestDispatcher reqdis = request.getRequestDispatcher("confirmacion.jsp");
                    reqdis.forward(request, response);
                } catch (ExcepcionDeAplicacion ex) {
                    Logger.getLogger(GestionaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    gbdP.cierraPedido(pedidoRealizacion, cliente.getDireccion());
                } catch (ExcepcionDeAplicacion ex) {
                    Logger.getLogger(GestionaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    private void procesaParams(PedidoEnRealizacion pedido, HttpServletRequest req) {
        ParameterParser pp = new ParameterParser(req);
        Enumeration<String> pnames = req.getParameterNames();
        while (pnames.hasMoreElements()) {
            String paramName = pnames.nextElement();
            if (paramName.startsWith("C_")) {
                String codLinea = paramName.substring(2);
                LineaEnRealizacion linea = pedido.getLinea(codLinea);
                int cantidad = pp.getIntParameter(paramName, 1);
                linea.setCantidad(cantidad);
            } else if (paramName.startsWith("F_")) {
                String codLinea = paramName.substring(2);
                LineaEnRealizacion linea = pedido.getLinea(codLinea);
                Calendar fe = pp.getCalendarParameter(paramName, "dd/MM/yyyy", Calendar.getInstance());
                linea.setFechaEntregaDeseada(fe);
            }
        }
    }
}
