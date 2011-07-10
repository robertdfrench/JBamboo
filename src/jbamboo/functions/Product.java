package jbamboo.functions;

import jbamboo.basetypes.Point;

/**
 * RealFunction that yields the product of two other RealFunctions
 * @author robertdfrench
 *
 */
public class Product extends RealFunction {

	RealFunction left;
	RealFunction right;
	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return left.valueForPoint(p) * right.valueForPoint(p);
	}

	/**
	 * Pass in two Real Functions and get a Real Function representing their product
	 * @param left
	 * @param right
	 */
	public Product(RealFunction left, RealFunction right) {
		this.left = left;
		this.right = right;
	}
	
	/**
	 * String representation
	 */
	public String toString() {
		return String.format("(%s) * (%s)", left, right);
	}

	@Override
	public RealFunction getDerivative() {
		// TODO Auto-generated method stub
		return new Sum(
			new Product(left.getDerivative(),right),
			new Product(left, right.getDerivative())
		);
	}
}
