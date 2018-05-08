package edu.iot.gateway.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DeviceGroup {
	private String groupName;
	private Map<String, Sensor> group;
	
	public DeviceGroup(String groupName) {
		super();
		this.groupName = groupName;
		group = new HashMap<>();
	}
	
	public void put(Sensor sensor) {
		group.put(sensor.getName(), sensor);
	}
	
}
