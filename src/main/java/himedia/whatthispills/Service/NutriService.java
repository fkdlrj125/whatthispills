package himedia.whatthispills.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Repository.NutriRepository;

@Service
public class NutriService {
	private final NutriRepository repository;

	public NutriService(NutriRepository repository) {
		this.repository = repository;
	}

	// 저장된 영양제
	public Nutri saveNutri(Nutri nutri) {
		return repository.saveNutri(nutri);
	}

	// 이름 검색
	public Optional<Nutri> findByNameNutri(String name) {
		return repository.findByNameNutri(name);
	}
	
	// 아이디 중복 검색
	public List<Nutri> findByIdNutri(Long idx) {
		return repository.findByIdNutri(idx);
	}

	// 전체 리스트
	public List<Nutri> findByAllNutri() {
		return repository.findByAllNutri();
	}


}
