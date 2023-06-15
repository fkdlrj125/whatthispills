package himedia.whatthispills.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Repository.NutriRepository;

@Service
public class NutriService {
	private final NutriRepository nutriRepository;

	public NutriService(NutriRepository repository) {
		this.nutriRepository = repository;
	}


	// 이름 검색
	public Optional<Nutri> findByNameNutri(String name) {
<<<<<<< HEAD
		return nutriRepository.findByNameNutri(name);
=======
		return repository.findByNameNutri(name);
	}
	
	// 아이디 중복 검색
	public List<Nutri> findByIdNutri(Long idx) {
		return repository.findByIdNutri(idx);
>>>>>>> 1dd594956a84b1922c4d2f7885a443b462951bc5
	}

	// 관리자 -------------------------------------------------------
	
	// 등록된 영양제의 전체 리스트
	public List<Nutri> findByAllNutri() {
		return nutriRepository.findByAllNutri();
	}
	
	// 영양제 저장
	public Nutri saveNutri(Nutri nutri) {
		return nutriRepository.saveNutri(nutri);
	}

<<<<<<< HEAD
	// 인덱스로 검색
	public Optional<Nutri> findIdNutri(Long nutri_idx) {
		return nutriRepository.findByIdxNutri(nutri_idx);
	}
=======
>>>>>>> 1dd594956a84b1922c4d2f7885a443b462951bc5

	// 영양제 수정
	public Optional<Nutri> nutriEdit(Long nutri_idx, Nutri nutri) {
		return nutriRepository.nutriEditByIdx(nutri_idx, nutri);
	}
	
	// 영양제 삭제
	public Optional<Nutri> removeNutri(Long nutri_idx) {
		return nutriRepository.nutriRemoveByIdx(nutri_idx);
	}


}
