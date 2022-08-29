package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import student.Name;
/*
 *this test case will test the Name class
 */
public class nameTest {

	
	@Test
	public void testConstructor() {
		Name name = new Name("Milan","M","Labus");
		assertEquals("Milan",name.getFirstName());
		assertEquals("M",name.getMiddleInitial());
		assertEquals("Labus",name.getLastName());
		
	}
	
	
	@Test
	public void FirstNametest() {
		Name name = new Name();
		name.setFirstName("Milan");
		assertTrue(name.getFirstName().equals("Milan"));
	}
	
	@Test
	public void getMiddleInitialtest() {
		Name name = new Name();
		name.setMiddleInitial("M");
		assertTrue(name.getMiddleInitial().equals("M"));
	}
	
	@Test
	public void LastNametest() {
		Name name = new Name();
		name.setLastName("Labus");
		assertTrue(name.getLastName().equals("Labus"));
	}
	
}
