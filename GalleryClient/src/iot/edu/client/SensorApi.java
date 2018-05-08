package iot.edu.client;

import java.util.List;

import retrofit2.Call;
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
	
}