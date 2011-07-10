package jbamboo.functions;

import jbamboo.basetypes.Point;

/**
 * This is a Real Function which is defined to be Zero everywhere.
 * @author robertdfrench
 *
 */
public class Zero extends RealFunction {

	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	public String toString() {
		return "0";
	}

	@Override
	public RealFunction getDerivative() {
		// TODO Auto-generated method stub
		return this;
	}
}
