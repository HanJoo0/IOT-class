package iot.edu.client.binary;

import java.io.FileOutputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ImageDownload {
	public static void main(String[] args) throws Exception{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
			      .url("http://localhost:8080/butter/gallery/image/4")
			      .build();
			 
	    Call call = client.newCall(request);
	    Response response = call.execute();	    
	    byte[] data = (byte[]) response.body().bytes();
	    
	    try (FileOutputStream out = new FileOutputStream("c:/temp/down_test.jpg")) {
	    	out.write(data);
	    	out.flush();
	    	System.out.println("저장 완료");
	    }
			 
	}
}
