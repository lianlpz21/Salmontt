SalmonttApp – Evaluación Final Transversal

Aplicación desarrollada en Java para la empresa ficticia Salmontt, donde se aplican conceptos de Programación Orientada a Objetos como encapsulamiento, herencia, polimorfismo, interfaces, uso de instanceof, manejo de colecciones y lectura de archivos .txt.

Descripción del sistema:
El sistema permite registrar y visualizar información de clientes, empleados y proveedores, cargar productos desde un archivo externo y generar órdenes de compra. Todas las personas del sistema implementan una interfaz común y son gestionadas mediante una colección polimórfica.

Estructura del proyecto:
app  
MenuConsola: clase principal que controla el menú del sistema y la interacción por consola.

model  
Persona: clase base del sistema.  
Cliente, Empleado, Proveedor: clases que heredan de Persona.  
Direccion y Rut: clases utilizadas por composición.  
Producto: representa productos cargados desde archivo .txt.  
Tarjeta: medio de pago asociado a una orden de compra.  
OrdenDeCompra: gestiona la creación de órdenes y sus productos.

interfaces  
Registrable: interfaz común implementada por las entidades registrables del sistema.

utils  
LeerProductos: clase utilitaria para leer productos desde archivo .txt.  
ValidadorRut: clase encargada de validar el formato del RUT.

data  
productos.txt: archivo con los datos de productos utilizados por el sistema.

Ejecución del programa:
Abrir el proyecto en IntelliJ IDEA.  
Ejecutar la clase app.MenuConsola.  
Utilizar el menú de la consola para registrar personas, visualizar registros, cargar productos y crear órdenes de compra.
