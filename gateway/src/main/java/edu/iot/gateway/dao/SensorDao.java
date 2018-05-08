package edu.iot.gateway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import edu.iot.gateway.model.Sensor;

public interface SensorDao {
	List<Sensor> list() throws Exception;

	Sensor get(int id) throws Exception;
	
	int insert(Sensor sensor) throws Exception;

	int update(Sensor sensor) throws Exception;
	
	int delete(int id) throws Exception;
}
