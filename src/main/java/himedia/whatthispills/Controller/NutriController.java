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
	    System.out.println("nutri" + nutri);
	    System.out.println("keyword" + keyword);
//	    if (nutri.isEmpty()) {
//	        model.addAttribute("message", "검색 결과가 없습니다.");
//	    } else if (nutri.size() > 1) {
//	        model.addAttribute("nutriSize", nutri.size());
//	    }
	    return "nutri/result";
	}
	
	// 영양제 결과
//		@GetMapping("/nutri/result")
//		public String nutriResult() {
//		    return "nutri/result";
//		}

		@GetMapping("/nutri/{nutri_idx}")
		public String nutriAbout(@PathVariable Long nutri_idx, Model model ) {
		    Nutri nutri = nutriService.findByIdNutri(nutri_idx).get();
		    System.out.println("nutri_idx:??"+nutri_idx);
		    model.addAttribute("nutri", nutri);
		    return "nutri/about";
		}

		
}
