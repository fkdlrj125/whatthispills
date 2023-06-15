//package himedia.whatthispills.Repository;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import himedia.whatthispills.Domain.Nutri;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class JDBCNutriRepositoryTest{
//	
//	@Autowired
//	JDBCNutriRepository repository;
//	
//	@Test
//	void 영양제이름검색() {
//		Nutri nutri = new Nutri(
//				"고구마77", 
//				"비타민C6", 
//				"고려은단6",
//				"비타민영양제5",
//				"피로감회복56",
//				"하루에2회45",
//				"과다복용시졸림유발35",
//				"기타는기타에용따량513",
//				"1@3#112");
//		nutri.setIdx(1434211L);
//	repository.saveNutri(nutri);
//	Optional<Nutri> result = repository.findByNameNutri(nutri.getName());
//	assertThat(result.get().getName()).isEqualTo(nutri.getName());
//	}
//	
//}
