package jbamboo.innerproducts;

import java.util.Set;

import jbamboo.basetypes.Natural;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.functions.RealFunction;

public class SuperSobolevInnerProduct implements InnerProduct {
	
	private InnerProduct sobolev;
	private InnerProduct standard;
	
	public SuperSobolevInnerProduct(Natural precision) {
		sobolev = new SobolevInnerProduct(precision);
		standard = new StandardRealInnerProduct(precision);
	}

	@Override
	public Double computeNumerically(RealFunction f, RealFunction g,
			Set<FiniteElement> elements) throws InvalidElementException,
			IntegrationException {
		// TODO Auto-generated method stub
		return sobolev.computeNumerically(f, g, elements) + standard.computeNumerically(f, g, elements);
	}

	@Override
	public Double computeGeometrically(RealFunction f, RealFunction g,
			Set<FiniteElement> elements) throws InvalidElementException {
		// TODO Auto-generated method stub
		Double innerproduct = 0.0;
		try {
			innerproduct = sobolev.computeGeometrically(f, g, elements) + standard.computeNumerically(f, g, elements);
		} catch (IntegrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return innerproduct;
	}

}
