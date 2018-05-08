package edu.iot.gateway.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import edu.iot.gateway.model.LocationStatus;
import edu.iot.gateway.service.SensorService;

@Component
public class MonitoringHandler extends TextWebSocketHandler {
	List<WebSocketSession> list = 
			Collections.synchronizedList(new ArrayList<>());

	@Resource(name="sensorServiceImple")
	SensorService service;
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, 
									CloseStatus status) throws Exception {
		list.remove(session);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) 
	throws Exception {
		list.add(session);
		send(service.getLocationStatus());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, 
									TextMessage message) throws Exception {
	}


	public synchronized void send(Map<String, LocationStatus> locationMap)  
			throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(locationMap);
		TextMessage message = new TextMessage(json);
		for(WebSocketSession session : list) {
			session.sendMessage(message);
		}
		
	}
}
