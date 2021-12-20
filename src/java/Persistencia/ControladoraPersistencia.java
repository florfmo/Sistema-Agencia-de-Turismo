package Persistencia;

import Logica.Cliente;
import Logica.Empleado;
import Logica.PaqueteTuristico;
import Logica.ServicioTuristico;
import Logica.Usuario;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    ClienteJpaController clieJPA = new ClienteJpaController();
    EmpleadoJpaController empleJPA = new EmpleadoJpaController();
    UsuarioJpaController usuJPA = new UsuarioJpaController();
    ServicioTuristicoJpaController servJPA = new ServicioTuristicoJpaController();
    PaqueteTuristicoJpaController paqJPA = new PaqueteTuristicoJpaController();
    VentaJpaController venJPA = new VentaJpaController();
    
    // Métodos USUARIOS
    // Traer usuarios
    public List<Usuario> traerUsuarios() {
        return usuJPA.findUsuarioEntities();
    }
    
    // Buscar usuario por ID    
    public Usuario buscarUsuario(int id) {
        return usuJPA.findUsuario(id);
    }
    
    // Modificar usuario
    public void modificarUsuario(Usuario usu) {
        try {
            usuJPA.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Eliminar usuario  
    public void eliminarUsuario(int id_user) {
        try {
            usuJPA.destroy(id_user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Métodos EMPLEADOS
    // Crear empleado
    public void crearEmpleado(Empleado empleado, Usuario user) {
        usuJPA.create(user);
        empleJPA.create(empleado);
    }
    
    // Traer empleados
    public List<Empleado> traerEmpleados(){
        return empleJPA.findEmpleadoEntities();
    }
    
    // Eliminar empleado
    public void eliminarEmpleado(int id) {
        try {
            empleJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Buscar empleado por ID
    public Empleado buscarEmpleado(int id) {
        return empleJPA.findEmpleado(id);
    }
    
    // Modificar empleado
    public void modificarEmpleado(Empleado emple) {
        try {
            empleJPA.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Métodos CLIENTES
    // Crear cliente
    public void crearCliente(Cliente cliente) {
        clieJPA.create(cliente);
    }
    
    // Traer clientes
    public List<Cliente> traerClientes(){
        return clieJPA.findClienteEntities();
    }
    
    // Eliminar cliente
    public void eliminarCliente(int id) {
        try {
            clieJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Buscar cliente por ID
    public Cliente buscarCliente(int id) {
        return clieJPA.findCliente(id);
    }
    
    // Modificar cliente
    public void modificarCliente(Cliente cli) {
        try {
            clieJPA.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Métodos SERVICIOS
    // Crear servicio
    public void crearServicio(ServicioTuristico servicio) {
        servJPA.create(servicio);
    }
    
    // Traer servicios
    public List<ServicioTuristico> traerServicios() {
        return servJPA.findServicioTuristicoEntities();
    }
    
    // Eliminar servicio
    public void eliminarServicio(int id) {
        try {
            servJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Buscar servicio por ID
    public ServicioTuristico buscarServicio(int id) {
        return servJPA.findServicioTuristico(id);
    }
    
    // Modificar servicio
    public void modificarServicio(ServicioTuristico serv) {
        try {
            servJPA.edit(serv);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Métodos PAQUETES
    // Crear paquete
    public void crearPaquete(PaqueteTuristico paquete) {
        paqJPA.create(paquete);
    }
    
    // Traer paquetes
    public List<PaqueteTuristico> traerPaquetes() {
        return paqJPA.findPaqueteTuristicoEntities();
    }
    
    // Eliminar paquete
    public void eliminarPaquete(int id) {
        try {
            paqJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Buscar paquete por ID
    public PaqueteTuristico buscarPaquete(int id) {
        return paqJPA.findPaqueteTuristico(id);
    }
    
    // Modificar paquete
        public void modificarPaquete(PaqueteTuristico paq) {
        try {
            paqJPA.edit(paq);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Métodos VENTAS
    // Crear venta
    public void crearVenta(Venta venta) {
        venJPA.create(venta);
    }
        
    // Traer venta
    public List<Venta> traerVentas(){
        return venJPA.findVentaEntities();
    }
    
    // Eliminar venta
    public void eliminarVenta(int id) {
        try {
            venJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Buscar venta por ID
    public Venta buscarVenta(int id) {
        return venJPA.findVenta(id);
    }
    
    // Modificar venta    
        public void modificarVenta(Venta ven) {
        try {
            venJPA.edit(ven);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
