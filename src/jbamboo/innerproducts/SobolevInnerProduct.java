package jbamboo.innerproducts;

import java.util.Set;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;

/**
 * This is an inner Product that can be used to calculate the Stiffness Matrix
 * for ODE's like <code>-u''=f(x)</code>.
 * @author robertdfrench
 *
 */
public class SobolevInnerProduct extends JBambooNamespace implements
		InnerProduct {

	private Natural precision;
	
	public SobolevInnerProduct(Natural precision) {
		this.precision = precision;
	}
	
	@Override
	public Double computeGeometrically(RealFunction f, RealFunction g,Set<FiniteElement> elements) throws InvalidElementException {
		Double innerProduct = 0.0;
		
		RealFunction lf_times_lg = f.getDerivative().times(g.getDerivative());
		for(FiniteElement e : elements) {
			//innerProduct += e.integrate(lf_times_lg, precision);
			double length = e.getLength();
			Point[] points = e.getPoints();
			Point p = point((points[0].x() + points[1].x())/2);
			double height = lf_times_lg.valueForPoint(p);
			innerProduct += length * height;
		}
		
		return innerProduct;
	}
	
	@Override
	public Double computeNumerically(RealFunction f, RealFunction g, Set<FiniteElement> elements)  throws InvalidElementException,
	IntegrationException {
		Double innerProduct = 0.0;
		
		RealFunction lf_times_lg = f.getDerivative().times(g.getDerivative());
		for(FiniteElement e : elements) {
			innerProduct += e.integrate(lf_times_lg, precision);
		}
		
		return innerProduct;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}