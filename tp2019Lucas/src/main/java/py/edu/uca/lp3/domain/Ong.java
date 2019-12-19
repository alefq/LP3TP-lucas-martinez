package py.edu.uca.lp3.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ong implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6186269575051313894L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String direccion;
	private int anhoFundacion;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getAnhoFundacion() {
		return anhoFundacion;
	}
	public void setAnhoFundacion(int anhoFundacion) {
		this.anhoFundacion = anhoFundacion;
	}
	
	
}
