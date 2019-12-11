package py.edu.uca.lp3.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasePerson {

	private String firstName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
