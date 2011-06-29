package jbamboo.basetypes.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Natural;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NaturalTest {

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
	public final void testNatural() {
		assertTrue((new Natural(5)).toInt() == 5);
		assertTrue((new Natural(0)).toInt() == 1);
		assertTrue((new Natural(-1)).toInt() == 1);
		assertTrue((new Natural(-5.7)).toInt() == 5);
	}

	@Test
	public final void testToInt() {
		Natural n = new Natural(-5);
		assertTrue(n.toInt() == 5);
	}

	@Test
	public final void testToDouble() {
		Natural n = new Natural(-5.7);
		assertTrue(n.toDouble() == 5.0);
	}

	@Test
	public final void testIterator() {
		Natural n = new Natural(10);
		Integer[] ints = new Integer[10];
		for(Integer i : n) {
			ints[i - 1] = i;
		}
		for (int i = 1; i <= 10; i++) {
			assertTrue(ints[i - 1] == i);
		}
	}

}
