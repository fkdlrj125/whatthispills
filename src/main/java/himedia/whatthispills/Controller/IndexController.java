package himedia.whatthispills.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import himedia.whatthispills.Domain.Admin;
import himedia.whatthispills.Domain.User;

@Controller
public class IndexController {
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/header")
	public String header(@SessionAttribute(required = false) User passUser, @SessionAttribute(required = false) Admin admin, Model model) {
		model.addAttribute("user", passUser);
		model.addAttribute("admin", admin);
		return "header";
	}
	
	@GetMapping("/footer")
	public String footer() {
		return "footer";
	}
}
