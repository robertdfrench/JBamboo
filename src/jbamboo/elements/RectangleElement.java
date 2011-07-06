package jbamboo.elements;

import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidRectangleException;
import jbamboo.functions.RealFunction;

public class RectangleElement extends FiniteElement {
	
	private Point a;
	private Point b;
	private Point c;
	private Point d;

	protected RectangleElement() {
		
	}
	
	public RectangleElement(Point p, Point q) throws InvalidRectangleException {
		if (p.x() >= q.x()) throw new InvalidRectangleException();
		if (p.y() >= q.y()) throw new InvalidRectangleException();
		initializePoints(natural(4));
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Double integrate(RealFunction f, Natural numPoints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getLength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point translateToGlobalPoint(Point localPoint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected FiniteElement semiconstructor(Point... points)
			throws InvalidElementException {
		// TODO Auto-generated method stub
		return null;
	}

}
