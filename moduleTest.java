package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import student.Name;
import student.Module;
/*this test case will test the Module class
 * 
 */
public class moduleTest {

	@Test
	public void testConstructor() {
		Module module = new Module("Biology");
		assertEquals("Biology",module.getModuleName());

	}

	@Test
	public void moduleNameTest() {
		Module module = new Module();
		module.setModuleName("Math");
		assertTrue(module.getModuleName().equals("Math"));
	}
	

}
