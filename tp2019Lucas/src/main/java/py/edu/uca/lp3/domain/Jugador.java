package py.edu.uca.lp3.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jugador extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5247209648697065642L;

	
	public Jugador() {
		// TODO Auto-generated constructor stub
	}

	public Jugador(String nombre, String apellido, int edad) {
		super(nombre, apellido, edad);
		// TODO Auto-generated constructor stub
	}

	public Jugador(String nombre, String apellido, int edad, int nroCedula) {
		super(nombre, apellido, edad, nroCedula);
		// TODO Auto-generated constructor stub
	}

	public Jugador(int numeroCedula, String nombre) {
		super(numeroCedula, nombre);
		// TODO Auto-generated constructor stub
	}
	
	private String posicion; //no te olvides de hacer set cargo = jugador


	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	
}
