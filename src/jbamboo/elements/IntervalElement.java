package jbamboo.elements;

import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.functions.RealFunction;

/**
 * Element Type representing a closed interval such as [0,1]
 * @author robertdfrench
 *
 */
public class IntervalElement extends FiniteElement {

	private Point lowerBound;
	private Point upperBound;
	
	/**
	 * This is the protected constructor that only the ElementFactory can call
	 */
	protected IntervalElement() {
		
	}
	
	/**
	 * Regular constructor for use outside the ElementFactory
	 * @param p
	 * @param q
	 * @throws InvalidIntervalException
	 */
	public IntervalElement(Point p, Point q) throws InvalidIntervalException {
		if (p.getCoordinate(1) >= q.getCoordinate(1)) throw new InvalidIntervalException();
		initializePoints(new Natural(2));
		
		lowerBound = p;
		setPoint(new Natural(1),p);
		
		upperBound = q;
		setPoint(new Natural(2),q);
	}

	@Override
	/**
	 * returns the element length
	 */
	public Double getLength() {
		// TODO Auto-generated method stub
		return upperBound.x() - lowerBound.x();
	}

	@Override
	public Point translateToGlobalPoint(Point localPoint) {
		// TODO Auto-generated method stub
		return lowerBound.plus(localPoint);
	}
	
	@Override
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
		/*Double integral = 0.0;
		Double width = this.getLength() / numPoints.toDouble();
		Point p = new Point(lowerBound);
		for (Integer i : numPoints) {
			p.x(lowerBound.x() + i*width);
			integral += width*f.valueForPoint(p);
		}
		
		return integral;*/
		
		// Composite Simpsons 1D
		/*Double compSimpsons1d = 0.0, evenSum = 0.0, oddSum = 0.0;
		Point p = new Point(lowerBound);
	    Point a = lowerBound;
	    Point b = upperBound;
	    Double h = this.getLength() / numPoints.toDouble();

	    for (int i=0; i < numPoints.toInt(); i++) {
	      if ( (i%2) == 0 ) {
	    	p.x(a.x() + (double)i*h);
	        evenSum += f.valueForPoint(p);
	      } else {
	    	p.x(a.x() + (double)i*h);
	        oddSum += f.valueForPoint(p);
	      }
	    }

	    compSimpsons1d = ((h/3.0) * ( f.valueForPoint(a) + f.valueForPoint(b) + 2.0*evenSum + 4.0*oddSum ));
	    
	    return compSimpsons1d;*/
		
		// Gaussian Quadrature 1D w/ n=5
		Double r[] = { 0.9061798459386640, 0.5384693101056831, 0.0000000000000000, -0.5384693101056831, -0.9061798459386640 };
		Double coeff[] = { 0.2369268850561891, 0.4786286704993665, 0.5688888888888889, 0.4786286704993665, 0.2369268850561891 };
		
		Double gaussQuad1d5 = 0.0, sum = 0.0;
	    Point a = lowerBound;
	    Point b = upperBound;
	    Point p = new Point(lowerBound);

	    for (int i = 0; i <= 4; i++) {
	    	p.x( ((b.x()-a.x())*r[i] + b.x() + a.x()) / 2.0 );
	    	sum += coeff[i]*f.valueForPoint(p);
	    }

	    gaussQuad1d5 = ( (b.x()-a.x()) / 2.0 ) * sum;
	      
	    return gaussQuad1d5;
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

	@Override
	public Double[] apply(RealFunction f, Natural numPoints) {
		Double[] results = new Double[numPoints.toInt()];
		Double width = this.getLength() / ((double) (numPoints.toInt() - 1));
		Point p = new Point(lowerBound);
		for(Integer i : numPoints) {
			results[i - 1] = f.valueForPoint(p);
			p.x(lowerBound.x() + i*width);
		}
		return results;
	}

}
