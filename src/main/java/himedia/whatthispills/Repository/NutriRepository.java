package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import himedia.whatthispills.Domain.Nutri;

public interface NutriRepository {

	// 저장 영양제
	Nutri saveNutri(Nutri nutri);

	// 영양제 이름으로 찾기
	Optional<Nutri> findByNameNutri(String name);

	// 영양제 전체 찾기
	List<Nutri> findByAllNutri();
	
	// 영양제 중복 아이디 찾기
	List<Nutri> findByIdNutri(Long idx);

	// 영양제 추천
	List<Nutri> getRecommendedProducts(String userId);

	// 영양제 history 추가
	void addProductToHistory(String userId, Nutri nutri);

	// 영양제 history
	List<Nutri> getHistory(String userId);

	// 영양제 history 지우기
	void clearHistory(String userId);


}
