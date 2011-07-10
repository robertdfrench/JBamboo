package jbamboo.functions;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;

/**
 * Represents 1-dimensional Real Functions
 * @author robert
 *
 */
public abstract class RealFunction extends JBambooNamespace {

	/**
	 * Returns the sum of this and that
	 * @param that
	 * @return
	 */
	public RealFunction plus(RealFunction that) {
		return new Sum(this, that);
	}
	
	/**
	 * Product of this and that
	 * @param that
	 * @return
	 */
	public RealFunction times(RealFunction that) {
		return new Product(this, that);
	}
	
	/**
	 * Returns the derivative of the Real Function
	 * @return
	 */
	//TODO: Needs to be abstracted to DifferentiableRealFunction
	public abstract RealFunction getDerivative();
	
	/**
	 * Calculates the value of this function at the given point P
	 * @param p
	 * @return
	 */
	public abstract Double valueForPoint(Point p);
}
