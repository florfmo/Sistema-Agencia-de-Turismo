package Servlets;

import Logica.Controladora;
import Logica.PaqueteTuristico;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvConsultaPaq", urlPatterns = {"/SvConsultaPaq"})
public class SvConsultaPaq extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Controladora control = new Controladora ();
        List<PaqueteTuristico> listaPaquetes = control.traerPaquetes();
        HttpSession misession = request.getSession();
        misession.setAttribute("listaPaquetes", listaPaquetes);
        response.sendRedirect("listaPaquetes.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
