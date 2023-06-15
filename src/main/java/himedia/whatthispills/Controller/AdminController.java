package himedia.whatthispills.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Service.AdminService;
import himedia.whatthispills.Service.NutriService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	private final NutriService nutriService;
	
	public AdminController(AdminService adminService, NutriService nutriService) {
		this.adminService = adminService;
		this.nutriService = nutriService;
	}
	
	
	// 관리자 -------------------------------------------------------
	
	@GetMapping("/nutri_list")
	public String nutriAll(Model model) {
		List<Nutri> nutri = nutriService.findByAllNutri();
		model.addAttribute("nutri", nutri);
		log.info("컨트롤러");
		return "admin/nutri_list";
	}
	
	@GetMapping("/nutri_add")
	public String nutriAddGet() {
		return "admin/nutri_add";
	}
	
	@PostMapping("/nutri_add")
	public String nutriAddPost(@ModelAttribute Nutri nutri) {
		nutriService.saveNutri(nutri);
		log.info("컨트롤러");
		return "redirect:/admin/nutri_list";
	}
	
	@GetMapping("/nutri_edit/{nutri_idx}")
	public String nutriEditGet(@PathVariable Long nutri_idx, Model model) {
		Nutri nutri = nutriService.findIdNutri(nutri_idx).get();
		model.addAttribute("nutri", nutri);
		return "admin/nutri_edit";
	}
	
	@PostMapping("/nutri_edit/{nutri_idx}")
	public String nutriEditPost(@PathVariable Long nutri_idx, Nutri nutri, Model model) {
		Nutri nutri_edit = nutriService.nutriEdit(nutri_idx, nutri);
		model.addAttribute("nutri", nutri);
		return "redirect:/admin/nutri_list";
	}
	
	@GetMapping("/nutri_remove{nutri_idx}")
	public String nutriRemove(@PathVariable Long nutri_idx) {
		nutriService.removeNutri(nutri_idx);
		return "redirect:/admin/nutri_list";
	}
	

	
}
