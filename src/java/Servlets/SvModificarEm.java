package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarEm", urlPatterns = {"/SvModificarEm"})
public class SvModificarEm extends HttpServlet {
    
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
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        Date fecha_nac = Date.valueOf(request.getParameter("fecha_nac"));
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");
        Double sueldo = Double.parseDouble(request.getParameter("sueldo"));
        
        Empleado emple = control.buscarEmpleado(id);
        emple.setNombre(nombre);
        emple.setApellido(apellido);
        emple.setDireccion(direccion);
        emple.setDni(dni);
        emple.setFecha_nac(fecha_nac);
        emple.setNacionalidad(nacionalidad);
        emple.setCelular(celular);
        emple.setEmail(email);
        emple.setCargo(cargo);
        emple.setSueldo(sueldo);
        
        control.modificarEmpleado(emple);
        
        // Actualizo mi lista de empleados
        request.getSession().setAttribute("listaEmpleados", control.traerEmpleados());
        response.sendRedirect("listaEmpleados.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // trae ID
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado empl = control.buscarEmpleado(id);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("empleado", empl);
        response.sendRedirect("modEmpleados.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
