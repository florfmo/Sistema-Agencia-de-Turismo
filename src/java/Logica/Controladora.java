package Logica;

import Persistencia.ControladoraPersistencia;
import static java.awt.SystemColor.control;
import java.util.Date;
import java.util.List;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    // Métodos USUARIOS
    // Verificar usuario
    public boolean verificarUsuario(String usuario, String contrasenia){
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        if (listaUsuarios != null){
            for (Usuario usu : listaUsuarios){
                if (usu.getNombre_usuario().equals(usuario) && usu.getContrasenia().equals(contrasenia)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // Buscar usuario
    public Usuario buscarUsuario(int id) {
        return controlPersis.buscarUsuario(id);
    }
    
    // Traer usuarios
    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }
    
    // Modificar usuario
    public void modificarUsuario(Usuario usu) {
        controlPersis.modificarUsuario(usu);
    }
    
    //Eliminar usuario
    public void eliminarUsuario(int id_user) {
        controlPersis.eliminarUsuario(id_user);
    }

    
    // Métodos EMPLEADOS
    // Crear empleado
    public void crearEmpleado(String nombre, String apellido, String direccion, String dni, Date fecha_nac, String nacionalidad, String celular, String email, String cargo, Double sueldo, String usuario, String contrasenia) {      
        Empleado empleado = new Empleado();
        Usuario user = new Usuario();
        
        // Asignación de valores a empleado
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDireccion(direccion);
        empleado.setDni(dni);
        empleado.setFecha_nac(fecha_nac);
        empleado.setNacionalidad(nacionalidad);
        empleado.setCelular(celular);
        empleado.setEmail(email);
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        
        // Asignación de valores a usuario
        user.setNombre_usuario(usuario);
        user.setContrasenia(contrasenia);
        
        // Asignación de usuario a empleado
        empleado.setNombre_usuario(user);
        
        controlPersis.crearEmpleado(empleado, user);
        
    }
    
    // Traer empleados
    public List<Empleado> traerEmpleados(){
        return controlPersis.traerEmpleados();
    }
    
    // Eliminar empleado
    public void eliminarEmpleado(int id) {
        controlPersis.eliminarEmpleado(id);
    }
    
    // Buscar empleado por ID
    public Empleado buscarEmpleado(int id) {
        return controlPersis.buscarEmpleado(id);
    }
    
    // Modificar empleado
    public void modificarEmpleado(Empleado emple) {
        controlPersis.modificarEmpleado(emple);
    }
    
    // Métodos CLIENTES
    // Crear cliente
    public void crearCliente(String nombre, String apellido, String direccion, String dni, Date fecha_nac, String nacionalidad, String celular, String email) {
        Cliente cliente = new Cliente();
        // Asignación de valores a cliente
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setFecha_nac(fecha_nac);
        cliente.setNacionalidad(nacionalidad);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        
        controlPersis.crearCliente(cliente);
    }
    
    // Traer clientes
    public List<Cliente> traerClientes(){
        return controlPersis.traerClientes();
    }

    // Eliminar cliente
    public void eliminarCliente(int id) {
        controlPersis.eliminarCliente(id);
    }
    
    // Buscar cliente por ID
    public Cliente buscarCliente(int id) {
        return controlPersis.buscarCliente(id);
    }
    
    // Modificar cliente
    public void modificarCliente(Cliente cli) {
        controlPersis.modificarCliente(cli);
    }
    
    // Métodos SERVICIOS
    // Crear servicio
    public void crearServicio(String nombre, String descripcion_breve, String destino_servicio, java.sql.Date fecha_servicio, double costo_servicio, String tipo_servicio) {
        ServicioTuristico servicio = new ServicioTuristico();
        // Asignación de valores a servicio
        servicio.setNombre(nombre);
        servicio.setDescripcion_breve(descripcion_breve);
        servicio.setDestino_servicio(destino_servicio);
        servicio.setFecha_servicio(fecha_servicio);
        servicio.setCosto_servicio(costo_servicio);
        servicio.setTipo_servicio(tipo_servicio);
        
        controlPersis.crearServicio(servicio);
    }
    
    // Traer servicios
    public List<ServicioTuristico> traerServicios(){
        return controlPersis.traerServicios();
    }
    
    // Eliminar servicio
    public void eliminarServicio(int id) {
        controlPersis.eliminarServicio(id);
    }
    
    // Buscar servicio por ID
    public ServicioTuristico buscarServicio(int id) {
        return controlPersis.buscarServicio(id);
    }
    
    // Modificar servicio
    public void modificarServicio(ServicioTuristico serv) {
        controlPersis.modificarServicio(serv);
    }
    
    // Métodos PAQUETES
    // Crear paquete
    public void crearPaquete(List<ServicioTuristico> servicios, double costo_paquete) {
        PaqueteTuristico paquete = new PaqueteTuristico();
        
        // Asignación de valores al paquete
        paquete.setLista_servicios_incluidos(servicios);
        paquete.setCosto_paquete(costo_paquete);
        
        controlPersis.crearPaquete(paquete);
    }
        
    // Traer paquete
    public List<PaqueteTuristico> traerPaquetes(){
        return controlPersis.traerPaquetes();
    }
    
    // Eliminar paquete
    public void eliminarPaquete(int id) {
        controlPersis.eliminarPaquete(id);
    }
    
    // Buscar paquete por ID
    public PaqueteTuristico buscarPaquete(int id) {
        return controlPersis.buscarPaquete(id);
    }
    
    // Modificar paquete
    public void modificarPaquete(PaqueteTuristico paq) {
        controlPersis.modificarPaquete(paq);
    }
    
    // Métodos VENTAS
    // Crear venta
    public void crearVenta(java.sql.Date fecha_venta, String medio_pago, Cliente cliente, Empleado empleado, String servicio, String paquete) {
        Venta venta = new Venta();
        
        // Asignación de valores a la venta
        venta.setFecha_venta(fecha_venta);
        venta.setMedio_pago(medio_pago);
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        
        if (servicio != null && !servicio.isEmpty()){
            int codigo_servicio = Integer.parseInt(servicio);
            ServicioTuristico serv = buscarServicio(codigo_servicio);
            venta.setServicio(serv);
        } else {
            venta.setServicio(null);
        }
        
        if (paquete != null && !paquete.isEmpty()){
            int codigo_paquete = Integer.parseInt(paquete);
            PaqueteTuristico paq = buscarPaquete(codigo_paquete);
            venta.setPaquete(paq);
        } else {
            venta.setPaquete(null);
        }
        
        controlPersis.crearVenta(venta);
    }
    
    // Traer venta
    public List<Venta> traerVentas(){
        return controlPersis.traerVentas();
    }
    
    // Eliminar venta
    public void eliminarVenta(int id) {
        controlPersis.eliminarVenta(id);
    }
    
    // Buscar venta por ID
    public Venta buscarVenta(int id) {
        return controlPersis.buscarVenta(id);
    }
    
    // Modificar venta
    public void modificarVenta(Venta ven) {
        controlPersis.modificarVenta(ven);
    }
    
}
