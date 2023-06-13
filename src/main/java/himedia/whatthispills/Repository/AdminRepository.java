package himedia.whatthispills.Repository;

import java.util.List;
import java.util.Optional;

import himedia.whatthispills.Domain.NutriDomain;

public interface AdminRepository {
	
	// 영양제 관리(조회)
	List<NutriDomain> findAll();
	
	// 영양제 관리(검색)
	Optional<NutriDomain> findByIdx(Long nutri_idx);
	Optional<NutriDomain> findByName(String nutri_name);

	// 영양제 관리(추가)
	NutriDomain addNutri(NutriDomain nutri);
	
	// 영양제 관리(수정)
	NutriDomain editNutri(Long nutri_idx, NutriDomain nutri);
	
	// 영양제 관리(삭제)
	void removeNutri(Long nutri_idx);
	
	
}
