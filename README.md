# TP Integrador - Desarrollo Web Fullstack con Java - Polo TIC Misiones / Silicon Misiones
## Aplicación Web para agencia de turismo

### 1) Escenario
Una agencia  de  turismo necesita  del  desarrollo  de  una  aplicación  web  para  la  venta  de  sus productos por parte de cada uno de sus empleados. Para comprender cada parte de la empresa y cada uno de sus productos y servicios, un analista funcional llevó a cabo el relevamiento de cada una de las acciones y cuestiones a tener en cuenta en el desarrollo del sistema, las mismas se citan a continuación.

#### Servicio turístico
✓ La agencia de turismo ofrece diferentes tipos de servicios a sus potenciales clientes:
- Hotel por noche/s
' Alquiler de auto
- Pasajes de colectivo
' Pasajes de avión
- Pasajes de trenoExcursiones
' Entradas a Eventos
 
✓ Cada servicio puede ser contratado de dos maneras posibles:
- De forma individual
- En un paquete turístico (con otros servicios)

✓ Cada servicio tiene los siguientes datos: 
- codigo_servicio
- nombre
- descripción_breve
- destino_servicio
- fecha_servicio
- costo_servicio

#### Paquete turístico
✓ Un paquete turístico es la combinación de dos o más servicios turísticos. Por ejemplo: un paquete puede contener una noche de hotel + un pasaje de avión.
✓ Los paquetes turísticos tienen los siguientes datos: 
- codigo_paquete
- lista_servicios_incluidos
- costo_paquete

✓ Cabe destacar que el costo del paquete es igual a la suma de los costos de los servicios que lo componen menos un 10% de descuento por contratarlos en forma de paquete. De esta manera, por ejemplo, si la noche de hotel vale $6000 y el pasaje de avión $45.000 entonces la sumatoria de ambos es $51.000, a esto, al descontar el 10% obtenemos el valor total del paquete ($51.000 -10% = $45.900).

#### Clientes
✓ El sistema debe ser capaz de llevar a caboel registro de nuevos clientes.
✓ De cada cliente se necesitan los siguientes datos:
- id_cliente
- nombre
- apellido
- dirección
- dni
- fecha_nac
- nacionalidad
- celular
- email

#### Empleados
✓ El sistema debe ser capaz de llevar a cabo el registro de cada uno de sus empleados.
✓ Cada empleado deberá contar con los mismos datos que un cliente, agregando además también los siguientes datos propios:
- Cargo
- Sueldo

#### Ventas
✓ El sistema debe ser capaz de llevar a cabo ventas de diferentes paquetes o serviciosturísticos.
✓ Cada venta cuenta con los siguientes datos propios:
- num_venta
- fecha_venta
- medio_pago

✓ Al mismo tiempo, cada venta debe estar relacionada con:
- Un cliente
- Un servicio o un paquete turístico (recordar que un paquete contiene variosservicios).
- Un empleado (el que realiza la venta)

### 2) ABML (Altas, bajas, modificaciones y lecturas necesarias)
✓ El  sistema  debe  ser  capaz  derealizar  las  operaciones  ABML  de  los  diferentes  servicios,paquetes, clientes, ventas y empleados. Para ello tener en cuenta que idealmente:
- Todos los clientes, servicios, paquetes y ventas deberán poder tener su ventana o apartado para realizar altas.
- Todos los clientes, servicios, paquetes y ventas deberán poder tener su ventana o apartado para realizar modificaciones.
- Todos los clientes, servicios, paquetes y ventas deberán poder tener suventana o apartado para realizar consultas/lectura de datos.
- Todos los clientes, servicios, paquetes y ventas deberán poder tener su ventana o apartado para realizar bajas o eliminaciones.

### 3) Bonus (OPCIONAL)
El sistema que se planteó con anterioridad no tiene pensado el manejo ni  de  dinero ni de ganancias; sin embargo, el dueño de la agencia propuso una recompensa para aquel desarrollador que realice una propuesta que le permita,  a través  del  sistema, conocer las ganancias diarias y mensuales en base  a  las ventas realizadas. Para ello tener en cuenta que las comisiones según medio de pago son: 
- Efectivo: Sin comisión
- Tarjeta de Débito: 3%
- Tarjeta de Crédito: 9%
- Monedero Virtual: Sin comisión
- Transferencia: 2.45%

## Diagrama de Clases planteado:
![TP Final Java drawio](https://user-images.githubusercontent.com/46388949/196831603-5da05e0b-672b-44cb-927c-577c1fead04d.png)





