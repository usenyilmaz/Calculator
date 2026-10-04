package Controller;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import Model.*;

public class ButtonListener implements ActionListener{
	protected TextField screen = new TextField();
	Set<JButton> buttons = new HashSet<>();
	TextField error = new TextField();
	
	public ButtonListener(TextField screen, Set<JButton> buttons, TextField error) {
		this.screen = screen;
		this.buttons = buttons;
		this.error = error;
		
		for (JButton j: buttons) {
			j.addActionListener(this);
		}
	}

	/* arrangement of numbers on the screen:
    CE C DEL
    1 2 3 /
    4 5 6 x
    7 8 9 -
    , 0 =
    */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if button = DEL -> delete 1 tile // CE -> last entry // C -> all of it
		//if button = == -> calculate and print screen (send to parser model)
		//operators cannot be written back to back -------- 453 */ 34
		//comma can only come after a number ---------- 2 + ,
		//only numbers can come after a comma --------- 23 - , *
		//num, comma (co) , operator (op), equals (eq)
		/* it all comes down to this:
		 *  	prev	curr
		 *  
		 *		num		num, co, op, eq
		 *		co		num
		 *		op		num
		 *		null	num
		 */  
		ClearError();
		String currScreen = screen.getText();
		String message = e.getActionCommand();
		String prevMessage = "";
		if(!isEmptyScreen()) {prevMessage = currScreen.substring(currScreen.length() - 1, currScreen.length());}
		
		
		if (isDEL(message)){
			DEL();
			return;
		}
		if(isCE(message)) {
			CE();
			return;
		}
		if(isC(message)) {
			C();
			return;
		}
			
		try {
			if(isEmptyScreen()) {
				if(isNumber(message)) {
					PrintScreen(message);
				}
				else {
					throw new InvalidExpression(currScreen + message, error);
				}
			}
			else if(isNumber(prevMessage)) {
				if(isEquals(message)) {
					C();
					StringBuilder expression = new StringBuilder(currScreen);
					expression.append(message);
					BigDecimal answer = Model.Calculate(expression.toString());
					PrintScreen(answer.toString());
				}
				else {
				PrintScreen(message);
				}
			}
			else if(isComma(prevMessage) || isOperator(prevMessage)) {
				if(isNumber(message)) {
					PrintScreen(message);
				}
				else {
					throw new InvalidExpression(currScreen + message, error);
				}
			}
		} catch (RuntimeException exp) {
			
		}
		
		
		
		
//		if (!isClearer(message) && !isEquals(message)) {
//			PrintScreen(message);
//		}
		

		
		
	}
	/* arrangement of numbers on the screen:
    CE C DEL
    1 2 3 /
    4 5 6 x
    7 8 9 -
    , 0 =
    */
	private void ClearError() {
		error.setText(null);
	}
	private void PrintScreen(String str) {
		String currentScreen = screen.getText();
		StringBuilder concat = new StringBuilder(currentScreen);
		concat.append(str);
		screen.setText(concat.toString());
	}
	private static String RemoveLast(String str) {
		return str.substring(0, str.length() - 1);
	}
	private boolean isEmptyScreen() {
		return screen.getText().length() == 0;
	}
	private void DEL() {
		if(isEmptyScreen()) {return;}
		String currentScreen = screen.getText();
		String newScreen = RemoveLast(currentScreen);
		screen.setText(newScreen);
	}

	private void CE() {
		if(isEmptyScreen()) {return;}
		String currentScreen = screen.getText();
		if (isOperator(currentScreen.substring(currentScreen.length() - 1, currentScreen.length())) || isComma(currentScreen.substring(currentScreen.length() - 1, currentScreen.length()))) {
			String newScreen = RemoveLast(currentScreen);
			screen.setText(newScreen);
			return;
		}
		String newScreen = currentScreen;
		for (int i = currentScreen.length() - 1; i >= 0; i--) {
			if (isNumber(currentScreen.substring(i, i + 1))){
				newScreen = RemoveLast(newScreen);
			}
			else {
				break;
			}
		}
		screen.setText(newScreen);
	}
	private void C() {
		screen.setText(null);
	}
	
	private boolean isNumber(String str) {
		return str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4") || str.equals("5") || str.equals("6") || str.equals("7") || str.equals("8") || str.equals("9")|| str.equals("0");
	}
	private boolean isClearer(String str) {
		return isCE(str) || isDEL(str) || isC(str);
	}
	private boolean isCE(String str) {
		return str.equals("CE");
	}
	private boolean isDEL(String str) {
		return str.equals("DEL");
	}
	private boolean isC(String str) {
		return str.equals("C");
	}
	private boolean isOperator(String str) {
		return str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*");
	}
	private boolean isComma(String str) {
		return str.equals(",");
	}
	private boolean isEquals(String str) {
		return str.equals("=");
	}

}
