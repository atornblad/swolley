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

}
