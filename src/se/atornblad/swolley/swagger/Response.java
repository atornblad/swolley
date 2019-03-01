package se.atornblad.swolley.swagger;


public class Response {
	private String description;
	private Schema schema;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema value) {
		schema = value;
	}
}
