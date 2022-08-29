package application;

/*<h1>Student Management</h1> The Student Management program uses a javafx GUI to manage a database
 * of students and all data related to that students such as name and module grades
 * @author Milan Labus
 * @version 1.0  @since 2022-05-05
 */

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import database.Database;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import student.Student;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import student.Module;


public class Main extends Application {


	Validation validation = new Validation();
	StudentController control = new StudentController();
	Database database = new Database();
	Module module;

	ObservableList<Student> students = FXCollections.observableArrayList();
	ObservableList<Module> modules = FXCollections.observableArrayList();
	Student student;



	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Here will will load all of the students and modules from the database into respective observable lists
			 */
			students = database.getStudents();		
			modules = database.getModules();

			Label enterIDlbl = new Label("Enter Student ID");
			TextField enterIDtxt = new TextField();
			HBox idhbox = new HBox(42,enterIDlbl,enterIDtxt);
			idhbox.setAlignment(Pos.CENTER);

			Label enterNamelbl = new Label("Enter Student Name");
			TextField enterNametxt = new TextField();
			HBox hbox1 = new HBox(42,enterNamelbl,enterNametxt);
			hbox1.setAlignment(Pos.CENTER);

			Label enterMiddleInitial = new Label("Enter Middle Initial");
			TextField MiddleInitialtxt = new TextField();
			HBox hbox2 = new HBox(65,enterMiddleInitial,MiddleInitialtxt);
			hbox2.setAlignment(Pos.CENTER);

			Label enterLastName = new Label("Enter Last Name");
			TextField LastNametxt = new TextField();
			HBox lastnamehbox = new HBox(65,enterLastName,LastNametxt);
			lastnamehbox.setAlignment(Pos.CENTER);

			Label enterDOBlbl = new Label("Enter Student Date of Birth");
			TextField enterDOBtxt = new TextField();
			HBox hbox3 = new HBox(17,enterDOBlbl,enterDOBtxt);
			hbox3.setAlignment(Pos.CENTER);

			Button addbtn = new Button("Save");			//button to add a student to the database
			Button load = new Button("Load");	       //load in all students from the database
			HBox hbox4 = new HBox(12,addbtn,load);
			hbox4.setAlignment(Pos.CENTER);

			TextArea textArea = new TextArea();
			HBox hbox5 = new HBox(textArea);


			Button exit = new Button("Exit");
			HBox hbox6 = new HBox(12,exit);
			hbox6.setAlignment(Pos.BOTTOM_RIGHT);


			VBox vbox1 = new VBox(29,idhbox,hbox1,hbox2,lastnamehbox,hbox3,hbox4,hbox5,hbox6);
			vbox1.setPadding(new Insets(10,10,10,10));

			/*
			 *show all students from the database in the textArea 
			 */
			load.setOnAction(e ->{
				textArea.clear();
				for (Iterator<Student> iterator = students.iterator(); iterator.hasNext();) {
					Student student = (Student) iterator.next();
					String tempstudent = String.valueOf(student.getName());
					textArea.appendText(tempstudent);					
				}

			});

			/*
			 * This button press will create a student
			 * first it will validate all of the fields the user entered
			 * then it will call the controllers addStudent method and pass
			 * all of the contents of the textfields
			 */
			addbtn.setOnAction(e-> {

				validation.isAlphabetic(enterNametxt);
				validation.isAlphabetic(MiddleInitialtxt);
				validation.isAlphabetic(LastNametxt);
				validation.isInt(enterIDtxt);

				int ID = Integer.parseInt(enterIDtxt.getText());
				String firstName = enterNametxt.getText();
				String middleInitial = MiddleInitialtxt.getText();
				String lastName = LastNametxt.getText();
				String dateOfBirth = enterDOBtxt.getText();
				students.add(control.addStudent(ID,firstName, middleInitial, lastName, dateOfBirth));  //creating the student

			});


			/*
			 * tab 2 Contents used for adding a students Grade
			 */

			Label selectStudent = new Label("Student Selection");
			ComboBox<Student> studentcomboBox = new ComboBox<Student>();
			studentcomboBox.setPromptText("Students");
			studentcomboBox.setMaxHeight(30);
			studentcomboBox.setMaxWidth(100);
			studentcomboBox.setItems(students);			//adding the students in the observable list to the combobox


			HBox hbox7 = new HBox(65,selectStudent,studentcomboBox);
			hbox7.setAlignment(Pos.CENTER);
			hbox7.setSpacing(80);

			Label enterModule = new Label("Module Selection");
			ComboBox<Module> modulecomboBox = new ComboBox<Module>();
			modulecomboBox.setPromptText("Modules");
			HBox hbox8 = new HBox(65,enterModule,modulecomboBox);
			hbox8.setAlignment(Pos.CENTER);
			hbox8.setSpacing(60);
			modulecomboBox.setItems(modules);

			Label enterGrade = new Label("Enter the Grade");
			TextField gradeTxt = new TextField();
			gradeTxt.setMaxWidth(100);
			HBox hbox9 = new HBox(65,enterGrade,gradeTxt);
			hbox9.setAlignment(Pos.CENTER);
			hbox9.setSpacing(83);

			Button addGrade = new Button("Add");			//Button to add grade for a student
			HBox hbox10 = new HBox(65,addGrade);
			hbox10.setAlignment(Pos.CENTER);

			
			/*
			 * This button action will write a grade to the studentGrade Database table
			 * it will the inputted grade and then pass it to the database writeGrade method
			 * then it will reload the students and modules observable list because there is 
			 * now a new grade
			 */
			addGrade.setOnAction(e -> {
				Student studentSelect = studentcomboBox.getValue();		//selecting the student obj
				Module moduleSelect = modulecomboBox.getValue();        //selecting the module obj
				validation.isInt(gradeTxt);
				int moduleGrade = Integer.parseInt(gradeTxt.getText());
				database.writeGrade(studentSelect,moduleSelect,moduleGrade);
				//students = database.getStudents();		//reloading
				//modules = database.getModules();

			});


			VBox vbox2 = new VBox(29,hbox7,hbox8,hbox9,hbox10);
			vbox2.setSpacing(45);
			vbox2.setPadding(new Insets(20,20,20,20));
			vbox2.setAlignment(Pos.CENTER);




			/*
			 * Tab 3 contents
			 */
			Label selectStudent2 = new Label("Select Student");
			ComboBox<Student> comboBox2 = new ComboBox<Student>();
			comboBox2.setMaxWidth(120);
			comboBox2.setItems(students);			//adding the students in the observable list to the combobox


			comboBox2.setMaxHeight(10);
			HBox hbox11 = new HBox(selectStudent2,comboBox2);
			hbox11.setSpacing(10);

			Label sortSelect = new Label("Sort By");
			ComboBox<String> comboBox3 = new ComboBox<String>();
			/*
			 * using an observableList to populate the comboBox for scalability
			 */
			ObservableList<String> sortList = comboBox3.getItems();
			sortList.add("All");	    //show all modules
			sortList.add("Over 70%");	//show only modules with more than 70% 
			comboBox3.setMaxWidth(90);



			HBox hbox12 = new HBox(sortSelect,comboBox3);
			hbox12.setSpacing(10);

			HBox hbox13  = new HBox(hbox11,hbox12);
			hbox13.setSpacing(20);

			TextArea textArea2 = new TextArea();
			textArea2.setMinHeight(280);
			HBox hbox14 = new HBox(textArea2);

			Button showbtn = new Button("Show");
			HBox hbox15 = new HBox(showbtn);
			hbox15.setAlignment(Pos.CENTER);

			VBox vbox3 = new VBox(29,hbox13,hbox14,hbox15);
			vbox3.setPadding(new Insets(20,20,20,20));
			/*
			 * button for showing the selected Student
			 * first it will get the selected student from the comboBox
			 * then it will append all of the students details to the textArea by converting the object to a string
			 */
			showbtn.setOnAction(e -> {
				Student studentSelect2 = comboBox2.getValue();			
				database.getGrade(studentSelect2);                      
				textArea2.clear();
				String student = String.valueOf(studentSelect2);
				textArea2.appendText(student);
			});

			/*Listening for the sorting selection selection
			 * if the user clicked sort by all it will retrieve the selected students
			 * grade from the databse and append it to the text area
			 * else if the user chose to sort by over 70% it will
			 * call the sort by grade method in the Student and then
			 * convert that returned student into a string and 
			 * append it to the textArea
			 */
			comboBox3.setOnAction((event) -> {
				if (comboBox3.valueProperty().get() == "All") {		
					Student studentSelect2 = comboBox2.getValue();			
					database.getGrade(studentSelect2);                      
					textArea2.clear();
					String student = String.valueOf(studentSelect2);
					textArea2.appendText(student);

				}
				else {														
					Student studentSelect2 = comboBox2.getValue();			
					database.getGrade(studentSelect2);                       
					textArea2.clear();
					String student = String.valueOf(studentSelect2.sortbyGrade(studentSelect2));
					textArea2.appendText(student);		
				}

			});




			/*
			 * tab 4 used for updating and deleting student records
			 */
			Label selectStudent3 = new Label("Select Student");
			ComboBox<Student> comboBox4 = new ComboBox<Student>();
			comboBox4.setMaxWidth(120);
			comboBox4.setMaxHeight(10);
			HBox hbox16 = new HBox(selectStudent3,comboBox4);
			hbox16.setSpacing(20);
			hbox16.setAlignment(Pos.CENTER);
			/*
			 * adding the students in the observable list to the combobox
			 */
			comboBox4.setItems(students);			


			Label editName  = new Label("Edit first name");
			TextField nameBox = new TextField();
			HBox hbox17 = new HBox(editName,nameBox);
			hbox17.setSpacing(60);
			hbox17.setAlignment(Pos.CENTER);

			Label editID  = new Label("Edit ID");
			TextField IDBox = new TextField();
			HBox hbox18 = new HBox(editID,IDBox);
			hbox18.setSpacing(75);
			hbox18.setAlignment(Pos.CENTER);

			Label editDOB  = new Label("Edit Date of Birth");
			TextField DOBBox = new TextField();
			HBox hbox19 = new HBox(editDOB,DOBBox);
			hbox19.setSpacing(20);
			hbox19.setAlignment(Pos.CENTER);

			Button editStudent = new Button("Edit");
			HBox hbox20 = new HBox(editStudent);
			hbox20.setAlignment(Pos.CENTER);
			hbox20.setSpacing(20);

			/*
			 * this button press will edit the selected students details
			 * first it will validate the textfield entries and then
			 * pass them as parameters to the database method
			 * changeStudentDetails
			 */
			editStudent.setOnAction(e -> {
				Student studentSelect = comboBox4.getValue();		
				validation.isAlphabetic(nameBox);
				validation.isInt(IDBox);
				int id = Integer.parseInt(IDBox.getText());			
				String name = nameBox.getText();
				String dob = DOBBox.getText();
				database.changeStudentDetails(studentSelect, name, id, dob);     
			});

			VBox vbox4 = new VBox(hbox16,hbox17,hbox18,hbox19,hbox20);
			vbox4.setPadding(new Insets(20,20,20,20));
			vbox4.setSpacing(60);

			/*
			 * tab5 to remove a student from the database
			 * 
			 */
			Label selectStudent4 = new Label("Select Student");
			ComboBox<Student> comboBox5 = new ComboBox<Student>();
			comboBox5.setMaxWidth(120);
			comboBox5.setMaxHeight(10);
			HBox hbox21 = new HBox(selectStudent4,comboBox5);
			hbox21.setSpacing(20);
			hbox21.setAlignment(Pos.CENTER);
			comboBox5.setItems(students);			//adding the students in the observable list to the combobox



			Button removebtn = new Button("Remove");    //remove a student from the database
			HBox hbox22 = new HBox(removebtn);
			hbox22.setSpacing(20);
			hbox22.setAlignment(Pos.CENTER);
			VBox vbox5 = new VBox(hbox21,hbox22);
			vbox5.setSpacing(70);
			vbox5.setPadding(new Insets(20,20,20,20));
			vbox5.setAlignment(Pos.CENTER);

			/*
			 * this button press will check which student was selected
			 * 
			 */
			removebtn.setOnAction(e -> {
				Student selectedStudent = comboBox5.getValue();		//selecting the student obj
				database.removeStudent(selectedStudent);
				students.remove(selectedStudent);					//removing the deleted student
			});


			TabPane tabPane = new TabPane();
			Tab tab1 = new Tab("Add", vbox1);
			Tab tab2 = new Tab("Record"  , vbox2);
			Tab tab3 = new Tab("View" , vbox3);
			Tab tab4 = new Tab("Update",vbox4);
			Tab tab5 = new Tab("Remove",vbox5);

			tabPane.getTabs().add(tab1);
			tabPane.getTabs().add(tab2);
			tabPane.getTabs().add(tab3);
			tabPane.getTabs().add(tab4);
			tabPane.getTabs().add(tab5);



			VBox vBox = new VBox(tabPane);
			Scene scene = new Scene(vBox,550,550);



			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}







}
