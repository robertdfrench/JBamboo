package jbamboo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	jbamboo.functions.AllFunctionTests.class, 
	jbamboo.basetypes.AllBasetypeTests.class,
	jbamboo.elements.AllElementTests.class,
	jbamboo.predicatefunctions.AllPredicateFunctionTests.class
})
public class JBambooTests {
}
