package iot.edu.client;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GalleryApi {
	@GET("gallery")
	Call<List<Image>> list(@Query("page") int page);
	
	@GET("gallery/{imageId}")
	Call<Image> get(@Path("imageId") int imageId);
	
	@Multipart
	@POST("gallery/")
	Call<Boolean> upload(
			@Part("title") 		RequestBody title, 
			@Part("description") RequestBody description,
			@Part("file")		MultipartBody.Part file
	);
	
	static GalleryApi service = 
			new Retrofit.Builder()
					    .baseUrl("http://localhost:8080/butter/api/")
					    .addConverterFactory(GsonConverterFactory.create())
					    .build()
						.create(GalleryApi.class);
	
}
