package se.atornblad.swolley.volley;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class GeneratedPackage {
	private String name;
	private GeneratedPackage parent;
	private String fullName;
	private Map<String, GeneratedPackage> subPackages;
	private Map<String, GeneratedClass> classes;
	
	public GeneratedPackage(String name, GeneratedPackage parent) {
		this.name = name;
		this.parent = parent;
		this.subPackages = new TreeMap<>();
		
		if (parent == null) {
			fullName = name;
		}
		else {
			fullName = parent.fullName + "." + name;
			parent.subPackages.put(name, this);
		}
		
		classes = new TreeMap<>();
	}

	public String getName() {
		return name;
	}

	public GeneratedPackage getParent() {
		return parent;
	}

	public String getFullName() {
		return fullName;
	}
	
	public Map<String, GeneratedPackage> getSubPackages() {
		return subPackages;
	}
	
	public Collection<GeneratedClass> getClasses() {
		return classes.values();
	}
	
	public void addClass(GeneratedClass newClass) {
		classes.put(newClass.getName(), newClass);
	}
	
	public String getLocalDir() {
		if (parent == null) {
			return name;
		}
		else {
			return parent.getLocalDir() + "/" + name;
		}
	}

	public static GeneratedPackage fromBaseUrl(URL url) {
		GeneratedPackage result = null;
		
		String[] pathParts = Arrays.stream(url.getPath().split("/")).map(part -> part.replace('-',  '_')).filter(part -> part.length() >= 1).toArray(String[]::new);
		for (int i = pathParts.length - 1; i >= 0; --i) {
			result = new GeneratedPackage(pathParts[i], result);
		}
		
		String[] hostParts = Arrays.stream(url.getHost().split("\\.")).map(part -> part.replace('-', '_')).toArray(String[]::new);
		for (int i = hostParts.length - 1; i >= 0; --i) {
			result = new GeneratedPackage(hostParts[i], result);
		}
		
		return result;
	}
	
	public static GeneratedPackage fromPackageName(String packageName) {
		GeneratedPackage result = null;
		
		String[] hostParts = Arrays.stream(packageName.split("\\.")).toArray(String[]::new);
		for (int i = hostParts.length - 1; i >= 0; --i) {
			result = new GeneratedPackage(hostParts[i], result);
		}
		
		return result;
	}

	public boolean hasClass(String clientClassName) {
		return classes.containsKey(clientClassName);
	}

	public GeneratedClass getClass(String clientClassName) {
		return classes.get(clientClassName);
	}

	public GeneratedPackage createOrGetSubPackage(String name) {
		if (subPackages.containsKey(name)) {
			return subPackages.get(name);
		}
		else {
			GeneratedPackage subPackage = new GeneratedPackage(name, this);
			return subPackage;
		}
	}

	public GeneratedClass createOrGetClass(String name) {
		if (classes.containsKey(name)) {
			return classes.get(name);
		}
		else {
			GeneratedClass theClass = new GeneratedClass(name, this);
			return theClass;
		}
	}
}
