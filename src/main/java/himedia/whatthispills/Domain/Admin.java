package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Admin {
	private Long idx;
	private String id;
	private String pwd;
	
	public Admin(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

}
