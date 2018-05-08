package edu.iot.common.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.iot.common.model.Member;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Password;
import edu.iot.common.service.MemberService;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping("/list")
	public void list(
				@RequestParam(value="page", defaultValue="1") int page, 
				Model model) throws Exception {

		Pagination pagination = service.getPagination(page);
		List<Member> list = service.getList(pagination);

		model.addAttribute("pagination", pagination);
		model.addAttribute("list", list);
		
		model.addAttribute("today", new Date());
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public void createForm(Member member){
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createSubmit(@Valid Member member, BindingResult result) 
									throws Exception {
		if(result.hasErrors() || !service.create(member)) 
			return "admin/member/create";

		return "redirect:list";
	}
	
	@RequestMapping(value="/view/{userId}", method=RequestMethod.GET)
	public String view(@PathVariable String userId, Model model) 
							throws Exception {
		Member member = service.getMember(userId);
		model.addAttribute("member", member);
		return "admin/member/view";
	}
	@RequestMapping(value="/edit/{userId}", method=RequestMethod.GET)
	public String editForm(@PathVariable String userId, Model model)
						 throws Exception {
		Member member = service.getMember(userId);
		model.addAttribute("member", member);
		return "admin/member/edit";
	}

	@RequestMapping(value="/edit/{userId}", method=RequestMethod.POST)
	public String editSubmit(
				@Valid Member member,
				BindingResult result,
				@RequestParam(value="page", defaultValue="1") int page, 
				HttpSession session ) 
								throws Exception {
			if(result.hasErrors()) return "admin/member/edit";
			
			// 관리자 비밀번호 체크
			Member admin = (Member)session.getAttribute("USER");
			if(!admin.getPassword().equals(member.getPassword())){
				result.reject("passwordNotMatch", "비밀번호가 맞지 않습니다.");
				return "admin/member/edit";
			}
			
			if(!service.updateByAdmin(member)) {
				result.reject("updateFail", "수정에 실패했습니다.");
				return "admin/member/edit";
			}
			return "redirect:/admin/member/view/" +
						member.getUserId() + "?page="+page;
	}

	@RequestMapping(value="/changepassword/{userId}", method=RequestMethod.GET)
	public String changePasswordForm(
							Password password,
							@PathVariable String userId)
						 throws Exception {
		
		password.setUserId(userId);
		return "admin/member/changepassword";
	}

	@RequestMapping(value="/changepassword/{userId}", method=RequestMethod.POST)
	public String changePasswordSubmit(
				@Valid Password password,				 
				BindingResult result, 
				@RequestParam(value="page", defaultValue="1") int page,
				HttpSession session ) 
								throws Exception {
			if(result.hasErrors()) return "admin/member/changepassword";
			
			// 관리자 비밀번호 체크
			Member admin = (Member)session.getAttribute("USER");
			if(!admin.getPassword().equals(password.getOldPassword())){
				result.reject("passwordNotMatch", "비밀번호가 맞지 않습니다.");
				return "admin/member/changepassword";
			}
			
			if(!service.changePasswordByAdmin(password)) {
				result.reject("updateFail", "수정에 실패했습니다.");
				return "admin/member/changepassword";
			}
			return "redirect:/admin/member/view/" +
					password.getUserId() + "?page="+page;
	}

}
