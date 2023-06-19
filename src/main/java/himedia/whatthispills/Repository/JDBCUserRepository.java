package himedia.whatthispills.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;

@Repository
public class JDBCUserRepository implements UserRepository{
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCUserRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public RowMapper<User> userMapper() {
		return (ResultSet rs, int rowNum) -> {
			User user = new User(
					rs.getString("user_email"),
					rs.getString("user_name"),
					rs.getString("user_pwd"),
					rs.getString("user_birth"),
					rs.getString("user_gender"));
			user.setIdx(rs.getLong("user_idx"));
			return user;
		};
	}
	
	public RowMapper<UserLikes> userLikesMapper() {
		return (ResultSet rs, int rowNum) -> {
			UserLikes userlikes = new UserLikes(
					rs.getLong("nutri_idx"));
			return userlikes;
		};
	}

	@Override
	public User saveUser(User user) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("user_").usingGeneratedKeyColumns("user_idx");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_email", user.getEmail());
		map.put("user_name", user.getName());
		map.put("user_pwd", user.getPwd());
		map.put("user_birth", user.getBirth());
		map.put("user_gender", user.getGender());
		Number result = insert.executeAndReturnKey(new MapSqlParameterSource(map));
		user.setIdx(result.longValue());
		return user;
	}
	
	@Override
	public void saveLikes(Long nutri_idx, Long user_idx) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("nutri_like");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx);
		map.put("nutri_idx", nutri_idx);
		insert.execute(map);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		List<User> userList = jdbcTemplate.query("select * from user_ where user_email like ?", userMapper(), email);
		return userList.stream().findAny();
	}

	@Override
	public Optional<User> findByEmailName(String email, String name) {
		List<User> userList = jdbcTemplate.query("select * from user_ where user_email like ? and user_name like ?", userMapper(), email, name);
		return userList.stream().findAny();
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("select * from user_", userMapper());
	}
	
	@Override
	public Optional<UserLikes> findByIdToNutriLike(Long nutri_idx, Long user_idx) {
		List<UserLikes> userLike = jdbcTemplate.query("select nutri_idx from nutri_like where nutri_idx = ? and user_idx = ?", userLikesMapper(), nutri_idx, user_idx);
		return userLike.stream().findAny();
	}
	
	@Override
	public List<UserLikes> userLikes(Long user_idx) {
		return jdbcTemplate.query("select * from nutri_like where user_idx = ?", userLikesMapper(), user_idx);
	}

	@Override
	public Optional<User> updateUser(User update_user) {
		jdbcTemplate.update("update user_ set user_name = ?, user_pwd = ?, user_gender = ?,user_birth = ? where user_email like ?", 
				update_user.getName(), update_user.getPwd(), update_user.getGender(), update_user.getBirth(), update_user.getEmail());
		return findByEmail(update_user.getEmail());
	}

	@Override
	public Optional<User> updatePwd(String user_email, String update_pwd) {
		jdbcTemplate.update("update user_ set user_pwd = ? where user_email = ?", 
				update_pwd, user_email);
		return findByEmail(user_email);
	}
	
	@Override
	public void removeLikes(Long nutri_idx, Long user_idx) {
		jdbcTemplate.update("delete from nutri_like where nutri_idx = ? and user_idx = ?", nutri_idx, user_idx);
	}
}
