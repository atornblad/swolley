package se.atornblad.swolley.volley;

import java.util.Set;
import java.util.TreeSet;

public class GeneratedClass implements Comparable<GeneratedClass> {
	private String name;
	private GeneratedPackage containingPackage;
	private String fullName;
	private Set<GeneratedClass> imports;
	
	public static GeneratedClass volleyRequest = GeneratedClass.createFromFullName("com.android.volley.Request");
	public static GeneratedClass volleyResponse = GeneratedClass.createFromFullName("com.android.volley.Response");
	
	public GeneratedClass(String name, GeneratedPackage containingPackage) {
		this.name = name;
		this.containingPackage = containingPackage;
		this.imports = new TreeSet<>();
		
		if (containingPackage == null) {
			this.fullName = name;
		}
		else {
			this.fullName = containingPackage.getFullName() + "." + name;
			containingPackage.addClass(this);
		}
	}
	
	public static GeneratedClass createFromFullName(String fullClassName) {
		int lastDot = fullClassName.lastIndexOf('.');
		if (lastDot == -1) {
			return new GeneratedClass(fullClassName, null);
		}
		else {
			return new GeneratedClass(fullClassName.substring(lastDot + 1), GeneratedPackage.fromPackageName(fullClassName.substring(0,  lastDot)));
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
			if (imports.size() > 0) {
				for (GeneratedClass clazz : imports) {
					builder.append("import ");
					builder.append(clazz.getFullName());
					builder.append(";\n");
				}
				builder.append("\n");
			}
			builder.append("public class ");
			builder.append(name);
			builder.append(" {\n");
			builder.append("    \n");
			builder.append("}\n");
		}
		return builder.toString();
	}
	
	public void addImport(GeneratedClass clazz) {
		imports.add(clazz);
	}

	@Override
	public int compareTo(GeneratedClass arg0) {
		return fullName.compareTo(arg0.fullName);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o instanceof GeneratedClass) {
			GeneratedClass c = (GeneratedClass)o;
			return fullName.equals(c.fullName);
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return ("GeneratedClass:" + fullName).hashCode();
	}
}
