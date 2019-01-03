package se.atornblad.swolley.json;

import java.io.IOException;
import java.math.BigDecimal;

import se.atornblad.swolley.io.PeekableReader;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JSONNumber extends JSONValue {
	
	private double value;
	
	public JSONNumber(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public String toJSON(int indentation) {
		return Double.toString(value);
	}

	public static boolean isNumberStart(char c) {
		return (c >= '0' && c <= '9') || c == '-' || c == '+' || c == '.';
	}

	public static JSONNumber readNumber(PeekableReader reader) throws IOException, InvalidJSONException {
		boolean negative = false;
		BigDecimal value = BigDecimal.ZERO;
		BigDecimal mult = BigDecimal.ONE;
		boolean negativeExponent = false;
		int exponent = 0;
		
		Character c = reader.peek();
		if (c == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of number");
		}
		if (c == '-') {
			negative = true;
			reader.readChar();
			c = reader.peek();
		}
		else if (c == '+') {
			reader.readChar();
			c = reader.peek();
		}
		
		// read integer part of value
		while (c != null && c >= '0' && c <= '9') {
			c = reader.readChar();
			value = value.multiply(BigDecimal.TEN).add(new BigDecimal(c - '0'));
			c = reader.peek();
		}
		
		if (c == null || c != '.' && c != 'e' && c != 'E') {
			return new JSONNumber(optionalNegate(negative, value));
		}
		
		// read decimal part of value
		if (c == '.') {
			reader.readChar();
			mult = mult.divide(BigDecimal.TEN);
			c = reader.peek();
			while (c != null && c >= '0' && c <= '9') {
				c = reader.readChar();
				value = value.add(mult.multiply(new BigDecimal(c - '0')));
				mult = mult.divide(BigDecimal.TEN);
				c = reader.peek();
			}
		}
		
		if (c == null || c != 'e' && c != 'E') {
			return new JSONNumber(optionalNegate(negative, value));
		}
		
		if (c == 'e' || c == 'E') {
			c = reader.readChar();
			c = reader.peek();
			if (c == '-') {
				c = reader.readChar();
				negativeExponent = true;
				c = reader.peek();
			}
			else if (c == '+') {
				c = reader.readChar();
				c = reader.peek();
			}
			while (c != null && c >= '0' && c <= '9') {
				c = reader.readChar();
				exponent = exponent * 10 + (c - '0');
				c = reader.peek();
			}
		}
		
		if (exponent != 0) {
			if (negativeExponent) {
				value = value.divide(BigDecimal.TEN.pow(exponent));
			}
			else {
				value = value.multiply(BigDecimal.TEN.pow(exponent));
			}
		}
		
		return new JSONNumber(optionalNegate(negative, value));
	}

	private static double optionalNegate(boolean negative, BigDecimal value) {
		if (negative) {
			return value.negate().doubleValue();
		}
		else {
			return value.doubleValue();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof JSONNumber)) return false;
		JSONNumber num = (JSONNumber)obj;
		return num.value == value;
	}
}
