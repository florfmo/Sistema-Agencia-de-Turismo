package Servlets;

import Logica.Cliente;
import Logica.Controladora;
import Logica.Empleado;
import Logica.PaqueteTuristico;
import Logica.ServicioTuristico;
import Logica.Venta;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarVen", urlPatterns = {"/SvModificarVen"})
public class SvModificarVen extends HttpServlet {
    
    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // trae ID y busco el objeto
        int num_venta = Integer.parseInt(request.getParameter("num_venta"));
        Venta venta = control.buscarVenta(num_venta);
        
        // Traigo los valores nuevos
        Date fecha_venta = Date.valueOf(request.getParameter("fecha_venta"));
        String medio_pago = request.getParameter("medio_pago");
        int id_cliente = Integer.parseInt(request.getParameter("cliente"));
        Cliente cliente = control.buscarCliente(id_cliente);
        int id_empleado = Integer.parseInt(request.getParameter("empleado"));
        Empleado empleado = control.buscarEmpleado(id_empleado);
        String servicio = request.getParameter("servicio");
        String paquete = request.getParameter("paquete");
        
        // Asigno los valores nuevos
        venta.setFecha_venta(fecha_venta);
        venta.setMedio_pago(medio_pago);
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        
        if (servicio != null && !servicio.isEmpty()){
            int codigo_servicio = Integer.parseInt(servicio);
            ServicioTuristico serv = control.buscarServicio(codigo_servicio);
            venta.setServicio(serv);
        } else {
            venta.setServicio(null);
        }
        
        if (paquete != null && !paquete.isEmpty()){
            int codigo_paquete = Integer.parseInt(paquete);
            PaqueteTuristico paq = control.buscarPaquete(codigo_paquete);
            venta.setPaquete(paq);
        } else {
            venta.setPaquete(null);
        }
        
        // Envío a controladora
        control.modificarVenta(venta);
        
        // response
        request.getSession().setAttribute("listaVentas", control.traerVentas());
        response.sendRedirect("listaVentas.jsp");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int num_venta = Integer.parseInt(request.getParameter("num_venta"));
        Venta venta = control.buscarVenta(num_venta);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("venta", venta);
        
        if (venta.getServicio() != null){
            response.sendRedirect("modVentaServ.jsp");
        } else{
            response.sendRedirect("modVentaPaq.jsp");
        }
 
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
