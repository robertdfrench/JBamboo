package jbamboo.basetypes.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JBambooNamespaceTest {

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
	public final void testPoint() {
		Point p = JBambooNamespace.point(0.33, 1.22, 5.6, -1700.0, 34.56);
		assertTrue(p.getCoordinate(1) == 0.33);
		assertTrue(p.getCoordinate(2) == 1.22);
		assertTrue(p.getCoordinate(3) == 5.6);
		assertTrue(p.getCoordinate(4) == -1700.0);
		assertTrue(p.getCoordinate(5) == 34.56);
	}

	@Test
	public final void testNaturalInt() {
		Natural n = JBambooNamespace.natural(5);
		Natural m = JBambooNamespace.natural(-5);
		assertTrue(n.toInt() == m.toInt());
	}

	@Test
	public final void testNaturalDouble() {
		Natural n = JBambooNamespace.natural(5);
		Natural m = JBambooNamespace.natural(-5.7);
		assertTrue(n.toInt() == m.toInt());
	}

}
