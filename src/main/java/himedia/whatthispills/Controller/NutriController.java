package himedia.whatthispills.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String nutriSearch(@RequestParam(required=false) String keyword, Model model) {
	    List<Nutri> nutri = nutriService.findByNameNutri(keyword);
	    model.addAttribute("nutri", nutri);
//	    if (nutri.isEmpty()) {
//	        model.addAttribute("message", "검색 결과가 없습니다.");
//	    } else if (nutri.size() > 1) {
//	        model.addAttribute("nutriSize", nutri.size());
//	    }

	    return "nutri/result";
	}
	
	
	// 상세 페이지
		@GetMapping("/nutri/{nutri_name}")
	    public String nutriAbout(@PathVariable String nutri_name, Model model) {
	        Nutri nutris = nutriService.findByNameInfo(nutri_name).get();
	        model.addAttribute("nutri", nutris);
	        return "nutri/about";
	    }

		
}
