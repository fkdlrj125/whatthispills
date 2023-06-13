package himedia.whatthispills.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Service.NutriService;

@Controller
public class NutriController {

	private final NutriService nutriService;

	@Autowired
	public NutriController(NutriService nutriService) {
		this.nutriService = nutriService;
	}

	// 영양제 검색
	@GetMapping("/nutri/search")
	public String nutriSearch(@RequestParam(required = false) String keyword, Model model) {

		Optional<Nutri> nutris = nutriService.findByNameNutri(keyword);
		if (keyword != null)
			nutris = nutriService.findByNameNutri(keyword);
		model.addAttribute("nutris", nutris);
		return "nutri/result";
	}
	
//	 @PostMapping("/nutri/check")
//	    @ResponseBody
//	    public String checkNutri(@RequestParam("classifi_code") String classifiCode) {
//	        List<Nutri> nutris = nutriService.findByIdNutri(classifiCode);
//	        if (nutris.isEmpty()) {
//	            return "available";  // 중복 없음
//	        } else {
//	            return "duplicate";  // 중복 있음
//	        }
//	    }
	
	
	// 영양제 결과
	@GetMapping("/nutri/result")
	public String nutriResult(Model model) {
		List<Nutri> nutrits = nutriService.findByAllNutri();
		model.addAttribute("nutrits", nutrits);
		return "nutri/about";
	}

	// 영양제 상세 페이지
	@GetMapping("/nutri/about")
	public String nutriAbout() {
		return "nutri/about";
	}

	// 상품 추천
	@GetMapping("/nutri/result")
	public String nutriRecommend(@RequestParam("userId") String userId, Model model) {
		List<Nutri> recommendedProducts = nutriService.getRecommendedProducts(userId);
		model.addAttribute("recommendedProducts", recommendedProducts);
		return "nutri/recommendation";
	}
//	// 영양제 히스토리 추가
//		@GetMapping("/nutri/history/add")
//		public String addToHistory(@RequestParam("userId") String userId, @RequestParam("nutriId") Long nutriId) {
//			Nutri nutri = nutriService.findNutriById(nutriId);
//			nutriService.addProductToHistory(userId, nutri);
//			return "redirect:/nutri/about";
//		}
//
//		// 영양제 히스토리 조회
//		@GetMapping("/nutri/history")
//		public String nutriHistory(@RequestParam("userId") String userId, Model model) {
//			List<Nutri> history = nutriService.getHistory(userId);
//			model.addAttribute("history", history);
//			return "nutri/history";
//		}
//
//		// 영양제 히스토리 초기화
//		@GetMapping("/nutri/history/clear")
//		public String clearHistory(@RequestParam("userId") String userId) {
//			nutriService.clearHistory(userId);
//			return "redirect:/nutri/history";
//		}
}
