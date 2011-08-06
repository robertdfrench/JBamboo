package jbamboo.basetypes;

import static org.junit.Assert.*;

import java.util.List;

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
	public final void testAsList() {
		int size = 10;
		Natural n = Natural.get(size);
		List<Integer> l = n.asList();
		for (int i = 0; i < size; i++) {
			assertTrue(String.format("%d == %d", l.get(i),i + 1),l.get(i) == i + 1);
		}
		
		boolean sizeIsOOB = false;
		try {
			l.get(size + 1);
		} catch (IndexOutOfBoundsException e) {
			sizeIsOOB = true;
		}
		assertTrue(sizeIsOOB);
	}
	
	@Test
	public final void testEquals() {
		Natural i = Natural.get(5);
		Natural j = Natural.get(5);
		assertEquals(i,j);
	}
	
	@Test
	public final void testGet() {
		assertTrue(Natural.get(1) == Natural.get(1));
		
		boolean zeroIsOOB = false;
		try {
			Natural.get(0);
		} catch (IllegalArgumentException e) {
			zeroIsOOB = true;
		}
		assertTrue(zeroIsOOB);
	}

	@Test
	public final void testIterator() {
		Natural n = Natural.get(10);
		Integer[] ints = new Integer[10];
		for(Integer i : n) {
			ints[i - 1] = i;
		}
		for (int i = 1; i <= 10; i++) {
			assertEquals(ints[i - 1],new Integer(i));
		}
	}

	@Test
	public final void testToDouble() {
		Natural n = Natural.get(5);
		assertTrue(n.toDouble() == (double) 5);
	}
	
	@Test
	public final void testToInt() {
		Natural n = Natural.get(5);
		assertTrue(n.toInt() == 5);
	}
}
