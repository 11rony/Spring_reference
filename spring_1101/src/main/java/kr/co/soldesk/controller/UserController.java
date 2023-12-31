package kr.co.soldesk.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.UserService;
import kr.co.soldesk.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean; //session 영역
	
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value="fail", defaultValue = "false") boolean fail,
						Model model) {
		model.addAttribute("fail", fail); //처음에는 무조건 false로 넘어감(fail=false)
										  //이후 실패시 fail=true

		
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
		
		//1. 유효성 검사
		if(result.hasErrors()) {
			
			return "user/login";
		}
		
		//로그인 가능 여부
		userService.getLoginUserInfo(tempLoginUserBean);
		
		//로그인 성공 여부
		if(loginUserBean.isUserLogin()==true) {
			return "user/login_success";
		} else {
			return "user/login_fail";
		}
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		
		return "user/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			
			return "user/join";
		}
		
		userService.addUserInfo(joinUserBean);
		
		return "user/join_success";
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) { //앞 modifyUserBean: 뷰, 뒤: 백단
		
		userService.getModifyUserInfo(modifyUserBean);
		
		return "user/modify";
	}

	@PostMapping("/modify_pro")
	public String modify(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) { 
		
		if(result.hasErrors()) {
			return "user/modify";
		}
		
		userService.modifyUserInfo(modifyUserBean);
		
		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		
		
		
		return "user/not_login";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
       UserValidator validator1 = new UserValidator();
       binder.addValidators(validator1);
    }
}
