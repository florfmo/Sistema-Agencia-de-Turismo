package Servlets;

import Logica.Controladora;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvServicio", urlPatterns = {"/SvServicio"})
public class SvServicio extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traigo atributos del front
        String nombre = request.getParameter("nombre");
        String descripcion_breve = request.getParameter("descripcion_breve");
        String destino_servicio = request.getParameter("destino_servicio");
        Date fecha_servicio = Date.valueOf(request.getParameter("fecha_servicio"));
        double costo_servicio = Double.valueOf(request.getParameter("costo_servicio"));
        String tipo_servicio = request.getParameter("tipo_servicio");
        
        // Traigo la sesión y asigno los atributos para todas las sesiones
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("descripcion_breve", descripcion_breve);
        request.getSession().setAttribute("destino_servicio", destino_servicio);
        request.getSession().setAttribute("fecha_servicio", fecha_servicio);
        request.getSession().setAttribute("costo_servicio", costo_servicio);
        request.getSession().setAttribute("tipo_servicio", tipo_servicio);
        
        // Me comunico con la lógica
        Controladora control = new Controladora ();
        control.crearServicio(nombre, descripcion_breve, destino_servicio, fecha_servicio, costo_servicio, tipo_servicio);
        
        // Armo mi response (respuesta)
        response.sendRedirect("SvConsultaSer"); 
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
