package py.edu.uca.lp3.domain;

import javax.persistence.Entity;

@Entity
public class Entrenador extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5352711405615465689L;
		
	private int titulosGanados; //como jugador
	private boolean exJugador;
	
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
