package edu.iot.home.device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.iot.home.api.SensorApi;
import edu.iot.home.sensor.Sensor;
import retrofit2.Call;
import retrofit2.Response;

public class LightSensor extends Thread{

	private String type;
	private String location;
	private String name;
	private float[] values;
	
	private List<OnChangeListener> listeners;
	
	public LightSensor(String location, String name, float[] values ) {
		type="조도";		
		this. location = location;
		this.name = name;
		this.values = values;
		listeners = new ArrayList<>();
	}
		
	public void run() {
		int ix = 0;
		while(true) {
			try {
				Thread.sleep(500);
				// 데이터 발생 				
				float data = values[ix++];
				Sensor sensor = new Sensor(0, name, data, type, 
											location, "2018-04-24");
				// gateway로 전송
				SensorApi.send(sensor);				
				
				for(OnChangeListener listener : listeners)
					listener.changeValue(data);
				if(ix == values.length) ix=0;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setOnChangeListener(OnChangeListener listener) {
		listeners.add(listener);
	}
	
	public interface OnChangeListener {
		public void changeValue(float value);
	}
	

}
