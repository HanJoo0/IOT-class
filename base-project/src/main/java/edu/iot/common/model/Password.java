package edu.iot.common.model;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Password {
	@NotEmpty(message="사용자 ID는 필수항목입니다.")
	private String userId;
	
	@NotEmpty(message="이전 비밀번호는 필수항목입니다.")
	private String oldPassword;
	
	@NotEmpty(message="새로운 비밀번호는 필수항목입니다.")
	private String newPassword;
}
