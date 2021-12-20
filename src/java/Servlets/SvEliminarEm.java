package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarEm", urlPatterns = {"/SvEliminarEm"})
public class SvEliminarEm extends HttpServlet {
    
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
        // trae ID
        int id = Integer.parseInt(request.getParameter("id"));
                
        // Me comunico con la lógica
        Controladora control = new Controladora();
        Empleado emple = control.buscarEmpleado(id);
        Usuario user = emple.getNombre_usuario();
        int id_user = user.getId_usuario();
        control.eliminarEmpleado(id);
        control.eliminarUsuario(id_user);
        
        // Actualizar lista de empleados
        request.getSession().setAttribute("listaEmpleados", control.traerEmpleados());
        
        // response
        response.sendRedirect("listaEmpleados.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
