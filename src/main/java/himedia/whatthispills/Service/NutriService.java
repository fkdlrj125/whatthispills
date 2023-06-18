package himedia.whatthispills.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Repository.NutriRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NutriService {
	private final NutriRepository nutriRepository;

	public NutriService(NutriRepository repository) {
		this.nutriRepository = repository;
	}

	// 이름 검색
	public List<Nutri> findByNameNutri(String keyword) {
		return nutriRepository.findByNameNutri(keyword);
	}
	// 상세페이지(이름로 검색)
	public Optional<Nutri> findByNameInfo(String nutri_name) {
		System.out.println("nutri_name 서비스"+ nutri_name);
		return nutriRepository.findByNameInfo(nutri_name);
	}
	
	// 관리자 -------------------------------------------------------

	// 등록된 영양제의 전체 리스트
	public List<Nutri> findByAllNutri() {
		return nutriRepository.findAllNutri();
	}

	// 영양제 저장
	public Nutri saveNutri(Nutri nutri) {
		return nutriRepository.saveNutri(nutri);
	}

	// 인덱스로 검색
	public Optional<Nutri> findIdNutri(Long nutri_idx) {
		return nutriRepository.findByIdxNutri(nutri_idx);
	}

	public List<Nutri> searchNutri(Object search) {
		log.info("서비스");
		return nutriRepository.search(search);

	}

	// 영양제 수정
	public Nutri nutriEdit(Long nutri_idx, Nutri nutri) {
		return nutriRepository.update(nutri_idx, nutri);
	}

	// 영양제 삭제
	public Optional<Nutri> removeNutri(Long nutri_idx) {
		return nutriRepository.delete(nutri_idx);
	}

}
