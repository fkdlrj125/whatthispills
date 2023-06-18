package himedia.whatthispills.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import himedia.whatthispills.Domain.Nutri;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JDBCNutriRepository implements NutriRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public JDBCNutriRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public RowMapper<Nutri> nutriMapper() {
		return (ResultSet rs, int rowNum) -> {
			Nutri nutri = new Nutri(
					rs.getLong("nutri_idx"),
					rs.getString("nutri_name"),
					rs.getString("nutri_category"),
					rs.getString("nutri_company"),
					rs.getString("nutri_base"),
					rs.getString("nutri_effect"),
					rs.getString("nutri_taking"),
					rs.getString("nutri_caution"),
					rs.getString("nutri_image"),
					rs.getString("nutri_etc")
					);
			return nutri;
		};
	}
	
	@Override
	public Nutri saveNutri(Nutri nutri) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("nutri_");
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
		log.info("리파지토리", nutri);
		return nutri;
	}
	
	
	@Override
	public List<Nutri> findByNameNutri(Object name) {
		String queryM = "SELECT * FROM nutri_ WHERE nutri_name LIKE ?";
		List<Nutri> nutriList = jdbcTemplate.query(queryM,
				nutriMapper(), "%" + name + "%");
		System.out.println("name 말:" + name);
		System.out.println("nutriList: " + nutriList);
		return nutriList;
	}
	

	// 관리자 -------------------------------------------------------
	
	@Override
	public List<Nutri> findAllNutri() {
		String sql = "select * from nutri_";
		List<Nutri> result = jdbcTemplate.query(sql, nutriMapper());
		return result;
	}
	
	
	@Override
	public Optional<Nutri> findByIdxNutri(Long nutri_idx) {
		String sql = "select * from nutri_ where nutri_idx = ?";
		List<Nutri> result = jdbcTemplate.query(sql, nutriMapper(), nutri_idx);
		return result.stream().findAny();
	}
	
	@Override
	public List<Nutri> search(Object search) {
		String sql = "select * from nutri_ where nutri_idx like ? or nutri_name like ?";
		String word = "%" + search + "%";
		List<Nutri> result = jdbcTemplate.query(sql, nutriMapper(), word, word);
		log.info("실행 결과 result >> ", result);
		return result;
	}


	@Override
	public Nutri update(Long nutri_idx, Nutri update_nutri) {
		String sql = "update nutri_ set nutri_name = ?, nutri_category = ?, nutri_company = ?, nutri_base = ?, nutri_effect = ?, nutri_taking = ?, nutri_caution= ?, nutri_image = ?, nutri_etc = ? where nutri_idx = ? " ;
		jdbcTemplate.update(sql, update_nutri.getName(), update_nutri.getCategory(),
				update_nutri.getCompany(), update_nutri.getBase(), update_nutri.getEffect(),
				update_nutri.getTaking(), update_nutri.getCaution(), update_nutri.getImage(),
				update_nutri.getEtc(), nutri_idx);
		update_nutri.setIdx(nutri_idx);
		return findByIdxNutri(nutri_idx).get();
	}

	@Override
	public Optional<Nutri> delete(Long nutri_idx) {
		String sql = "delete from nutri_ where nutri_idx = ?";
		jdbcTemplate.update(sql, nutri_idx);
		return Optional.empty();
	}


	@Override
	public Optional<Nutri> nutriRemoveByIdx(Long nutri_idx) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
