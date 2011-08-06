package jbamboo.functions;

import static org.junit.Assert.*;
import jbamboo.basetypes.Point;
import jbamboo.functions.Product;
import jbamboo.mockobjects.MockRealFunction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductTest {

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
		Product f_times_f = new Product(f,f);
		String expected = String.format("(%s) * (%s)",f,f);
		String got = f_times_f.toString();
		assertEquals(expected,got);
	}

	@Test
	public final void testValueForPoint() {
		MockRealFunction f = new MockRealFunction();
		Product f_times_f = new Product(f,f);
		Double expected = 1.0;
		Double got = f_times_f.valueForPoint(new Point(0.0));
		assertEquals(expected,got);
	}

	@Test
	public final void testProduct() {
		MockRealFunction f = new MockRealFunction();
		new Product(f,f);
	}

}
