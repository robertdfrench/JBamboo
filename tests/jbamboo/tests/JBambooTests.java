package jbamboo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	jbamboo.functions.tests.AllTests.class, 
	jbamboo.basetypes.tests.AllTests.class,
	jbamboo.elements.tests.AllTests.class
})
public class JBambooTests {
}
