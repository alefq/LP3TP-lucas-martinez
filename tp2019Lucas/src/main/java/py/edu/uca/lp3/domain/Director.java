package py.edu.uca.lp3.domain;

import javax.persistence.Entity;

@Entity
public class Director extends Empleado {

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
