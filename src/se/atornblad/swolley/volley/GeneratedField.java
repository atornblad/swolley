package se.atornblad.swolley.volley;

import java.util.Set;

public class GeneratedField implements Comparable<GeneratedField> {
	private String name;
	private GeneratedClass type;
	private String jsdocDescription;
	
	public GeneratedField(String name, GeneratedClass type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public GeneratedClass getType() {
		return type;
	}
	
	public boolean hasJsdoc() {
		return jsdocDescription != null && !jsdocDescription.equals("");
	}
	
	public String getJsdoc() {
		return jsdocDescription;
	}
	
	public void setJsdoc(String jsdoc) {
		this.jsdocDescription = jsdoc;
	}

	@Override
	public int compareTo(GeneratedField arg0) {
		return name.compareTo(arg0.name);
	}

	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append(indentation);
		builder.append("private ");
		builder.append(type.getFullName(imports));
		builder.append(" ");
		builder.append(name);
		builder.append(";\n");
	}
	
	public void generateParameterJava(StringBuilder builder, Set<GeneratedClass> imports) {
		builder.append(type.getFullName(imports));
		builder.append(" ");
		builder.append(name);
	}

	public void generateGetterJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		String methodName = "get" + CodeGenerator.capitalizeFirst(name);
		builder.append(indentation);
		builder.append("public ");
		builder.append(type.getFullName(imports));
		builder.append(" ");
		builder.append(methodName);
		builder.append("() {\n");
		builder.append(indentation);
		builder.append("  ");
		builder.append("return this.");
		builder.append(name);
		builder.append(";\n");
		builder.append(indentation);
		builder.append("}\n");
	}

	public void generateSetterJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		String methodName = "set" + CodeGenerator.capitalizeFirst(name);
		builder.append(indentation);
		builder.append("public void ");
		builder.append(methodName);
		builder.append("(");
		builder.append(type.getFullName(imports));
		builder.append(" newValue) {\n");
		builder.append(indentation);
		builder.append("  ");
		builder.append("this.");
		builder.append(name);
		builder.append(" = newValue;\n");
		builder.append(indentation);
		builder.append("}\n");
	}
}
