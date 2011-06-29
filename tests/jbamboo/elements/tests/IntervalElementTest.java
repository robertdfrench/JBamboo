package jbamboo.elements.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.mockobjects.MockRealFunction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IntervalElementTest {
	
	private Point a,b;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		a = new Point(-1.0);
		b = new Point(1.0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testToString() throws InvalidIntervalException {
		IntervalElement e = new IntervalElement(a,b);
		String eString = e.toString();
		String cString = "[-1.000000,1.000000]";
		assertTrue (
			String.format("Got\t %s\nExp\t %s", eString, cString),
			eString.equals(cString)
		);
	}

	@Test
	public final void testIntegrate() throws InvalidIntervalException {
		IntervalElement e = new IntervalElement(a,b);
		Double integral = e.integrate(new MockRealFunction(), new Natural(100));
		assertTrue (
			String.format("Got\t %f\nExp\t %f", integral, 2.0),
			Math.abs(integral - 2.0) < 0.00001
		);
	}

	@Test
	public final void testGetLength() throws InvalidIntervalException {
		IntervalElement e = new IntervalElement(a,b);
		assertTrue (
			String.format("Got\t %f\nExp\t %f", e.getLength(), 2.0),
			e.getLength() == 2.0 
		);
	}

	@Test
	public final void testTranslateToGlobalPoint() throws InvalidIntervalException {
		IntervalElement e = new IntervalElement(a,b);
		Point x = new Point(0.7);
		Point xPrime = e.translateToGlobalPoint(x);
		assertTrue (
			String.format("Got\t %f\nExp\t %f", xPrime.x(), -0.3),
			Math.abs(xPrime.x() - -0.3) < 0.00001
		);
	}

	@Test
	public final void testContains() throws InvalidIntervalException {
		IntervalElement e = new IntervalElement(a,b);
		Point x = new Point(0.7);
		Point y = new Point(1.7);
		assertTrue(e.contains(x));
		assertFalse(e.contains(y));
	}

	@Test
	public final void testIntervalElement() throws InvalidIntervalException {
		new IntervalElement(a,b);
		boolean complainedAboutBadConfig = false;
		try {
			new IntervalElement(b,a);
		} catch (InvalidIntervalException exception) {
			complainedAboutBadConfig = true;
		}
		assertTrue(complainedAboutBadConfig);
	}

}
