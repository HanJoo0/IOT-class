package edu.iot.common.dao;

import edu.iot.common.model.Member;
import edu.iot.common.model.Password;

public interface MemberDao 
	extends BaseDao<Member, String>{
	
	int changePassword(Password password) throws Exception;

	// 관리자 호출 메서드
	int updateByAdmin(Member member) throws Exception;
	int changePasswordByAdmin(Password password) throws Exception;
	
}
