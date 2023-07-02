package himedia.whatthispills.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Domain.NutriRec;
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
		return nutriRepository.findByNameInfo(nutri_name);
	}

	// 카테고리 이동
	public List<Nutri> findByCategory(String keyword) {
		return nutriRepository.findByCategory(keyword);
	}
	
	// 추천
	public Optional<NutriRec> recGenderAge(String gender, String birth) {
		LocalDate now = LocalDate.now();
		LocalDate user_date = LocalDate.parse(birth);
		int age = now.getYear() - user_date.getYear();
		
		if(age / 10 == 0) {
			age /= 10;
			age++;
			age *= 10;
		} else {
			age /= 10;
			age *= 10;
		}
		
		String user_age = age < 60 ? (age + "대") : (age + "대 이상");
		
		return nutriRepository.findByGenderAge(gender, user_age);
	}
	
	public Optional<NutriRec> recforAll() {
		return nutriRepository.findRecforAll();
	}

	// 관리자 -------------------------------------------------------

	// 등록된 영양제의 전체 리스트
	public List<Nutri> findByAllNutri() {
		return nutriRepository.findAllNutri();
	}

	// 영양제 저장
	public Nutri saveNutri(Nutri nutri, String image) throws Exception {
		if(!image.isBlank()) {
			nutri.setImage(image);
		}
		return nutriRepository.save(nutri);
	}
	
	public Optional<Nutri> findIdxNutri(Long nutri_idx) {
		return nutriRepository.findByIdxNutri(nutri_idx);
	}

	public List<Nutri> searchNutri(Object search) {
		return nutriRepository.search(search);
	}

	public Nutri nutriEdit(Long nutri_idx, Nutri nutri) {
		return nutriRepository.update(nutri_idx, nutri);
	}

	public Optional<Nutri> removeNutri(Long nutri_idx) {
		return nutriRepository.delete(nutri_idx);
	}
	
	public Long checkIdx(String check_idx) {
		Long result = nutriRepository.checkIdx(check_idx);
		return result;
	}

}
