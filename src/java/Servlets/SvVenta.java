package Servlets;

import Logica.Cliente;
import Logica.Controladora;
import Logica.Empleado;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvVenta", urlPatterns = {"/SvVenta"})
public class SvVenta extends HttpServlet {

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
        // Traigo atributos del front
        Date fecha_venta = Date.valueOf(request.getParameter("fecha_venta"));
        String medio_pago = request.getParameter("medio_pago");
        int id_cliente = Integer.parseInt(request.getParameter("cliente"));
        Cliente cliente = control.buscarCliente(id_cliente);
        int id_empleado = Integer.parseInt(request.getParameter("empleado"));
        Empleado empleado = control.buscarEmpleado(id_empleado);
        String servicio = request.getParameter("servicio");
        String paquete = request.getParameter("paquete");

        // Traigo la sesión y asigno los atributos para todas las sesiones
        request.getSession().setAttribute("fecha_venta", fecha_venta);
        request.getSession().setAttribute("metodo_pago", medio_pago);
        request.getSession().setAttribute("cliente", cliente);
        request.getSession().setAttribute("empleado", empleado);
        request.getSession().setAttribute("servicio", servicio);
        request.getSession().setAttribute("paquete", paquete);
        
        // Me comunico con la lógica
        control.crearVenta(fecha_venta, medio_pago, cliente, empleado, servicio, paquete);
        
        // response
        response.sendRedirect("SvConsultaVen"); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
