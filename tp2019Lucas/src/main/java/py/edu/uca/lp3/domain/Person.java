package py.edu.uca.lp3.domain;

import javax.persistence.Entity;

@Entity
public class Person extends BasePerson {
	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
