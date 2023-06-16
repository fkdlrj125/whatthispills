package himedia.whatthispills.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import himedia.whatthispills.Domain.Admin;
import himedia.whatthispills.Domain.Nutri;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class JDBCAdminRepository implements AdminRepository{
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCAdminRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public RowMapper<Admin> adminMapper() {
		return (ResultSet rs, int rowNum) -> {
			Admin admin = new Admin(
					rs.getString("admin_id"),
					rs.getString("admin_pwd"));
			admin.setIdx(rs.getLong("admin_idx"));
			return admin;
		};
	}

	@Override
	public Optional<Admin> findByEmail(String id) {
		List<Admin> adminList = jdbcTemplate.query("select * from admin_ where admin_id like ?", adminMapper(), id);
		return adminList.stream().findAny();
	}
}
