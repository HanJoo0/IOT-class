package edu.iot.home;

import java.util.List;

import edu.iot.home.sensor.Sensor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SensorApi {
	static final String url = "http://localhost:8080/gateway/api/";
	
	@GET("sensor")
	Call<List<Sensor>> list();
	
	@GET("gallery/{sensorId}")
	Call<List<Sensor>> get(@Path("sensorId") int SensorId);
	
	@POST("sensor")
	Call<Boolean> add(Sensor sensor);

	@PUT("sensor")
	Call<Boolean> edit(Sensor sensor);
	
	
	@DELETE("gallery/{sensorId}")
	Call<Boolean> dlete(@Path("sensorId") int SensorId);
	
	static SensorApi service = 
		new Retrofit.Builder()
				    .baseUrl(url)
				    .addConverterFactory(GsonConverterFactory.create())
				    .build()
					.create(SensorApi.class);				
	
}
