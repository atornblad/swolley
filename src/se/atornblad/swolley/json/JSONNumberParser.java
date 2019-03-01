package se.atornblad.swolley.json;

import java.io.IOException;
import java.math.BigDecimal;

import se.atornblad.swolley.io.PeekableReader;

public class JSONNumberParser {
	private boolean negative = false;
	private BigDecimal value = BigDecimal.ZERO;
	private BigDecimal mult = BigDecimal.ONE;
	private boolean negativeExponent = false;
	private int exponent = 0;
	private PeekableReader reader;
	
	public JSONNumberParser(PeekableReader reader) {
		this.reader = reader;
	}
	
	public JSONNumber parse() throws IOException, InvalidJSONException {
		Character c = reader.peek();
		if (c == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of number");
		}
		
		c = parseOptionalInitialSign(c);
		c = parseIntegerPart(c);
		if (c == null || c != '.' && c != 'e' && c != 'E') {
			return new JSONNumber(optionalNegate(negative, value));
		}
		c = parseFractionPart(c);
		if (c == null || c != 'e' && c != 'E') {
			return new JSONNumber(optionalNegate(negative, value));
		}
		parseExponentPart(c);
		handleExponent();
		
		return new JSONNumber(optionalNegate(negative, value));
	}

	private void handleExponent() {
		if (exponent != 0) {
			if (negativeExponent) {
				value = value.divide(BigDecimal.TEN.pow(exponent));
			}
			else {
				value = value.multiply(BigDecimal.TEN.pow(exponent));
			}
		}
	}

	private void parseExponentPart(Character c) throws IOException {
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
	}

	private Character parseFractionPart(Character c) throws IOException {
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
		return c;
	}

	private Character parseIntegerPart(Character c) throws IOException {
		// read integer part of value
		while (c != null && c >= '0' && c <= '9') {
			c = reader.readChar();
			value = value.multiply(BigDecimal.TEN).add(new BigDecimal(c - '0'));
			c = reader.peek();
		}
		return c;
	}

	private Character parseOptionalInitialSign(Character c) throws IOException {
		if (c == '-') {
			negative = true;
			reader.readChar();
			c = reader.peek();
		}
		else if (c == '+') {
			reader.readChar();
			c = reader.peek();
		}
		return c;
	}
	
	private static double optionalNegate(boolean negative, BigDecimal value) {
		if (negative) {
			return value.negate().doubleValue();
		}
		else {
			return value.doubleValue();
		}
	}
}
