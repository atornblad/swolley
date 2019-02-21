package se.atornblad.swolley.volley;

import java.util.Set;

import se.atornblad.swolley.json.JSONString;

public class StringLiteralExpression extends LiteralExpression {

	private String value;
	
	public StringLiteralExpression(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		JSONString temp = new JSONString(value);
		builder.append(temp.toJSON());
	}

}
