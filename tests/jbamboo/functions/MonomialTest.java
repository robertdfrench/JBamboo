package jbamboo.functions;

import static org.junit.Assert.*;
import jbamboo.basetypes.Point;
import jbamboo.functions.Monomial;
import jbamboo.functions.Polynomial;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonomialTest {

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
	public final void testMonomial() {
		Point p = new Point(0.0,0.0);
		Point q = new Point(1.0,1.0);
		Polynomial f = new Monomial(p,q);
		assertTrue(f.getCoefficient(0) == 0.0);
		assertTrue(f.getCoefficient(1) == 1.0);
	}

}
