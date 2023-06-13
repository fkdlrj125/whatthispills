package himedia.whatthispills.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserLikes {
	private Long user_idx;
	private Long nutri_idx;
	private boolean likes;
	
	
	public UserLikes(Long user_idx, Long nutri_idx, boolean likes) {
		super();
		this.user_idx = user_idx;
		this.nutri_idx = nutri_idx;
		this.likes = likes;
	}
}
