package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;

public interface UserRepository {
	public User saveUser(User user);
	public void saveLikes(Long nutri_idx, Long user_idx);
	public Optional<User> findByEmail(String email);
	public Optional<User> findByEmailName(String email, String name);
	public List<User> findAll();
	public Optional<User> updateUser(User update_user);
	public Optional<User> updatePwd(String user_email, String update_pwd);
	public void removeLikes(Long nutri_idx, Long user_idx);
	List<UserLikes> userLikes(Long user_idx);
	Optional<UserLikes> findByIdToNutriLike(Long nutri_idx, Long user_idx);
}
