package jbamboo.functions;

import jbamboo.basetypes.Point;

public class Sum extends RealFunction {

	RealFunction left;
	RealFunction right;
	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return left.valueForPoint(p) + right.valueForPoint(p);
	}

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
