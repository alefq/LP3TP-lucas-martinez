package py.edu.uca.lp3.domain;

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
	private long impuestoTecnologia;
	private long impuestoPromocion;
	private long impuestoLujo;
	private boolean elEquipoPagoImpuestoDeLujo;
	private long aumentoPorLujo = 0;
	private String nombre;
	
	private long topeSalarial = 5000; //promedio del salario por equipo
	private long promedioSalarial;
	
	public Equipo() {
		super();
		this.impuestoTecnologia = (long) (0.04 * salarioClub);
		this.impuestoPromocion = (long) (0.02 * salarioClub);
		this.impuestoLujo = (long) (0.3 * salarioClub);
	}

	public long getSalarioClub() {
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

	
	
	
	
}
