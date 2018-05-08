package edu.iot.home;

import java.util.Date;
import java.util.Random;

import edu.iot.home.sensor.Sensor;

public class TemperatureSensor extends Thread {
	Random rand= new Random();
	float base;
	
	public TemperatureSensor(float base) {
		this.rand= new Random();
		this.base = base;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
				float value =base + rand.nextFloat();
				//Sensor s = new Sensor(0, "온도", value, "거실", new Date());
				//System.out.println(s);
						
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
	}
}
