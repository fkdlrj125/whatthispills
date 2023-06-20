package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLikes {
	private Long nutri_idx;
	
	
	public UserLikes(Long nutri_idx) {
		this.nutri_idx = nutri_idx;
	}
}
