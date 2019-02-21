package se.atornblad.swolley.volley;

public class AssignmentExpression extends BinaryExpression {

	public static final String OPERATOR = "="; 
	
	public AssignmentExpression(GeneratedExpression left, GeneratedExpression right) {
		super(left, right, OPERATOR);
	}
	
}
