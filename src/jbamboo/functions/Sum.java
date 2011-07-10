package jbamboo.functions;

import jbamboo.basetypes.Point;

/**
 * This is a RealFunction subclass that acts as a <code>+</code> in an execution tree. It's job
 * is to evaluate both of its addend functions for a given point, and then to return their sum.
 * @author robertdfrench
 *
 */
public class Sum extends RealFunction {

	RealFunction left;
	RealFunction right;
	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return left.valueForPoint(p) + right.valueForPoint(p);
	}

	/**
	 * Creates a Real Function that will evaluate <code>left(point) + right(point)</code>
	 * @param left
	 * @param right
	 */
	public Sum(RealFunction left, RealFunction right) {
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
		return String.format("(%s) + (%s)", left, right);
	}

	@Override
	public RealFunction getDerivative() {
		// TODO Auto-generated method stub
		return new Sum(left.getDerivative(),right.getDerivative());
	}
}
