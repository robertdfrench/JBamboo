package jbamboo.innerproducts;

import java.util.Set;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;

/**
 * This is an Inner Product which can be used to construct the load vector anywhere, I think.
 * @author robertdfrench
 *
 */
public class StandardRealInnerProduct extends JBambooNamespace implements InnerProduct {
	
	private Natural precision;
	
	public StandardRealInnerProduct(Natural precision) {
		this.precision = precision;
	}

	@Override
	public Double compute(RealFunction f, RealFunction g,
			Set<FiniteElement> elements) throws InvalidElementException,
			IntegrationException {
		Double innerProduct = 0.0;
		
		RealFunction f_times_g = f.times(g);
		for(FiniteElement e : elements) {
			innerProduct += e.integrate(f_times_g, precision);
		}
		
		return innerProduct;
	}

	public String toString() {
		return String.format("\\int_{elements}f*g, precision = %s", precision); 
	}
}
