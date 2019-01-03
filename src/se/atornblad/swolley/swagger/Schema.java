package se.atornblad.swolley.swagger;

public class Schema {
	private String type;   // ENUM
	private String format; // ENUM
	private String $ref;
	private Schema items;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
}
