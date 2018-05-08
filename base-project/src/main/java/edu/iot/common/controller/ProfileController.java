package edu.iot.common.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.iot.common.model.Member;
import edu.iot.common.model.Password;
import edu.iot.common.service.MemberService;

@Controller
@RequestMapping("/member")
public class ProfileController {
	@Autowired
	MemberService service;

	public void setMember(HttpSession session, Model model) throws Exception {
		Member m = (Member)session.getAttribute("USER");
		Member member = service.getMember(m.getUserId());
		model.addAttribute("member", member);
	}
	
	@RequestMapping("/profile")
	public void profile(HttpSession session, Model model) throws Exception {
		setMember(session, model);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public void editForm(HttpSession session, Model model) throws Exception {
		setMember(session, model);
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String editSubmit(@Valid Member member, 
							BindingResult result,
							HttpSession session) 
				throws Exception {
		if(result.hasErrors()) return "member/edit";	
		
		// 정보 수정 처리 
		if(!service.update(member)) {// 실패한 경우
			result.reject("updateFail", "비밀번호가 일치하지 않습니다.");
			return "member/edit";
		}
		
		session.setAttribute("USER", member);
		return "redirect:profile";		 
	}
	
	@RequestMapping(value="/changepassword", method=RequestMethod.GET)
	public void changePasswordForm(Password password) throws Exception {
		
	}

	@RequestMapping(value="/changepassword", method=RequestMethod.POST)
	public String changePasswordSubmit(@Valid Password password, 
							BindingResult result) 
				throws Exception {
		if(result.hasErrors()) return "member/changepassword";	
		
		// 정보 수정 처리 
		if(!service.changePassword(password)) {// 실패한 경우
			result.reject("updateFail", "비밀번호가 일치하지 않습니다.");
			return "member/changepassword";
		}
		
		return "redirect:profile";		 
	}
	
	
}
