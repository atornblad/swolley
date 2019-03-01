package se.atornblad.swolley.volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlockStatement extends GeneratedStatement {
	private List<GeneratedStatement> statements;
	private boolean allowVariablesAnywhere = false;
	
	public BlockStatement() {
		statements = new ArrayList<>();
	}
	
	public void setAllowVariablesAnywhere(boolean value) {
		allowVariablesAnywhere = value;
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append("{\n");
		String innerIndentation = indentation + "  ";

		if (allowVariablesAnywhere) {
			for (GeneratedStatement statement : statements) {
				builder.append(innerIndentation);
				statement.generateJava(builder, innerIndentation, imports);
				builder.append("\n");
			}
		}
		else {
			for (GeneratedStatement statement : statements) {
				if (statement instanceof GeneratedVariable) {
					builder.append(innerIndentation);
					statement.generateJava(builder, innerIndentation, imports);
					builder.append("\n");
				}
			}
			
			for (GeneratedStatement statement : statements) {
				if (! (statement instanceof GeneratedVariable)) {
					builder.append(innerIndentation);
					statement.generateJava(builder, innerIndentation, imports);
					builder.append("\n");
				}
			}
		}
		
		builder.append(indentation);
		builder.append("}");
	}
	
	public void add(GeneratedStatement statement) {
		statements.add(statement);
	}
	
	public void add(GeneratedExpression expression) {
		statements.add(new ExpressionStatement(expression));
	}
}