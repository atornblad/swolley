package se.atornblad.swolley.swagger;

import java.util.Map;

public class Action {
	private String[] tags;
	private String summary;
	private String description;
	private String operationId;
	private String[] consumes;
	private String[] produces;
	private Parameter[] parameters = new Parameter[0];
	private Map<String, Response> responses;
	private boolean deprecated;
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public boolean hasSummary() {
		return summary != null && !summary.equals("");
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public boolean hasDescription() {
		return description != null && !description.equals("");
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOperationId() {
		return operationId;
	}
	
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	
	public String[] getConsumes() {
		return consumes;
	}
	
	public void setConsumes(String[] consumes) {
		this.consumes = consumes;
	}
	
	public String[] getProduces() {
		return produces;
	}
	
	public void setProduces(String[] produces) {
		this.produces = produces;
	}
	
	public Parameter[] getParameters() {
		return parameters;
	}
	
	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}
	
	public Map<String, Response> getResponses() {
		return responses;
	}
	
	public void setResponses(Map<String, Response> responses) {
		this.responses = responses;
	}
	
	public boolean isDeprecated() {
		return deprecated;
	}

	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}
}
