package se.atornblad.swolley.volley;

import java.util.Set;

public class GeneratedVariable extends GeneratedStatement {
	private GeneratedClass type;
	private String name;
	private GeneratedExpression initializer;
	
	public GeneratedVariable(GeneratedClass type, String name, GeneratedExpression initializer) {
		this.type = type;
		this.name = name;
		this.initializer = initializer;
	}
	
	public GeneratedVariable(GeneratedClass type, String name) {
		this(type, name, null);
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append(type.getFullName(imports));
		builder.append(" ");
		builder.append(name);
		if (initializer != null) {
			builder.append(" = ");
			initializer.generateJava(builder, indentation, imports);
		}
		builder.append(";");
	}

	public GeneratedClass getType() {
		return type;
	}

	public void setType(GeneratedClass type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeneratedExpression getInitializer() {
		return initializer;
	}

	public void setInitializer(GeneratedExpression initializer) {
		this.initializer = initializer;
	}
	
	
}
