package edu.iot.gateway.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.gateway.dao.SensorDao;
import edu.iot.gateway.handler.MonitoringHandler;
import edu.iot.gateway.model.LocationStatus;
import edu.iot.gateway.model.Sensor;

@Service("sensorService")
public class SensorServiceSqliteImpl implements SensorService {
	@Autowired
	SensorDao dao;
	
	Map<String, LocationStatus> locationMap;

	@Autowired
	MonitoringHandler handler;

	public SensorServiceSqliteImpl() {
		locationMap = new HashMap<>();
	}

	@Override
	public Map<String, LocationStatus> getLocationStatus() {
		return locationMap;
	}


	@Override
	public List<Sensor> getList() throws Exception {
		return dao.list();
	}
	
	@Override
	public Sensor get(int id)  throws Exception{
		return dao.get(id);
	}
	
	@Override
	public boolean insert(Sensor sensor) throws Exception {
		LocationStatus location = locationMap.get(sensor.getLocation());
		if(location == null) 
			location = new LocationStatus(sensor.getLocation());
		location.put(sensor);
		locationMap.put(location.getLocation(), location);
		// 값 전달
		handler.send(locationMap);
		
		return dao.insert(sensor)==1;
	}
	
	@Override
	public boolean update(Sensor sensor) throws Exception {
		return dao.update(sensor) ==1;
	}
	
	@Override
	public boolean delete(int id)  throws Exception{
		return dao.delete(id) ==1;
	}
}
