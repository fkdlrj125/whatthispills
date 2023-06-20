package himedia.whatthispills.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Service.NutriService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	private final NutriService nutriService;

	public AdminController(NutriService nutriService) {
		this.nutriService = nutriService;
	}

	@GetMapping("/nutri_list")
	public String nutriAll(Model model) {
		List<Nutri> nutri = nutriService.findByAllNutri();
		model.addAttribute("nutri", nutri);
		return "admin/nutri_list";
	}

	@GetMapping("/nutri_search")
	public String adminSearch(@RequestParam Object search, Model model) {
		List<Nutri> nutri = nutriService.searchNutri(search);
		model.addAttribute("nutri", nutri);
		model.addAttribute("search", search);
		return "admin/nutri_search";
	}

	@GetMapping("/nutri_add")
	public String nutriAddGet() {
		return "admin/nutri_add";
	}

	@PostMapping("/nutri_add")
	public String nutriAddPost(@ModelAttribute Nutri nutri, MultipartFile file) throws Exception {
	    nutriService.saveNutri(nutri, file);
	    return "redirect:/admin/nutri_list";
	}
	

	@GetMapping("/nutri_edit/{nutri_idx}")
	public String nutriEditGet(@PathVariable Long nutri_idx, Model model) {
		Nutri nutri = nutriService.findIdxNutri(nutri_idx).get();
		model.addAttribute("nutri", nutri);
		return "admin/nutri_edit";
	}

	@PostMapping("/nutri_edit/{nutri_idx}")
	public String nutriEditPost(@PathVariable Long nutri_idx, Nutri nutri, Model model) {
		Nutri nutri_edit = nutriService.nutriEdit(nutri_idx, nutri);
		model.addAttribute("nutri", nutri_edit);
		return "redirect:/admin/nutri_list";
	}

	@GetMapping("/nutri_remove/{nutri_idx}")
	public String nutriRemove(@PathVariable Long nutri_idx) {
		nutriService.removeNutri(nutri_idx);
		return "redirect:/admin/nutri_list";
	}
	
	@ResponseBody
	@RequestMapping("/check_idx")
	  public String checkIdx(@RequestParam("nutri_idx") String check_idx) {
        String result = "N";
        log.info("컨트롤러1 >> {}", result);
        Long flag = nutriService.checkIdx(check_idx);
        if(flag == 1) 
        	result = "Y"; 
        log.info("컨트롤러2 >> {}", result);
        return result;
	}
}
