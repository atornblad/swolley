package se.atornblad.swolley.swagger;

import java.util.HashMap;
import java.util.Map;

public class Document {
	private String swagger;
	private String host;
	private String basePath;
	private DocumentInformation info;
	private Tag[] tags;
	private Map<String, Path> paths;
	private Map<String, Definition> definitions;
	
	public Document() {
		paths = new HashMap<String, Path>();
		definitions = new HashMap<String, Definition>();
	}
	
	public Map<String, Path> getPaths() {
		return paths;
	}

	public void setPaths(Map<String, Path> paths) {
		this.paths = paths;
	}

	public Tag[] getTags() {
		return tags;
	}

	public void setTags(Tag[] tags) {
		this.tags = tags;
	}

	public void setSwagger(String value) {
		swagger = value;
	}
	
	public String getSwagger() {
		return swagger;
	}
	
	public void setInfo(DocumentInformation value) {
		info = value;
	}
	
	public DocumentInformation getInfo() {
		return info;
	}
	
	public void setHost(String value) {
		host = value;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setBasePath(String value) {
		basePath = value;
	}
	
	public String getBasePath() {
		return basePath;
	}
	
	public Map<String, Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(Map<String, Definition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public String toString() {
		return info.getDescription() + " v" + info.getVersion() + " (Swagger version: " + swagger + ")\n" +
	           "Host: " + host + "\n" +
			   "Base path: " + basePath + "\n" +
	           "License: " + info.getLicense().getName();
	}
}
