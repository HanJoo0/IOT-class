package edu.iot.common.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Member {
	@NotEmpty(message="사용자 ID는 필수 항목입니다.")
	private String 	userId;
	@NotEmpty(message="이름은 필수 항목입니다.")
	private String 	name;
	@NotEmpty(message="비밀번호는 필수 항목입니다.")
	private String 	password;
	@NotEmpty(message="전화번호는 필수 항목입니다.")
	private String 	cellPhone;
	private String 	email;
	private String 	address;
	private int 	grade;
	private Date	regDate;
	private Date 	updateDate;
}
