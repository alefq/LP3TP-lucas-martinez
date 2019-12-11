package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Persona;

@Service
public class PersonaService {

	// Utilizamos una clave maestra con esta constante, con fines didácticos
	private static final String MASTERKEY = "123456";

	//Esta implementación de la interface java.util.List será el contenedor 
	//de nuestra lista de personas para los ejemplos de clase
	private List<Persona> personasExampleList = new ArrayList<Persona>();

	public PersonaService() {
		initializeExamples();
	}

	private void initializeExamples() {
		// Inicializamos dos personas de ejemplo
		Persona luca = new Persona();
		luca.setNumeroCedula(111);
		luca.setNombre("Luca");
		Persona bart = new Persona();
		bart.setNumeroCedula(222);
		bart.setNombre("Bart");

		// Agragamos a nuestra lista
		personasExampleList.add(luca);
		personasExampleList.add(bart);
	}

	public Persona findPersonaByNroCedula(Integer numeroCedula) {
		Persona personaARetornar = null;
		// En este ejemplo recorremos la lista de ejemplo
		// en un ejemplo más complejo de uso real consultaríamos un repositorio de datos
		// Base de Datos, otro Web Service, algún servicio de mensajería, etc.
		for (Persona personaIteracion : personasExampleList) {
			if (numeroCedula != null && numeroCedula.equals(personaIteracion.getNumeroCedula())) {
				personaARetornar = personaIteracion;
			}
		}
		return personaARetornar;
	}

	public static PersonaService buildInstance() {
		// Para fines didácticos este factory retorna una instancia común
		// En un ambiente de Spring Boot completo esto sería un @Service
		// que se haría @Autowired
		return new PersonaService();
	}

	public Persona validateLogin(String username, String password) {
		Persona personaEncontrada;
		if (StringUtils.isNoneBlank(username, password) && password.equals(MASTERKEY)) {
			// Con fines didácticos, la autenticación se
			personaEncontrada = new Persona("Ale", "F.", 41);
		} else {
			// Con fines didácticos si la contraseña no coincide con el MASTERKEY informamos
			// que hay problemas con las credenciales
			personaEncontrada = null;
		}
		return personaEncontrada;
	}

}
