package himedia.whatthispills.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Domain.NutriRec;
import himedia.whatthispills.Domain.User;
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

	@GetMapping("/nutri/search")
	public String nutriSearch(@SessionAttribute(required = false) User passUser, Model model) {
		Optional<NutriRec> rec;
		List<Nutri> rec_list = new ArrayList<Nutri>();
		if(passUser == null || passUser.getGender() == "없음") {
			rec = nutriService.recforAll();
			rec_list.add(nutriService.findIdNutri(rec.get().getRecommend11()).get());
			rec_list.add(nutriService.findIdNutri(rec.get().getRecommend12()).get());
			rec_list.add(nutriService.findIdNutri(rec.get().getRecommend13()).get());
		} else {
			rec = nutriService.recGenderAge(passUser.getGender(), passUser.getBirth());
			rec_list.add(nutriService.findIdNutri(rec.get().getRecommend11()).get());
			rec_list.add(nutriService.findIdNutri(rec.get().getRecommend12()).get());
			rec_list.add(nutriService.findIdNutri(rec.get().getRecommend13()).get());
		}
		
		if(passUser != null) {
			List<Long> idx_list = userService.likeIdxList(passUser.getIdx());
			model.addAttribute("like_list", idx_list);
		}
		
		model.addAttribute("nutri_rec", rec_list);
		model.addAttribute("user", passUser);
		return "nutri/search";
	}

	// 영양제 검색
	@GetMapping("/nutri/result")
	public String nutriResult(@SessionAttribute(required = false) User passUser, @RequestParam(required = false) String keyword, Model model) {
		List<Nutri> nutri = nutriService.findByNameNutri(keyword);
		if (keyword == null || keyword.trim().isEmpty()) {
			return "index";
		}
		
		if(passUser != null) {
			List<Long> idx_list = userService.likeIdxList(passUser.getIdx());
			model.addAttribute("like_list", idx_list);
		}
		model.addAttribute("nutri", nutri);
		model.addAttribute("keyword", keyword);
		model.addAttribute("user", passUser);
		return "nutri/result";
	}

	// 상세 페이지
	@GetMapping("/nutri/{nutri_name}")
	public String nutriAboutGet(@SessionAttribute(required = false) User passUser, @PathVariable String nutri_name,
			Model model) {
		Nutri nutri = nutriService.findByNameInfo(nutri_name).get();
		if (passUser != null) {
			List<Long> idx_list = userService.likeIdxList(passUser.getIdx());
			model.addAttribute("like_list", idx_list);
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
