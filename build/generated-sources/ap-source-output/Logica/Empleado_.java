package Logica;

import Logica.Usuario;
import Logica.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-20T11:10:11")
@StaticMetamodel(Empleado.class)
public class Empleado_ extends Persona_ {

    public static volatile ListAttribute<Empleado, Venta> lista_ventas;
    public static volatile SingularAttribute<Empleado, Double> sueldo;
    public static volatile SingularAttribute<Empleado, String> cargo;
    public static volatile SingularAttribute<Empleado, Usuario> nombre_usuario;

}