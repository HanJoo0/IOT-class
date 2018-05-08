package edu.iot.home.api;

import java.io.IOException;
import java.util.List;

import edu.iot.home.sensor.Sensor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SensorApi {
	@GET("sensor")
	Call<List<Sensor>> list();
	
	@GET("sensor/{sensorId}")
	Call<Sensor> get(@Path("sensorId") int sensorId);
	
	@POST("sensor")
	Call<Boolean> post(@Body Sensor sensor);
	
	@PUT("sensor/{sensorId}")
	Call<Boolean> put(  @Path("sensorId") int sensorId,
						@Body Sensor sensor);
	
	@DELETE("sensor/{sensorId}")
	Call<Boolean> delete(  @Path("sensorId") int sensorId);
	
	static SensorApi service = 
			new Retrofit.Builder()
					    .baseUrl("http://localhost:8080/gateway/api/")
					    .addConverterFactory(GsonConverterFactory.create())
					    .build()
						.create(SensorApi.class);
	
	public static void send(Sensor sensor) {
		Call<Boolean> call = SensorApi.service.post(sensor);
		try {
			Response<Boolean> res = call.execute();
			if(res.code() == 200) {
				if(!res.body()) {
					System.out.println("등록 실패");
				}
			}else {
				System.out.println("에러 코드 : " + res.code());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}