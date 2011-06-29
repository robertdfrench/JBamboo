package jbamboo.functions.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Point;
import jbamboo.functions.Sum;
import jbamboo.mockobjects.MockRealFunction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SumTest {

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
		MockRealFunction f = new MockRealFunction();
		Sum f_plus_f = new Sum(f,f);
		String expected = String.format("(%s) + (%s)",f,f);
		String got = f_plus_f.toString();
		assertEquals(expected,got);
	}

	@Test
	public final void testValueForPoint() {
		MockRealFunction f = new MockRealFunction();
		Sum f_plus_f = new Sum(f,f);
		Double expected = 2.0;
		Double got = f_plus_f.valueForPoint(new Point(0.0));
		assertEquals(expected,got);
	}

	@Test
	public final void testSum() {
		MockRealFunction f = new MockRealFunction();
		new Sum(f,f);
	}

}
