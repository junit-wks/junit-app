package com.capgemini.junit.wks.util;

import java.math.BigDecimal;

public final class MathHelper {
	
	public static final BigDecimal NULL_BD = BigDecimal.ZERO;

	public static BigDecimal getDecimal(String value) {
		return new BigDecimal(value);
	}
	
	public static BigDecimal getDecimal(int value) {
		return new BigDecimal(value);
	}
	
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		BigDecimal value1 = a == null? NULL_BD:a;
		BigDecimal value2 = b == null? NULL_BD:b;
		return value1.add(value2);
	}
	
	public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
		BigDecimal value1 = a == null? NULL_BD:a;
		BigDecimal value2 = b == null? NULL_BD:b;
		return value1.multiply(value2);
	}
	
	public static BigDecimal multiply(BigDecimal a, int b) {
		return multiply(a, getDecimal(b));
	}
	
	public static boolean isEqual(BigDecimal a, BigDecimal b) {
		BigDecimal value1 = a == null? NULL_BD:a;
		BigDecimal value2 = b == null? NULL_BD:b;
		return value1.compareTo(value2) == 0;
	}
	
	public static boolean isGreater(BigDecimal a, BigDecimal b) {
		BigDecimal value1 = a == null? NULL_BD:a;
		BigDecimal value2 = b == null? NULL_BD:b;
		return value1.compareTo(value2) > 0;
	}
	
	public static boolean isLess(BigDecimal a, BigDecimal b) {
		BigDecimal value1 = a == null? NULL_BD:a;
		BigDecimal value2 = b == null? NULL_BD:b;
		return value1.compareTo(value2) < 0;
	}
}
