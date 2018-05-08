package iot.edu.client;

import java.util.List;

import retrofit2.Response;

public class SensorListTest {
	public static void main(String[] args) throws Exception{
		Response<List<Sensor>> res= 
				SensorApi.service.list().execute();
		if(res.code() == 200) {
			List<Sensor> list = res.body();
			for(Sensor s : list) {
				System.out.println(s);
			}
			
		}else {
			System.out.println("에러 코드 : " + res.code());
		}	
	}
}
