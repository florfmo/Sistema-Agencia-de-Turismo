package Servlets;

import Logica.Controladora;
import Logica.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarUsu", urlPatterns = {"/SvModificarUsu"})
public class SvModificarUsu extends HttpServlet {
    
    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traigo ID y parámetros
        int id = Integer.parseInt(request.getParameter("id"));
        String usuario = request.getParameter("nombre_usuario");
        String contrasenia = request.getParameter("contrasenia");
        
        Usuario usu = control.buscarUsuario(id);
        
        // Asigno datos nuevos
        usu.setNombre_usuario(usuario);
        usu.setContrasenia(contrasenia);
        
        // Llamo a controladora
        control.modificarUsuario(usu);
        
        // Response
        response.sendRedirect("SvLogout");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traigo ID mediante nombre de usuario
        String nombre_usuario = request.getParameter("nombre_usuario");
        List<Usuario> listaUsuarios = control.traerUsuarios();
        int id = 0;
        for (Usuario user : listaUsuarios ){
            if (user.getNombre_usuario().equals(nombre_usuario)){
                id = user.getId_usuario();
            }
        }   
        Usuario usuario = control.buscarUsuario(id);
        
        response.sendRedirect("modUsuario.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
