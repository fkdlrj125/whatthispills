package himedia.whatthispills.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import himedia.whatthispills.Domain.Admin;
import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/nutri_list")
	public String nutri_list(@SessionAttribute(required=false) Admin admin, Model model) {
		model.addAttribute("user", admin);
		return "admin/nutri_list";
	}
	
	@GetMapping("/nutri_add")
	public String nutri_add_form() {
		return "admin/nutri_add";
	}
	
	@PostMapping("/nutri_add")
	public String nutri_add(@ModelAttribute Nutri nutri, RedirectAttributes attributes) {
//		adminService.add(nutri);
		return "redirect:/admin/nutri_list";
	}
	
	@GetMapping("/nutri_edit")
	public String nutri_edit_form() {
		return "admin/nutri_edit";
	}
	
	@PostMapping("/nutri_edit")
	public String nutri_edit() {
		return "redirect:/admin/nutri_list";
	}
	
	@GetMapping("/nutri_remove")
	public String nutri_remove() {
		
		return "admin/nutri_list";
	}
	
	
}
