package Logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Persona { 

    //relación 1 a N con Venta, mediante collection
    @OneToMany
    private List<Venta> lista_ventas;

    public Cliente() {
    }

    public Cliente(List<Venta> lista_ventas, int id, String nombre, String apellido, String direccion, String dni, String nacionalidad, String celular, String email, Date fecha_nac) {
        super(id, nombre, apellido, direccion, dni, nacionalidad, celular, email, fecha_nac);
        this.lista_ventas = lista_ventas;
    }

    public List<Venta> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(List<Venta> lista_ventas) {
        this.lista_ventas = lista_ventas;
    }

    @Override
    public String toString() {
        return "Cliente{" + "lista_ventas=" + lista_ventas + '}';
    }

}
