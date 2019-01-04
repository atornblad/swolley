package se.atornblad.swolley.volley;

public class GeneratedClass {
	private String name;
	private GeneratedPackage containingPackage;
	private String fullName;
	
	public GeneratedClass(String name, GeneratedPackage containingPackage) {
		this.name = name;
		this.containingPackage = containingPackage;
		
		if (containingPackage == null) {
			this.fullName = name;
		}
		else {
			this.fullName = containingPackage.getFullName() + "." + name;
			containingPackage.addClass(this);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeneratedPackage getContainingPackage() {
		return containingPackage;
	}

	public void setContainingPackage(GeneratedPackage containingPackage) {
		this.containingPackage = containingPackage;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String renderJava() {
		StringBuilder builder = new StringBuilder();
		if (containingPackage != null) {
			builder.append("package ");
			builder.append(containingPackage.getFullName());
			builder.append(";\n\n");
			builder.append("public class ");
			builder.append(name);
			builder.append(" {\n");
			builder.append("    \n");
			builder.append("}\n");
		}
		return builder.toString();
	}
}
