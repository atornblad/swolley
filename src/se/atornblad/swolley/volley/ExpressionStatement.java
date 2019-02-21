package se.atornblad.swolley.volley;

import java.util.Set;

public class ExpressionStatement extends GeneratedStatement {
	private GeneratedExpression expression;
	
	public ExpressionStatement(GeneratedExpression expression) {
		this.expression = expression;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		expression.generateJava(builder, indentation, imports);
		builder.append(";");
	}
}
