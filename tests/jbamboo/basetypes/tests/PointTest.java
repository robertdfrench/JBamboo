package jbamboo.basetypes.tests;

import static org.junit.Assert.*;
import jbamboo.basetypes.Point;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PointTest {

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
		Point p = new Point();
		assertTrue(p.getCoordinate(0) == 0);
		assertTrue(p.getCoordinate(1) == 0);
		assertTrue(p.getCoordinate(10) == 0);
	}

	@Test
	public final void testPointDouble() {
		Point p = new Point(5.0);
		assertTrue(p.getCoordinate(1) == 5.0);
	}

	@Test
	public final void testPointDoubleDouble() {
		Point p = new Point(5.0,10.0);
		assertTrue(p.getCoordinate(1) == 5.0);
		assertTrue(p.getCoordinate(2) == 10.0);
	}

	@Test
	public final void testPointDoubleDoubleDouble() {
		Point p = new Point(5.0,10.0,15.0);
		assertTrue(p.getCoordinate(1) == 5.0);
		assertTrue(p.getCoordinate(2) == 10.0);
		assertTrue(p.getCoordinate(3) == 15.0);
	}

	@Test
	public final void testPlus() {
		Point p = new Point(1.0, 2.0, 3.0);
		Point q = new Point(0.5, 0.5, 0.5);
		Point r = p.plus(q);
		assertTrue(r.getCoordinate(1) == 1.5);
		assertTrue(r.getCoordinate(2) == 2.5);
		assertTrue(r.getCoordinate(3) == 3.5);
	}

	@Test
	public final void testPointPoint() {
		Point p = new Point(1.0, 2.0, 3.0);
		Point q = new Point(p);
		
		assertTrue(q.getCoordinate(1) == 1.0);
		assertTrue(q.getCoordinate(2) == 2.0);
		assertTrue(q.getCoordinate(3) == 3.0);
	}

	@Test
	public final void testSetCoordinate() {
		Point q = new Point();
		assertTrue(q.getCoordinate(10) == 0.0);
		q.setCoordinate(10,500.0);
		assertTrue(q.getCoordinate(10) == 500.0);
	}

	@Test
	public final void testGetCoordinate() {
		Point q = new Point();
		assertTrue(q.getCoordinate(10) == 0.0);
		q.setCoordinate(10,500.0);
		assertTrue(q.getCoordinate(10) == 500.0);
	}

	@Test
	public final void testX() {
		Point p = new Point();
		p.setCoordinate(1,10.0);
		assertTrue(p.x() == 10.0);
	}

	@Test
	public final void testXDouble() {
		Point p = new Point();
		p.x(10.0);
		assertTrue(p.getCoordinate(1) == 10.0);
	}

	@Test
	public final void testY() {
		Point p = new Point();
		p.setCoordinate(2,10.0);
		assertTrue(p.y() == 10.0);
	}

	@Test
	public final void testYDouble() {
		Point p = new Point();
		p.y(10.0);
		assertTrue(p.getCoordinate(2) == 10.0);	}

	@Test
	public final void testZ() {
		Point p = new Point();
		p.setCoordinate(3,10.0);
		assertTrue(p.z() == 10.0);
	}

	@Test
	public final void testZDouble() {
		Point p = new Point();
		p.z(10.0);
		assertTrue(p.getCoordinate(3) == 10.0);	}

}
