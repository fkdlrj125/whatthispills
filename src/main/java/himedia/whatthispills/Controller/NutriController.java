package himedia.whatthispills.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Service.NutriService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NutriController {

	private final NutriService nutriService;

	public NutriController(NutriService nutriService) {
		this.nutriService = nutriService;
	}

	// 영양제 검색
	@GetMapping("/nutri/search")
	public String nutriSearch(String keyword, Model model) {
		Optional<Nutri> nutris = nutriService.findByNameNutri(keyword);
		
			model.addAttribute("nutris", nutris);
			return "nutri/result";
	}
	// 영양제 결과
	@GetMapping("/nutri/result")
	public String nutriResult(Model model) {
		List<Nutri> nutrits = nutriService.findByAllNutri();
		model.addAttribute("nutrits", nutrits);
		return "nutri/about";
	}

	// 영양제 상세 페이지
	@GetMapping("/nutri/about")
	public String nutriAbout(@RequestParam String keyword, Model model) {
		List<Nutri> nutrits = nutriService.findByAllNutri();
		model.addAttribute("nutrits", nutrits);
		
		return "nutri/about";
	}
	
	// 관리자 -------------------------------------------------------
	
	@GetMapping("/admin/nutri_list")
	public String nutriAll(Model model) {
		List<Nutri> nutri = nutriService.findByAllNutri();
		model.addAttribute("nutri", nutri);
		log.info("컨트롤러");
		return "admin/nutri_list";
	}
	
	@GetMapping("/admin/nutri_add")
	public String nutriAddGet() {
		return "admin/nutri_add";
	}
	
	@PostMapping("/admin/nutri_add")
	public String nutriAddPost(@ModelAttribute Nutri nutri) {
		nutriService.saveNutri(nutri);
		log.info("컨트롤러");
		return "redirect:/admin/nutri_list";
	}
	
	@GetMapping("/admin/nutri_edit/{nutri_idx}")
	public String nutriEditGet(@PathVariable Long nutri_idx, Model model) {
		Nutri nutri = nutriService.findIdNutri(nutri_idx).get();
		model.addAttribute("nutri", nutri);
		return "admin/nutri_edit";
	}
	
	@PostMapping("/admin/nutri_edit/{nutri_idx}")
	public String nutriEditPost(@PathVariable Long nutri_idx, Nutri nutri, Model model) {
		Nutri nutri_edit = nutriService.nutriEdit(nutri_idx, nutri).get();
		return "redirect:/admin/nutri_list";
	}
	
	@GetMapping("/admin/nutri_remove{nutri_idx}")
	public String nutriRemove(@PathVariable Long nutri_idx) {
		nutriService.removeNutri(nutri_idx);
		return "redirect:/admin/nutri_list";
	}
	
}
