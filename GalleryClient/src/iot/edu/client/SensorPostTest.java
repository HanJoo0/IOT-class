package iot.edu.client;

import java.util.List;

import retrofit2.Response;

public class SensorPostTest {
	public static void main(String[] args) throws Exception{
		Sensor s = new Sensor(0, 15, "온도", "화장실","2018-04-24");

		Response<Boolean> res1= 
				SensorApi.service.post(s).execute();
		
		if(res1.code() == 200) {
			boolean result = res1.body();
			if(result) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
		}else {
			System.out.println("에러 코드 : " + res1.code());
		}	
		
	}
}
