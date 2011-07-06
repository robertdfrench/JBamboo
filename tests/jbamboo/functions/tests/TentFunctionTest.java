package jbamboo.functions.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Point;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.functions.RealFunction;
import jbamboo.functions.TentFunction2D;
import jbamboo.mesh.MeshNode;
import jbamboo.mockobjects.MockMeshNode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TentFunctionTest {

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
	public final void testToString() throws InvalidIntervalException, BadTentConfigurationException {
		Point L = new Point(-1.0);
		Point C = new Point(0.0);
		Point R = new Point(1.0);
		IntervalElement LC = new IntervalElement(L,C);
		IntervalElement CR = new IntervalElement(C,R);
		TentFunction2D f = new TentFunction2D(LC,CR,1.0);
		assertEquals(f.toString(),"");
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testValueForPoint() throws InvalidIntervalException, BadTentConfigurationException {
		Point L = new Point(-1.0);
		Point C = new Point(0.0);
		Point R = new Point(1.0);
		IntervalElement LC = new IntervalElement(L,C);
		IntervalElement CR = new IntervalElement(C,R);
		TentFunction2D f = new TentFunction2D(LC,CR,1.0);
		
		Double expected = 0.5;
		Double got = f.valueForPoint(new Point(0.5));
		assertEquals(expected,got);
	}

	@Test
	public final void testTentFunctionForNode() throws InvalidIntervalException, BadTentConfigurationException {
		MeshNode i = MockMeshNode.make();
		TentFunction2D f = TentFunction2D.forNode(i,1.0);
		
		Double expected = 0.5;
		Double got = f.valueForPoint(new Point(0.5));
		assertEquals(expected,got);
	}

	@Test
	public final void testTentFunction() throws InvalidIntervalException, BadTentConfigurationException {
		Point L = new Point(-1.0);
		Point C = new Point(0.0);
		Point R = new Point(1.0);
		IntervalElement LC = new IntervalElement(L,C);
		IntervalElement CR = new IntervalElement(C,R);
		TentFunction2D f = new TentFunction2D(LC,CR,1.0);
		
		Double expected = 0.5;
		Double got = f.valueForPoint(new Point(0.5));
		assertEquals(expected,got);
	}

	@Test
	public final void testTimesTentFunction() throws InvalidIntervalException, BadTentConfigurationException {
		Point L = new Point(-1.0);
		Point C = new Point(0.0);
		Point R = new Point(1.0);
		Point S = new Point(2.0);
		IntervalElement LC = new IntervalElement(L,C);
		IntervalElement CR = new IntervalElement(C,R);
		IntervalElement RS = new IntervalElement(R,S);
		TentFunction2D f = new TentFunction2D(LC,CR,1.0);
		TentFunction2D g = new TentFunction2D(CR,RS,1.0);
		RealFunction h = f.times(g);
		
		Double expected = 0.25;
		Double got = h.valueForPoint(new Point(0.5));
		assertEquals(expected,got);
	}

	@Test
	public final void testPlusTentFunction() throws InvalidIntervalException, BadTentConfigurationException {
		Point L = new Point(-1.0);
		Point C = new Point(0.0);
		Point R = new Point(1.0);
		Point S = new Point(2.0);
		IntervalElement LC = new IntervalElement(L,C);
		IntervalElement CR = new IntervalElement(C,R);
		IntervalElement RS = new IntervalElement(R,S);
		TentFunction2D f = new TentFunction2D(LC,CR,1.0);
		TentFunction2D g = new TentFunction2D(CR,RS,1.0);
		RealFunction h = f.plus(g);
		
		Double expected = 1.0;
		Double got = h.valueForPoint(new Point(0.5));
		assertEquals(expected,got);
	}

}
