package se.atornblad.swolley.json;

public class JSONNull extends JSONValue {

	@Override
	public String toJSON(int indentation) {
		return "null";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return obj instanceof JSONNull;
	}
}
