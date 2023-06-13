package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
	private Long idx;
	private String email;
	private String name;
	private String pwd;
	private String birth;
	private String gender;
	
	public User(String email, String name, String pwd, String birth, String gender) {
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.birth = birth;
		this.gender = gender;
	}
	

}
