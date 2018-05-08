package iot.edu.client;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ImageOneApp {

	public static void main(String[] args) throws Exception{
		int imageId = 3;		
		Thread t = new Thread(()->{
			try {
				Response<Image> res = GalleryApi.service
											.get(imageId)
											.execute();
				if(res.code() == 200) {
					Image image = res.body();
					System.out.println(image);
				}else {
					System.out.println("에러 코드 : " + res.code());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		t.start();
		// imageId = 4;
		
	}

}
