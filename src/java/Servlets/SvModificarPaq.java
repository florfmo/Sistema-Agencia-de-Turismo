package Servlets;

import Logica.Controladora;
import Logica.PaqueteTuristico;
import Logica.ServicioTuristico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarPaq", urlPatterns = {"/SvModificarPaq"})
public class SvModificarPaq extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Controladora control = new Controladora ();
            int codigo_paquete = Integer.parseInt(request.getParameter("codigo_paquete"));
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
                // Obtengo el costo, calculo el descuento y lo acumulo al total
                costo_paquete += (serv.getCosto_servicio() - (serv.getCosto_servicio()*0.1)); // ESTA LINEA ESTA TIRANDO NULL EXCEPCION
            }
                
            PaqueteTuristico paq = control.buscarPaquete(codigo_paquete);
            paq.setLista_servicios_incluidos(lista_serv);
            paq.setCosto_paquete(costo_paquete);
            
            // Me comunico con la lógica
            control.modificarPaquete(paq);
                
            // Actualizo mi lista de paquetes
            request.getSession().setAttribute("listaPaquetes", control.traerPaquetes());
            response.sendRedirect("listaPaquetes.jsp");    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigo_paquete = Integer.parseInt(request.getParameter("codigo_paquete"));
        Controladora control = new Controladora();
        PaqueteTuristico paq = control.buscarPaquete(codigo_paquete);

        HttpSession misession = request.getSession();
        misession.setAttribute("paquete", paq);
        response.sendRedirect("modPaquetes.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
