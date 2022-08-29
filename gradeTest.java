package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import student.Grade;
import student.Module;

/*this test case will test the methods of the Grade class
 * 
 */
public class gradeTest {

	@Test
	public void testConstructor() {
		Grade grade = new Grade("Biology",78);
		assertEquals("Biology",grade.getModuleName());
		assertEquals(78,grade.getScore());
	}
	
	
	
	@Test
	public void gradeModuleNameTest() {
		Grade grade = new Grade();
		grade.setModuleName("Math");
		assertTrue(grade.getModuleName().equals("Math"));
	}
	
	
	@Test
	public void gradeScoreTest() {
		Grade grade = new Grade();
		grade.setScore(55);
		assertTrue(grade.getScore() == 55);
	}
	
}
