package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class ServicioTuristico implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigo_servicio;
    @Basic
    private String nombre;
    private String descripcion_breve;
    private String destino_servicio;
    private double costo_servicio;
    private String tipo_servicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_servicio;
    
    // relacion NaN con PaqueteTuristico
    @ManyToMany
    private List<PaqueteTuristico> lista_paquetes;
    
    // relacion 1aN con Venta
    @OneToMany
    private List<Venta> lista_ventas;

    public ServicioTuristico() {
    }

    public ServicioTuristico(int codigo_servicio, String nombre, String descripcion_breve, String destino_servicio, double costo_servicio, String tipo_servicio, Date fecha_servicio, List<PaqueteTuristico> lista_paquetes, List<Venta> lista_ventas) {
        this.codigo_servicio = codigo_servicio;
        this.nombre = nombre;
        this.descripcion_breve = descripcion_breve;
        this.destino_servicio = destino_servicio;
        this.costo_servicio = costo_servicio;
        this.fecha_servicio = fecha_servicio;
        this.lista_paquetes = lista_paquetes;
        this.lista_ventas = lista_ventas;
    }

    public int getCodigo_servicio() {
        return codigo_servicio;
    }

    public void setCodigo_servicio(int codigo_servicio) {
        this.codigo_servicio = codigo_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion_breve() {
        return descripcion_breve;
    }

    public void setDescripcion_breve(String descripcion_breve) {
        this.descripcion_breve = descripcion_breve;
    }

    public String getDestino_servicio() {
        return destino_servicio;
    }

    public void setDestino_servicio(String destino_servicio) {
        this.destino_servicio = destino_servicio;
    }

    public double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(double costo_servicio) {
        this.costo_servicio = costo_servicio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public Date getFecha_servicio() {
        return fecha_servicio;
    }

    public void setFecha_servicio(Date fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    public List<PaqueteTuristico> getLista_paquetes() {
        return lista_paquetes;
    }

    public void setLista_paquetes(List<PaqueteTuristico> lista_paquetes) {
        this.lista_paquetes = lista_paquetes;
    }

    public List<Venta> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(List<Venta> lista_ventas) {
        this.lista_ventas = lista_ventas;
    }
   
    
}
