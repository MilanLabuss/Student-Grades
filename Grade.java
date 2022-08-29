package student;
/*
 *class for a Grade comprised of a module name and the score for that module
 */
public class Grade {		
	
	private String moduleName;
	private int score;
	
	public Grade() {
		
	}
	public Grade(String moduleName,int score) {
		this.setModuleName(moduleName);
		this.setScore(score);
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override public String toString() {
	    return   "\nModule Name: " + moduleName + " Grade: " + score;
	}
	
	
}
