/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.ser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paw.bd.GestorBD;
import paw.model.Cliente;
import paw.model.Direccion;
import paw.model.ExcepcionDeAplicacion;
import paw.util.UtilesString;
import paw.util.Validacion;
import paw.util.servlet.UtilesServlet;

/**
 *
 * @author alruiz_o
 */
public class NuevoCliente extends HttpServlet {

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
        response.sendRedirect("nuevoCliente.jsp");
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
        request.setCharacterEncoding("UTF-8");
        Cliente cliente = (Cliente) UtilesServlet.populateBean("paw.model.Cliente", request);
        Direccion direccion = (Direccion) UtilesServlet.populateBean("paw.model.Direccion", request);
        cliente.setDireccion(direccion);
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String repContrasena = request.getParameter("repContrasena");
        String privacidad = request.getParameter("privacidad");
        int si_noPrivacidad;
        try {
            si_noPrivacidad = Integer.parseInt(privacidad);
        } catch (NumberFormatException e) {
            si_noPrivacidad = 0;
        }
        List<String> listaErrores;
        try {
            listaErrores = valida(cliente, nombreUsuario, contrasena, repContrasena, si_noPrivacidad, request, gbd);
            if (listaErrores.isEmpty()) {
                if (gbd.insertaCliente(cliente, nombreUsuario, contrasena) != null) {
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("cliente", cliente);
                    response.sendRedirect("clientes/AreaCliente");
                    return;
                }
            }
            request.setAttribute("nombreUsuario", nombreUsuario);
            request.setAttribute("contrasena", contrasena);
            request.setAttribute("repContrasena", repContrasena);
            request.setAttribute("privacidad", si_noPrivacidad);
            request.setAttribute("alerta", listaErrores);
            request.setAttribute("cliente", cliente);
            RequestDispatcher rd = request.getRequestDispatcher("/nuevoCliente.jsp");
            rd.forward(request, response);
        } catch (ExcepcionDeAplicacion ex) {
            Logger.getLogger(NuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Realiza las validaciones para los campos del formulario de registro de
     * nuevo cliente
     *
     * @param cli objeto paw.model.Cliente con los datos leídos del formulario
     * @param usr valor del campo "nombre de usuario" del formulario
     * @param pwd valor del campo "contraseña" del formulario
     * @param rpwd valor del campo "Repita contraseña" del formulario
     * @param privacidadOK debe tener valor 1 si la casilla de "Política de
     * privacidad" está marcada
     * @param request objeto HttpServletRequest
     * @param gbd objeto GestorBD para ser usado en las comprobaciones que
     * requieran de conexión a al BD
     * @return Una lista de String con mensajes de error correspondientes a las
     * reglas de validación que no se cumplen
     * @throws ExcepcionDeAplicacion
     */
    private List<String> valida(Cliente cli,
            String usr,
            String pwd,
            String rpwd,
            int privacidadOK,
            HttpServletRequest request,
            GestorBD gbd) throws ExcepcionDeAplicacion, IOException {
        List<String> errores = new ArrayList<String>();

        if (UtilesString.isVacia(cli.getNombre())
                || UtilesString.isVacia(cli.getCif())
                || UtilesString.isVacia(cli.getDireccion().getCalle())
                || UtilesString.isVacia(cli.getDireccion().getCiudad())
                || UtilesString.isVacia(cli.getDireccion().getProvincia())
                || UtilesString.isVacia(cli.getDireccion().getCp())
                || UtilesString.isVacia(cli.getEmail())
                || UtilesString.isVacia(usr)
                || UtilesString.isVacia(pwd)
                || UtilesString.isVacia(rpwd)) {
            errores.add("Debes proporcionar valor para todos los campos requeridos");
        }

        if (cli.getNombre() != null && cli.getNombre().length() > 50) {
            errores.add("La longitud máxima del nombre son 50 caracteres");
        }

        if (cli.getCif() != null && cli.getCif().length() > 12) {
            errores.add("La longitud máxima del CIF son 12 caracteres");
        }

        if (cli.getTfno() != null && cli.getTfno().length() > 11) {
            errores.add("La longitud máxima del teléfono son 11 caracteres");
        }

        if (cli.getEmail() != null && cli.getEmail().length() > 100) {
            errores.add("La longitud máxima del email son 100 caracteres");
        }

        if (usr != null && usr.length() > 50) {
            errores.add("La longitud máxima del userName son 50 caracteres");
        }

        if (cli.getDireccion().getCalle() != null && cli.getDireccion().getCalle().length() > 50) {
            errores.add("La longitud máxima de la calle son 50 caracteres");
        }

        if (cli.getDireccion().getCiudad() != null && cli.getDireccion().getCiudad().length() > 20) {
            errores.add("La longitud máxima de la ciudad son 20 caracteres");
        }

        if (privacidadOK != 1) {
            errores.add("Debes leer la cláusula de privacidad y marcar la casilla correspondiente");
        }

        if (!UtilesString.isVacia(pwd) && !UtilesString.isVacia(rpwd) && !pwd.equals(rpwd)) {
            errores.add("Las constraseñas son diferentes");
        }

        if (!UtilesString.isVacia(usr) && !usr.trim().equals(usr)) {
            errores.add("El nombre de usuario tiene espacios en blanco por delante o detrás");
        }

        if (!UtilesString.isVacia(usr) && gbd.getClienteByUserName(usr) != null) {
            errores.add("Ya existe un usuario en la BD con ese nombre de usuario");
        }

        if (!UtilesString.isVacia(cli.getCif()) && gbd.getClienteByCIF(cli.getCif()) != null) {
            errores.add("Ya existe un usuario en la BD con ese CIF");
            cli.setCif(null);
        }

        if (!UtilesString.isVacia(cli.getEmail()) && !Validacion.isEmailValido(cli.getEmail())) {
            errores.add("El email no parece una dirección de correo válida");
            cli.setEmail(null);
        }

        if (!UtilesString.isVacia(cli.getDireccion().getCp()) && !Validacion.isCPValido(cli.getDireccion().getCp())) {
            errores.add("El CP no parece un código postal válido");
            cli.getDireccion().setCp(null);
        }

        return errores;
    }
}