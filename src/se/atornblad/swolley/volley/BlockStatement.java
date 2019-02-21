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
		indentation = indentation + "  ";

		if (allowVariablesAnywhere) {
			for (GeneratedStatement statement : statements) {
				builder.append(indentation);
				statement.generateJava(builder, indentation, imports);
				builder.append("\n");
			}
		}
		else {
			for (GeneratedStatement statement : statements) {
				if (statement instanceof GeneratedVariable) {
					builder.append(indentation);
					statement.generateJava(builder, indentation, imports);
					builder.append("\n");
				}
			}
			
			for (GeneratedStatement statement : statements) {
				if (! (statement instanceof GeneratedVariable)) {
					builder.append(indentation);
					statement.generateJava(builder, indentation, imports);
					builder.append("\n");
				}
			}
		}
		
		indentation = indentation.substring(0,  indentation.length() - 2);
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