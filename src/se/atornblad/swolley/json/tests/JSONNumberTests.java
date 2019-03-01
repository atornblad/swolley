package se.atornblad.swolley.json.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import se.atornblad.swolley.io.PeekableReader;
import se.atornblad.swolley.json.InvalidJSONException;
import se.atornblad.swolley.json.JSONNumber;

public class JSONNumberTests {
	
	public JSONNumber read(String value) throws IOException, InvalidJSONException {
		try (StringReader reader = new StringReader(value)) {
			try (PeekableReader peeker = new PeekableReader(reader)) {
				return JSONNumber.readNumber(peeker);
			}
		}
	}
	
	@Test
	public void testEmptyStringThrows() {
		assertThrows(InvalidJSONException.class, () -> read(""));
	}

	@ParameterizedTest
	@MethodSource("numberValues")
	public void testReadNumberValue(String source, double expected, String message) throws IOException, InvalidJSONException {
		double value = read(source).getValue();
		assertEquals(expected, value, 0.00000001, message);
	}
	
	public static Stream<Arguments> numberValues() {
		return Stream.of(
			Arguments.of("0", 0.0, "Zero"),
			Arguments.of("+0", 0.0, "Positive Zero"),
			Arguments.of("-0", 0.0, "Negative Zero"),
			Arguments.of("0.0", 0.0, "Zero point Zero"),
			Arguments.of("+0.0", 0.0, "Positive Zero point Zero"),
			Arguments.of("-0.0", 0.0, "Negative Zero point Zero"),
			Arguments.of(".0", 0.0, "point Zero"),
			Arguments.of("+.0", 0.0, "Positive point Zero"),
			Arguments.of("-.0", 0.0, "Negative point Zero"),
			Arguments.of("1", 1.0, "One"),
			Arguments.of("+1", 1.0, "Positive One"),
			Arguments.of("-1", -1.0, "Negative One"),
			Arguments.of("1.0", 1.0, "One point Zero"),
			Arguments.of("+1.0", 1.0, "Positive One point Zero"),
			Arguments.of("-1.0", -1.0, "Negative One point Zero"),
			Arguments.of(".5", 0.5, "point Five"),
			Arguments.of("+.5", 0.5, "Positive point Five"),
			Arguments.of("-.5", -0.5, "Negative point Five"),
			Arguments.of("0.5", 0.5, "Zero point Five"),
			Arguments.of("+0.5", 0.5, "Positive Zero point Five"),
			Arguments.of("-0.5", -0.5, "Negative Zero point Five"),
			Arguments.of("12.345", 12.345, "Twelve point Three Four Five"),
			Arguments.of("+12.345", 12.345, "Positive Twelve point Three Four Five"),
			Arguments.of("-12.345", -12.345, "Negative Twelve point Three Four Five"),
			Arguments.of(".0e-7", 0.0, "point Zero Exponent Negative Seven"),
			Arguments.of("-314.15e-2", -3.1415, "Negative Three One Four point One Five Exponent Negative Two"),
			Arguments.of("0.000314159265358979e+4", 3.14159265358979, "PI as strange exponent")
		);
	}
}
