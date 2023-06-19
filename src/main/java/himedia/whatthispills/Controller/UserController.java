package himedia.whatthispills.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import himedia.whatthispills.Domain.Nutri;
import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
	private final UserService userService;

	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/edit")
	public String userEditGet(@SessionAttribute(required = false) User passUser, Model model) {
		model.addAttribute("user", passUser);
		return "user/edit";
	}
	
	@PostMapping("/edit")
	public String userEditPost(@ModelAttribute User updateUser, HttpServletRequest request) {
		User user = userService.update(updateUser);
		log.info(user.getPwd());
		request.getSession().setAttribute("passUser", user);
		return "redirect:/user/info";
	}
	
	@GetMapping("/info")
	public String userInfo(@SessionAttribute(required = false) User passUser, Model model) {
		log.info(passUser.getEmail());
		model.addAttribute("user", passUser);
		return "user/info";
	}
	
	@GetMapping("/likes")
	public String userLikes(@SessionAttribute(required = false) User passUser, Model model) {
		List<Nutri> like_list = userService.userLikeList(passUser.getIdx());
		List<Long> idx_list = userService.likeIdxList(passUser.getIdx());
		model.addAttribute("idx_list", idx_list);
		model.addAttribute("like_list", like_list);
		model.addAttribute("user", passUser);
		return "user/likes";
	}
	
	@GetMapping("/like")
	public String userLikes(Long nutri_idx, Long user_idx, Model model, HttpServletRequest request) {
		userService.like(nutri_idx, user_idx);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@GetMapping("/menu")
	public String userMenu(@SessionAttribute(required = false) User passUser, Model model) {
		model.addAttribute("user", passUser);
		return "user/menu";
	}
	
}
