package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvEliminarServ", urlPatterns = {"/SvEliminarServ"})
public class SvEliminarServ extends HttpServlet {

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
        control.eliminarServicio(id); 
        
        // Actualizar lista de servicios
        request.getSession().setAttribute("listaServicios", control.traerServicios());
        
        // response
        response.sendRedirect("listaServicios.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
