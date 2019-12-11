package py.edu.uca.lp3.domain;

import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import py.edu.uca.lp3.repository.EquipoRepository;

public class BaseEquipo {
	@Autowired
	private EquipoRepository equipoRepository;

	protected long topeSalarial; //promedio del salario por equipo
	protected long promedioSalarial;

	public long getPromedioSalarial() {
		return promedioSalarial;
	}

	public void setPromedioSalarial(long promedioSalarial) {
		this.promedioSalarial = promedioSalarial;
	}
	
	public void setPromedioSalarial() {
		Iterator<Equipo> iteratorEquipos2 = equipoRepository.findAll().iterator();
		int cantidadDeEquipos = 0;
		long sumaDeSalarios = 0;
		while(iteratorEquipos2.hasNext()) {
			cantidadDeEquipos ++;
			Equipo actual = iteratorEquipos2.next();
			sumaDeSalarios = Long.sum(actual.getSalarioClub(),sumaDeSalarios);
		}
		this.promedioSalarial = Long.divideUnsigned(sumaDeSalarios, cantidadDeEquipos);
	}

	public long getTopeSalarial() {
		return topeSalarial;
	}

	public void setTopeSalarial(long topeSalarial) {
		this.topeSalarial = topeSalarial;
	}
	public void setTopeSalarial() {
		long x = this.promedioSalarial;
		long tope = (long) (x + 0.2*x + 0.3*1.2*x);
		this.topeSalarial = tope;
	}
	
}
