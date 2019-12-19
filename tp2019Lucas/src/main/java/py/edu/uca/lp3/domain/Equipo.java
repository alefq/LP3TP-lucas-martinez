package py.edu.uca.lp3.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long salarioClub; //cuanto el club paga anual
	private long impuestoRentaPersonal;
	private long impuestoTecnologia;
	private long impuestoPromocion;
	private long impuestoLujo;
	private boolean elEquipoPagoImpuestoDeLujo = false;
	private long aumentoPorLujo = 0;
	private String nombre;
	private boolean calificaParaInternacional = false;

	private long topeSalarial; 
	private long promedioSalarial;//promedio del salario por equipo
	
	private String viajes;
	private String tecnologias;
	private ArrayList<String> ong;
	
	Equipo() {
		impuestoRentaPersonal = (long) (0.10 * salarioClub);
		impuestoTecnologia = (long) (0.04 * salarioClub);
		impuestoPromocion = (long) (0.02 * salarioClub);
		impuestoLujo = (long) (0.3 * salarioClub);
	}

	public long getSalarioClub() {
		if(isElEquipoPagoImpuestoDeLujo()) {
			return (salarioClub + aumentoPorLujo +impuestoPromocion + impuestoTecnologia + impuestoRentaPersonal);
		}
		return (salarioClub +impuestoPromocion + impuestoTecnologia + impuestoRentaPersonal);
	}
	
	public long getSalarioNetoClub() {
		if(isElEquipoPagoImpuestoDeLujo()) {
			return (salarioClub + aumentoPorLujo);
		}
		return salarioClub;
	}

	public void setSalarioClub(long salarioClub) {
		this.salarioClub = salarioClub;
	}

	public long getImpuestoTecnologia() {
		return impuestoTecnologia;
	}

	public void setImpuestoTecnologia() {
		this.impuestoTecnologia = (long)(this.salarioClub * 0.04);
	}

	public long getImpuestoPromocion() {
		return impuestoPromocion;
	}

	public void setImpuestoPromocion() {
		this.impuestoPromocion = (long)(this.salarioClub * 0.02);
	}

	public long getImpuestoLujo() {
		return impuestoLujo;
	}

	public void setImpuestoLujo() {
		long newImpuesto = (long)(this.salarioClub * 0.3);
		if (newImpuesto > 2*getPromedioSalarial()) {
			newImpuesto = 2*getPromedioSalarial();
		}
		this.impuestoLujo = newImpuesto;
	}

	public long getTopeSalarial() {
		return topeSalarial;
	}

	public void setTopeSalarial(long topeSalarial) {
		this.topeSalarial = topeSalarial;
	}

	public long getPromedioSalarial() {
		return promedioSalarial;
	}

	public void setPromedioSalarial(long promedioSalarial) {
		this.promedioSalarial = promedioSalarial;
	}

	public boolean isElEquipoPagoImpuestoDeLujo() {
		return elEquipoPagoImpuestoDeLujo;
	}

	public void setElEquipoPagoImpuestoDeLujo(boolean elEquipoPagoImpuestoDeLujo) {
		this.elEquipoPagoImpuestoDeLujo = elEquipoPagoImpuestoDeLujo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getAumentoPorLujo() {
		return aumentoPorLujo;
	}

	public void setAumentoPorLujo() {
		long aumento = (long) (0.2 * getTopeSalarial());
		this.aumentoPorLujo = aumento;
	}
	
	
	public boolean isCalificaParaInternacional() {
		return calificaParaInternacional;
	}

	public void setCalificaParaInternacional(boolean calificaParaInternacional) {
		this.calificaParaInternacional = calificaParaInternacional;
	}

	public long getImpuestoRentaPersonal() {
		return impuestoRentaPersonal;
	}

	public void setImpuestoRentaPersonal() {
		this.impuestoRentaPersonal = (long)(this.salarioClub * 0.02);
	}

	public String getViajes() {
		return viajes;
	}

	public void setViajes(String viajes) {
		this.viajes = viajes;
	}

	public String getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}

	public List<String> getOng() {
		return ong;
	}

	public void setOng(ArrayList<String> ong) {
		this.ong = ong;
	}
	
	
	
}
