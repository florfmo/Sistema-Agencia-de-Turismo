package Servlets;

import Logica.Cliente;
import Logica.Controladora;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarCl", urlPatterns = {"/SvModificarCl"})
public class SvModificarCl extends HttpServlet {
    
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
        
        Cliente cli = control.buscarCliente(id);
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setDireccion(direccion);
        cli.setDni(dni);
        cli.setFecha_nac(fecha_nac);
        cli.setNacionalidad(nacionalidad);
        cli.setCelular(celular);
        cli.setEmail(email);
        
        control.modificarCliente(cli);
        
        // Actualizo mi lista de clientes
        request.getSession().setAttribute("listaClientes", control.traerClientes());
        response.sendRedirect("listaClientes.jsp");
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // trae ID
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente clie = control.buscarCliente(id);
        
        HttpSession misession = request.getSession();
        misession.setAttribute("cliente", clie);
        response.sendRedirect("modClientes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
