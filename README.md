# LP3TP

Este README asume que se esta trabajando en localhost con puerto 8080, de no ser asi, reemplace ""localhost:8080" por su correspondiente

#### Dsclaimer: este proyecto esta basado/inspirado en el proyecto/carpeta llamado "lp3-tp2017" encontrado en https://github.com/alefq/lp3-2019

## Instituciones aprobadas por la ONG

## Cargar instituciones

Realizar un request de tipo POST a: localhost:8080/api/lp3/ong

con una lista en foramto JSON con los siguientes parametros para cada elemento:

nombre : nombre de la institucion (String)

direccion : direccion de la institucion (String)

anhoFundacion : anho de fundacion de la institucion (int)

## Ver las instituciones

Para ver todas las instituciones aprobadas por la ONG, realizar un request de tipo GET a:

localhost:8080/api/lp3/ong/


## Equipos

### Cargar equipos:

Realizar un request de tipo POST a: localhost:8080/api/lp3/equipos

con una lista en foramto JSON con los siguientes parametros para cada elemento:

nombre : nombre del club (String)

viajes : viajes que se realizaran con el impuesto de promocion (String)

tecnologia : tecnologias en las que se invertira el impuesto de tecnologia (String)

ong : instituciones ong a quienes se ayudarancon el impuesto de lujo (Lista/Array de String)

elEquipoPagoImpuestoLujo : 1 si pago el impuesto de lujo, 0 si no (bool)

calificaParaInternacional : 1 si califica para torneos internacionales, 0 si no (bool)


### Ver equipos:

Para ver todos los equipos, realizar un request de tipo GET a:

localhost:8080/api/lp3/equipos/

Para ver un equipo en especifico, realizar un request de tipo GET a:

localhost:8080/api/lp3/equipos/{nombreDelEquipo}


### Editar equipo

Para modificar el valor de elEquipoPagoImpuestoLujo, realizar un request de tipo PUT a:

localhost:8080/api/lp3/equipos/impuesto-lujo

con una lista en foramto JSON con los siguientes parametros para cada elemento:

nombre : nombre del club (String)

elEquipoPagoImpuestoLujo : 1 si pago el impuesto de lujo, 0 si no (bool)

Para modificar el valor de calificaParaInternacional, realizar un request de tipo PUT a:

localhost:8080/api/lp3/equipos/clasifica-internacional

con una lista en foramto JSON con los siguientes parametros para cada elemento:

nombre : nombre del club (String)

calificaParaInternacional : 1 si califica para torneos internacionales, 0 si no (bool)


## Empleados

### Cargar empleados:

Realizar un request de tipo POST a: localhost:8080/api/lp3/empleados 

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombre : nombre del Empleado (String)

apellido : apellido del empleado (String)

edad : edad del empleado (int)

numeroCedula : numero de cedula del empleado (int)

salario : salario anual del empleado (int)

cargo :  cargo del empleado en el club (String)

club : nombre del club del empleado (String)

sexo : sexo del empelado (String)


### Ver empleados:

Para ver una lista con TODOS los empleados, realizar un request de tip9o GET a:

localhost:8080/api/lp3/empleados/

Para ver los empleados de un club o equipo en especifico, realizar un request de tipo GET a:

localhost:8080/api/lp3/empleados/equipo/{nombreDelClub}

Para ver el salario promedio de los empleados de un club, realizar un request de tipo GET a:

localhost:8080/api/lp3/empleados/equipo/{nombreDelClub}/promedio-salario


### Editar la inforamcino de algun empleado:

Para transferir/cambiar el club de un empleado, realizar un request de tipo PUT a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}/transferir-a/{clubAlCualTransferir}

Para cambiar el cargo de un empleado, realizar un request de tipo PUT a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}/cambiar-cargo/{nuevoCargo}

Para cambiar el saalrio de un empleado, realizar un request de tipo PUT a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}/cambiar-salario/{nuevoSalario}

### Eliminar un empleado

Para eliminar un empleado, realizar un request de tipo DELETE a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}


## Jugadores

### Cargar jugadores:

Realizar un request de tipo POST a: localhost:8080/api/lp3/jugadores 

Si es la primera vez que inicia la app, utilice : localhost:8080/api/lp3/jugadores/inicializar

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombre : nombre del jugador (String)

apellido : apellido del jugador (String)

edad : edad del jugador (int)

numeroCedula : numero de cedula del jugador (int)

salario : salario anual del jugador (int)

posicion : posicion del jugador (String)

club : nombre del club del jugador (String)

sexo : sexo del jugador (String)

potencia : (int)

habilidad : (int)

velocidad : (int)

resistencia : (int)

piernaHabil: (String)

### Ver jugadores:

Para ver una lista con TODOS los jugadores, realizar un request de tip9o GET a:

localhost:8080/api/lp3/jugadores/

Para ver los empleados de un club o equipo en especifico, realizar un request de tipo GET a:

localhost:8080/api/lp3/jugadores/equipo/{nombreDelClub}

Para ver el salario promedio de los empleados de un club, realizar un request de tipo GET a:

localhost:8080/api/lp3/jugadores/equipo/{nombreDelClub}/promedio-salario

Para ver el salario promedio de todos los jugadores, realizar un request de tipo GET a:

localhost:8080/api/lp3/jugadores/promedio-salario

### Editar jugadores

Para editar los atributos (potencia, habilidad, velocidad, resistencia, piernaHabil) de un jugaodr, realizar un request de tipo PUT a:

localhost:8080/api/lp3/jugadores/editar-atributos

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

numeroCedula : numero de cedula del jugador (int)

potencia : (int)

habilidad : (int)

velocidad : (int)

resistencia : (int)

piernaHabil: (String)


## Entrenadores 

### Cargar entrenadores

Realizar un request de tipo POST a: localhost:8080/api/lp3/entrenadores 

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombre : nombre del entrenador (String)

apellido : apellido del entrenador (String)

edad : edad del entrenador (int)

numeroCedula : numero de cedula del entrenador (int)

salario : salario anual del entrenador (int)

club : nombre del club del entrenador (String)

sexo : sexo del entrenador (String)

titulosGanados : (int)

exJugador : 1 si es un ex jugaodr, 0 si no (bool)

### Ver entrenadores

Para ver una lista con TODOS los entrenadores, realizar un request de tipo GET a:

localhost:8080/api/lp3/entrenadores/

Para ver el entrenador de un club o equipo en especifico, realizar un request de tipo GET a:

localhost:8080/api/lp3/entrenadores/equipo/{nombreDelClub}


## Directores 

### Cargar directores

Realizar un request de tipo POST a: localhost:8080/api/lp3/directores

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombre : nombre del entrenador (String)

apellido : apellido del entrenador (String)

edad : edad del entrenador (int)

numeroCedula : numero de cedula del entrenador (int)

salario : salario anual del entrenador (int)

club : nombre del club del entrenador (String)

sexo : sexo del entrenador (String)

### Ver directores

Para ver una lista con TODOS los directores, realizar un request de tipo GET a:

localhost:8080/api/lp3/directores/

Para ver el director de un club o equipo en especifico, realizar un request de tipo GET a:

localhost:8080/api/lp3/directores/equipo/{nombreDelClub}


## Asociacion de empleados

### Agregar miembros a la asociacion:

Realizar un request de tipo POST a: localhost:8080/api/lp3/asociacion-de-empleados/

con un array (JSON) con los numero de cedulas de los empleados que quiere agregar


### Ver miembros de la asociacion:

Para ver todos los miembros de la asociacion, realizar un request de tipo GET a:

localhost:8080/api/lp3/asociacion-de-empleados/


### Eliminar miembros de la asociacion

Para eliminar a un miembro de la asociacion, realizar un request de tipo DELETE a:

localhost:8080/api/lp3/asociacion-de-empleados/{numeroDeCedulaDelEmpleado}


## Torneos

### Crear un torneo

Realizar un request de tipo POST a: localhost:8080/api/lp3/torneos

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombreDelTorneo : nombre del torneo (String)

tipo : si es de tipo Nacional o Internacional (String)

participantes : lista de los equipos participantes (List/Array de String)

### Ver torneo

Para ver todos los torneos existentes, realizar un request de tipo GET a:

localhost:8080/api/lp3/torneos/

Para ver el salario promedio de los equipos participantes de un torneo, realizar un request de tipo GET a:

localhost:8080/api/lp3/torneos/{nombreDelTorneo}/salario-promedio

### Editar un torneo

Para editar los miembros un torneo, realizar un request de tipo PUT a:

localhost:8080/api/lp3/torneos/editar

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombreDelTorneo : nombre del torneo (String)

participantes : lista de los equipos participantes (List/Array de String)


