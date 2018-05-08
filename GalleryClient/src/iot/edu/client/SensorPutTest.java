package iot.edu.client;

import retrofit2.Response;

public class SensorPutTest {
	public static void main(String[] args) throws Exception{
		Response<Sensor> res= 
				SensorApi.service.get(4).execute();
		if(res.code() == 200) {
			Sensor s = res.body();			
			s.setValue((float)18.5);			
			Response<Boolean> res2= 
					SensorApi.service
						.put(s.getSensorId(), s).execute();			
			Boolean result = res2.body();
			if(result) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
		}else {
			System.out.println("에러 코드 : " + res.code());
		}	
	}
}
