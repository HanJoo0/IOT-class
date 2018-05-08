package edu.iot.home.device;

import edu.iot.home.api.SensorApi;
import edu.iot.home.sensor.Sensor;

public class Light implements LightSensor.OnChangeListener{
	private float lightLevel;
	private String type ;
	private String name;
	private String location ;
	
	private boolean state;	// true : ON, false: OFF
	public Light(String location, String name, float lightLevel) {
		this.type ="조명";
		this.location = location;
		this.name = name;
		this.lightLevel = lightLevel;
		this.state = false; 
	}

	// 게이트웨이에 상태 전송
	public void send(float value) {
		Sensor sensor = new Sensor(0, name,  value, type, 
				location, "2018-04-24");
		SensorApi.send(sensor);
	}
	
	@Override
	public void changeValue(float value) {
		// lightLevel 이하면 전등 켜기, 이상이면 끄기
		if(state) {	// 켜져 있는 상태
			if(value > lightLevel) {
				state = false;
				System.out.println(location + " 전등 꺼짐: " + value);
				send(0);
			}
		} else {	// 꺼져 있는 상태
			if(value <= lightLevel) {
				state = true;
				System.out.println(location + " 전등 켜짐: " + value);
				send(1);
			}
		}
	}

}
