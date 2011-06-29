package jbamboo.mockobjects;

import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.functions.RealFunction;

public class MockFiniteElement extends FiniteElement {

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
	
	public void initializePoints(Natural numPoints) {
		super.initializePoints(numPoints);
	}
	
	public void setPoint(Natural i, Point p) {
		super.setPoint(i, p);
	}
}
