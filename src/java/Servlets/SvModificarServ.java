package Servlets;

import Logica.Controladora;
import Logica.ServicioTuristico;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarServ", urlPatterns = {"/SvModificarServ"})
public class SvModificarServ extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // trae ID
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion_breve = request.getParameter("descripcion_breve");
        String destino_servicio = request.getParameter("destino_servicio");
        Date fecha_servicio = Date.valueOf(request.getParameter("fecha_servicio"));
        double costo_servicio = Double.valueOf(request.getParameter("costo_servicio"));
        String tipo_servicio = request.getParameter("tipo_servicio");
        
        ServicioTuristico servi = control.buscarServicio(id);
        servi.setNombre(nombre);
        servi.setDescripcion_breve(descripcion_breve);
        servi.setDestino_servicio(destino_servicio);
        servi.setFecha_servicio(fecha_servicio);
        servi.setCosto_servicio(costo_servicio);
        servi.setTipo_servicio(tipo_servicio);
        
        control.modificarServicio(servi);
        
        // Actualizo mi lista de servicios
        request.getSession().setAttribute("listaServicios", control.traerServicios());
        response.sendRedirect("listaServicios.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // trae ID
        int id = Integer.parseInt(request.getParameter("id"));
        ServicioTuristico serv = control.buscarServicio(id);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("servicio", serv);
        response.sendRedirect("modServicios.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
