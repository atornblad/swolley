package se.atornblad.swolley.swagger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Definition {
	private String title;
	private String type;
	private Map<String, Schema> properties;
	private String[] required;
	
	public Definition() {
		properties = new HashMap<String, Schema>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String[] getRequired() {
		return required;
	}
	
	public void setRequired(String[] required) {
		this.required = required;
		for (Entry<String, Schema> e : properties.entrySet()) {
			if (Arrays.asList(required).contains(e.getKey())) {
				e.getValue().setRequired(true);
			}
			else {
				e.getValue().setRequired(false);
			}
		}
	}

	public Map<String, Schema> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Schema> properties) {
		this.properties = properties;
	}
}
