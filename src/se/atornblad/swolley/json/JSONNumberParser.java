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
			Character nextC = reader.readChar();
			nextC = reader.peek();
			if (nextC == '-') {
				nextC = reader.readChar();
				negativeExponent = true;
				nextC = reader.peek();
			}
			else if (nextC == '+') {
				nextC = reader.readChar();
				nextC = reader.peek();
			}
			while (nextC != null && nextC >= '0' && c <= '9') {
				nextC = reader.readChar();
				exponent = exponent * 10 + (nextC - '0');
				nextC = reader.peek();
			}
		}
	}

	private Character parseFractionPart(Character c) throws IOException {
		// read decimal part of value
		Character nextC = c;
		if (nextC == '.') {
			reader.readChar();
			mult = mult.divide(BigDecimal.TEN);
			nextC = reader.peek();
			while (nextC != null && nextC >= '0' && nextC <= '9') {
				nextC = reader.readChar();
				value = value.add(mult.multiply(new BigDecimal(nextC - '0')));
				mult = mult.divide(BigDecimal.TEN);
				nextC = reader.peek();
			}
		}
		return nextC;
	}

	private Character parseIntegerPart(Character c) throws IOException {
		// read integer part of value
		Character nextC = c;
		while (nextC != null && nextC >= '0' && nextC <= '9') {
			nextC = reader.readChar();
			value = value.multiply(BigDecimal.TEN).add(new BigDecimal(nextC - '0'));
			nextC = reader.peek();
		}
		return nextC;
	}

	private Character parseOptionalInitialSign(Character c) throws IOException {
		Character nextC = c;
		if (nextC == '-') {
			negative = true;
			reader.readChar();
			nextC = reader.peek();
		}
		else if (nextC == '+') {
			reader.readChar();
			nextC = reader.peek();
		}
		return nextC;
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
