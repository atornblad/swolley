package se.atornblad.swolley.json;

public class JSONBoolean extends JSONValue {
	
	private boolean value;

	public JSONBoolean(boolean b) {
		value = b;
	}

	@Override
	public String toJSON(int indentation) {
		// TODO Auto-generated method stub
		return value ? "true" : "false";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof JSONBoolean)) return false;
		JSONBoolean bool = (JSONBoolean)obj;
		return bool.value == value;
	}
}
