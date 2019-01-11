package se.atornblad.swolley.volley;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.atornblad.swolley.strings.StringReplacer;
import se.atornblad.swolley.swagger.*;

public final class CodeGenerator {

	public static void generate(Document doc, String srcPath) throws MalformedURLException {
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

	private static void generateJavaFiles(GeneratedPackage pkg, String srcPath) {
		for (GeneratedClass cls : pkg.getClasses()) {
			generateJavaFile(cls, srcPath);
		}
		
		for (GeneratedPackage subPkg : pkg.getSubPackages().values()) {
			generateJavaFiles(subPkg, srcPath + "/" + subPkg.getName());
		}
	}

	private static void generateJavaFile(GeneratedClass cls, String srcPath) {
		System.out.println(srcPath + "/" + cls.getName() + ".java :\n");
		System.out.println(cls.renderJava());
		System.out.println("\n\n");
	}

	private static void createDefinitionClass(Document doc, GeneratedPackage rootPackage, String className, Definition definition) {
		GeneratedPackage modelsPackage = rootPackage.createOrGetSubPackage("models");
		
		GeneratedClass defClass = modelsPackage.createOrGetClass(className);
		for (Entry<String, Schema> field : definition.getProperties().entrySet()) {
			defClass.addField(field.getKey(), GeneratedClass.fromSchema(field.getValue(), rootPackage));
		}
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
		genClass.addImport(GeneratedClass.createFromFullName("java.util.function.Consumer"));
		genClass.addImport(GeneratedClass.createFromFullName("com.android.volley.RequestQueue"));	
		genClass.addImport(GeneratedClass.volleyRequest);
		genClass.addImport(GeneratedClass.volleyResponse);
		genClass.addImport(GeneratedClass.createFromFullName("com.android.volley.VolleyError"));
		genClass.addImport(GeneratedClass.createFromFullName("com.android.volley.toolbox.Volley"));
		
		String methodName = action.getSummary(); // .getOperationId();
		if (genClass.hasMethod(methodName)) {
			methodName = action.getOperationId();
		}
		
		genClass.addMethod(GeneratedMethod.createAccessMethod(methodName, doc, rootPackage, path, action, httpMethod, pathKey));
	}
	
	public static String capitalizeFirst(String word) {
		return word.substring(0,  1).toUpperCase() + word.substring(1);
	}
}
