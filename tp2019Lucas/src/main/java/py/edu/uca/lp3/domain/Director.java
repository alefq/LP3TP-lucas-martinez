package py.edu.uca.lp3.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Director extends Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143593704491996544L;
	
	public Director() {
		// TODO Auto-generated constructor stub
	}

	public Director(String nombre, String apellido, int edad) {
		super(nombre, apellido, edad);
		// TODO Auto-generated constructor stub
	}

	public Director(String nombre, String apellido, int edad, int nroCedula) {
		super(nombre, apellido, edad, nroCedula);
		// TODO Auto-generated constructor stub
	}

	public Director(int numeroCedula, String nombre) {
		super(numeroCedula, nombre);
		// TODO Auto-generated constructor stub
	}

}
