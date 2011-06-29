package jbamboo.functions.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Point;
import jbamboo.functions.RealFunction;
import jbamboo.mockobjects.MockRealFunction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RealFunctionTest {

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
	public final void testPlus() {
		RealFunction f = new MockRealFunction();
		RealFunction g = new MockRealFunction();
		RealFunction f_plus_g = f.plus(g);
		Double expected = 2.0;
		Double got = f_plus_g.valueForPoint(new Point(0.0));
		assertEquals(expected,got);
	}

	@Test
	public final void testTimes() {
		RealFunction f = new MockRealFunction();
		RealFunction g = new MockRealFunction();
		RealFunction f_times_g = f.times(g);
		Double expected = 1.0;
		Double got = f_times_g.valueForPoint(new Point(0.0));
		assertEquals(expected,got);
	}

	@Test
	public final void testValueForPoint() {
		RealFunction f = new MockRealFunction();
		Double expected = 1.0;
		Double got = f.valueForPoint(new Point(0.0));
		assertEquals(expected,got);
	}

}
