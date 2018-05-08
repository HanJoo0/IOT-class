package iot.edu.client;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ImageApp {
	public static void main(String[] args) {
		try {
			Response<List<Image>> res= 
					GalleryApi.service.list(2).execute();
			if(res.code() == 200) {
				List<Image> list = res.body();
				for(Image image : list) {
					System.out.println(image);
				}
				
			}else {
				System.out.println("에러 코드 : " + res.code());
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
