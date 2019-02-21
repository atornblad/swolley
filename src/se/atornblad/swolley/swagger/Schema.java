package se.atornblad.swolley.swagger;

public class Schema {
	private String type;   // ENUM
	private String format; // ENUM
	private String $ref;
	private Schema items;
	private String[] enumValues;
	private boolean required;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String[] getEnum() {
		return enumValues;
	}
	
	public void setEnum(String[] enumValues) {
		this.enumValues = enumValues;
	}
	
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String get$ref() {
		return $ref;
	}
	
	public void set$ref(String $ref) {
		this.$ref = $ref;
	}
	
	public Schema getItems() {
		return items;
	}
	
	public void setItems(Schema items) {
		this.items = items;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}
