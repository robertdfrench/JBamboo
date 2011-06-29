package jbamboo.innerproducts;

import java.util.Set;

import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;


public interface InnerProduct {
	public abstract Double 
		compute(
			RealFunction f, 
			RealFunction g,
			Set<FiniteElement> elements) 
		throws 
			InvalidElementException, 
			IntegrationException;
}
