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
import himedia.whatthispills.Domain.NutriRec;
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
	            rs.getString("nutri_shape"),
	            rs.getString("nutri_base"),
	            rs.getString("nutri_taking"),
	            rs.getString("nutri_effect"),
	            rs.getString("nutri_caution"),
	            rs.getString("nutri_type"),
	            null 
	        );

	        String imagePath = rs.getString("nutri_image");
	        if (imagePath != null && !imagePath.isEmpty()) {
	            nutri.setImage(imagePath);
	        }

	        return nutri;
	    };
	}
	
	
	public RowMapper<NutriRec> nutriRecMapper() {
		return (ResultSet rs, int rowNum) -> {
			NutriRec nutriRec = new NutriRec(
					rs.getString("gender"),
					rs.getString("age"),
					rs.getLong("recommend1"),
					rs.getLong("recommend2"),
					rs.getLong("recommend3"));
			return nutriRec;
		};
	}

	@Override
	public Nutri save(Nutri nutri) {
	    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("nutri_");
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("nutri_idx", nutri.getIdx());
	    map.put("nutri_name", nutri.getName());
	    map.put("nutri_category", nutri.getCategory());
	    map.put("nutri_company", nutri.getCompany());
	    map.put("nutri_shape", nutri.getShape());
	    map.put("nutri_base", nutri.getBase());
	    map.put("nutri_taking", nutri.getTaking());
	    map.put("nutri_effect", nutri.getEffect());
	    map.put("nutri_caution", nutri.getCaution());
	    map.put("nutri_type", nutri.getType());
	    map.put("nutri_image", nutri.getImage());
	    insert.execute(map);
	    
	    return nutri;
	}

	// 제품명 검색
	@Override
	public List<Nutri> findByNameNutri(Object name) {
		List<Nutri> nutriList = jdbcTemplate.query("SELECT * FROM nutri_ WHERE nutri_name LIKE ?",
				nutriMapper(), "%" + name + "%");
		return nutriList;
	}

	// 검색에서 상세 페이지
	@Override
	public Optional<Nutri> findByNameInfo(Object nutri_name) {
		List<Nutri> result = jdbcTemplate.query("select * from nutri_ where nutri_name = ?", nutriMapper(), nutri_name);
		return result.stream().findAny();
	}

	// 리스트에서 카테고리 페이지
	public List<Nutri> findByCategory(Object category) {
		List<Nutri> categoryList = jdbcTemplate.query("SELECT * FROM nutri_ WHERE nutri_category LIKE ?", nutriMapper(),
				"%" + category + "%");
		return categoryList;
	}
	
	// 추천
	@Override
	public Optional<NutriRec> findByGenderAge(String gender, String age) {
		List<NutriRec> rec_list = jdbcTemplate.query("select * from nutri_recommend where gender = ? and age = ?",
				nutriRecMapper(), gender, age);
		return rec_list.stream().findAny();
		
	}
	
	@Override
	public Optional<NutriRec> findRecforAll() {
		List<NutriRec> rec_list = jdbcTemplate.query("select * from nutri_recommend where gender = '전체' and age = '전체'",
				nutriRecMapper());
		return rec_list.stream().findAny();
	}
	
	
	// admin ===============================================================

	@Override
	public Nutri update(Long nutri_idx, Nutri update_nutri) {
		String sql = "update nutri_ set nutri_name = ?, nutri_category = ?, nutri_company = ?, nutri_shape = ?, nutri_base = ?, nutri_taking = ?, nutri_effect = ?, nutri_caution= ?, nutri_type = ?, nutri_image = ? where nutri_idx = ? ";
		jdbcTemplate.update(sql, update_nutri.getName(), update_nutri.getCategory(),
				update_nutri.getCompany(), update_nutri.getShape(), update_nutri.getBase(), update_nutri.getTaking(),
				update_nutri.getEffect(), update_nutri.getCaution(), update_nutri.getType(),
				update_nutri.getImage(), nutri_idx);
		update_nutri.setIdx(nutri_idx);
		return findByIdxNutri(nutri_idx).get();
	}

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
	public Optional<Nutri> delete(Long nutri_idx) {
		String sql = "delete from nutri_ where nutri_idx = ?";
		jdbcTemplate.update(sql, nutri_idx);
		return Optional.empty();
	}

	@Override
	public Long checkIdx(String check_idx) {
		String sql = "select count(*) from nutri_ where nutri_idx = ?";
		Long result = jdbcTemplate.queryForObject(sql, Long.class, check_idx);
		return result;
	}

}
