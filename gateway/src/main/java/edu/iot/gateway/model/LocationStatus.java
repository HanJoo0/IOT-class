package edu.iot.gateway.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class LocationStatus {
	private String location;
	Map<String, DeviceGroup> deviceGroup;
	
	public LocationStatus(String location) {
		super();
		this.location = location;
		deviceGroup = new HashMap<>();
	}

	public void put(Sensor sensor) {
		DeviceGroup group = deviceGroup.get(sensor.getType());
		if(group==null) group = new DeviceGroup(sensor.getType());
		group.put(sensor);
		deviceGroup.put(group.getGroupName(), group);
	}

}
