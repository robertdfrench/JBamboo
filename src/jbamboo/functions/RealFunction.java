package jbamboo.functions;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;

public abstract class RealFunction extends JBambooNamespace {

	public RealFunction plus(RealFunction that) {
		return new Sum(this, that);
	}
	
	public RealFunction times(RealFunction that) {
		return new Product(this, that);
	}
	
	public abstract RealFunction getDerivative();
	public abstract Double valueForPoint(Point p);
}
