package se.atornblad.swolley.json;

import java.io.IOException;

import se.atornblad.swolley.io.PeekableReader;

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
		JSONNumberParser parser = new JSONNumberParser(reader);
		return parser.parse();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof JSONNumber)) return false;
		JSONNumber num = (JSONNumber)obj;
		return num.value == value;
	}
}
