package jbamboo.functions;

import jbamboo.basetypes.Point;

public class Euler extends RealFunction {
	private Double exponentCoefficient;
	private Double regularCoefficient;
	
	/**
	 * creates ae^{bx}
	 * @param a
	 * @param b
	 */
	public Euler(Double a, Double b) {
		this.exponentCoefficient = a;
		this.regularCoefficient = b;
	}

	@Override
	public RealFunction getDerivative() {
		// TODO Auto-generated method stub
		return new Euler(exponentCoefficient * regularCoefficient, exponentCoefficient);
	}

	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return regularCoefficient * Math.pow(Math.E, exponentCoefficient * p.x());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%fe^{%fx}",regularCoefficient,exponentCoefficient);
	}

}
