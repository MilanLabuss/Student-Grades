package student;

//class for a Module
public class Module {
	
	private String moduleName;
	
	public void CreateModule(String moduleName) {
		this.moduleName=moduleName;
	}
	public Module() {
		
	}
	
	public Module(String moduleName) {
		this.setModuleName(moduleName);
	}

	public String getModuleName() {
		return moduleName;
	}
	
	/*
	 * @param moduelName must be of type String
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Override public String toString() {
	    return moduleName;
	}

}
