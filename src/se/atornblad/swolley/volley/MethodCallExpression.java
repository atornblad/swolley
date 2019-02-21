/**
 * 
 */
package se.atornblad.swolley.volley;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author ato
 *
 */
public class MethodCallExpression extends GeneratedExpression {

	private GeneratedExpression target;
	private List<GeneratedExpression> parameters;
	
	/**
	 * 
	 */
	public MethodCallExpression(GeneratedExpression target) {
		this.target = target; 
		parameters = new ArrayList<GeneratedExpression>();
	}
	
	public void addParameter(GeneratedExpression parameter) {
		parameters.add(parameter);
	}

	@Override
	public void generateJava(StringBuilder builder, String indentation, Set<GeneratedClass> imports) {
		target.generateJava(builder, indentation, imports);
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
