package Logica;

import Logica.Cliente;
import Logica.Empleado;
import Logica.PaqueteTuristico;
import Logica.ServicioTuristico;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T11:10:11")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> num_venta;
    public static volatile SingularAttribute<Venta, Cliente> cliente;
    public static volatile SingularAttribute<Venta, ServicioTuristico> servicio;
    public static volatile SingularAttribute<Venta, Empleado> empleado;
    public static volatile SingularAttribute<Venta, String> medio_pago;
    public static volatile SingularAttribute<Venta, Date> fecha_venta;
    public static volatile SingularAttribute<Venta, PaqueteTuristico> paquete;

}