package com.capgemini.junit.wks.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MathHelperTest {

	@Test
	void testGetDecimalFromString() {
		assertEquals(new BigDecimal("23.33"), MathHelper.getDecimal("23.33"));
	}
	
	@Test
	void testGetDecimalFromStringThrowsNFE() {
		assertThrows(NumberFormatException.class, () -> {
			MathHelper.getDecimal("23,33");
		});
	}
	
	@Test
	void testGetDecimalFromStringThrowsNPE() {
		assertThrows(NullPointerException.class, () -> {
			MathHelper.getDecimal(null);
		});
	}
	
	@Test
	void getDecimalFromInt() {
		assertEquals(new BigDecimal(11), MathHelper.getDecimal(11));
	}
		
	@ParameterizedTest()
	@CsvSource({"1.1, 22.22, 23.32",
		" , -1, -1",
		"1.1, , 1.1",
		", , 0"})
	void testAdd(BigDecimal a, BigDecimal b, BigDecimal expected) {
		assertEquals(expected, MathHelper.add(a, b));
	}
	
	@ParameterizedTest()
	@CsvSource({"1.1, 10, 11.0",
		" , -1, 0",
		"1.1, , 0.0",
		"1.1, -1.1, -1.21",
		", , 0"})
	void testMultiplyBigDecimals(BigDecimal a, BigDecimal b, BigDecimal expected) {
		assertEquals(expected, MathHelper.multiply(a, b));
	}
	
	@ParameterizedTest()
	@CsvSource({"1.1, 2, 2.2",
		"0, 1, 0",
		"1, 0, 0",
		"1.1, -2, -2.2",
		"-1.1, 2, -2.2",
		"-1.1, -2, 2.2",
		", 10, 0"})
	void testMultiplyBigDecimalAndInt(BigDecimal a, int b, BigDecimal expected) {
		assertEquals(expected, MathHelper.multiply(a, b));
	}
}
