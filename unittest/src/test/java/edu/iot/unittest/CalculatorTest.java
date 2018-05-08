package edu.iot.unittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	Calculator calculator;
	 
	@Before
	public void before( ) {
		calculator = new Calculator();
	}

	@Test
	public void testAdd() {
		double result = calculator.add(10, 50);
		assertEquals(60, result, 0);	// 인자 : 예상값, 실제값, 허용 오차
	}
	
	@Test
	public void testSub() {
		double result = calculator.sub(10, 50);
		assert result == -40 : "10-50은 -40이여야 합니다.";
	}
}
