package Servlets;

import Logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEmpleado", urlPatterns = {"/SvEmpleado"})
public class SvEmpleado extends HttpServlet {
    
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
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        Date fecha_nac = Date.valueOf(request.getParameter("fecha_nac"));
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");
        Double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        String usuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("contrasenia");
        
        // Traigo la sesión y asigno los atributos para todas las sesiones
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("apellido", apellido);
        request.getSession().setAttribute("direccion", direccion);
        request.getSession().setAttribute("dni", dni);
        request.getSession().setAttribute("fecha_nac", fecha_nac);
        request.getSession().setAttribute("nacionalidad", nacionalidad);
        request.getSession().setAttribute("celular", celular);
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("cargo", cargo);
        request.getSession().setAttribute("sueldo", sueldo);
        
        // Me comunico con la lógica
        Controladora control = new Controladora ();
        control.crearEmpleado (nombre, apellido, direccion, dni, fecha_nac, nacionalidad, celular, email, cargo, sueldo, usuario, contrasenia);
        
        // Armo mi response (respuesta)
        response.sendRedirect("SvConsultaEm");   
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
