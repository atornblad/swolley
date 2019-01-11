package se.atornblad.swolley.volley;

import java.util.Set;

public class GeneratedField implements Comparable<GeneratedField> {
	private String name;
	private GeneratedClass type;
	
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

	public void generateGetterJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append(indentation);
		builder.append("public ");
		builder.append(type.getFullName(imports));
		builder.append(" get");
		builder.append(CodeGenerator.capitalizeFirst(name));
		builder.append("() {\n");
		builder.append(indentation);
		builder.append(indentation);
		builder.append("return this.");
		builder.append(name);
		builder.append(";\n");
		builder.append(indentation);
		builder.append("}\n");
		builder.append("\n");
	}

	public void generateSetterJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append(indentation);
		builder.append("public void set");
		builder.append(CodeGenerator.capitalizeFirst(name));
		builder.append("(");
		builder.append(type.getFullName(imports));
		builder.append(" newValue) {\n");
		builder.append(indentation);
		builder.append(indentation);
		builder.append("this.");
		builder.append(name);
		builder.append(" = newValue;\n");
		builder.append(indentation);
		builder.append("}\n");
	}
}
