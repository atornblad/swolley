package se.atornblad.swolley.json;

import java.io.IOException;

import se.atornblad.swolley.io.PeekableReader;

public class JSONString extends JSONValue {
	private String value;
	
	public JSONString() {
		value = null;
	}
	
	public JSONString(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}

	public static JSONString readString(PeekableReader peeker) throws IOException, InvalidJSONException {
		// Skip whitespace
		while (Character.isWhitespace(peeker.peek())) {
			peeker.readChar();
		}
		
		Character quote = peeker.peek();
		if (quote == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of string");
		}
		
		quote = peeker.readChar();
		if (quote != '"' && quote != '\'') {
			throw new InvalidJSONException("Unexpected character '" + quote + "' - expected start of string");
		}
		
		StringBuilder builder = new StringBuilder();
		Character c = peeker.peek();
		while (c != null && !c.equals(quote)) {
			builder.append(peeker.readEscapedChar());
			c = peeker.peek();
		}
		
		if (c == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected string continuation");
		}
		
		// Read final " or ' character
		peeker.readChar();
		
		return new JSONString(builder.toString());
	}

	@Override
	public String toJSON(int indentation) {
		if (value == null) {
			return "null";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		value.chars().forEach(c -> {
			switch (c) {
			case '"':
				builder.append("\\\"");
				break;
			case '\r':
				builder.append("\\r");
				break;
			case '\n':
				builder.append("\\n");
				break;
			case '\t':
				builder.append("\\t");
				break;
			default:
				if (c >= ' ' && c <= '}') {
					builder.append((char)c);
				}
				else {
					builder.append("\\u" + String.format("%04x",  (int)c));
				}
			}
		});
		builder.append("\"");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof JSONString)) return false;
		JSONString str = (JSONString)obj;
		return str.value.equals(value);
	}
}
