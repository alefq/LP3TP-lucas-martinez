#### Observacion: todas las clases citadas a continuacion cuentan con una clase equivalente <nombreDeLaClase>Controller , <NombreDeLaClase>Service y una interface <NombreDeLaClase>Repository

### TODAS las clases implementan Serializable


# Clases

## AsociacionDeEmpleados

Clase para los miembros pertenecientes a la asociacion de empleados

Variables:

private long id

private int numeroCeculaMiembro //numero de cedula

private String nombreDelMiembro //nombre

private String ApellidoDelMiembro //apellido


## Director

Clase para los directores de los equipos, extiende de Empleado

Variables:

Las mismas que Empleado


## Empleado

Clase para los empleados, extiende de Persona

Variables:

Las mismas que Persona

private int salario //su salario anual

private String cargo //su cargo dentro del club

private String club //club al cual esta inscripto


## Entrenador

Clase para el entrenador de los equipos, extiende de Empleado

Variables:

Las mismas que Empleado

private int titulosGanados //cuantos titulos gano siendo jugador

private boolean exJugador //true si fue jugador en su momento, false si no


## Equipo

private long id

private long salarioClub //cuanto el club esta pagando al anho

private long impuestoRentaPersonal //cuanto debe pagar por el IRP

private long impuestoTecnologia //cuanto paga por el impuesto a la tecnologia

private long impuestoPromocion //cuanto paga por su impuesto de promocion

private long impuestoLujo //cuanto pagaria si este quiere el impuesto de Lujo

private boolean el EquipoPagoImpuestoDeLujo //true si pago el impuesto de lujo, false si no

private long aumentoPorLujo //cuanto aumenta su tope salarial si paga el impuesto de Lujo

private String nombre //el nombre del club o equipo

private boolean calificaParaInterncional //true si el equipo califica para competir en torneos internacionales

private long topeSalarial //el tope salarial de los equipos

private long promedioSalarial //el promedio de salarioClub de los equipos

private String viajes //viajes que se realiaran con el impuesto de promociones

private String tecnologias //las tecnologias en las que se invertira el impuesto a la tecnologia

private ArrayList<String> ong //las instituciones aprobadas por la ong a las cuales donar el impuesto de lujo


## Jugador

Clase para los jugadores, extiende de Empleado

Variables:

Las mismas que Empleado

private String piernaHabil //pierna habil: Derecha o Izquierda

private int potencia

private int resistencia

private int habilidad

private int velocidad


## Ong

Clase para las instituciones aprobadas por la ONG

Variables:

private long id

private String nombre //el nombre de la institucion

private String direccion //la direccion o calle de la institucion

private int anhoFundacion //anho de su fundacion


## Persona

Clase para las personas

Variables:

private long id

private String nombre //nombre de la persona

private String sexo //sexo de la persona

private String apellido //apellido de la persona

private int edad //edad de la persona

private int numeroCedula //numero de cedula de la persona


## Torneo

Clase para los torneos

Variables:

private long id

private String nombreDelTorneo //nombre del torneo

private String tipo //Nacional o Internacional

private ArrayList<String> participantes //equipos participantes del torneo
