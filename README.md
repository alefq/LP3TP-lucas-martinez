# LP3TP

## Equipos

### Cargar equipos:

Realizar un request de tipo POST a: localhost:8080/api/lp3/equipos

con una lista en foramto JSON con los siguientes parametros para cada elemento:

nombre, elEquipoPagoImpuestoLujo


### Ver equipos:

Para ver todos los equipos, realizar un request de tipo GET a:

localhost:8080/api/lp3/equipos/

Para ver un equipo en especifico, realizar un request de tipo GET a:

localhost:8080/api/lp3/equipos/{nombreDelEquipo}


## Empleados

### Cargar empleados:

Realizar un request de tipo POST a: localhost:8080/api/lp3/empleados 

con una lista en formato JSON con los siguiente parametros para cada elemento de la lista:

nombre, apellido, edad, numeroCedula, salario, cargo, club


### Ver empleados:

Para ver una lista con TODOS los empleados, realizar un request de tip9o GET a:

localhost:8080/api/lp3/empleados/

Para ver los empleados de un club o equipo en especifico, realizar un request de tipo GET a:
localhost:8080/api/lp3/empleados/club/{nombreDelClub}


### Editar la inforamcino de algun empleado:

Para transferir/cambiar el club de un empleado, realizar un request de tipo PUT a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}/transferir-a/{clubAlCualTransferir}

Para cambiar el cargo de un empleado, realizar un request de tipo PUT a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}/cambiar-cargo/{nuevoCargo}

Para cambiar el saalrio de un empleado, realizar un request de tipo PUT a:

localhost:8080/api/lp3/empleados/{numeroDeCedulaDelEmpleado}/cambiar-salario/{nuevoSalario}


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
