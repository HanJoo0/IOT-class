package iot.edu.client.binary;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImageApi {
	@GET("image/{imageId}")
	Call<byte[]> get(@Path("imageId") int imageId);
	

	static ImageApi service = 
			new Retrofit.Builder()
					    .baseUrl("http://localhost:8080/butter/gallery/")
					    .addConverterFactory(GsonConverterFactory.create())
					    .build()
						.create(ImageApi.class);
	
}
