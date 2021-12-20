package Servlets;

import Logica.Controladora;
import Logica.ServicioTuristico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvPaquete", urlPatterns = {"/SvPaquete"})
public class SvPaquete extends HttpServlet {

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
            Controladora control = new Controladora ();
            
            // Guardo los servicios seleccionados en una lista
            String[] lista_servicios = request.getParameterValues("codigo_servicio");
            List<ServicioTuristico> lista_serv = new ArrayList<>();
            
            // Cálculo del costo
            double costo_paquete = 0;
            // Itero en cada código
            for (String codigo : lista_servicios) {
            // Traigo el ID y busco el servicio
                int codigo_servicio = Integer.parseInt(codigo);
                ServicioTuristico serv = control.buscarServicio(codigo_servicio);
                // Agrego el servicio a la lista
                lista_serv.add(serv);
                // Obtengo el costo, calculo e descuento y lo acumulo al total
                costo_paquete += (serv.getCosto_servicio() - (serv.getCosto_servicio()*0.1));
            }
                
            // Traigo la sesión y asigno los atributos para todas las sesiones
            request.getSession().setAttribute("lista_servicios", lista_serv);
            request.getSession().setAttribute("costo_paquete", costo_paquete);
            
            // Me comunico con la lógica
            control.crearPaquete(lista_serv, costo_paquete);
            response.sendRedirect("SvConsultaPaq");         
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
