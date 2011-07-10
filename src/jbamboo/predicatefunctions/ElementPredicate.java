package jbamboo.predicatefunctions;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.InvalidIntervalException;

/**
 * This is a RealPredicate that associates a different RealFunction with each Element
 * @author robertdfrench
 *
 */
public class ElementPredicate extends JBambooNamespace implements RealPredicate {
	
	private FiniteElement e;
	
	/**
	 * These refer to closed intervals
	 * @param lowerBound
	 * @param upperBound
	 * @throws InvalidIntervalException 
	 */
	public ElementPredicate(FiniteElement e) {
		this.e = e;
	}

	@Override
	public boolean matches(Point p) {
		return e.contains(p);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("p \\in %s", e);
	}

}
