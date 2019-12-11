package py.edu.uca.lp3.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Torneo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1796674952194499268L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombreDelTorneo;
	private String tipo = "Nacional";
	private ArrayList<String> participantes;
	
	
	public String getNombreDelTorneo() {
		return nombreDelTorneo;
	}
	public void setNombreDelTorneo(String nombreDelTorneo) {
		this.nombreDelTorneo = nombreDelTorneo;
	}
	public ArrayList<String> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(ArrayList<String> participantes) {
		this.participantes = participantes;
	}
	public String getNacionalInternacional() {
		return tipo;
	}
	public void setNacionalInternacional(String nacionalInternacional) {
		this.tipo = nacionalInternacional;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

}
