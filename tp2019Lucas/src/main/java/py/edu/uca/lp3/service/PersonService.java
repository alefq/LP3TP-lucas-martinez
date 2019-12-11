package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Person;
import py.edu.uca.lp3.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person findById(Long id) {
		Person person = personRepository.findOne(id);
		return person;
	}

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		Iterator<Person> iteratorPersons = personRepository.findAll().iterator();
		while(iteratorPersons.hasNext()) {
			persons.add(iteratorPersons.next());
		}
		return persons;
	}

	public void save(Person person) {
		personRepository.save(person);
	}

	public void delete(Long id) {
		personRepository.delete(id);
	}

}
