package himedia.whatthispills.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Repository.JDBCNutriRepository;

@SpringBootTest
@Transactional
public class NutriServiceTest {
	@Autowired
	NutriService service;
	@Autowired
	JDBCNutriRepository repository;
	
	
	@Test
	void 이름검색() {
		Nutri nutri = new Nutri(
				1235L,
				"영양제1",
				"노랑",
				"파랑",
				"빨강",
				"초록",
				"남색",
				"보라",
				"검은색",
				"핑크색",
				"베이지"
				);
		repository.save(nutri); 
		List<Nutri> result = service.findByNameNutri("영양제1"); 
		assertThat(result.equals(nutri)); 
	}
	
	@Test
	void 상세페이지() {
		Nutri nutri = new Nutri(
				1235L,
				"영양제1",
				"노랑",
				"파랑",
				"빨강",
				"초록",
				"남색",
				"보라",
				"검은색",
				"핑크색",
				"베이지"
				);
		repository.save(nutri);
		Optional<Nutri> result = service.findByNameInfo("영양제1");
		assertThat(result.equals(nutri));
	}
	@Test
	void 카테고리이동() {
		Nutri nutri = new Nutri(
				1235L,
				"영양제1",
				"노랑",
				"파랑",
				"빨강",
				"초록",
				"남색",
				"보라",
				"검은색",
				"핑크색",
				"베이지"
				);
		repository.save(nutri);
		List<Nutri> result = service.findByCategory("파랑");
		assertThat(result.equals(nutri));
	}
	
}
	
	

