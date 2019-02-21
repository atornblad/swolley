package se.atornblad.swolley.volley;

import java.util.Set;

public class CastExpression extends GeneratedExpression {

	private GeneratedClass targetType;
	private GeneratedExpression expression;
	
	public CastExpression(GeneratedClass targetType, GeneratedExpression expression) {
		this.targetType = targetType;
		this.expression = expression;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append("(");
		builder.append(targetType.getFullName());
		builder.append(")(");
		expression.generateJava(builder,  indentation,  imports);
		builder.append("))");
	}

}
