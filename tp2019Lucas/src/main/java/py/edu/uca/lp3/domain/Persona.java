package py.edu.uca.lp3.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private static final long serialVersionUID = -4419931048984717671L;
	
	private String nombre;

	private String apellido;
	
	private int edad;
	private int numeroCedula;
	public static final String NACIONALIDAD = "Paraguayo";

	public Persona() {
		// TODO Auto-generated constructor stub

	}

	public Persona(String nombre, String apellido, int edad) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	public Persona(int numeroCedula, String nombre) {
		super();
		this.nombre = nombre;
		this.numeroCedula = numeroCedula;
	}

	public Persona(String nombre2, String apellido2, int edad2, int nroCedula) {
		this(nombre2, apellido2, edad2);
		setNumeroCedula(nroCedula);
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNumeroCedula() {
		return numeroCedula;
	}

	public void setNumeroCedula(int numeroCedula) {
		this.numeroCedula = numeroCedula;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
