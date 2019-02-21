package se.atornblad.swolley.volley;

import java.util.Set;

public abstract class BinaryExpression extends GeneratedExpression {
	private GeneratedExpression left;
	private GeneratedExpression right;
	protected String operator;
	
	public BinaryExpression(GeneratedExpression left, GeneratedExpression right, String operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		left.generateJava(builder, indentation, imports);
		builder.append(" ");
		builder.append(operator);
		builder.append(" ");
		right.generateJava(builder, indentation, imports);
	}
}
