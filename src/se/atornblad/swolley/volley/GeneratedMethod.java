package se.atornblad.swolley.volley;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import se.atornblad.swolley.swagger.Action;
import se.atornblad.swolley.swagger.Document;
import se.atornblad.swolley.swagger.Parameter;
import se.atornblad.swolley.swagger.Path;

public class GeneratedMethod {
	private String name;
	private boolean isPublic;
	private boolean isStatic;
	private GeneratedClass returnType;
	private List<GeneratedField> parameters;
	private BlockStatement body;
	private String jsdocDescription;
	
	protected GeneratedMethod() {
		parameters = new ArrayList<>();
		body = new BlockStatement();
	}
	
	public GeneratedMethod(GeneratedClass returnType, String name) {
		this();
		this.returnType = returnType;
		this.name = name;
		
		if (returnType == null) {
			this.jsdocDescription = "Creates a new " + name + " object";
		}
	}
	
	public static GeneratedMethod createAccessMethod(String name, Document doc, GeneratedPackage rootPackage, Path path, Action action, String httpMethod, String pathKey) {
		GeneratedMethod method = new GeneratedMethod();
		method.name = name;
		method.isPublic = true;
		method.returnType = GeneratedClass.VOID;
		if (action.hasDescription()) {
			method.jsdocDescription = action.getDescription() + "\nUses the " + httpMethod + " method to communicate with the " + pathKey + " endpoint";
		}
		else if (action.hasSummary()) {
			method.jsdocDescription = action.getSummary() + "\nUses the " + httpMethod + " method to communicate with the " + pathKey + " endpoint";
		}
		else {
			method.jsdocDescription = "Uses the " + httpMethod + " method to communicate with the " + pathKey + " endpoint";
		}
		
		// Add the input parameters
		for (Parameter parameter : action.getParameters()) {
			method.parameters.add(new GeneratedField(parameter.getName(), GeneratedClass.fromParameter(parameter, rootPackage)));
		}
		
		// Add the Consumer, if the response has a return type
		GeneratedClass returnType = GeneratedClass.fromSchema(action.getResponses().get("200").getSchema(), rootPackage);
		if (returnType == null || returnType.equals(GeneratedClass.VOID)) {
			// Use the java.lang.Runnable interface for notifying that the method call has finished without error
			method.parameters.add(new GeneratedField("callback", GeneratedClass.createFromFullName("java.lang.Runnable")));
		}
		else {
			// Use the java.lang.function.Consumer<T> for notifying that the method call has finished without error, and pass the result
			method.parameters.add(new GeneratedField("consumer", GeneratedClass.createFromFullName("java.util.function.Consumer").addGenericTypeArgument(returnType)));
		}
		method.parameters.add(new GeneratedField("errorCallback", GeneratedClass.createFromFullName("java.util.function.Consumer").addGenericTypeArgument(GeneratedClass.createFromFullName("java.lang.Exception"))));
		
		
		
		return method;
	}
	
	public void addParameter(String name, GeneratedClass parameterType) {
		parameters.add(new GeneratedField(name, parameterType));
	}

	public String getName() {
		return name;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public BlockStatement getBody() {
		return this.body;
	}

	public GeneratedClass getReturnType() {
		return returnType;
	}

	public String getJsdocDescription() {
		return jsdocDescription;
	}

	public void setJsdocDescription(String jsdocDescription) {
		this.jsdocDescription = jsdocDescription;
	}

	/**
	 * 
	 * @param builder
	 * @param indentation
	 * @param imports
	 */
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		
		if (jsdocDescription != null && !jsdocDescription.equals("")) {
			builder.append(indentation);
			builder.append("/**\n");
			for (String line : jsdocDescription.split("\n")) {
				builder.append(indentation);
				builder.append(" * ");
				builder.append(line);
				builder.append("\n");
			}
			
			for (GeneratedField parameter : parameters) {
				builder.append(indentation);
				builder.append(" * @param ");
				builder.append(parameter.getName());
				if (parameter.hasJsdoc()) {
					builder.append(" ");
					builder.append(parameter.getJsdoc());
				}
				builder.append("\n");
			}
			
			if (returnType != null && !returnType.equals(GeneratedClass.VOID)) {
				builder.append(indentation);
				builder.append(" * @return ");
				builder.append(returnType.describe());
				builder.append("\n");
			}
			
			builder.append(indentation);
			builder.append(" */\n");
		}
		
		builder.append(indentation);
		if (isPublic) {
			builder.append("public ");
		}
		else {
			builder.append("private ");
		}
		if (isStatic) {
			builder.append("static ");
		}
		if (returnType != null) {
			builder.append(returnType.getFullName(imports));
			builder.append(" ");
		}
		builder.append(name);
		builder.append("(");
		Iterator<GeneratedField> i = parameters.iterator();
		while (i.hasNext()) {
			GeneratedField parameter = i.next();
			parameter.generateParameterJava(builder, imports);
			if (i.hasNext()) {
				builder.append(", ");
			}
		}
		
		builder.append(") ");
		
		body.generateJava(builder, indentation, imports);
		
		builder.append("\n");
	}

	public String getSignature() {
		StringBuilder builder = new StringBuilder();
		if (returnType == null) {
			builder.append("_");
		}
		else {
			builder.append(returnType.getSignature());
		}
		builder.append(":");
		builder.append(name);
		for (GeneratedField field : parameters) {
			builder.append(":");
			builder.append(field.getType().getSignature());
		}
		return builder.toString();
	}

	public List<GeneratedField> getParameters() {
		return parameters;
	}
}
