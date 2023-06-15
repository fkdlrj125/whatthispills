package himedia.whatthispills.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import himedia.whatthispills.Domain.Admin;

@Repository
public class JDBCAdminRepository implements AdminRepository{
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCAdminRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public RowMapper<Admin> adminMapper(){
		return (ResultSet rs, int rowNum) -> {
			Admin admin = new Admin(
					rs.getString("admin_id"),
					rs.getString("admin_pwd"));
			
			return admin;
		};
	}

	@Override
	public Optional<Admin> findByEmail(String email) {
		List<Admin> adminList = jdbcTemplate.query("select * from admin_ where admin_id like ?", adminMapper(), email);
		return adminList.stream().findAny();
	}
<<<<<<< HEAD

=======
>>>>>>> 1dd594956a84b1922c4d2f7885a443b462951bc5
}
