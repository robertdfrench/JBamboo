package jbamboo.functions;

import jbamboo.basetypes.Point;

public class Product extends RealFunction {

	RealFunction left;
	RealFunction right;
	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return left.valueForPoint(p) * right.valueForPoint(p);
	}

	public Product(RealFunction left, RealFunction right) {
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
		return String.format("(%s) * (%s)", left, right);
	}
}
