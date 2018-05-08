package iot.edu.client;

import retrofit2.Response;

public class SensorOneGetTest {
	public static void main(String[] args) throws Exception{
		Response<Sensor> res= 
				SensorApi.service.get(4).execute();
		if(res.code() == 200) {
			Sensor s = res.body();
			System.out.println(s);
		}else {
			System.out.println("에러 코드 : " + res.code());
		}	
	}
}
