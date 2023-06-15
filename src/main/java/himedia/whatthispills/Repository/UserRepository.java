package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;

public interface UserRepository {
	public User save(User user);
	public Optional<User> findByEmail(String email);
	public Optional<User> findByEmailName(String email, String name);
	public List<User> findAll();
	public List<UserLikes> userLikes(Long user_idx);
	public Optional<User> updateUser(Long update_idx, User update_user);
	public Optional<User> updatePwd(String user_email, String update_pwd);
}
