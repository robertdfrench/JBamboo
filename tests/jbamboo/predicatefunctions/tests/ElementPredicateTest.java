package jbamboo.predicatefunctions.tests;

import static org.junit.Assert.*;
import jbamboo.predicatefunctions.ElementPredicate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElementPredicateTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testToString() {
		ElementPredicate pred = new ElementPredicate(new MockFiniteElement);
	}

	@Test
	public final void testElementPredicate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testMatches() {
		fail("Not yet implemented"); // TODO
	}

}
