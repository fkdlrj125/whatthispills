package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import himedia.whatthispills.Domain.Nutri;

public interface NutriRepository {


	// 영양제 이름으로 찾기
	List<Nutri> findByNameNutri(Object name);


	// 관리자에서 사용 ----------------------------------------
	// 영양제 전체 조회
	List<Nutri> findAllNutri();
	
	// 영양제 검색
	List<Nutri> search(Object search);

	// 영양제 추가
	Nutri saveNutri(Nutri nutri);
	
	// 영양제 번호로 찾기
	Optional<Nutri> findByIdxNutri(Long nutri_idx);
	
	// 영양제 수정
	Nutri update(Long nutri_idx, Nutri update_nutri);
	
	// 영양제 삭제
	Optional<Nutri> delete(Long nutri_idx);

	Optional<Nutri> nutriRemoveByIdx(Long nutri_idx);

}
