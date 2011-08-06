package jbamboo.integration;

import jbamboo.elements.FiniteElement;
import jbamboo.functions.RealFunction;

public interface IntegrationStrategy {
	public Double execute(RealFunction f, FiniteElement e);
}
