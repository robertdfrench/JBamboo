package jbamboo.predicatefunctions;

import java.util.HashMap;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.functions.RealFunction;


public class PredicateFunctionConstructor extends JBambooNamespace {

	private HashMap<RealPredicate, RealFunction> rules;
	
	public PredicateFunctionConstructor() {
		rules = new HashMap<RealPredicate, RealFunction>();
	}
	
	public void addRule(RealPredicate predicate, RealFunction function) {
		rules.put(predicate, function);
	}
	
	public PredicateFunction constructPredicateFunction() {
		PredicateFunction pf = new PredicateFunction(rules);
		return pf;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("PredicateFunctionConstructor with rules: %s", rules);
	}
	
}
