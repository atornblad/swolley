package se.atornblad.swolley.volley;

import java.util.Set;

public class ForEachStatement extends BlockStatement {
	private GeneratedClass itemType;
	private String itemVariableName;
	private GeneratedExpression iterable;
	
	public ForEachStatement(GeneratedClass itemType, String itemVariableName, GeneratedExpression iterable) {
		this.itemType = itemType;
		this.itemVariableName = itemVariableName;
		this.iterable = iterable;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append("for (");
		builder.append(itemType.getFullName(imports));
		builder.append(" ");
		builder.append(itemVariableName);
		builder.append(" : ");
		iterable.generateJava(builder, indentation, imports);
		builder.append(") ");
		super.generateJava(builder, indentation, imports);
	}

	public GeneratedClass getItemType() {
		return itemType;
	}

	public void setItemType(GeneratedClass itemType) {
		this.itemType = itemType;
	}

	public String getItemVariableName() {
		return itemVariableName;
	}

	public void setItemVariableName(String itemVariableName) {
		this.itemVariableName = itemVariableName;
	}

	public GeneratedExpression getIterable() {
		return iterable;
	}

	public void setIterable(GeneratedExpression iterable) {
		this.iterable = iterable;
	}
}
