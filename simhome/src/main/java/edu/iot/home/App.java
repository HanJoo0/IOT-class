package edu.iot.home;

import edu.iot.home.device.Light;
import edu.iot.home.device.LightSensor;

public class App {
	static float[] value1 = {
		80, 86, 85, 85, 81, 78, 77, 74, 73, 71, 69, 68, 65, 60, 57, 59,
		80, 86, 85, 85, 81, 78, 71, 69, 68, 65, 60
	};
	
	
	public static void main(String[] args) throws Exception {
		makeGate();
		makeToilet();
		makeRoad();
		System.out.println("기동");
		Thread.sleep(1000*100);
		System.out.println("--- 종료 -----");
		System.exit(0);
	}
	
	public static void makeGate() {
		LightSensor sensor1 = new LightSensor("입구", "조도1", value1);
		sensor1.setDaemon(true);
		sensor1.setOnChangeListener(new Light("입구", "조명1", 80));
		sensor1.setOnChangeListener(new Light("입구", "조명2", 60));
		sensor1.setOnChangeListener(new Light("입구", "조명3", 60));
		sensor1.setOnChangeListener(new Light("입구", "조명4", 70));
		sensor1.start();
	}
	
	public static void makeToilet() {
		LightSensor sensor1 = new LightSensor("남자화장실", "조도1", value1);
		sensor1.setDaemon(true);
		sensor1.setOnChangeListener(new Light("남자화장실", "조명1", 80));
		sensor1.setOnChangeListener(new Light("남자화장실", "조명2", 60));
		sensor1.start();
		
		LightSensor sensor2 = new LightSensor("여자화장실", "조도1", value1);
		sensor2.setDaemon(true);
		sensor2.setOnChangeListener(new Light("여자화장실", "조명1", 80));
		sensor2.setOnChangeListener(new Light("여자화장실", "조명2", 60));
		
		sensor2.start();
	}

	public static void makeRoad() {
		LightSensor sensor1 = new LightSensor("산책로", "조도1", value1);
		//sensor1.setDaemon(true);
		sensor1.setOnChangeListener(new Light("산책로", "조명1", 80));
		sensor1.start();
		
		LightSensor sensor2 = new LightSensor("산책로", "조도2", value1);
		//sensor2.setDaemon(true);
		sensor2.setOnChangeListener(new Light("산책로", "조명2", 80));
		sensor2.start();
		

		LightSensor sensor3 = new LightSensor("산책로", "조도3", value1);
		//sensor3.setDaemon(true);
		sensor3.setOnChangeListener(new Light("산책로", "조명3", 80));
		sensor3.start();		

	}
}



