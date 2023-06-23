package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Domain.NutriRec;
import himedia.whatthispills.Domain.UserLikes;

public interface NutriRepository {

	// 영양제 이름으로 찾기
	List<Nutri> findByNameNutri(Object name);

	// 영양제 이름로 찾기(상세페이지)
	Optional<Nutri> findByNameInfo(Object nutri_name);

	// 카테고리 이동
	List<Nutri> findByCategory(Object keyword);
	
	// 추천
	public Optional<NutriRec> findByGenderAge(String gender, String age);
	
	public Optional<NutriRec> findRecforAll();
	
	// 영양제 전체 조회
	List<Nutri> findAllNutri();

	// 영양제 검색
	List<Nutri> search(Object search);

	// 영양제 추가
	Nutri save(Nutri nutri);

	// 영양제 번호로 찾기
	Optional<Nutri> findByIdxNutri(Long nutri_idx);

	// 영양제 수정
	Nutri update(Long nutri_idx, Nutri update_nutri);

	// 영양제 삭제
	Optional<Nutri> delete(Long nutri_idx);
	
	// 영양제 분류코드 중복검사
	Long checkIdx(String check_idx);

}
