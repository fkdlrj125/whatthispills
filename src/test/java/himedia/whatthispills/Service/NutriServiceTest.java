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
				"영양제2",
				"영양제3",
				"영양제4",
				"영양제5",
				"영양제6",
				"영양제7",
				"영양제8",
				"영양제9",
				"영양제11"
				);
		repository.save(nutri); //give
		List<Nutri> result = service.findByNameNutri("영양제"); //when
		assertThat(result.equals(nutri)); //
	}
	
	@Test
	void 상세페이지() {
		Nutri nutri = new Nutri(
				1235L,
				"영양제1",
				"영양제2",
				"영양제3",
				"영양제4",
				"영양제5",
				"영양제6",
				"영양제7",
				"영양제8",
				"영양제9",
				"영양제11"
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
				"영양제2",
				"영양제3",
				"영양제4",
				"영양제5",
				"영양제6",
				"영양제7",
				"영양제8",
				"영양제9",
				"영양제11"
				);
		repository.save(nutri);
		List<Nutri> result = service.findByCategory("영양");
		assertThat(result.equals(nutri));
	}
	
}
	
	

