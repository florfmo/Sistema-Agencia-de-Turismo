package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PaqueteTuristico implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigo_paquete;
    @Basic
    private double costo_paquete;
    
    // relación NaN con ServicioTuristico, mediante collection
    @ManyToMany
    private List<ServicioTuristico> lista_servicios_incluidos;
    
    // relación 1aN con Venta
    @OneToMany
    private List<Venta> lista_ventas;

    public PaqueteTuristico() {
    }

    public PaqueteTuristico(int codigo_paquete, double costo_paquete, List<ServicioTuristico> lista_servicios_incluidos, List<Venta> lista_ventas) {
        this.codigo_paquete = codigo_paquete;
        this.costo_paquete = costo_paquete;
        this.lista_servicios_incluidos = lista_servicios_incluidos;
        this.lista_ventas = lista_ventas;
    }

    public int getCodigo_paquete() {
        return codigo_paquete;
    }

    public void setCodigo_paquete(int codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(double costo_paquete) {
        this.costo_paquete = costo_paquete;
    }

    public List<ServicioTuristico> getLista_servicios_incluidos() {
        return lista_servicios_incluidos;
    }

    public void setLista_servicios_incluidos(List<ServicioTuristico> lista_servicios_incluidos) {
        this.lista_servicios_incluidos = lista_servicios_incluidos;
    }

    public List<Venta> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(List<Venta> lista_ventas) {
        this.lista_ventas = lista_ventas;
    }


}
