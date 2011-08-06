package jbamboo.basetypes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	JBambooNamespaceTest.class, 
	NaturalTest.class, 
	PointTest.class,
	IndexTest.class,
	SymmetricPairIteratorTest.class,
	IntegerRangeTest.class
	})
public class AllBasetypeTests {
}
