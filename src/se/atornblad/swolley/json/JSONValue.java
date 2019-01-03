package se.atornblad.swolley.json;

import java.io.IOException;
import java.io.Reader;

import sw.atornblad.swolley.io.PeekableReader;

public abstract class JSONValue {
	
	public static JSONValue read(PeekableReader reader) throws IOException, InvalidJSONException {
		reader.skipWhitespace();
		Character c = reader.peek();
		if (c == null) {
			return null;
		}
		switch (c) {
			case '{':
				return JSONObject.readObject(reader);
			case '[':
				return JSONArray.readArray(reader);
			case '"':
				return JSONString.readString(reader);
			case '\'':
				return JSONString.readString(reader);
			default:
				if (JSONNumber.isNumberStart(c)) {
					return JSONNumber.readNumber(reader);
				}
				if (Character.isLetter(c) || c == '@' || c == '_') {
					return JSONValue.readIdentifier(reader);
				}
		}
		
		return null;
	}
	
	private static JSONValue readIdentifier(PeekableReader reader) throws IOException, InvalidJSONException {
		StringBuilder builder = new StringBuilder();
		
		reader.skipWhitespace();
		Character c = reader.peek();
		if (c == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of identifier");
		}
		if (Character.isLetter(c) || c == '@' || c == '_') {
			while (c != null && (Character.isLetter(c) || Character.isDigit(c) || c == '@' || c == '_')) {
				builder.append(reader.readChar());
				c = reader.peek();
			}
		}
		String value = builder.toString();
		if ("true".equals(value)) {
			return new JSONBoolean(true);
		}
		else if ("false".equals(value)) {
			return new JSONBoolean(false);
		}
		else if ("null".equals(value)) {
			return new JSONNull();
		}
		else if ("NaN".equals(value)) {
			return new JSONNumber(Double.NaN);
		}
		else {
			throw new InvalidJSONException("Unknown identifier " + value);
		}
	}

	public String toJSON() {
		return this.toJSON(0);
	}
	
	public abstract String toJSON(int indentation);
}
