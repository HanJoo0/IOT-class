package iot.edu.client;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PartUtil {
	
	public RequestBody getPart(String value) {
		return RequestBody.create(MediaType.parse("multipart/form-data"), value);
	}
	
	public MultipartBody.Part getFilePart(String path, String mime) {
		File file = new File(path);
		RequestBody fileBody = RequestBody.create(MediaType.parse(mime),
												file); 
		MultipartBody.Part filePart = MultipartBody.Part.createFormData(
							"file", file.getName(), fileBody);
	    return filePart;
	}
	
	public MultipartBody.Part getFilePart(byte[] data, String fileName, String mime) {
		RequestBody fileBody = RequestBody.create(MediaType.parse(mime), data);
		MultipartBody.Part filePart = MultipartBody.Part.createFormData(
										"file", fileName, fileBody);
		return filePart;
	}

	
}
