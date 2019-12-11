package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Empresa;
import py.edu.uca.lp3.repository.EmpresaRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public Empresa findById(Long id) {
		Empresa empresa = empresaRepository.findOne(id);
		return empresa;
	}

	public void saveBatch(List<Empresa> empresas) throws InscripcionException {
		for (Empresa aGuardar : empresas) {
			if (aGuardar.getAnhoFundacion() < 1990) {
				InscripcionException inscripcionException =  new InscripcionException(
						"No se permite la inscripción para el año de fundacion: " + aGuardar.getAnhoFundacion());
				inscripcionException.setContacto("ale@gmail.com");
			}
			save(aGuardar);
		}
	}

	public List<Empresa> findAll() {
		List<Empresa> empresas = new ArrayList<>();
		Iterator<Empresa> iteratorEmpresas = empresaRepository.findAll().iterator();
		while (iteratorEmpresas.hasNext()) {
			empresas.add(iteratorEmpresas.next());
		}
		return empresas;
	}

	public void save(Empresa empresa) {
		empresaRepository.save(empresa);
	}

	public void delete(Long id) {
		empresaRepository.delete(id);
	}

}
