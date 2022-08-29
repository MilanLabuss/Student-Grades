package student;

import java.util.ArrayList;
import java.util.Iterator;



//class for a student who has a name and date of birth object
public class Student {
	
	
	private int ID;			//the students ID
	private DOB dob;
	private Name name;
	
	private ArrayList <Grade> grades;			//creating the students module arraylist

	
	/*
	 * This constructor will initialize a student objects
	 * @param name and dob must be objects of the class Name and dob
	 * it will initialize an arraylist of grades for that Student
	 */
	public Student(int ID,Name name,DOB dob) {
		this.setID(ID);
		this.dob=dob;
		this.name=name;
		setGrades(new ArrayList <Grade>());			
	}
	public Student() {
		
	}
	/*
	 * method to create a student
	 * @param name and dob must be objects of the class Name and dob
	 */
	public void CreateStudent(int ID,Name name,DOB dob) {
		this.setID(ID);
		this.dob=dob;
		this.name=name;
	}


	public DOB getDob() {
		return dob;
	}
/*
 * @param dob must be an object of DOB
 */
	public void setDob(DOB dob) {
		this.dob = dob;
	}


	public Name getName() {
		return name;
	}
	/*
	 * @param name must be an object of Name
	 */
	public void setName(Name name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	/*
	 * @stu must be of type Student
	 * This method will iterate over the students grades and remove any under a score of 70
	 * @return a student object with an arraylist of grades only over 70
	 */
	public Student sortbyGrade(Student stu) {
		Student student = stu;
		
		for (Iterator<Grade> iterator = stu.grades.iterator(); iterator.hasNext();) {		//iterating through
			Grade grade = (Grade) iterator.next();
			
			if (grade.getScore()<70) {			//if the grade is less than 70 remove it from the stuednts list
				stu.grades.remove(grade);
			}
		}
		return student;
		
	}
@Override public String toString() {
    return   "ID: " + ID  +   "    | Name: " + name + "    |  " + dob  +  "\n" + "Grades: " + "\n" + grades + "\n";
}
public ArrayList <Grade> getGrades() {
	return grades;
}
/*
 * @grades has to be an arraylist of grade objects
 */
public void setGrades(ArrayList <Grade> grades) {
	this.grades = grades;
}
/*
 * @param grade has to be an object of class grade
 * this method will add a grade object to the arraylist grades
 */
public void addGrade(Grade grade) {
	grades.add(grade);					
}

	
}
