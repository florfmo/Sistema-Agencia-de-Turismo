package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvEliminarVen", urlPatterns = {"/SvEliminarVen"})
public class SvEliminarVen extends HttpServlet {


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
        int num_venta = Integer.parseInt(request.getParameter("num_venta"));
        
        // Me comunico con la lógica
        Controladora control = new Controladora();
        control.eliminarVenta(num_venta); 
        
        // Actualizar lista de ventas
        request.getSession().setAttribute("listaVentas", control.traerVentas());
        
        // response
        response.sendRedirect("listaVentas.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
