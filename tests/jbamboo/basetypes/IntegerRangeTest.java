package jbamboo.basetypes;

import static org.junit.Assert.*;

import java.util.List;

import jbamboo.basetypes.IntegerRange;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IntegerRangeTest {

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
	public final void testHashCode() {
		IntegerRange ir = new IntegerRange(1,12);
		assertEquals(ir.hashCode(), 16380);
	}

	@Test
	public final void testIntegerRange() {
		boolean badArgsThrowException = false;
		try {
			new IntegerRange(12,1);
		} catch (IllegalArgumentException e) {
			badArgsThrowException = true;
		}
		assertTrue(badArgsThrowException);
	}

	@Test
	public final void testLowerBound() {
		IntegerRange ir = new IntegerRange(1,12);
		assertEquals(ir.lowerBound(),1);
	}

	@Test
	public final void testUpperBound() {
		IntegerRange ir = new IntegerRange(1,12);
		assertEquals(ir.upperBound(),12);
	}

	@Test
	public final void testEqualsObject() {
		IntegerRange ir1 = new IntegerRange(1,12);
		IntegerRange ir2 = new IntegerRange(1,12);
		assertEquals(ir1,ir2);
	}

	@Test
	public final void testToString() {
		IntegerRange ir = new IntegerRange(1,12);
		assertEquals(ir.toString(), "[1...12]");
	}

	@Test
	public final void testIterator() {
		IntegerRange ir = new IntegerRange(0,11);
		Integer j = 0;
		for (Integer i : ir) {
			assertEquals(i,j);
			j++;
		}
		assertTrue(j == 12);
	}

	@Test
	public final void testAsList() {
		IntegerRange ir = new IntegerRange(0,11);
		List<Integer> ints = ir.asList();
		for (int i = 0; i < 12; i++) {
			assertEquals(ints.get(i),new Integer(i));
		}
		
	}

}
