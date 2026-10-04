package Controller;

import java.awt.TextField;

public class InvalidExpression extends RuntimeException {
	public InvalidExpression(String message, TextField field) {
		field.setText("Invalid Expression: " + message);
	}
}
