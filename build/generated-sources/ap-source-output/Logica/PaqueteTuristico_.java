package Logica;

import Logica.ServicioTuristico;
import Logica.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T11:10:11")
@StaticMetamodel(PaqueteTuristico.class)
public class PaqueteTuristico_ { 

    public static volatile ListAttribute<PaqueteTuristico, Venta> lista_ventas;
    public static volatile SingularAttribute<PaqueteTuristico, Double> costo_paquete;
    public static volatile SingularAttribute<PaqueteTuristico, Integer> codigo_paquete;
    public static volatile ListAttribute<PaqueteTuristico, ServicioTuristico> lista_servicios_incluidos;

}