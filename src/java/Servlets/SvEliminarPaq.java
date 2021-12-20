package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarPaq", urlPatterns = {"/SvEliminarPaq"})
public class SvEliminarPaq extends HttpServlet {


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
        int id = Integer.parseInt(request.getParameter("codigo_paquete"));
        
        // Me comunico con la lógica
        Controladora control = new Controladora();
        control.eliminarPaquete(id); 
        
        // Actualizar lista de paquetess
        request.getSession().setAttribute("listaPaquetes", control.traerPaquetes());
        
        // response
        response.sendRedirect("listaPaquetes.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
