package se.atornblad.swolley.volley;

import java.util.Set;

public class ReturnStatement extends GeneratedStatement {
	
	private GeneratedExpression expression;
	
	public ReturnStatement(GeneratedExpression expression) {
		this.expression = expression;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append("return ");
		expression.generateJava(builder, indentation, imports);
		builder.append(";");
	}

}
