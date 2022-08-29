package student;


//class for a Students Date of Birth
public class DOB {
	
	private String dateOfBirth;
	
	public DOB() {
		
	}

	public DOB(	String dateOfBirth) {
		this.setDateOfBirth(dateOfBirth);
		
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}
/*
 * @param dateOfBirth must be of type String
 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override public String toString() {
	    return dateOfBirth;
	}


}
