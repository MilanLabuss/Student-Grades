package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class alerts {
	
	
/*
 * this class will hold alert boxes
 */
	
	
	/*
	 * this method will show an input error
	 */
	public static void getinputError () { 			
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Error");
		alert1.setHeaderText("incorrect input");
		alert1.setContentText("Please input the correct Value ");
		alert1.showAndWait();
	}
	/*
	 * This method will show an file non existent alert
	 */
	public static void fileDoesntExist () { 			

		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Error");
		alert1.setHeaderText("Database is Empty");
		alert1.setContentText("Please add students to the database ");
		alert1.showAndWait();
	}
	
	/*
	 * this method will show an user already exists alert
	 */
	public static void userExistsAlready () { 			
		Alert alert1 = new Alert(AlertType.ERROR);
		alert1.setTitle("Error");
		alert1.setHeaderText("A user with that ID already exists");
		alert1.setContentText("Please enter a different ID ");
		alert1.showAndWait();
	}
	
	
	
	
}
