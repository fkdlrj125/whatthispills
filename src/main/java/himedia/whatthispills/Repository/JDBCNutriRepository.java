package himedia.whatthispills.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import himedia.whatthispills.Domain.Nutri;

@Repository
public class JDBCNutriRepository implements NutriRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCNutriRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public RowMapper<Nutri> nutriMapper() {
		return (ResultSet rs, int rowNum) -> {
			Nutri nutri = new Nutri(
					rs.getString("nutri_name"),
					rs.getString("nutri_category"),
					rs.getString("nutri_company"),
					rs.getString("nutri_base"),
					rs.getString("nutri_effect"),
					rs.getString("nutri_taking"),
					rs.getString("nutri_caution"),
					rs.getString("nutri_etc"),
					rs.getString("nutri_image")
					); 
			nutri.setIdx(rs.getLong("nutri_idx"));
			return nutri;
		};
	}
	
	@Override
	public Nutri saveNutri(Nutri nutri) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("nutri");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nutri_idx", nutri.getIdx());
		map.put("nutri_name", nutri.getName());
		map.put("nutri_category", nutri.getCategory());
		map.put("nutri_company", nutri.getCompany());
		map.put("nutri_base", nutri.getBase());
		map.put("nutri_effect", nutri.getEffect());
		map.put("nutri_taking", nutri.getTaking());
		map.put("nutri_caution", nutri.getCaution());
		map.put("nutri_etc", nutri.getEtc());
		map.put("nutri_image", nutri.getImage());
		insert.execute(map);
		return nutri;
	}
	
	
	@Override
	public Optional<Nutri> findByNameNutri(String name) {
	    List<Nutri> nutriList = jdbcTemplate.query("select * from nutri where nutri_name like ?", nutriMapper(), "%"+name+"%");
	    return nutriList.stream().findAny();
	}
	

	// 관리자 -------------------------------------------------------
	
	@Override
	public List<Nutri> findByAllNutri() {
	    return jdbcTemplate.query("select * from nutri", nutriMapper());
	}
	
	@Override
	public Optional<Nutri> findByIdxNutri(Long nutri_idx) {
		String sql = "select * from nutri where nutri_idx = ?";
		List<Nutri> result = jdbcTemplate.query(sql, nutriMapper(), nutri_idx);
		return result.stream().findAny();
	}


	@Override
	public Optional<Nutri> nutriEditByIdx(Long nutri_idx, Nutri update_nutri) {
		String sql = "update nutri_ set nutri_name = ?, nutri_category = ?, nutri_company = ?, nutri_base = ?, nutri_effect = ?, nutri_taking = ?, nutri_warning = ?, nutri_image = ?, nutri_etc = ? ";
		jdbcTemplate.update(sql, update_nutri.getName(), update_nutri.getCategory(),
				update_nutri.getCompany(), update_nutri.getBase(), update_nutri.getEffect(),
				update_nutri.getTaking(), update_nutri.getCaution(), update_nutri.getImage(),
				update_nutri.getEtc());
		return findByIdxNutri(nutri_idx);
	}

	@Override
	public Optional<Nutri> nutriRemoveByIdx(Long nutri_idx) {
		String sql = "delete from nutri_ where nutri_idx = ?";
		jdbcTemplate.update(sql, nutri_idx);
		return Optional.empty();
	}







}
