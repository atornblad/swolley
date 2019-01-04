package se.atornblad.swolley.volley;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.atornblad.swolley.strings.StringReplacer;
import se.atornblad.swolley.swagger.Action;
import se.atornblad.swolley.swagger.Document;
import se.atornblad.swolley.swagger.Path;
import sun.misc.Regexp;

public class CodeGenerator {

	public static void generate(Document doc, String srcPath) throws MalformedURLException {
		GeneratedPackage rootPackage = GeneratedPackage.fromBaseUrl(new URL("http://" + doc.getHost() + doc.getBasePath()));
		for (Entry<String, Path> entry : doc.getPaths().entrySet()) {
			String pathKey = entry.getKey();
			Path path = entry.getValue();
			generate(doc, rootPackage, path, path.getGet(), "GET", pathKey);
			generate(doc, rootPackage, path, path.getPost(), "POST", pathKey);
			generate(doc, rootPackage, path, path.getPut(), "PUT", pathKey);
			generate(doc, rootPackage, path, path.getDelete(), "DELETE", pathKey);
			generate(doc, rootPackage, path, path.getHead(), "HEAD", pathKey);
			generate(doc, rootPackage, path, path.getOptions(), "OPTIONS", pathKey);
			generate(doc, rootPackage, path, path.getPatch(), "PATCH", pathKey);
		}
		
		generate(rootPackage, srcPath + "/" + rootPackage.getLocalDir());
	}

	private static void generate(GeneratedPackage pkg, String srcPath) {
		for (GeneratedClass cls : pkg.getClasses()) {
			generate(cls, srcPath);
		}
		
		for (GeneratedPackage subPkg : pkg.getSubPackages().values()) {
			generate(subPkg, srcPath + "/" + subPkg.getName());
		}
	}

	private static void generate(GeneratedClass cls, String srcPath) {
		System.out.println(srcPath + "/" + cls.getName() + ".java :\n");
		System.out.println(cls.renderJava());
		System.out.println("\n\n");
	}

	private static void generate(Document doc, GeneratedPackage rootPackage, Path path, Action action, String httpMethod, String pathKey) {
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
		
		String methodName = action.getSummary(); // .getOperationId();
	}
}
