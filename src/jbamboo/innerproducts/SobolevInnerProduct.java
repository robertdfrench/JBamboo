package jbamboo.innerproducts;

import java.util.Set;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;

public class SobolevInnerProduct extends JBambooNamespace implements
		InnerProduct {

	private Natural precision;
	
	public SobolevInnerProduct(Natural precision) {
		this.precision = precision;
	}
	@Override
	public Double compute(RealFunction f, RealFunction g,
			Set<FiniteElement> elements) throws InvalidElementException,
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
