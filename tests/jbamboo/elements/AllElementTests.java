package jbamboo.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	FiniteElementTest.class, 
	IntervalElementTest.class
})
public class AllElementTests {
}
