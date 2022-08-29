package application;

import java.sql.SQLException;

import database.Database;
import student.DOB;
import student.Grade;
import student.Name;
import student.Student;
/*
 * This class will act as a controller class for Student
 */
public class StudentController {
	
	 DOB dob= new DOB();		
	 Name name = new Name();
	 Student student = new Student();		
	 Database database = new Database();
	 Grade grade = new Grade();		

	
	/*
	 * This method is used to create a student object and write that student to the database by calling a method
	 * of the database class
	 * it uses the parameters to create a student, name and date of birth object and then sets that name and date of birth
	 * to the student object
	 * @param ID is used for the students ID
	 * @param firstName,middleInitial and lastName are used to create the name object
	 * @param dateOfBirth is used to create the date of birth objects
	 * the last line calls the database classes write method and passes the student as a parameter
	 * @return the created student
	 */
	public Student addStudent(int ID,String firstName,String middleInitial,String lastName,String dateOfBirth)  {
		this.student.setID(ID);
		this.name.setFirstName(firstName);   				
		this.name.setMiddleInitial(middleInitial);  		
		this.name.setLastName(lastName);
		this.dob.setDateOfBirth(dateOfBirth);
		student.setName(name);					
		student.setDob(dob);
		System.out.println("student to string  " + student.toString());
	    database.writeStudent(student);
		return student;
	}
	



}
