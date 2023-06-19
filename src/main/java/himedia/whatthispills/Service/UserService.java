package himedia.whatthispills.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.Admin;
import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;
import himedia.whatthispills.Repository.JDBCAdminRepository;
import himedia.whatthispills.Repository.JDBCNutriRepository;
import himedia.whatthispills.Repository.JDBCUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	private final JDBCUserRepository userRepository;
	private final JDBCNutriRepository nutriRepository;
	private final JDBCAdminRepository adminRepository;
	
	public UserService(JDBCUserRepository userRepository, JDBCNutriRepository nutriRepository, JDBCAdminRepository adminRepository) {
		this.userRepository = userRepository;
		this.nutriRepository = nutriRepository;
		this.adminRepository = adminRepository;
	}
	
	public User save(User user) {
		return userRepository.saveUser(user);
	}
	
	public void like(Long nutri_idx, Long user_idx) {
		Optional<UserLikes> user_like = userRepository.findByIdToNutriLike(nutri_idx, user_idx);
		if(user_like.isEmpty()) {
			userRepository.saveLikes(nutri_idx, user_idx);
		} else {
			userRepository.removeLikes(nutri_idx, user_idx);
		}
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
	
	public UserLikes userLike(Long nutri_idx, Long user_idx) {
		Optional<UserLikes> user_like = userRepository.findByIdToNutriLike(nutri_idx, user_idx);
		if(user_like.isEmpty()) {
			return null;
		}
		return user_like.get();
	}
	
	public List<Nutri> userLikeList(Long user_idx) {
		List<Long> idx_list = likeIdxList(user_idx);
		List<Nutri> nutri_list = new ArrayList<Nutri>();
		idx_list.stream().forEach(idx -> {
			Nutri nutri = nutriRepository.findByIdxNutri(idx).stream().findAny().get();
			nutri_list.add(nutri);
		});
		return nutri_list;
	}
	
	public List<Long> likeIdxList(Long user_idx) {
		List<UserLikes> like_list = userRepository.userLikes(user_idx);
		List<Long> idx_list = new ArrayList<Long>();
		for(UserLikes like : like_list) {
			idx_list.add(like.getNutri_idx()); 
		}
		return idx_list;
	}
	
	public String login(String try_email, String try_pwd) {
		Optional<Admin> admin = adminRepository.findById(try_email);
		Optional<User> user = userRepository.findByEmail(try_email);
		if(user.isPresent() && user.get().getPwd().equals(try_pwd)) {
			return "user";
		} else if(admin.isPresent() && admin.get().getPwd().equals(try_pwd)) {
			return "admin";
		}
		return "";
	}
	
	public boolean findPwd(String try_email, String try_name) {
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
        Optional<User> user = userRepository.findByEmail(user_email);
        if(!update_pwd.equals(user.get().getPwd())) {
        	userRepository.updatePwd(user_email, update_pwd);
            return true;
        }
        return false;
    }
}
