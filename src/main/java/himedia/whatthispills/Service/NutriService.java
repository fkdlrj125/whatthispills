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
		
		if(age == 0) {
			age++;
			age *= 10;
		} else {
			age /= 10;
			age *= 10;
		}
		
		String user_age = age <= 60 ? age + "대" : age + "대 이상";
		
		switch(gender) {
		case "male":
			gender = "남자";
			break;
			
		case "female":
			gender= "여자";
			break;
			
		case "nan":
			gender = "전체";
			user_age = "전체";
		}
		
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
	public Nutri saveNutri(Nutri nutri, MultipartFile file) throws Exception {
		String upload_dir = "/src/main/resources/static/img/nutri/" + nutri.getCategory() + "/";
		String file_name =  nutri.getIdx() + ".jpg";
		String project_path = System.getProperty("user.dir") + upload_dir;
		log.info(System.getProperty("user.dir"));
		File save_file = new File(project_path, file_name);
		file.transferTo(save_file);
		
		
		
		return nutriRepository.save(nutri);
	}
	
//	private String upload(MultipartFile uploadFile,String type) throws IOException {
//  String origName = uploadFile.getOriginalFilename();
//  log.info(origName);
//  if(!origName.isEmpty()) {
//      // 확장자를 찾기 위한 코드
//      final String ext = origName.substring(origName.lastIndexOf('.'));
//
//      // 파일 객체 생성
//      // System.getProperty => 시스템 환경에 관한 정보를 얻을 수 있다. (user.dir = 현재 작업 디렉토리를 의미함)
//      File file = new File(System.getProperty("user.dir") + saveFileName);
//      // 파일 변환
//      uploadFile.transferTo(file);
//      // S3 파일 업로드
//      uploadOnS3(saveFileName, file, type);
//      // 파일 삭제
//      file.delete();
//      // 파일이름 리턴
//      return type+"/"+saveFileName;
//  }
//  return "";
//}

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
