package himedia.whatthispills.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Service.NutriService;

@Controller
public class NutriController {

	private final NutriService nutriService;

	public NutriController(NutriService nutriService) {
		this.nutriService = nutriService;
	}

	// 영양제 검색
	@GetMapping("/nutri/search")
	public String nutriSearch(@RequestParam String keyword, Model model) {
		Optional<Nutri> nutris = nutriService.findByNameNutri(keyword);
		nutris.ifPresent(nutri -> model.addAttribute("nutris", Collections.singletonList(nutri)));
		return "nutri/result";
	}

	// 영양제 결과
	@GetMapping("/nutri/result")
	public String nutriResult() {
		return "nutri/result";
	}

	// 영양제 상세 페이지
	@GetMapping("/nutri/about")
	public String nutriAbout() {
		return "nutri/about";
	}

}
