package se.atornblad.swolley.swagger;

import java.util.HashMap;
import java.util.Map;

public class Definition {
	private String title;
	private String type;
	private Map<String, Schema> properties;
	
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

	public Map<String, Schema> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Schema> properties) {
		this.properties = properties;
	}
}
