package py.edu.uca.lp3.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entrenador extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5352711405615465689L;
	
	public Entrenador() {
		// TODO Auto-generated constructor stub
	}

	public Entrenador(String nombre, String apellido, int edad) {
		super(nombre, apellido, edad);
		// TODO Auto-generated constructor stub
	}

	public Entrenador(String nombre, String apellido, int edad, int nroCedula) {
		super(nombre, apellido, edad, nroCedula);
		// TODO Auto-generated constructor stub
	}

	public Entrenador(int numeroCedula, String nombre) {
		super(numeroCedula, nombre);
		// TODO Auto-generated constructor stub
	}
	
	
	
	private int titulosGanados; //como jugador
	private boolean exJugador;

	public int getTitulosGanados() {
		return titulosGanados;
	}

	public void setTitulosGanados(int titulosGanados) {
		this.titulosGanados = titulosGanados;
	}

	public boolean isExJugador() {
		return exJugador;
	}

	public void setExJugador(boolean exJugador) {
		this.exJugador = exJugador;
	}
	
	

}
