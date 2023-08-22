# TP Integrador (Final project) - Desarrollo Web Fullstack con Java - Polo TIC Misiones / Silicon Misiones
## Aplicación Web para agencia de turismo (Travel Agency web app)

### 1) Escenario
Una agencia  de  turismo necesita  del  desarrollo  de  una  aplicación  web  para  la  venta  de  sus productos por parte de cada uno de sus empleados. Para comprender cada parte de la empresa y cada uno de sus productos y servicios, un analista funcional llevó a cabo el relevamiento de cada una de las acciones y cuestiones a tener en cuenta en el desarrollo del sistema, las mismas se citan a continuación.

#### Servicio turístico
✓ La agencia de turismo ofrece diferentes tipos de servicios a sus potenciales clientes:
- Hotel por noche/s
- Alquiler de auto
- Pasajes de colectivo
- Pasajes de avión
- Pasajes de tren o Excursiones
- Entradas a Eventos
 
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



-------------------------------------------------------------



### Scenario
A travel agency requires the development of a web application for the sale of its products by each of its employees. To comprehend each aspect of the company and its products and services, a functional analyst carried out the survey of each action and consideration in the system's development. These are outlined below:

### Tourist Service
✓ The travel agency offers various types of services to potential clients:
- Nightly Hotel Stays 
- Car Rentals
- Bus Tickets
- Airplane Tickets
- Train Tickets
- Excursions
- Event Tickets

✓ Each service can be contracted in two possible ways:
- Individually
-In a tourist package (with other services)

✓ Each service has the following data:
- service_code
- name
- brief_description
- service_destination
- service_date
- service_cost

### Tourist Package
✓ A tourist package is a combination of two or more tourist services. For example, a package can contain a hotel night + an airplane ticket. ✓ Tourist packages have the following data:
- package_code
- included_services_list
- package_cost

✓ It's worth noting that the package cost is equal to the sum of the costs of the services it contains, minus a 10% discount for being contracted as a package. For instance, if a hotel night costs $6000 and an airplane ticket costs $45,000, then the sum of both is $51,000. Subtracting 10% gives the total value of the package ($51,000 - 10% = $45,900).

### Customers
✓ The system must be capable of registering new customers. ✓ The following data is required for each customer:
- customer_id
- first_name
- last_name
- address
- identification_number (DNI)
- date_of_birth
- nationality
- cell_number
- email

### Employees
✓ The system must be capable of registering each of its employees. ✓ Each employee should have the same data as a customer, in addition to the following unique data:
- Position
- Salary

### Sales
✓ The system should be able to carry out sales of various tourist packages or services. ✓ Each sale has its own data:
- sale_number
- sale_date
- payment_method

✓ Simultaneously, each sale must be related to:
- A customer
- A service or a tourist package (remember, a package contains multiple services).
- An employee (the one making the sale)

### CRUD Operations (Create, Read, Update, Delete)
✓ The system must be capable of performing CRUD operations on different services, packages, customers, sales, and employees. To achieve this, consider the following:
- All customers, services, packages, and sales should have their respective sections for creating new entries.
- All customers, services, packages, and sales should have their respective sections for making updates.
- All customers, services, packages, and sales should have their respective sections for querying/reading data.
- All customers, services, packages, and sales should have their respective sections for performing deletions.

### Bonus (OPTIONAL)
The system described earlier doesn't account for handling money or profits. However, the agency owner proposed a reward for any developer who devises a solution that allows him to track daily and monthly profits based on sales. For this purpose, consider the following commission rates based on payment method:
- Cash: No commission
- Debit Card: 3%
- Credit Card: 9%
- Virtual Wallet: No commission
- Bank Transfer: 2.45%

## Diagrama de Clases planteado (Proposed Class Diagram):
![TP Final Java drawio](https://user-images.githubusercontent.com/46388949/196831603-5da05e0b-672b-44cb-927c-577c1fead04d.png)





