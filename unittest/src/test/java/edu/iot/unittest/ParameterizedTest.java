package edu.iot.unittest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTest {
	
	@Parameters
	public static Collection<Integer[]> getTestParameters() {
		return Arrays.asList(new Integer[][] {
			// 기대값, 값1, 값2
			{2, 1, 1},
			{3, 2, 1},
			{4, 3, 1},
		});
	}
	private double expected;
	private double number1;
	private double number2;

	public ParameterizedTest(double expected, 
									double number1, double number2) {
		this.expected = expected;
		this.number1 = number1;
		this.number2 = number2;
	}
	
	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		assertEquals(expected, calculator.add(number1, number2), 0);
	}
}

