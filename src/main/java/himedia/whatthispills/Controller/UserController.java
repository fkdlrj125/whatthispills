package himedia.whatthispills.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Domain.UserLikes;
import himedia.whatthispills.Service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/join")
	public String join_get() {
		return "join";
	}
	@PostMapping("/join")
	public String join_post(@ModelAttribute User addUser) {
		userService.saveUser(addUser);
		return "redirect:/login";
	}
	@GetMapping("/login")
	public String login_get() {
		
		return "login";
	}
	@PostMapping("/login")
	public String login_post(@RequestParam String user_email, @RequestParam String user_pwd, HttpServletRequest request) {
		if(userService.login(user_email, user_pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("passUser", userService.findEmail(user_email));
			return "redirect:/";
		}
		return "redirect:/login";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return ""; // 현재 페이지로 이동
	}
	
	@GetMapping("/user/find_pwd")
	public String find_pwd_get() {
		return "find_pwd";
	}
	@PostMapping("/user/find_pwd")
	public String find_pwd_post(String user_email, String user_name, Model model, HttpServletRequest request) {
		if(userService.find_pwd(user_email, user_name)) {
			request.getSession().setAttribute("user_email", user_email);
			return "redirect:/user/reset_pwd";
		}
		model.addAttribute(""); //alert 문구
		return "find_pwd";
	}
	@GetMapping("/user/reset_pwd")
	public String reset_pwd_get(String user_email) {
		return "reset_pwd";
	}
	@PostMapping("/user/reset_pwd")
	public String reset_pwd_post(@SessionAttribute String user_email, String user_pwd, HttpServletRequest request, Model model) {
		if(userService.updatePassword(user_email, user_pwd)) {
			request.getSession().invalidate();
			return "redirect:/login";
		}
		model.addAttribute(""); // alert 문구
		return "redirect:/user/reset_pwd";
	}
	
	@GetMapping("/user/edit")
	public String user_edit_get(/* @SessionAttribute User passUser, */Model model) {
//		model.addAttribute("user", passUser);
		return "user/edit";
	}
	
	@PostMapping("/user/edit")
	public String user_edit_post(@ModelAttribute User updateUser, HttpServletRequest request) {
		User user = userService.update(updateUser.getIdx(), updateUser);
		request.getSession().setAttribute("passUser", user);
		return "redirect:/user/info";
	}
	
	@GetMapping("/user/info")
	public String user_info(@SessionAttribute User passUser, Model model) {
		model.addAttribute("user", passUser);
		return "user/info";
	}
	
	@GetMapping("/user/likes")
	public String user_likes(@SessionAttribute User passUser, Model model) {
		List<UserLikes> like_list = userService.userLikeList(passUser.getIdx());
		model.addAttribute("like_list", like_list);
		return "user/likes";
	}
	
	@GetMapping("/user/menu")
	public String user_menu() {
		return "user/menu";
	}
	
}
