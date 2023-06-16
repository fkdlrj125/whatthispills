package himedia.whatthispills.Service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Repository.JDBCUserRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class UserServiceTest {
	
	@Autowired JDBCUserRepository userRepository;
	@Autowired UserService userService;

	@Test
	void 로그인1() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		String result = userService.login("test2", "1111");
//		then
		assertThat(result).isEmpty();
	}
	
	@Test
	void 로그인2() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		String result = userService.login(save_user.getEmail(), save_user.getPwd());
//		then
		assertThat(result).isEqualTo("user");
	}
	
	@Test
	void 로그인3() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		String result = userService.login(" ", " ");
//		then
		assertThat(result).isEmpty();
	}
	
	@Test
	void 비밀번호찾기1() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		boolean result = userService.findPwd(null, null);
//		then
		assertThat(result).isFalse();
	}
	
	@Test
	void 비밀번호찾기2() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		boolean result = userService.findPwd(user.getEmail(), user.getName());
//		then
		assertThat(result).isTrue();
	}
	
	@Test
	void 비밀번호업데이트1() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		boolean result = userService.updatePassword(user.getEmail(), "0101");
//		then
		assertThat(result).isTrue();
	}
	
	@Test
	void 비밀번호업데이트2() {
//		given
		User user = new User("test1@test1.com", "test1", "1111", "1998-09-02", "male");
		User save_user = userRepository.save(user);
//		when
		boolean result = userService.updatePassword("test2", "0101");
//		then
		assertThat(result).isFalse();
	}
}
