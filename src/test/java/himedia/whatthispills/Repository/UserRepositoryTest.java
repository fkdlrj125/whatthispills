package himedia.whatthispills.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import himedia.whatthispills.Domain.User;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class UserRepositoryTest {

	@Autowired JDBCUserRepository userRepository;
	
	@Test
	void 저장() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
//		when
		User result = userRepository.save(user);
//		then
		log.info("id >> {}", result.getIdx());
		assertThat(result.getIdx()).isNotNull();
	}
	
	@Test
	void 이메일조회() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		Optional<User> result = userRepository.findByEmail(user.getEmail());
//		then
		log.info("email >> {}", result.get().getEmail());
		log.info("id >> {}", result.get().getIdx());
		assertThat(result.get().getIdx()).isEqualTo(save_user.getIdx());
	}
	
	@Test
	void 이메일이름조회() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		Optional<User> result = userRepository.findByEmailName(user.getEmail(), user.getName());
//		then
		log.info("email >> {}", result.get().getEmail());
		log.info("name >> {}", result.get().getName());
		assertThat(result.get().getIdx()).isEqualTo(save_user.getIdx());
	}
	
	@Test
	void 전체조회() {
//		given
		int len = userRepository.findAll().size();
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		userRepository.save(user);
//		when
		int result = userRepository.findAll().size();
//		then
		log.info("size >> {}", result);
		assertThat(result).isEqualTo(len+1);
	}
	
	@Test
	void 유저정보수정() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		User update_user = new User("test1@test1.com", "test2", "1234", "1997-03-27", "nan");
		Optional<User> result = userRepository.updateUser(update_user);
//		then
		log.info("idx >> {}", result.get().getIdx());
		log.info("name >> {}", result.get().getName());
		log.info("gender >> {}", result.get().getGender());
		assertThat(result.get().getName()).isEqualTo(update_user.getName());
	}
	
	@Test
	void 비밀번호수정() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		String user_email = save_user.getEmail();
		String update_pwd = "1234";
		Optional<User> result = userRepository.updatePwd(user_email, update_pwd);
//		then
		log.info("idx >> {}", result.get().getIdx());
		log.info("pwd >> {}", result.get().getPwd());
		assertThat(result.get().getPwd()).isEqualTo(update_pwd);
	}
}
