package se.atornblad.swolley.volley;

import java.util.Set;

public class ReferenceExpression extends GeneratedExpression {
	private GeneratedExpression target;
	private String identifier;
	
	public final static ReferenceExpression THIS = new ReferenceExpression("this");
	public final static ReferenceExpression NULL = new ReferenceExpression("null");
	
	public ReferenceExpression(String identifier) {
		this.identifier = identifier;
		this.target = null;
	}
	
	public ReferenceExpression(GeneratedExpression target, String identifier) {
		this.target = target;
		this.identifier = identifier;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		if (target != null) {
			target.generateJava(builder, indentation, imports);
			builder.append(".");
		}
		
		builder.append(identifier);
	}
}
