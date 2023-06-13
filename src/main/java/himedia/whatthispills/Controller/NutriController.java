package himedia.whatthispills.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import himedia.whatthispills.Service.NutriService;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class NutriController {
	
	@GetMapping("/nutri/search")
	public String nutirSearch() {
		return null;
		
	}
	@GetMapping("/nutri/result")
	public String nutirResult() {
		return null;
		
	}
	@GetMapping("/nutri/about")
	public String nutirAbout() {
		return null;
		
	}
	
	
}
