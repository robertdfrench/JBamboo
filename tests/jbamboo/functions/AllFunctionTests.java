package jbamboo.functions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
	PolynomialTest.class,
	MonomialTest.class,
	ProductTest.class,
	RealFunctionTest.class,
	SumTest.class,
	TentFunctionTest.class
})
public class AllFunctionTests {
}
