package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int num_venta;
    @Basic
    private String medio_pago;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_venta;
    
    
    // relación Na1 con Cliente
    @ManyToOne
    private Cliente cliente;
    
    // relación Na1 con ServicioTuristico
    @ManyToOne
    private ServicioTuristico servicio;
    
    // relación Na1 con PaqueteTuristico
    @ManyToOne
    private PaqueteTuristico paquete;
    
    // relación Na1 con Empleado
    @ManyToOne
    private Empleado empleado;

    public Venta() {
    }

    public Venta(int num_venta, Date fecha_venta, String medio_pago, Cliente cliente, ServicioTuristico servicio, PaqueteTuristico paquete, Empleado empleado) {
        this.num_venta = num_venta;
        this.fecha_venta = fecha_venta;
        this.medio_pago = medio_pago;
        this.cliente = cliente;
        this.servicio = servicio;
        this.paquete = paquete;
        this.empleado = empleado;
    }

    public int getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(int num_venta) {
        this.num_venta = num_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ServicioTuristico getServicio() {
        return servicio;
    }

    public void setServicio(ServicioTuristico servicio) {
        this.servicio = servicio;
    }

    public PaqueteTuristico getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteTuristico paquete) {
        this.paquete = paquete;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
      
}
