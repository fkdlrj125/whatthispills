package himedia.whatthispills.Repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import himedia.whatthispills.Domain.Nutri;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
class JDBCNutriRepositoryTest{
	
	@Autowired
	JDBCNutriRepository nutriRepository,repository;
	
	
	@Test
	void 이름검색에서상세페이지() {
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
		List<Nutri> result = repository.findByNameNutri("영양제1");
		assertThat(result.get(0).getName()).isEqualTo(nutri.getName());
		
		Optional<Nutri> result1 = repository.findByNameInfo("영양제1");
		assertThat(result1.get().getName()).isEqualTo(nutri.getName());
	}
	@Test
	void 카테고리() {
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
		List<Nutri> result = repository.findByCategory("영양제2");
		assertThat(result.get(0).getName()).isEqualTo(nutri.getName());
	}
	
	
	
	
	
	@Test
	void 영양제저장() {
		Nutri nutri = new Nutri(123456L, "비타민C1000", "비타민C", "고려금단", "알약", 
				"하루세번식후30분", "면역력증가", "테스트", "테스트", "테스트", "테스트");
		log.info("nutri name >> {}", nutri.getName());
		Nutri save_nutri = nutriRepository.save(nutri);
		log.info("saveNutri name >> {}", save_nutri.getName());
		assertThat(nutri.getName()).isEqualTo(save_nutri.getName());
	}
	
	@Test
	void 영양제수정() {
		Nutri before = new Nutri(123456L, "비타민C1000", "비타민C", "고려금단", "알약", 
				"하루세번식후30분", "면역력증가", "테스트", "테스트", "테스트", "테스트");
		nutriRepository.save(before);
		Nutri after = nutriRepository.update(before.getIdx(), new Nutri(123456L, "비타민C500", "비타민C", 
				"고려은단", "알약", "비타민C", "하루세번식후30분", "면역력증가", "테스트", "테스트", "테스트"));
		log.info("before >> {}, {}", before.getName(), before.getCompany());
		log.info("after >> {}, {}", after.getName(), after.getCompany());
		assertThat(before.getIdx()).isEqualTo(after.getIdx());
	}
	
	@Test
	void 영양제전체조회() {
		Nutri nutri_one = new Nutri(1000L, "테스트1", "테스트1", "테스트1", "테스트1", 
				"테스트1", "테스트1", "테스트1", "테스트1", "테스트1", "테스트1");
		Nutri nutri_two = new Nutri(2000L, "테스트2", "테스트2", "테스트2", "테스트2", 
				"테스트2", "테스트2", "테스트2", "테스트2", "테스트2", "테스트2");
		nutriRepository.save(nutri_one);
		nutriRepository.save(nutri_two);
		int result = nutriRepository.findAllNutri().size();
		log.info("nutri size >> {}", result);
		assertThat(result).isEqualTo(2);
	}
	
	@Test
	void 영양제분류코드조회() {
		Nutri nutri = new Nutri(123456L, "비타민C1000", "비타민C", "고려금단", "알약", 
				"하루세번식후30분", "면역력증가", "테스트", "테스트", "테스트", "테스트");
		Nutri save_nutri = nutriRepository.save(nutri);
		Optional<Nutri> result = nutriRepository.findByIdxNutri(nutri.getIdx());
		log.info("idx >> {}", result.get().getIdx());
		assertThat(result.get().getIdx()).isEqualTo(save_nutri.getIdx());
	}
	
	@Test
	void 영양제검색() {
		Nutri nutri_one = new Nutri(1000L, "테스트1", "테스트1", "테스트1", "테스트1", 
				"테스트1", "테스트1", "테스트1", "테스트1", "테스트1", "테스트1");
		Nutri nutri_two = new Nutri(2000L, "테스트2", "테스트2", "테스트2", "테스트2", 
				"테스트2", "테스트2", "테스트2", "테스트2", "테스트2", "테스트2");
		Nutri nutri_three = new Nutri(3000L, "테스트3", "테스트3", "테스트3", "테스트3", 
				"테스트3", "테스트3", "테스트3", "테스트3", "테스트3", "테스트3");
		nutriRepository.save(nutri_one);
		nutriRepository.save(nutri_two);
		nutriRepository.save(nutri_three);
		List<Nutri> result = nutriRepository.search("테스트");
		log.info("result.size() >> {}", result.size());
		assertThat(result.size()).isEqualTo(3);
	}
	
	@Test
	void 영양제삭제() {
		List<Nutri> before_delete = nutriRepository.findAllNutri();
		Nutri nutri = new Nutri(1000L, "테스트1", "테스트1", "테스트1", "테스트1",
				"테스트1", "테스트1", "테스트1", "테스트1", "테스트1", "테스트1");
		nutriRepository.save(nutri);
		Long nutri_idx = nutri.getIdx();
		nutriRepository.delete(nutri_idx);
		List<Nutri> after_delete = nutriRepository.findAllNutri();
		assertThat(before_delete.size()).isEqualTo(after_delete.size());
	}
}

