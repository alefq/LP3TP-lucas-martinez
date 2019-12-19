package py.edu.uca.lp3.domain;

import javax.persistence.Entity;

@Entity
public class Jugador extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5247209648697065642L;

	private String posicion; //no te olvides de hacer set cargo = jugador
	
	private String piernaHabil = "Derecha";
	private int potencia;
	private int resistencia;
	private int habilidad;
	private int velocidad;
	
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
	
	

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getPiernaHabil() {
		return piernaHabil;
	}

	public void setPiernaHabil(String piernaHabil) {
		this.piernaHabil = piernaHabil;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getResistencia() {
		return resistencia;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public int getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(int habilidad) {
		this.habilidad = habilidad;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	
	
}
