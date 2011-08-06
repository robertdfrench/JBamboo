package jbamboo.functions;

import static org.junit.Assert.*;

import jbamboo.basetypes.Point;
import jbamboo.functions.Polynomial;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolynomialTest {
	
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
	public final void testValueForPoint() {
		// (x + 7)*(x - 12) = x^2 - 5x - 84
		Polynomial f = new Polynomial();
		f.setCoefficient(0,-84);
		f.setCoefficient(1,-5);
		f.setCoefficient(2,1);
		
		assertTrue(f.valueForPoint(new Point(-7.0)) == 0);
		assertTrue(f.valueForPoint(new Point(12.0)) == 0);
	}

	@Test
	public final void testPolynomial() {
		@SuppressWarnings("unused")
		Polynomial f = new Polynomial();
	}

	@Test
	public final void testPlusPolynomial() {
		Polynomial f = new Polynomial();
		f.setCoefficient(1,1);
		f.setCoefficient(2,2);
		f.setCoefficient(3,3);
		
		Polynomial g = new Polynomial();
		g.setCoefficient(1,1);
		g.setCoefficient(2,2);
		g.setCoefficient(3,3);
		
		Polynomial h = f.plus(g);
		assertTrue(h.getCoefficient(1) == 2);
		assertTrue(h.getCoefficient(2) == 4);
		assertTrue(h.getCoefficient(3) == 6);
	}

	@Test
	public final void testTimesPolynomial() {
		Polynomial f = new Polynomial();
		f.setCoefficient(1,1);
		f.setCoefficient(2,2);
		f.setCoefficient(3,3);
		
		Polynomial g = new Polynomial();
		g.setCoefficient(1,1);
		g.setCoefficient(2,2);
		g.setCoefficient(3,3);
		
		Polynomial h = f.times(g);
		assertTrue(h.getCoefficient(1) == 0);
		assertTrue(h.getCoefficient(2) == 1);
		assertTrue(h.getCoefficient(3) == 4);
		assertTrue(h.getCoefficient(4) == 10);
		assertTrue(h.getCoefficient(5) == 12);
		assertTrue(h.getCoefficient(6) == 9);
	}

	@Test
	public final void testPlusEqualsCoefficient() {
		Polynomial f = new Polynomial();
		f.setCoefficient(1,5);
		f.plusEqualsCoefficient(1,5);
		assertTrue(f.getCoefficient(1) == 10);
	}

	@Test
	public final void testGetCoefficient() {
		Polynomial f = new Polynomial();
		assertTrue(f.getCoefficient(15) == 0);
	}

	@Test
	public final void testSetCoefficient() {
		Polynomial f = new Polynomial();
		f.setCoefficient(15,100);
		assertTrue(f.getCoefficient(15) == 100);
	}

}
