package jbamboo.elements.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.mockobjects.MockFiniteElement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FiniteElementTest {

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
	public final void testGetPoints() {
		MockFiniteElement e = new MockFiniteElement();
		assertNull(e.getPoints());
	}

	@Test
	public final void testInitializePoints() {
		MockFiniteElement e = new MockFiniteElement();
		e.initializePoints(new Natural(5));
		assertTrue(e.getPoints().length == 5);
	}

	@Test
	public final void testSetPoint() {
		MockFiniteElement e = new MockFiniteElement();
		e.initializePoints(new Natural(5));
		Point p = new Point(1.0,2.0);
		e.setPoint(new Natural(4), p);
	}

}
