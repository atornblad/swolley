package se.atornblad.swolley.volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import se.atornblad.swolley.swagger.Action;
import se.atornblad.swolley.swagger.Document;
import se.atornblad.swolley.swagger.Path;

public class GeneratedMethod {
	private String name;
	private boolean isPublic;
	private GeneratedClass returnType;
	private List<GeneratedField> parameters;
	
	public GeneratedMethod() {
		parameters = new ArrayList<GeneratedField>();
	}
	
	public static GeneratedMethod createAccessMethod(String name, Document doc, GeneratedPackage rootPackage, Path path, Action action, String httpMethod, String pathKey) {
		GeneratedMethod method = new GeneratedMethod();
		method.name = name;
		method.isPublic = true;
		method.returnType = GeneratedClass.fromSchema(action.getResponses().get("200").getSchema(), rootPackage);
		method.parameters.add(new GeneratedField("requestQueue", GeneratedClass.volleyRequestQueue));
		
		return method;
	}

	public String getName() {
		return name;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public GeneratedClass getReturnType() {
		return returnType;
	}

	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append(indentation);
		if (isPublic) {
			builder.append("public ");
		}
		else {
			builder.append("private ");
		}
		builder.append(returnType.getFullName(imports));
		builder.append(" ");
		builder.append(name);
		builder.append("() {\n");
		builder.append(indentation);
		builder.append("}\n");
		builder.append("\n");
	}
}
