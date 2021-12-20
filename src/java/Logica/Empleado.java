package Logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empleado extends Persona {
    @Basic
    private String cargo;
    private double sueldo;
    
    // relación 1 a 1 con Usuario, mediante objeto
    @OneToOne
    private  Usuario nombre_usuario; 
    
    //relación 1 a N con Venta, mediante collection (ej. Lista)
    @OneToMany
    private  List<Venta> lista_ventas;

    public Empleado() {
    }

    public Empleado(String cargo, double sueldo, Usuario nombre_usuario, List<Venta> lista_ventas, int id, String nombre, String apellido, String direccion, String dni, String nacionalidad, String celular, String email, Date fecha_nac) {
        super(id, nombre, apellido, direccion, dni, nacionalidad, celular, email, fecha_nac);
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.nombre_usuario = nombre_usuario;
        this.lista_ventas = lista_ventas;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Usuario getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(Usuario nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public List<Venta> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(List<Venta> lista_ventas) {
        this.lista_ventas = lista_ventas;
    }

    @Override
    public String toString() {
        return "Empleado{" + "cargo=" + cargo + ", sueldo=" + sueldo + ", nombre_usuario=" + nombre_usuario + ", lista_ventas=" + lista_ventas + '}';
    }

    
 
}
