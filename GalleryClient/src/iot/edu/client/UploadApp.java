package iot.edu.client;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class UploadApp {
	public static void main(String[] args) {
		try {
			PartUtil part = new PartUtil();
			Call<Boolean> call = GalleryApi.service.upload(
					part.getPart("타이틀 테스트"),
					part.getPart("설명 테스트"),
					part.getFilePart("c:/temp/test.jpg", "image/jpg")
			);
			
			Response<Boolean> res= call.execute();
			if(res.code() == 200) {
				System.out.println("업로드 성공");
			}else {
				System.out.println("에러 코드 : " + res.code());
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
