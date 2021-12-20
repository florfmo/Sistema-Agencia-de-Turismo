package Logica;

import Logica.PaqueteTuristico;
import Logica.Venta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T11:10:11")
@StaticMetamodel(ServicioTuristico.class)
public class ServicioTuristico_ { 

    public static volatile SingularAttribute<ServicioTuristico, Date> fecha_servicio;
    public static volatile ListAttribute<ServicioTuristico, Venta> lista_ventas;
    public static volatile ListAttribute<ServicioTuristico, PaqueteTuristico> lista_paquetes;
    public static volatile SingularAttribute<ServicioTuristico, Double> costo_servicio;
    public static volatile SingularAttribute<ServicioTuristico, Integer> codigo_servicio;
    public static volatile SingularAttribute<ServicioTuristico, String> nombre;
    public static volatile SingularAttribute<ServicioTuristico, String> descripcion_breve;
    public static volatile SingularAttribute<ServicioTuristico, String> destino_servicio;
    public static volatile SingularAttribute<ServicioTuristico, String> tipo_servicio;

}