package edu.iot.gateway.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.iot.gateway.model.Sensor;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:servlet-context.xml",
	"classpath:config/database-context.xml"
})
public class SensorDaoTest {
	@Autowired
	SensorDao dao;
	
	int testId;
	
	@Before
	public void befor() {
		testId = 2;		// 추출, 업데트, 삭제 id
	}

	@Test
	public void testDaoDI() {
		assert dao != null : "Dao 생성 실패";
	}	

	@Test
	public void testInsert() throws Exception {
		Sensor sensor = new Sensor(0, "조도1", 20, "조도", 
				"거실", "2018-04-24");
		assert dao.insert(sensor) == 1 : "dao.insert() 실패";
	}
	
	@Test
	public void testList() throws Exception {
		assert dao.list() != null : "dao.list() 실패";
		System.out.println(dao.list());
	}
	
	@Test
	public void testGet() throws Exception {
		Sensor sensor = dao.get(testId);
		assert sensor != null : "dao.get() 실패";
		System.out.println(sensor);
	}
	
	@Test
	public void testUpdate() throws Exception {
		Sensor sensor = dao.get(testId);
		sensor.setLocation("욕실");
		assert dao.update(sensor) == 1 : "dao.update() 실패";
	}
	
	
	@Test
	public void testDelete() throws Exception {
		assert dao.delete(1) == testId : "dao.delete() 실패";
	}
}
