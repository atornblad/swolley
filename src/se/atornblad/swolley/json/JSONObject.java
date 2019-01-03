package se.atornblad.swolley.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import se.atornblad.swolley.io.PeekableReader;

public class JSONObject extends JSONValue {
	private HashMap<String, JSONValue> properties;
	private List<String> orderedPropertyNames;
	
	public JSONObject() {
		properties = new HashMap<>();
		orderedPropertyNames = new ArrayList<>();
	}
	
	public void set(String key, JSONValue value) {
		if (!properties.containsKey(key)) {
			orderedPropertyNames.add(key);
		}
		properties.put(key, value);
	}
	
	public JSONValue get(String key) {
		return properties.get(key);
	}
	
	public Set<String> getPropertyNames() {
		return properties.keySet();
	}
	
	public boolean containsKey(String key) {
		return properties.containsKey(key);
	}

	public static JSONObject readObject(PeekableReader peeker) throws IOException, InvalidJSONException {
		JSONObject obj = new JSONObject();
		
		peeker.skipWhitespace();
		
		Character bracket = peeker.peek();
		if (bracket == null) {
			throw new InvalidJSONException("Unexpected end of stream - expected start of object");
		}
		bracket = peeker.readChar();
		if (bracket != '{') {
			throw new InvalidJSONException("Unexpected character '" + bracket + "' - expected start of object");
		}
		
		peeker.skipWhitespace();
		while (peeker.peek() != '}') {
			JSONString key = JSONString.readString(peeker);
			peeker.skipWhitespace();
			if (peeker.peek() == null) {
				throw new InvalidJSONException("Unexpected end of stream - expected colon");
			}
			if (peeker.readChar() != ':') {
				throw new InvalidJSONException("Unexpected character '" + peeker.peek() + "' - expected colon");
			}
			peeker.skipWhitespace();
			JSONValue value = JSONValue.read(peeker);
			obj.set(key.toString(), value);
			peeker.skipWhitespace();
			if (peeker.peek() == ',') {
				peeker.readChar();
				peeker.skipWhitespace();
			}
		}
		
		// Read final } character
		peeker.readChar();
		
		return obj;
	}
	
	@Override
	public String toString() {
		return this.toJSON();
	}

	@Override
	public String toJSON(int indentation) {
		if (properties.size() == 0) return "{}";
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		indentation++;
		Iterator<String> keyIter = orderedPropertyNames.iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			builder.append(String.format("%" + (indentation * 2) + "s", ""));
			builder.append((new JSONString(key)).toJSON());
			builder.append(" : ");
			builder.append(properties.get(key).toJSON(indentation));
			if (keyIter.hasNext()) {
				builder.append(",\n");
			}
		}
		indentation--;
		builder.append("\n");
		if (indentation > 0) builder.append(String.format("%" + (indentation * 2) + "s", ""));
		builder.append("}");
		
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof JSONObject)) return false;
		JSONObject o = (JSONObject)obj;
		Set<String> remainingKeys = o.getPropertyNames();
		for (String key : properties.keySet()) {
			if (remainingKeys.contains(key)) {
				remainingKeys.remove(key);
				if (!get(key).equals(o.get(key))) return false;
			}
			else {
				return false;
			}
		}
		return remainingKeys.isEmpty();
	}
}
