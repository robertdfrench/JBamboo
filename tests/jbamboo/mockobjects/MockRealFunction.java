package jbamboo.mockobjects;

import jbamboo.basetypes.Point;
import jbamboo.functions.RealFunction;

public class MockRealFunction extends RealFunction {
	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return 1.0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "mock(p)";
	}

	@Override
	public RealFunction getDerivative() {
		// TODO Auto-generated method stub
		return null;
	}
	
}