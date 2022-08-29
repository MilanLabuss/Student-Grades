package database;
import java.sql.*;
import java.util.ArrayList;

import application.StudentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import student.DOB;
import student.Grade;
import student.Name;
import student.Student;
import student.Module;


/*
 * class for using the jdbc api to connect to database and manipulate data
 */
public class Database {
	
	private Student student;
	private Module module;
	private int score;						
	//StudentController control = new StudentController();
	//where do I get this grade from
	ObservableList<Student> students = FXCollections.observableArrayList();
	ObservableList<Module> modules = FXCollections.observableArrayList();
	
	static Statement st;
	static ResultSet rs;
	static Connection con;
	static ResultSet res;
	static String QueryModules  = "SELECT * FROM Modules";
	static String QueryStudents = "SELECT * FROM Student";
	           

	final static String dbUrl = "jdbc:mysql://localhost:3306/StudentManagment"; //maybe port number is 3307
	final static String user = "root";
	final static String pass = "";
	
	/*
	 * This method will connect to the database associated with this program
	 */
	public void connect() throws SQLException { // method to connect to the database

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagment", "root", "milanlabus"); // Get a Connection
			st = con.createStatement(); // Create a statement to view
			System.out.println("Database Connected");

	}

	
	
	/*
	 * method for showing all modules in the database
	 */
	public void showAllModules() throws SQLException {
		   connect();
		   rs = st.executeQuery(QueryModules);
		   rs.next();//taking pointer to the next element
		   String name = rs.getString("moduleName");
		   System.out.println(name);
			
	}
	
	/*
	 * Method for getting the grades of a student
	 * @param is a student object
	 * it will set a students arraylist of grades equal to the arraylist that is
	 * filled via an sql query
	 */
	public void getGrade(Student student) {
		try {
			connect();
			this.student=student;			//creating a student
			String getGrades = "SELECT * FROM StudentGrades WHERE idStudent = '"  + student.getID() + "';";
			rs = st.executeQuery(getGrades);
			ArrayList <Grade> grades = new ArrayList<Grade>();			//creating the students module arraylist

			while(rs.next()) {
				Grade grade = new Grade();					//creating a grade object
				String moduleName = rs.getString("moduleName");
				int score = rs.getInt("grade");
				grade.setModuleName(moduleName);    //creating the grade object
				grade.setScore(score);
				grades.add(grade);			//passing it into the student list	
				student.setGrades(grades);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			
	
	/*
	 * method for adding a student to the Student Table
	 * @param is a student object
	 */
	public void writeStudent(Student student) {
		this.student=student;
		
		String addStudentQuery = "INSERT INTO StudentManagment.Student(idStudent,firstName,middleInitial,lastName,DateOfBirth) "
				+ "VALUES('"  +  student.getID() +  "' , '" + student.getName().getFirstName()  + "', '" + student.getName().getMiddleInitial()  + "','" + student.getName().getLastName()  + "','"
				 +  student.getDob() +  "')";
		try {
			connect();
			st.executeUpdate(addStudentQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}			


	}
	
	/*
	 * This method will write a grade to the database
	 * @param student is a student object
	 * @param module is a module object
	 * @param score is the actual score for the student got in that module
	 */
	public void writeGrade(Student student,Module module,int score) {	
		this.student=student;
		this.module=module;
		this.score=score;
		String addGradeQuery  = "INSERT INTO StudentGrades(idStudent,moduleName,grade) "
				+ "VALUES('"+  student.getID() + "','" + module.getModuleName() + "','" +   score    + "')";
		try {
			connect();
			st.executeUpdate(addGradeQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	
/*
 * method to return all of the students in the database in an observable list of students
 * @return is an obesrvable list of all students
 */
	public ObservableList<Student> getStudents() {
			
		String getStudentQuery = "SELECT * FROM StudentManagment.Student ";

		try {
			connect();
			rs=st.executeQuery(getStudentQuery);
			while(rs.next()) {	
				Student tempstudent = new Student();
				Name name = new Name();
				DOB dob = new DOB();
				tempstudent.setID(rs.getInt("idStudent"));
				name.setFirstName(rs.getString("firstName"));
				name.setMiddleInitial(rs.getString("middleInitial"));
				name.setLastName(rs.getString("lastName"));
				dob.setDateOfBirth(rs.getString("DateOfBirth"));
				tempstudent.setName(name);
				tempstudent.setDob(dob);
				students.add(tempstudent);					//adding the temporary student to the student observable list
			    
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		
			  return students;									//returning the observable list
	}
	
	

	
	/*
	 * method to return all of the Modules in the database in an observable list of modules
	 * @return is an observable list of all modules
	 */
		public ObservableList<Module> getModules() {
				
			String getModuleQuery = "SELECT * FROM StudentManagment.Modules ";

			try {
				connect();
				rs=st.executeQuery(getModuleQuery);
				while(rs.next()) {						
					Module module = new Module();
					module.CreateModule(rs.getString("moduleName"));
					modules.add(module);				    
				}
								
			} catch (SQLException e) {
				e.printStackTrace();
			}						
				  return modules;									
		}
		
		
		/*method to update and change a students details
		 * @param student is a student object that will be updates
		 * @param name is the firstName for that student
		 * @param ID is that students ID
		 * @param dateOfBirth is the students date of the birth
		 */
		public void changeStudentDetails(Student student, String name,int ID, String dateOfBirth) {
			String studentname = name;
			Student selectedStudent = student;
			int idStudent = ID;
			String dob = dateOfBirth;
			String changeQuery = "UPDATE StudentManagment.Student  SET  idStudent = '" +  idStudent + "', firstName = '"  + studentname  +  "',DateOfBirth = '" + dob  +
					"'WHERE idStudent = '" +  selectedStudent.getID()  + "';";
			try {
				connect();
				st.executeUpdate(changeQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		}
		/*
		 * method to remove a student and all of their module grade information
		 * @param student is a student object
		 */
		public void removeStudent(Student student) {
			Student selectedStudent = student;		//the student that will be deleted
			String deleteGradeQuery = "DELETE FROM StudentManagment.StudentGrades WHERE idStudent = '"  + selectedStudent.getID()  + "';   ";		//first deleting the students grades
			String deleteStudentQuery = "DELETE FROM StudentManagment.Student WHERE idStudent = '"  + selectedStudent.getID()  + "';   ";			//then deleting the student
			try {
				connect();
				st.executeUpdate(deleteGradeQuery);
				st.executeUpdate(deleteStudentQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	
	

}






