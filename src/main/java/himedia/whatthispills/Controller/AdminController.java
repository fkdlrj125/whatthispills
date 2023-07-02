package himedia.whatthispills.Controller;

import java.io.IOException;
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
import himedia.whatthispills.Service.S3Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	private final NutriService nutriService;
	private final S3Service s3Service;

	public AdminController(NutriService nutriService, S3Service s3Service) {
		this.nutriService = nutriService;
		this.s3Service = s3Service;
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
		String url = "";
		if(file != null) {
			url = s3Service.saveFile(file, nutri.getIdx(), nutri.getCategory());
		}
		nutriService.saveNutri(nutri, url);
	    return "redirect:/admin/nutri_list";
	}
	

	@GetMapping("/nutri_edit/{nutri_idx}")
	public String nutriEditGet(@PathVariable Long nutri_idx, Model model) {
		Nutri nutri = nutriService.findIdxNutri(nutri_idx).get();
		model.addAttribute("nutri", nutri);
		return "admin/nutri_edit";
	}

	@PostMapping("/nutri_edit/{nutri_idx}")
	public String nutriEditPost(@PathVariable Long nutri_idx, @ModelAttribute Nutri nutri, MultipartFile file, Model model) throws IOException {
		if(!file.isEmpty()) {
			s3Service.deleteFile(nutri_idx);
			String url = s3Service.saveFile(file, nutri_idx, nutri.getCategory());
			nutri.setImage(url);
		} else {
			nutri.setImage(nutriService.findIdxNutri(nutri_idx).get().getImage());
		}
		Nutri nutri_edit = nutriService.nutriEdit(nutri_idx, nutri);
		model.addAttribute("nutri", nutri_edit);
		return "redirect:/admin/nutri_list";
	}

	@GetMapping("/nutri_remove/{nutri_idx}")
	public String nutriRemove(@PathVariable Long nutri_idx) {
		s3Service.deleteFile(nutri_idx);
		nutriService.removeNutri(nutri_idx);
		return "redirect:/admin/nutri_list";
	}
	
	@ResponseBody
	@RequestMapping("/check_idx")
	  public String checkIdx(@RequestParam("nutri_idx") String check_idx) {
        String result = "N";
        Long flag = nutriService.checkIdx(check_idx);
        if(flag == 1) 
        	result = "Y"; 
        return result;
	}
}
