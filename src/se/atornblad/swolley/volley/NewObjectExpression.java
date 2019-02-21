package se.atornblad.swolley.volley;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NewObjectExpression extends GeneratedExpression {
	
	private GeneratedClass type;
	private List<GeneratedExpression> parameters;
	
	/**
	 * 
	 */
	public NewObjectExpression(GeneratedClass type) {
		this.type = type; 
		parameters = new ArrayList<GeneratedExpression>();
	}
	
	public void addParameter(GeneratedExpression parameter) {
		parameters.add(parameter);
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		builder.append("new ");
		builder.append(type.getFullName(imports));
		builder.append("(");
		Iterator<GeneratedExpression> i = parameters.iterator();
		while (i.hasNext()) {
			GeneratedExpression expr = i.next();
			expr.generateJava(builder, indentation, imports);
			if (i.hasNext()) {
				builder.append(", ");
			}
		}
		builder.append(")");
	}

}
