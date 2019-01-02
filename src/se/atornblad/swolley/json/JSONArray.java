package se.atornblad.swolley.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import sw.atornblad.swolley.io.PeekableReader;

public class JSONArray extends JSONValue {
	private ArrayList<JSONValue> items;
	
	public JSONArray() {
		items = new ArrayList<JSONValue>();
	}
	
	public void add(JSONValue value) {
		items.add(value);
	}
	
	public JSONValue get(int index) {
		return items.get(index);
	}
	
	public int size() {
		return items.size();
	}

	public static JSONArray readArray(PeekableReader peeker) throws IOException, InvalidJSONException {
		JSONArray arr = new JSONArray();
		
		peeker.skipWhitespace();
		
		Character bracket = peeker.peek();
		if (bracket == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of array");
		}
		bracket = peeker.readChar();
		if (bracket != '[') {
			throw new InvalidJSONException("Unexpected character '" + bracket + "' - expected start of object");
		}
		
		peeker.skipWhitespace();
		while (peeker.peek() != ']') {
			JSONValue value = JSONValue.read(peeker);
			arr.add(value);;
			peeker.skipWhitespace();
			if (peeker.peek() == ',') {
				peeker.readChar();
				peeker.skipWhitespace();
			}
		}
		
		// Read final } character
		peeker.readChar();
		
		return arr;
	}


	@Override
	public String toJSON(int indentation) {
		StringBuilder builder = new StringBuilder();
		builder.append("[\n");
		indentation++;
		Iterator<JSONValue> iter = items.iterator();
		
		while (iter.hasNext()) {
			JSONValue value = iter.next();
			builder.append(String.format("%" + (indentation * 2) + "s", ""));
			builder.append(value.toJSON(indentation));
			if (iter.hasNext()) {
				builder.append(",\n");
			}
		}
		indentation--;
		builder.append("\n");
		if (indentation >= 1) builder.append(String.format("%" + (indentation * 2) + "s", ""));
		builder.append("]");
		
		return builder.toString();
	}
}
