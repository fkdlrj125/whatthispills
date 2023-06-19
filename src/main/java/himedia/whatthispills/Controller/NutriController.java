package himedia.whatthispills.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;
import himedia.whatthispills.Service.NutriService;
import himedia.whatthispills.Service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NutriController {

	private final UserService userService;
	private final NutriService nutriService;

	public NutriController(UserService userService, NutriService nutriService) {
		this.userService = userService;
		this.nutriService = nutriService;
	}

	@GetMapping("/nutri/nutri_search")
	public String goToSearch() {
		return "nutri/nutri_search";
	}

	// 영양제 검색
//	@GetMapping("/nutri/search")
//	public String nutriSearch(@RequestParam String keyword, Model model) {
//		List<Nutri> nutri = nutriService.findByNameNutri(keyword);
//		model.addAttribute("nutri", nutri);
//		model.addAttribute("keyword", keyword);
//		return "nutri/result";
//	}
	@GetMapping("/nutri/search")
	public String nutriSearch(@RequestParam String keyword, Model model) {
	    if (keyword == null || keyword.trim().isEmpty()) {
	        return "index";
	    }
	    List<Nutri> nutri = nutriService.findByNameNutri(keyword);
	    model.addAttribute("nutri", nutri);
	    model.addAttribute("keyword", keyword);
	    return "nutri/result";
	}

	// 상세 페이지
	@GetMapping("/nutri/{nutri_name}")
	public String nutriAbout(@SessionAttribute(required = false) User passUser, @PathVariable String nutri_name,
			Model model) {
		Nutri nutri = nutriService.findByNameInfo(nutri_name).get();
		if (passUser != null) {
			List<UserLikes> like_list = userService.userLikeList(passUser.getIdx());
			Optional<UserLikes> user_like = like_list.stream().filter(like -> {
				return like.getNutri_idx().equals(nutri.getIdx());
			}).findAny();
			if (user_like.isPresent()) {
				model.addAttribute("like", user_like.get());
			}
		}
		model.addAttribute("nutri", nutri);
		model.addAttribute("user", passUser);
		return "nutri/about";
	}

	// 카테고리 검색
	@GetMapping("/nutri/category")
	public String nutriCategory(@RequestParam String category, Model model) {
		List<Nutri> nutri = nutriService.findByCategory(category);
		model.addAttribute("nutri", nutri);
		model.addAttribute("keyword", category);
		return "nutri/result";
	}

}
