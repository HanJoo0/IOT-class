package edu.iot.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	public static void copy(String path, HttpServletResponse response) {

		try (InputStream in = new FileInputStream(path); 
			OutputStream out = response.getOutputStream();) {
			copy(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copy(InputStream in, OutputStream out) throws Exception {

		BufferedInputStream bis = new BufferedInputStream(in);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		int data;
		while ((data = bis.read()) != -1) {
			bos.write(data);
		}
		bos.flush();
	}
}
