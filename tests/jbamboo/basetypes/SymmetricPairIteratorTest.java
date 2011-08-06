package jbamboo.basetypes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jbamboo.basetypes.Pair;
import jbamboo.basetypes.SymmetricPairIterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SymmetricPairIteratorTest {

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
	public final void testSymmetricPairIterator() {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		assertNotNull(new SymmetricPairIterator<Integer>(ints));
	}

	@Test
	public final void testWholeFuckinThing() {
		ArrayList<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		ints.add(2);
		ints.add(3);
		SymmetricPairIterator<Integer> spi = new SymmetricPairIterator<Integer>(ints);
		StringBuilder sb = new StringBuilder();
		for(Pair<Integer> p : spi) {
			sb.append(p);
		}
		String controlString = "Pair {1,1}Pair {1,2}Pair {1,3}Pair {2,2}Pair {2,3}Pair {3,3}";
		assertEquals(sb.toString(),controlString);
	}

}
