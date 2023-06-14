package himedia.whatthispills.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.Admin;
import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;
import himedia.whatthispills.Repository.JDBCAdminRepository;
import himedia.whatthispills.Repository.JDBCUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private final JDBCUserRepository userRepository;
	private final JDBCAdminRepository adminRepository;
	
	public UserService(JDBCUserRepository userRepository, JDBCAdminRepository adminRepository) {
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> findEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<User> findEmailName(String email, String name) {
		return userRepository.findByEmailName(email, name);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public List<UserLikes> userLikeList(Long user_idx) {
		return userRepository.userLikes(user_idx);
	}
	
	public String login(String try_email, String try_pwd) {
		Optional<Admin> admin = adminRepository.findByEmail(try_email);
		Optional<User> user = userRepository.findByEmail(try_email);
		
		if(user.isPresent() && user.get().getPwd().equals(try_pwd)) {
			return "user";
		} else if(admin.isPresent() && admin.get().getPwd().equals(try_pwd)) {
			return "admin";
		}
		return "";
	}
	
	public boolean find_pwd(String try_email, String try_name) {
		Optional<User> user = userRepository.findByEmailName(try_email, try_name);
		
		if(user.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public User update(User update_user) {
		return userRepository.updateUser(update_user).get();
	}
	
    public boolean updatePassword(String user_email, String update_pwd) {
        Optional<User> idx = userRepository.updatePwd(user_email, update_pwd);
        if(idx.isPresent()) {
            return true;
        }
        return false;
    }
}
