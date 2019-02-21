package se.atornblad.swolley.volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.atornblad.swolley.strings.StringReplacer;
import se.atornblad.swolley.swagger.*;

public final class CodeGenerator {

	public static void generate(Document doc, String srcPath) throws MalformedURLException, FileNotFoundException, UnsupportedEncodingException {
		GeneratedPackage rootPackage = GeneratedPackage.fromBaseUrl(new URL("http://" + doc.getHost() + doc.getBasePath()));
		for (Entry<String, Path> entry : doc.getPaths().entrySet()) {
			String pathKey = entry.getKey();
			Path path = entry.getValue();
			createActionMethod(doc, rootPackage, path, path.getGet(), "GET", pathKey);
			createActionMethod(doc, rootPackage, path, path.getPost(), "POST", pathKey);
			createActionMethod(doc, rootPackage, path, path.getPut(), "PUT", pathKey);
			createActionMethod(doc, rootPackage, path, path.getDelete(), "DELETE", pathKey);
			createActionMethod(doc, rootPackage, path, path.getHead(), "HEAD", pathKey);
			createActionMethod(doc, rootPackage, path, path.getOptions(), "OPTIONS", pathKey);
			createActionMethod(doc, rootPackage, path, path.getPatch(), "PATCH", pathKey);
		}
		
		for (Entry<String, Definition> entry : doc.getDefinitions().entrySet()) {
			String className = entry.getKey();
			Definition definition = entry.getValue();
			createDefinitionClass(doc, rootPackage, className, definition);
		}
		
		generateJavaFiles(rootPackage, srcPath + "/" + rootPackage.getLocalDir());
	}

	private static void generateJavaFiles(GeneratedPackage pkg, String srcPath) throws FileNotFoundException, UnsupportedEncodingException {
		for (GeneratedClass cls : pkg.getClasses()) {
			generateJavaFile(cls, srcPath);
		}
		
		for (GeneratedPackage subPkg : pkg.getSubPackages().values()) {
			generateJavaFiles(subPkg, srcPath + "/" + subPkg.getName());
		}
	}

	private static void generateJavaFile(GeneratedClass cls, String srcPath) throws FileNotFoundException, UnsupportedEncodingException {
		String filename = srcPath + "/" + cls.getName() + ".java";
		System.out.println(filename + " :\n");
		System.out.println(cls.renderJava());
		System.out.println("\n\n");
		
		new File(srcPath).mkdirs();
		try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
			writer.write(cls.renderJava());
		}
	}

	private static void createDefinitionClass(Document doc, GeneratedPackage rootPackage, String className, Definition definition) {
		GeneratedPackage modelsPackage = rootPackage.createOrGetSubPackage("models");
		
		GeneratedClass defClass = modelsPackage.createOrGetClass(className);
		
		GeneratedMethod constructor = new GeneratedMethod(null, className);
		constructor.setPublic(true);
		defClass.addMethod(constructor);
		
		GeneratedMethod constructor2 = new GeneratedMethod(null, className);
		constructor2.setPublic(true);
		
		GeneratedMethod createFromJsonObject = new GeneratedMethod(defClass, "createFromJsonObject");
		createFromJsonObject.addParameter("jsonObject", GeneratedClass.jsonObject);
		createFromJsonObject.setStatic(true);
		defClass.addMethod(createFromJsonObject);
		
		GeneratedVariable result = new GeneratedVariable(defClass, "result", new NewObjectExpression(defClass));
		createFromJsonObject.getBody().add(result);
		
		GeneratedMethod populateJsonObject = new GeneratedMethod(defClass, "populateJsonObject");
		populateJsonObject.addParameter("jsonObject", GeneratedClass.jsonObject);
		ReferenceExpression jsonObjectRef = new ReferenceExpression("jsonObject");
		defClass.addMethod(populateJsonObject);

		String[] fieldKeys = definition.getProperties().keySet().stream().sorted().toArray(String[]::new);
		for (String fieldKey : fieldKeys) {
			Schema field = definition.getProperties().get(fieldKey);
			GeneratedClass fieldType = GeneratedClass.fromSchema(field, rootPackage);
			defClass.addField(fieldKey, fieldType);
			constructor2.addParameter(fieldKey, fieldType);
			constructor2.getBody().add(new AssignmentExpression(new ReferenceExpression(ReferenceExpression.THIS, fieldKey), new ReferenceExpression(fieldKey)));
			
			MethodCallExpression putValue = new MethodCallExpression(new ReferenceExpression(jsonObjectRef, "put"));
			putValue.addParameter(new StringLiteralExpression(fieldKey));
			GeneratedExpression valueToPut;
			
			GeneratedExpression setInstanceValue = ReferenceExpression.NULL;
			
			if (fieldType.isSimple()) {
				valueToPut = new ReferenceExpression(ReferenceExpression.THIS, fieldKey);
				if (fieldType.equals(GeneratedClass.Float)) {
					valueToPut = new CastExpression(GeneratedClass.FLOAT, valueToPut);
				}
				if (fieldType.equals(GeneratedClass.FLOAT)) {
					valueToPut = new CastExpression(GeneratedClass.DOUBLE, valueToPut);
				}
				if (fieldType.equals(GeneratedClass.Double)) {
					valueToPut = new CastExpression(GeneratedClass.DOUBLE, valueToPut);
				}
				if (fieldType.equals(GeneratedClass.Boolean)) {
					valueToPut = new CastExpression(GeneratedClass.BOOLEAN, valueToPut);
				}
				
				if (fieldType.equals(GeneratedClass.STRING)) { 
					setInstanceValue = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression("jsonObject"), "getString"));
					((MethodCallExpression)setInstanceValue).addParameter(new StringLiteralExpression(fieldKey));
				}
				if (fieldType.equals(GeneratedClass.BOOLEAN)) { 
					setInstanceValue = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression("jsonObject"), "getBoolean"));
					((MethodCallExpression)setInstanceValue).addParameter(new StringLiteralExpression(fieldKey));
				}
				if (fieldType.equals(GeneratedClass.INT)) { 
					setInstanceValue = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression("jsonObject"), "getInt"));
					((MethodCallExpression)setInstanceValue).addParameter(new StringLiteralExpression(fieldKey));
				}
				if (fieldType.equals(GeneratedClass.Float)) {
					setInstanceValue = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression("jsonObject"), "getDouble"));
					((MethodCallExpression)setInstanceValue).addParameter(new StringLiteralExpression(fieldKey));
					setInstanceValue = new CastExpression(GeneratedClass.FLOAT, setInstanceValue);
					setInstanceValue = new CastExpression(GeneratedClass.Float, setInstanceValue);
				}
				if (fieldType.equals(GeneratedClass.FLOAT)) {
					setInstanceValue = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression("jsonObject"), "getDouble"));
					((MethodCallExpression)setInstanceValue).addParameter(new StringLiteralExpression(fieldKey));
					setInstanceValue = new CastExpression(GeneratedClass.FLOAT, setInstanceValue);
				}
			}
			else if (fieldType.isArray()) {
				// TODO: Add an if (this.{fieldKey} != null) check
				GeneratedVariable fieldJsonArray = new GeneratedVariable(GeneratedClass.jsonArray, fieldKey + "JsonArray", new NewObjectExpression(GeneratedClass.jsonArray));
				ForEachStatement forEachItem = new ForEachStatement(fieldType.getItemClass(), "item", new ReferenceExpression(ReferenceExpression.THIS, fieldKey));
				populateJsonObject.getBody().add(forEachItem);
				GeneratedVariable itemJsonObject = new GeneratedVariable(GeneratedClass.jsonObject, "itemJsonObject", new NewObjectExpression(GeneratedClass.jsonObject));
				forEachItem.add(itemJsonObject);
				MethodCallExpression callPopulate = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression(forEachItem.getItemVariableName()), "populateJsonObject"));
				callPopulate.addParameter(new ReferenceExpression(itemJsonObject.getName()));
				forEachItem.add(callPopulate);
				MethodCallExpression putInArray = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression(fieldJsonArray.getName()), "put"));
				putInArray.addParameter(new ReferenceExpression(itemJsonObject.getName()));
				forEachItem.add(putInArray);
				populateJsonObject.getBody().add(fieldJsonArray);
				valueToPut = new ReferenceExpression(fieldJsonArray.getName());
			}
			else {
				// TODO: Add an if (this.{fieldKey} != null) check
				GeneratedVariable fieldJsonObject = new GeneratedVariable(GeneratedClass.jsonObject, fieldKey + "JsonObject", new NewObjectExpression(GeneratedClass.jsonObject));
				populateJsonObject.getBody().add(fieldJsonObject);
				MethodCallExpression callPopulate = new MethodCallExpression(new ReferenceExpression(new ReferenceExpression(ReferenceExpression.THIS, fieldKey), "populateJsonObject"));
				callPopulate.addParameter(new ReferenceExpression(fieldJsonObject.getName()));
				populateJsonObject.getBody().add(callPopulate);
				valueToPut = new ReferenceExpression(fieldJsonObject.getName());
			}

			putValue.addParameter(valueToPut);
			populateJsonObject.getBody().add(putValue);
			
			AssignmentExpression setInstanceVariable = new AssignmentExpression(new ReferenceExpression(new ReferenceExpression(result.getName()), fieldKey), setInstanceValue);
			createFromJsonObject.getBody().add(setInstanceVariable);
		}
		
		createFromJsonObject.getBody().add(new ReturnStatement(new ReferenceExpression(result.getName())));
		
		defClass.addMethod(constructor2);
	}

	private static void createActionMethod(Document doc, GeneratedPackage rootPackage, Path path, Action action, String httpMethod, String pathKey) {
		if (action == null) return;
		
		GeneratedPackage clientsPackage = rootPackage.createOrGetSubPackage("clients");
		
		Pattern p = Pattern.compile("-[a-zA-Z]");
		String clientClassName;
		
		String[] controllerTags = Arrays.stream(action.getTags())
										.filter(tag -> tag.endsWith("-controller"))
										.toArray(String[]::new);
		
		if (controllerTags.length >= 1) {
			String controllerTag = controllerTags[0];
			clientClassName = StringReplacer.replace(controllerTag, Pattern.compile("((^)|(-))([a-zA-Z])"), (Matcher m) -> m.group(4).toUpperCase()) + "Client";
		}
		else {
			clientClassName = "apiClient";
		}
		
		GeneratedClass genClass = clientsPackage.createOrGetClass(clientClassName);
		if (!genClass.hasPublicConstructor()) {
			genClass.setJsdoc(doc.getInfo().getTitle() + "\n" + doc.getInfo().getDescription());
			
			genClass.addField("volleyRequestQueue", GeneratedClass.volleyRequestQueue);
			
			GeneratedMethod constructor = new GeneratedMethod(null, clientClassName);
			constructor.setPublic(true);
			constructor.addParameter("volleyRequestQueue", GeneratedClass.volleyRequestQueue);
			
			constructor.getBody().add(new AssignmentExpression(
				new ReferenceExpression(ReferenceExpression.THIS, "volleyRequestQueue"),
				new ReferenceExpression("volleyRequestQueue")
			));
			
			genClass.addMethod(constructor);
			
			GeneratedMethod emptyConstructor = new GeneratedMethod(null, clientClassName);
			emptyConstructor.setPublic(true);
			
			MethodCallExpression callThisCtorNull = new MethodCallExpression(ReferenceExpression.THIS);
			callThisCtorNull.addParameter(ReferenceExpression.NULL);
			emptyConstructor.getBody().add(callThisCtorNull);
			
			
			genClass.addMethod(emptyConstructor);
		}
		
		genClass.addImport(GeneratedClass.createFromFullName("java.util.function.Consumer"));
		genClass.addImport(GeneratedClass.volleyRequestQueue);
		genClass.addImport(GeneratedClass.volleyRequest);
		genClass.addImport(GeneratedClass.volleyResponse);
		genClass.addImport(GeneratedClass.createFromFullName("com.android.volley.VolleyError"));
		genClass.addImport(GeneratedClass.createFromFullName("com.android.volley.toolbox.Volley"));
		
		String methodName = action.getOperationId();
		if (genClass.hasMethod(methodName)) {
			methodName = action.getOperationId();
		}
		if (path.hasOnlyOneMethod() && Pattern.matches("(.*)Using((DELETE)|(GET)|(HEAD)|(OPTIONS)|(PATCH)|(POST)|(PUT))$", methodName)) {
			int usingPosition = methodName.lastIndexOf("Using");
			methodName = methodName.substring(0,  usingPosition);
		}
		
		genClass.addMethod(GeneratedMethod.createAccessMethod(methodName, doc, rootPackage, path, action, httpMethod, pathKey));
	}
	
	public static String capitalizeFirst(String word) {
		return word.substring(0,  1).toUpperCase() + word.substring(1);
	}
}
