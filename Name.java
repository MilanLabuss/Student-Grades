package student;

public class Name {
	
	private String firstName;
	private String MiddleInitial;
	private String lastName;
	
	public Name(String firstName, String MiddleInitial, String lastName){		//constructor
		this.setFirstName(firstName);
		this.setMiddleInitial(MiddleInitial);
		this.setLastName(lastName);
		
	}
	public Name(){		//default constructor 

	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleInitial() {
		return MiddleInitial;
	}
	
	public void setMiddleInitial(String middleInitial) {
		MiddleInitial = middleInitial;
	} 
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	@Override public String toString() {
	    return "FirstName: " + firstName  + "    | Middle Initial:  " +  MiddleInitial + "    | lastName:  " + lastName +  "\n";
	}

}
