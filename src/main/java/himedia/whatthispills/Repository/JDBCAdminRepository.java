package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import himedia.whatthispills.Domain.NutriDomain;

@Repository
public class JDBCAdminRepository implements AdminRepository{
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCAdminRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<NutriDomain> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<NutriDomain> findByIdx(Long nutri_idx) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<NutriDomain> findByName(String nutri_name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public NutriDomain addNutri(NutriDomain nutri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NutriDomain editNutri(Long nutri_idx, NutriDomain nutri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeNutri(Long nutri_idx) {
		// TODO Auto-generated method stub
		
	}




}
