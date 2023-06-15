package himedia.whatthispills.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import himedia.whatthispills.Domain.User;
import himedia.whatthispills.Service.AdminService;
import himedia.whatthispills.Service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
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
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinPost(@ModelAttribute User addUser) {
		userService.saveUser(addUser);
		return "redirect:/user/login";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam String input_email, @RequestParam String input_pwd, HttpServletRequest request) {
		String result = userService.login(input_email, input_pwd);
		if(result.equals("user")) {
			HttpSession session = request.getSession();
			session.setAttribute("passUser", userService.findEmail(input_email).get());
			return "redirect:/";
		} else if(result.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", adminService.findEmail(input_email).get());
			return "redirect:/admin/nutri_list";
		}
		return "redirect:/user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index"; // 현재 페이지로 이동
	}
	
	@GetMapping("/find_pwd")
	public String findPwdGet() {
		return "user/find_pwd";
	}
	
	@PostMapping("/find_pwd")
	public String findPwdPost(String input_email, String input_name, Model model, HttpServletRequest request) {
		if(userService.findPwd(input_email, input_name)) {
			request.getSession().setAttribute("user_email", input_email);
			return "redirect:/user/reset_pwd";
		}
		model.addAttribute(""); //alert 문구
		return "user/find_pwd";
	}
	
	@GetMapping("/reset_pwd")
	public String resetPwdGet(String input_email) {
		return "user/reset_pwd";
	}
	
	@PostMapping("/reset_pwd")
	public String resetPwdPost(@SessionAttribute String input_email, String input_pwd, HttpServletRequest request, Model model) {
		if(userService.updatePassword(input_email, input_pwd)) {
			request.getSession().invalidate();
			return "redirect:/user/login";
		}
		model.addAttribute(""); // alert 문구
		return "redirect:/user/reset_pwd";
	}
}
