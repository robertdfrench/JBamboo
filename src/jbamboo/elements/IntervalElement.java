package jbamboo.elements;

import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.functions.RealFunction;

public class IntervalElement extends FiniteElement {

	private Point lowerBound;
	private Point upperBound;
		
	protected IntervalElement() {
		
	}
	
	public IntervalElement(Point p, Point q) throws InvalidIntervalException {
		if (p.getCoordinate(1) >= q.getCoordinate(1)) throw new InvalidIntervalException();
		initializePoints(new Natural(2));
		
		lowerBound = p;
		setPoint(new Natural(1),p);
		
		upperBound = q;
		setPoint(new Natural(2),q);
	}

	@Override
	public Double getLength() {
		// TODO Auto-generated method stub
		return upperBound.x() - lowerBound.x();
	}

	@Override
	public Point translateToGlobalPoint(Point localPoint) {
		// TODO Auto-generated method stub
		return lowerBound.plus(localPoint);
	}
	
	public Point[] getPoints() {
		Point[] points = {lowerBound, upperBound};
		return points;
	}

	@Override
	public boolean contains(Point p) {
		return (lowerBound.x() <= p.x() && p.x() <= upperBound.x());
	}

	@Override
	public Double integrate(RealFunction f, Natural numPoints) {
		Double integral = 0.0;
		Double width = this.getLength() / numPoints.toDouble();
		Point p = new Point(lowerBound);
		for (Integer i : numPoints) {
			p.x(lowerBound.x() + i*width);
			integral += width*f.valueForPoint(p);
		}
		
		return integral;
	}

	@Override
	public String toString() {
		return String.format("[%f,%f]", lowerBound.x(), upperBound.x());
	}

	@Override
	protected FiniteElement semiconstructor(Point... points) throws InvalidElementException {
		if (points.length < 2) throw new InvalidIntervalException();
		return new IntervalElement(points[0], points[1]);
	}

	@Override
	public boolean equals(Object o) {
		IntervalElement that = (IntervalElement) o;
		return (this.lowerBound == that.lowerBound && this.upperBound == that.upperBound);
	}

}
