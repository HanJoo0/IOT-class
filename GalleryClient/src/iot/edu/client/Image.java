package iot.edu.client;


import java.util.Date;

import lombok.Data;

@Data
public class Image {
	private int		imageId;	
	private String 	title;
	private String 	description;
	private String 	fileName;	// 원본 이름 
	private String	newName;	// 서버에서의 이름
	private String 	thumbName;	// thumbnail 이름
	private long 	fileSize;
	private String	mimeType;
	private String	regDate;
	
}