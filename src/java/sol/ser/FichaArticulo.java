package sol.ser;

import java.io.IOException;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import paw.bd.GestorBD;
import paw.model.*;
import paw.util.UtilesString;

public class FichaArticulo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cart = request.getParameter("cart");
        try {
            if (UtilesString.isVacia(cart)) {
                response.sendRedirect("index.html");
            } else {
                Articulo art = new GestorBD().getArticulo(cart);
                if (art == null) {
                    request.setAttribute("cart", cart);
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "El artículo solicitado no existe");
                } else {
                    request.setAttribute("art", art);
                    String urlAnterior = request.getHeader("Referer");
                    if(urlAnterior != null){
                        request.setAttribute("anterior", urlAnterior);
                    } else{
                        request.setAttribute("anterior", "BuscarArticulos");
                    }
                    
                    RequestDispatcher rd = request.getRequestDispatcher("fichaArticulo.jsp");
                    rd.forward(request, response);
                }
            }
        } catch (ExcepcionDeAplicacion ex) {
            Logger.getLogger(BuscarArticulos.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException(ex);
        }
    }

}