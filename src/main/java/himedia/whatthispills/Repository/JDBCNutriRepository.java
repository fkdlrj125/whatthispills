package himedia.whatthispills.Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import himedia.whatthispills.Domain.Nutri;

@Repository
public class JDBCNutriRepository implements NutriRepository {
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public RowMapper<Nutri> nutriMapper() {
		return (ResultSet rs, int rowNum) -> {
			Nutri nutri = new Nutri(
					rs.getString("name"),
					rs.getString("category"),
					rs.getString("company"),
					rs.getString("base"),
					rs.getString("effect"),
					rs.getString("taking"),
					rs.getString("warning"),
					rs.getString("etc"),
					rs.getString("image")
					);
			return nutri;
		};
	}
	
	@Override
	public Nutri saveNutri(Nutri nutri) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("nutri_").usingGeneratedKeyColumns("nutri_idx");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nutri_name", nutri.getName());
		map.put("nutri_category", nutri.getCategory());
		map.put("nutri_company", nutri.getCompany());
		map.put("nutri_base", nutri.getBase());
		map.put("nutri_effect", nutri.getEffect());
		map.put("nutri_taking", nutri.getTaking());
		map.put("nutri_warning", nutri.getWarning());
		map.put("nutri_etc", nutri.getEtc());
		map.put("nutri_image", nutri.getImage());
		Number result = insert.executeAndReturnKey(new MapSqlParameterSource(map));
		nutri.setIdx(result.longValue());
		return null;
	}
	
	
	@Override
	public Optional<Nutri> findByNameNutri(String name) {
	    List<Nutri> nutriList = jdbcTemplate.query("select * from nutri where name like ?", nutriMapper(), "%"+name+"%");
	    return nutriList.stream().findAny();
	}

	@Override
	public List<Nutri> findByAllNutri() {
	    return jdbcTemplate.query("select * from nutri", nutriMapper());
	}
	
	@Override
	public List<Nutri> findByIdNutri(Long idx) {
		return jdbcTemplate.query("select * from nutri where id like ?", nutriMapper(), idx);
	}


}
