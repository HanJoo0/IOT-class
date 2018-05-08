package edu.iot.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.iot.commn.exception.LoginFailException;
import edu.iot.common.model.Login;
import edu.iot.common.model.Member;
import edu.iot.common.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AccountController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm(Login login) {
		return "account/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginSubmit(@Valid Login login, 
								BindingResult result, 
								HttpSession session) throws Exception  {
		if(result.hasErrors()) return "account/login";
		
		Member member = service.checkLogin(login);
/*		if(member==null) {
			result.reject("failLogin",
					"사용자 ID 또는 비밀번호가 일치하지 않습니다.");
			return "account/login";
		}		*/	
		session.setAttribute("USER", member);
		return "redirect:/";
	}

	@ExceptionHandler(LoginFailException.class)
	public String handleLoginError(HttpServletRequest request, 
									Exception e) {
		request.setAttribute("login", new Login());
		request.setAttribute("error", e.getMessage());
		return "account/login";
	}

	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm(Member member) {
		return "account/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinSubmit(@Valid Member member, 
							BindingResult result,
							RedirectAttributes ra) throws Exception {
		if(result.hasErrors()) return "account/join";
		service.create(member);
		ra.addFlashAttribute("member", member);
		return "redirect:/join_success";				
	}
	
	@RequestMapping(value="/join_success", method=RequestMethod.GET)
	public String joinSuccess() {
		return "account/join_success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.GET)
	public boolean checkId(@RequestParam("userId") String userId) {
		try {
			return service.checkId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	
//	@ExceptionHandler({SQLException.class, DataAccessException.class})
//	public String handleError() {
//		return "error/database_error";
//	}

}



