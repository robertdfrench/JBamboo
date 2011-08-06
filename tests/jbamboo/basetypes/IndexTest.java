package jbamboo.basetypes;

import static org.junit.Assert.*;

import java.util.List;

import jbamboo.basetypes.Index;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IndexTest {

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
		Index n = Index.get(size);
		List<Integer> l = n.asList();
		for (int i = 0; i <= size; i++) {
			assertTrue(String.format("%d == %d", l.get(i),i),l.get(i) == i);
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
		assertEquals(Index.get(5),Index.get(5));
	}

	@Test
	public final void testGet() {
		assertTrue(Index.get(0) == Index.get(0));
		
		boolean negativeOneIsOOB = false;
		try {
			Index.get(-1);
		} catch (IllegalArgumentException e) {
			negativeOneIsOOB = true;
		}
		assertTrue(negativeOneIsOOB);
	}

	@Test
	public final void testIterator() {
		Index n = Index.get(9);
		Integer[] ints = new Integer[10];
		for(Integer i : n) {
			ints[i] = i;
		}
		for (int i = 0; i < 10; i++) {
			assertEquals(ints[i],new Integer(i));
		}
	}

	@Test
	public final void testToDouble() {
		Index n = Index.get(5);
		assertTrue(n.toDouble() == (double) 5);
	}

	@Test
	public final void testToInt() {
		Index n = Index.get(5);
		assertTrue(n.toInt() == 5);
	}

}
