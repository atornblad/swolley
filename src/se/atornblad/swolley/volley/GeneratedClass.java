package se.atornblad.swolley.volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import se.atornblad.swolley.swagger.Schema;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GeneratedClass implements Comparable<GeneratedClass> {
	private String name;
	private GeneratedPackage containingPackage;
	private String fullName;
	private Set<GeneratedClass> imports;
	private Set<GeneratedField> fields;
	private Map<String, GeneratedMethod> methods;
	private GeneratedClass itemClass;
	private boolean isArray;
	
	public static final GeneratedClass volleyRequest = GeneratedClass.createFromFullName("com.android.volley.Request");
	public static final GeneratedClass volleyRequestQueue = GeneratedClass.createFromFullName("com.android.volley.RequestQueue");
	public static final GeneratedClass volleyResponse = GeneratedClass.createFromFullName("com.android.volley.Response");
	public static final GeneratedClass VOID = GeneratedClass.createFromFullName("void");
	public static final GeneratedClass INT = GeneratedClass.createFromFullName("int");
	public static final GeneratedClass BOOLEAN = GeneratedClass.createFromFullName("boolean");
	public static final GeneratedClass FLOAT = GeneratedClass.createFromFullName("float");
	public static final GeneratedClass DOUBLE = GeneratedClass.createFromFullName("double");
	public static final GeneratedClass STRING = GeneratedClass.createFromFullName("String");
	public static final GeneratedClass OBJECT = GeneratedClass.createFromFullName("Object");
	
	public GeneratedClass(String name, GeneratedPackage containingPackage) {
		this.name = name;
		this.containingPackage = containingPackage;
		this.imports = new TreeSet<>();
		this.fields = new TreeSet<>();
		this.methods = new TreeMap<>();
		this.itemClass = null;
		this.isArray = false;
		
		if (containingPackage == null) {
			this.fullName = name;
		}
		else {
			this.fullName = containingPackage.getFullName() + "." + name;
			containingPackage.addClass(this);
		}
	}
	
	private GeneratedClass() {
		this(null, null);
	}
	
	public static GeneratedClass createArrayOf(GeneratedClass itemType) {
		GeneratedClass arrayType = new GeneratedClass();
		arrayType.isArray = true;
		arrayType.itemClass = itemType;
		arrayType.fullName = itemType.fullName + "[]";
		return arrayType;
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

	public String getFullName(Set<GeneratedClass> imports) {
		if (this.containingPackage == null) return fullName;
		if (imports.contains(this)) return name;
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String renderJava() {
		return renderJava(imports);
	}
	
	public String renderJava(Set<GeneratedClass> imports) {
		StringBuilder builder = new StringBuilder();
		builder.append("// This code was auto-generated from Swagger API documentation by Swolley v0.9.14\n");
		builder.append("// Please do not change this code; it might be regenerated whenever\n");
		builder.append("\n");
		
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
			
			builder.append("/**\n");
			builder.append(" * @author swolley-v0.9.14\n");
			builder.append(" * @version " + (new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime())) + "\n");
			builder.append(" */\n");
			builder.append("public class ");
			builder.append(name);
			builder.append(" {\n");
			builder.append("    \n");
			
			if (fields.size() > 0) {
				for (GeneratedField field : fields) {
					field.generateJava(builder, "    ", imports);
				}
				builder.append("\n");
			}
			
			builder.append("    public ");
			builder.append(name);
			builder.append("() {\n");
			builder.append("    }\n");
			builder.append("\n");
			
			if (fields.size() > 0) {
				for (GeneratedField field : fields) {
					field.generateGetterJava(builder, "    ", imports);
					field.generateSetterJava(builder, "    ", imports);
				}
				builder.append("\n");
			}
			
			if (methods.size() > 0) {
				for (Entry<String, GeneratedMethod> entry : methods.entrySet()) {
					entry.getValue().generateJava(builder, "    ", imports);
				}
				builder.append("\n");
			}
			
			builder.append("}\n");
		}
		return builder.toString();
	}
	
	public void addImport(GeneratedClass clazz) {
		imports.add(clazz);
	}
	
	public void addField(String name, GeneratedClass clazz) {
		fields.add(new GeneratedField(name, clazz));
	}
	
	public boolean hasMethod(String name) {
		return methods.containsKey(name);
	}
	
	public void addMethod(GeneratedMethod method) {
		methods.put(method.getName(), method);
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

	public static GeneratedClass fromSchema(Schema value, GeneratedPackage rootPackage) {
		if (value == null) {
			return GeneratedClass.VOID;
		}
		if (value.getType() == null) {
			String ref = value.get$ref();
			if (ref != null) {
				if (ref.startsWith("#/definitions/")) {
					GeneratedPackage modelsPackage = rootPackage.createOrGetSubPackage("models");
					return modelsPackage.createOrGetClass(ref.substring(14));
				}
			}
		}
		if ("object".equals(value.getType())) {
			return GeneratedClass.OBJECT;
		}
		if ("string".equals(value.getType())) {
			return GeneratedClass.STRING;
		}
		if ("boolean".equals(value.getType())) {
			return GeneratedClass.BOOLEAN;
		}
		if ("array".equals(value.getType())) {
			return GeneratedClass.createArrayOf(GeneratedClass.fromSchema(value.getItems(), rootPackage));
		}
		if ("integer".equals(value.getType())) {
			if (value.getFormat().equals("int32")) {
				return GeneratedClass.INT;
			}
		}
		System.out.println("Cannot yet create GeneratedClass from Schema where type = " + value.getType());
		throw new NotImplementedException();
	}
}
