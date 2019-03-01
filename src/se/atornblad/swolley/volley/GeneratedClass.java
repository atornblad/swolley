package se.atornblad.swolley.volley;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import se.atornblad.swolley.Swolley;
import se.atornblad.swolley.swagger.Parameter;
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
	private List<GeneratedClass> genericTypeArguments;
	private boolean isArray;
	private String jsdoc;
	private boolean isSimple;
	
	public static final GeneratedClass volleyRequest = GeneratedClass.createFromFullName("com.android.volley.Request");
	public static final GeneratedClass volleyRequestQueue = GeneratedClass.createFromFullName("com.android.volley.RequestQueue");
	public static final GeneratedClass volleyResponse = GeneratedClass.createFromFullName("com.android.volley.Response");
	
	public static final GeneratedClass jsonObject = GeneratedClass.createFromFullName("org.json.JSONObject");
	public static final GeneratedClass jsonArray = GeneratedClass.createFromFullName("org.json.JSONArray");
	public static final GeneratedClass jsonException = GeneratedClass.createFromFullName("org.json.JSONException");
	
	public static final GeneratedClass VOID = GeneratedClass.createFromFullName("void");
	public static final GeneratedClass INT = GeneratedClass.createSimpleFromFullName("int");
	public static final GeneratedClass Integer = GeneratedClass.createSimpleFromFullName("java.lang.Integer");
	public static final GeneratedClass BYTE = GeneratedClass.createSimpleFromFullName("byte");
	public static final GeneratedClass BOOLEAN = GeneratedClass.createSimpleFromFullName("boolean");
	public static final GeneratedClass Boolean = GeneratedClass.createSimpleFromFullName("java.lang.Boolean");
	public static final GeneratedClass FLOAT = GeneratedClass.createSimpleFromFullName("float");
	public static final GeneratedClass Float = GeneratedClass.createSimpleFromFullName("java.lang.Float");
	public static final GeneratedClass DOUBLE = GeneratedClass.createSimpleFromFullName("double");
	public static final GeneratedClass Double = GeneratedClass.createSimpleFromFullName("java.lang.Double");
	public static final GeneratedClass STRING = GeneratedClass.createSimpleFromFullName("String");
	public static final GeneratedClass OBJECT = GeneratedClass.createSimpleFromFullName("Object");
	
	public GeneratedClass(String name, GeneratedPackage containingPackage) {
		this.name = name;
		this.containingPackage = containingPackage;
		this.imports = new TreeSet<>();
		this.fields = new TreeSet<>();
		this.methods = new TreeMap<>();
		this.itemClass = null;
		this.isArray = false;
		this.genericTypeArguments = new ArrayList<>();
		
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
	
	public String describe() {
		if (this.isArray) {
			return "an array of " + this.itemClass.describePlural();
		}
		else if (!this.genericTypeArguments.isEmpty()) {
			return "a " + this.name + " of " + genericTypeArguments.get(0).describePlural();
		}
		else if (this.equals(GeneratedClass.VOID)) {
			return "void";
		}
		else if (this.equals(GeneratedClass.INT)) {
			return "an integer";
		}
		else {
			return "a " + name + " object";
		}
	}
	
	public String describePlural() {
		if (this.isArray) {
			return "arrays of " + this.itemClass.describePlural();
		}
		else if (!this.genericTypeArguments.isEmpty()) {
			return this.name + "s of " + genericTypeArguments.get(0).describePlural();
		}
		else if (this.equals(GeneratedClass.VOID)) {
			return "void";
		}
		else if (this.equals(GeneratedClass.INT)) {
			return "integers";
		}
		else {
			return name + " objects";
		}
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
	
	public static GeneratedClass createSimpleFromFullName(String fullClassName) {
		GeneratedClass temp = createFromFullName(fullClassName);
		temp.isSimple = true;
		return temp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isArray() {
		return this.isArray;
	}
	
	public GeneratedClass getItemClass() {
		return this.itemClass;
	}
	
	public boolean isSimple() {
		return this.isSimple;
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
		if (this.isArray) return itemClass.getFullName(imports) + "[]";
		if (!this.genericTypeArguments.isEmpty()) {
			GeneratedClass temp = new GeneratedClass();
			temp.name = name;
			temp.fullName = fullName;
			temp.containingPackage = containingPackage;
			temp.imports = imports;
			StringBuilder builder = new StringBuilder();
			builder.append(temp.getFullName(imports));
			builder.append("<");
			Iterator<GeneratedClass> i = this.genericTypeArguments.iterator();
			while (i.hasNext()) {
				GeneratedClass typeArg = i.next();
				builder.append(typeArg.getFullName(imports));
				if (i.hasNext()) {
					builder.append(",");
				}
			}
			builder.append(">");
			return builder.toString();
		}
		if (this.containingPackage == null) return fullName;
		if (imports.contains(this)) return name;
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getJsdoc() {
		return jsdoc;
	}

	public void setJsdoc(String jsdoc) {
		this.jsdoc = jsdoc;
	}

	public String renderJava() {
		return renderJava(imports);
	}
	
	public String renderJava(Set<GeneratedClass> imports) {
		StringBuilder builder = new StringBuilder();
		builder.append("// This code was auto-generated from Swagger API documentation by Swolley v");
		builder.append(Swolley.SWOLLEY_VERSION);
		builder.append("\n");
		builder.append("// Please do not change this code; it might be regenerated whenever\n");
		builder.append("\n");
		
		if (containingPackage != null) {
			renderPackageDeclaration(builder);
		}
		
		renderImports(imports, builder);
		renderClassJsdoc(builder);
		renderClassDeclaration(imports, builder);
		
		return builder.toString();
	}

	private void renderClassDeclaration(Set<GeneratedClass> imports, StringBuilder builder) {
		builder.append("public class ");
		builder.append(name);
		builder.append(" {\n");
		builder.append("  \n");
		
		renderClassFields(imports, builder);
		
		renderClassMethods(imports, builder);
		
		renderGettersAndSetters(imports, builder);
		
		builder.append("}\n");
	}

	private void renderGettersAndSetters(Set<GeneratedClass> imports, StringBuilder builder) {
		if (fields.size() > 0) {
			for (GeneratedField field : fields) {
				field.generateGetterJava(builder, "  ", imports);
				field.generateSetterJava(builder, "  ", imports);
				builder.append("\n");
			}
		}
	}

	private void renderClassMethods(Set<GeneratedClass> imports, StringBuilder builder) {
		if (methods.size() > 0) {
			for (Entry<String, GeneratedMethod> entry : methods.entrySet()) {
				entry.getValue().generateJava(builder, "  ", imports);
				builder.append("\n");
			}
		}
	}

	private void renderClassFields(Set<GeneratedClass> imports, StringBuilder builder) {
		if (fields.size() > 0) {
			for (GeneratedField field : fields) {
				field.generateJava(builder, "  ", imports);
			}
			builder.append("\n");
		}
	}

	private void renderImports(Set<GeneratedClass> imports, StringBuilder builder) {
		if (imports.size() > 0) {
			for (GeneratedClass clazz : imports) {
				builder.append("import ");
				builder.append(clazz.getFullName());
				builder.append(";\n");
			}
			builder.append("\n");
		}
	}

	private void renderPackageDeclaration(StringBuilder builder) {
		builder.append("package ");
		builder.append(containingPackage.getFullName());
		builder.append(";\n\n");
	}

	private void renderClassJsdoc(StringBuilder builder) {
		builder.append("/**\n");
		if (jsdoc != null && !jsdoc.equals("")) {
			String[] lines = jsdoc.split("\n");
			for (String line : lines) {
				builder.append(" * ");
				builder.append(line);
				builder.append("\n");
			}
		}
		builder.append(" * @author swolley-v");
		builder.append(Swolley.SWOLLEY_VERSION);
		builder.append("\n");
		builder.append(" * @version " + (new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime())) + "\n");
		builder.append(" */\n");
	}
	
	public void addImport(GeneratedClass clazz) {
		GeneratedClass importClass = clazz;
		if (clazz == null) return;
		if (!clazz.genericTypeArguments.isEmpty()) {
			GeneratedClass temp = new GeneratedClass();
			temp.name = clazz.name;
			temp.fullName = clazz.fullName;
			temp.isArray = clazz.isArray;
			temp.imports = clazz.imports;
			temp.itemClass = clazz.itemClass;
			addImport(temp);
			for (GeneratedClass templArg : clazz.genericTypeArguments) {
				addImport(templArg);
			}
		}
		else {
			while (importClass != null) {
				if (importClass.isArray) {
					importClass = importClass.itemClass;
				}
				else {
					if (importClass.containingPackage != null && !importClass.isArray) {
						imports.add(importClass);
					}
					importClass = null;
				}
			}
		}
	}
	
	public void addField(String name, GeneratedClass clazz) {
		addImport(clazz);
		
		fields.add(new GeneratedField(name, clazz));
	}
	
	public boolean hasMethod(String name) {
		return methods.containsKey(name);
	}
	
	public boolean hasPublicConstructor() {
		for (GeneratedMethod method : methods.values()) {
			if (method.getName().equals(name) && method.getReturnType() == null && method.isPublic()) {
				return true;
			}
		}
		return false;
	}
	
	public void addMethod(GeneratedMethod method) {
		addImport(method.getReturnType());
		for (GeneratedField parameter : method.getParameters()) {
			addImport(parameter.getType());
		}
		methods.put(method.getSignature(), method);
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
			if (c == this) return true;
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
		else if (value.getType() == null) {
			String ref = value.get$ref();
			if (ref != null) {
				if (ref.startsWith("#/definitions/")) {
					GeneratedPackage modelsPackage = rootPackage.createOrGetSubPackage("models");
					return modelsPackage.createOrGetClass(ref.substring(14));
				}
			}
		}
		else if ("object".equals(value.getType())) {
			return GeneratedClass.OBJECT;
		}
		else if ("string".equals(value.getType())) {
			return GeneratedClass.STRING;
		}
		else if ("boolean".equals(value.getType())) {
			if (value.isRequired()) {
				return GeneratedClass.BOOLEAN;
			}
			else {
				return GeneratedClass.Boolean;
			}
		}
		else if ("array".equals(value.getType())) {
			return GeneratedClass.createArrayOf(GeneratedClass.fromSchema(value.getItems(), rootPackage));
		}
		else if ("integer".equals(value.getType())) {
			if (value.getFormat().equals("int32")) {
				return GeneratedClass.INT;
			}
		}
		else if ("number".equals(value.getType())) {
			if (value.getFormat().equals("float")) {
				if (value.isRequired()) {
					return GeneratedClass.FLOAT;
				}
				else {
					return GeneratedClass.Float;
				}
			}
		}
		System.out.println("Cannot yet create GeneratedClass from Schema where type = " + value.getType());
		throw new NotImplementedException();
	}

	public static GeneratedClass fromParameter(Parameter parameter, GeneratedPackage rootPackage) {
		if (parameter.getSchema() != null) {
			return fromSchema(parameter.getSchema(), rootPackage);
		}
		
		if ("file".equals(parameter.getType())) {
			return GeneratedClass.createArrayOf(GeneratedClass.BYTE);
		}
		
		Schema tempSchema = new Schema();
		tempSchema.setType(parameter.getType());
		tempSchema.setFormat(parameter.getFormat());
		return GeneratedClass.fromSchema(tempSchema, rootPackage);
		
	}

	public String getSignature() {
		return getFullName();
	}

	public GeneratedClass addGenericTypeArgument(GeneratedClass type) {
		if (type == null) {
			type = GeneratedClass.VOID;
		}
		else if (type.equals(INT)) {
			type = Integer;
		}
		else if (type.equals(BOOLEAN)) {
			type = Boolean;
		}
		else if (type.equals(FLOAT)) {
			type = Float;
		}
		else if (type.equals(DOUBLE)) {
			type = Double;
		}
		this.genericTypeArguments.add(type);
		return this;
	}
}
