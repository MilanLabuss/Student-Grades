package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Validation {
	/*
	 * this class will validate if user inputs are an int or not
	 * @param is a textfield 
	 */
	
	public boolean isInt(TextField enterIDtxt) {
		try {
			int id = Integer.parseInt(enterIDtxt.getText());
			return true;
		} catch (NumberFormatException e) {
			alerts.getinputError();
			return false;
		}
	
		
	}
	
	/*
	 * this class will validate if user inputs are an alphabetical String or not
	 * @param is a textfield 
	 */
	public boolean isAlphabetic(TextField text) { 
		String field = text.getText();

		if(field.matches("[a-zA-Z\\s']+")) { 
			return true; 

		}else 
			alerts.getinputError();
		return false;
	}
	
	

	

}
