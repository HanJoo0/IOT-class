package edu.iot.butter;

import com.google.gson.Gson;

import edu.iot.common.model.Member;

public class GsonTest {

	public static void main(String[] args) {
		Member member = new Member();
		member.setUserId("hong");
		member.setName("홍길동");
		member.setPassword("1234");
		
		
		Gson gson = new Gson();
		String json = gson.toJson(member);
		System.out.println(json);		

		Member member2 = gson.fromJson(json, Member.class);
		System.out.println(member2);
		
	}

}
