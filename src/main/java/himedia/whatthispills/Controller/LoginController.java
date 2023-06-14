package himedia.whatthispills.Controller;

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
import himedia.whatthispills.Service.AdminService;
import himedia.whatthispills.Service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	private final UserService userService;
	private final AdminService adminService;
	
	public LoginController(UserService userService, AdminService adminService) {
		this.userService = userService;
		this.adminService = adminService;
	}
	
	@GetMapping("/join")
	public String joinGet() {
		return "join";
	}
	
	@PostMapping("/join")
	public String joinPost(@ModelAttribute User addUser) {
		userService.saveUser(addUser);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam String input_email, @RequestParam String input_pwd, HttpServletRequest request) {
		String result = userService.login(input_email, input_pwd);
		log.info(result);
		if(result.equals("user")) {
			HttpSession session = request.getSession();
			session.setAttribute("passUser", userService.findEmail(input_email).get());
			return "redirect:/";
		} else if(result.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("passUser", adminService.findEmail(input_email).get());
		}
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index"; // 현재 페이지로 이동
	}
	
	@GetMapping("/user/find_pwd")
	public String findPwdGet() {
		return "find_pwd";
	}
	
	@PostMapping("/user/find_pwd")
	public String findPwdPost(String input_email, String input_name, Model model, HttpServletRequest request) {
		if(userService.find_pwd(input_email, input_name)) {
			request.getSession().setAttribute("user_email", input_email);
			return "redirect:/user/reset_pwd";
		}
		model.addAttribute(""); //alert 문구
		return "find_pwd";
	}
	
	@GetMapping("/user/reset_pwd")
	public String resetPwdGet(String input_email) {
		return "reset_pwd";
	}
	
	@PostMapping("/user/reset_pwd")
	public String resetPwdPost(@SessionAttribute String input_email, String input_pwd, HttpServletRequest request, Model model) {
		if(userService.updatePassword(input_email, input_pwd)) {
			request.getSession().invalidate();
			return "redirect:/login";
		}
		model.addAttribute(""); // alert 문구
		return "redirect:/user/reset_pwd";
	}
}
