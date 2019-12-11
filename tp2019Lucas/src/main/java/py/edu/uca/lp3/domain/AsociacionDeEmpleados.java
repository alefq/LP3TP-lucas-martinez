package py.edu.uca.lp3.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AsociacionDeEmpleados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2688705988924162453L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int numeroCedulaMiembro;
	private String clubDelMiembro;
	private String nombreDelMiembro;
	private String ApellidoDelMiembro;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumeroCedulaMiembro() {
		return numeroCedulaMiembro;
	}
	public void setNumeroCedulaMiembro(int numeroCedulaMiembro) {
		this.numeroCedulaMiembro = numeroCedulaMiembro;
	}
	public String getClubDelMiembro() {
		return clubDelMiembro;
	}
	public void setClubDelMiembro(String clubDelMiembro) {
		this.clubDelMiembro = clubDelMiembro;
	}
	public String getNombreDelMiembro() {
		return nombreDelMiembro;
	}
	public void setNombreDelMiembro(String nombreDelMiembro) {
		this.nombreDelMiembro = nombreDelMiembro;
	}
	public String getApellidoDelMiembro() {
		return ApellidoDelMiembro;
	}
	public void setApellidoDelMiembro(String apellidoDelMiembro) {
		ApellidoDelMiembro = apellidoDelMiembro;
	}
	
	
	
}
