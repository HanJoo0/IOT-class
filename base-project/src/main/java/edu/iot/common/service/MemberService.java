package edu.iot.common.service;

import java.util.List;

import edu.iot.common.model.Login;
import edu.iot.common.model.Member;
import edu.iot.common.model.Pagination;
import edu.iot.common.model.Password;

public interface MemberService {
	Member checkLogin(Login login) throws Exception;

	boolean checkId(String userId) throws Exception;

	boolean create(Member member) throws Exception;
	
	boolean update(Member member) throws Exception;
	
	Member getMember(String userId) throws Exception;
	
	boolean changePassword(Password password) throws Exception;

	Pagination getPagination(int page) throws Exception ;
	
	List<Member> getList(Pagination pagination) throws Exception;

	
	// 관리자만 호출하는 메서드
	boolean updateByAdmin(Member member) throws Exception;

	boolean changePasswordByAdmin(Password password) throws Exception;

	
	
}
