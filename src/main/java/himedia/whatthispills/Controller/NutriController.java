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

	// 영양제 검색
	@GetMapping("/nutri/search")
	public String nutriSearch(@SessionAttribute(required = false) User passUser, @RequestParam(required=false) String keyword, Model model) {
	    List<Nutri> nutris = nutriService.findByNameNutri(keyword);
	    model.addAttribute("nutri", nutris);
	    model.addAttribute("user", passUser);
//	    if (nutri.isEmpty()) {
//	        model.addAttribute("message", "검색 결과가 없습니다.");
//	    } else if (nutri.size() > 1) {
//	        model.addAttribute("nutriSize", nutri.size());
//	    }

	    return "nutri/result";
	}
	
	
	// 상세 페이지
		@GetMapping("/nutri/{nutri_name}")
	    public String nutriAbout(@SessionAttribute(required = false) User passUser, @PathVariable String nutri_name, Model model) {
	        Nutri nutri = nutriService.findByNameInfo(nutri_name).get();
	        if(passUser != null) {
	        	List<UserLikes> like_list = userService.userLikeList(passUser.getIdx());
			    Optional<UserLikes> user_like = like_list.stream()
										        		 .filter(like -> {
										        			 return like.getNutri_idx().equals(nutri.getIdx());
										        		 })
										        		 .findAny();
			    if(user_like.isPresent()) {
			    	model.addAttribute("like", user_like.get());
			    }
	        }
	        model.addAttribute("nutri", nutri);
	        model.addAttribute("user", passUser);
	        return "nutri/about";
	    }

		
}
