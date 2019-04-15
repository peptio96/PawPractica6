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
import paw.bd.CriteriosArticulo;
import paw.bd.GestorBD;
import paw.bd.Paginador;
import paw.model.ExcepcionDeAplicacion;
import paw.util.UtilesString;

/**
 *
 * @author alruiz_o
 */
public class BuscarArticulos extends HttpServlet {

    private static int tamanioPagina = 15;
    private static GestorBD gbd = new GestorBD();

    @Override
    public void init() throws ServletException {
        try {
            tamanioPagina = Integer.parseInt(this.getInitParameter("tamanioPagina"));
        } catch (Exception ex) {
            Logger.getLogger(BuscarArticulos.class.getName()).log(Level.WARNING, null, ex);
        }
    }

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
        String pagina = request.getParameter("p");
        String tipo = request.getParameter("tipo");
        String fabricante = request.getParameter("fabricante");
        String precio = request.getParameter("precio");
        String nombre = request.getParameter("nombre");
        String codigo = request.getParameter("codigo");
        Paginador paginador = null;
        int pag=1;
        try {
            if ((UtilesString.isVacia(tipo)) && (UtilesString.isVacia(fabricante)) && (UtilesString.isVacia(precio))) {
                //crear sin criterios
                request.setAttribute("tiposArticulos", gbd.getTiposArticulos());
                request.setAttribute("fabricanteArticulos", gbd.getFabricantes());
                request.setAttribute("pagina", 1);
                RequestDispatcher rd = request.getRequestDispatcher("/catalogo.jsp");
                rd.forward(request, response);
            } else {
                //crear con criterios
        /*------------------------------COMPROBACIONES PARA LOS CRITERIOS DE LOS ARTICULOS-------------------------------*/
                CriteriosArticulo criteriosArticulo = new CriteriosArticulo();
                if (!UtilesString.isVacia(tipo)) {
                    criteriosArticulo.setTipo(tipo);
                    request.setAttribute("tipo", tipo);
                    request.setAttribute("fabricanteArticulos", gbd.getFabricantesTipo(tipo));
                } else {
                    request.setAttribute("fabricanteArticulos", gbd.getFabricantes());
                }
                if (!UtilesString.isVacia(fabricante)) {
                    criteriosArticulo.setFabricante(fabricante);
                    request.setAttribute("fabricante", fabricante);
                }
                if (!UtilesString.isVacia(precio)) {
                    criteriosArticulo.setPrecio(precio);
                    request.setAttribute("precio", precio);
                }
                if(!UtilesString.isVacia(nombre)){
                    criteriosArticulo.setNombre(nombre);
                    request.setAttribute("nombre", nombre);
                }
                if(!UtilesString.isVacia(codigo)){
                    criteriosArticulo.setCodigo(codigo);
                    request.setAttribute("codigo", codigo);
                }
        /*------------------------------COMPROBACIONES PARA LOS CRITERIOS DE LOS ARTICULOS-------------------------------*/
                /*------------------------------COMPROBACIONES PARA EL NUMERO DE PAGINA-------------------------------*/
                paginador = gbd.getPaginadorArticulos(criteriosArticulo, tamanioPagina);
                try {
                    pag = Integer.parseInt(pagina);
                } catch (NumberFormatException e) {
                    pag = 1;
                }
                if (pag < 1) {
                    pag = 1;
                }
                if (pag > paginador.getNumPaginas()) {
                    pag = paginador.getNumPaginas();
                }
                request.setAttribute("pagina", pag);
                if(pag != 0){
                    if(gbd.getArticulos(criteriosArticulo, pag, tamanioPagina).size()==1){
                        response.sendRedirect("FichaArticulo?cart="+gbd.getArticulos(criteriosArticulo, pag, tamanioPagina).get(0).getCodigo());
                        return;
                    }else{
                        request.setAttribute("articulos", gbd.getArticulos(criteriosArticulo, pag, tamanioPagina));
                    }
                }else{
                    request.setAttribute("articulos", gbd.getArticulos(criteriosArticulo, 1, tamanioPagina));
                }
                /*------------------------------COMPROBACIONES PARA EL NUMERO DE PAGINA-------------------------------*/
                request.setAttribute("tiposArticulos", gbd.getTiposArticulos());
                request.setAttribute("paginador", paginador);
                request.setAttribute("numRegs", paginador.getNumRegistros());
                request.setAttribute("numPags", paginador.getNumPaginas());
                request.setAttribute("adyacentes", paginador.adyacentes(pag));
                RequestDispatcher rd = request.getRequestDispatcher("/catalogo.jsp");
                rd.forward(request, response);
            }
        } catch (ExcepcionDeAplicacion ex) {
            Logger.getLogger(BuscarArticulos.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }
}