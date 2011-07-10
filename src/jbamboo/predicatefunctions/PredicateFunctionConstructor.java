package jbamboo.predicatefunctions;

import java.util.HashMap;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.functions.RealFunction;

/**
 * Used as a constructor for Predicate Functions
 * @author robert
 *
 */
public class PredicateFunctionConstructor extends JBambooNamespace {

	private HashMap<RealPredicate, RealFunction> rules;
	
	/**
	 * Construct a new PRedicateFunctionConstructor with an empty ruleset
	 */
	public PredicateFunctionConstructor() {
		rules = new HashMap<RealPredicate, RealFunction>();
	}
	
	/**
	 * Associates the given function with the given predicate
	 * @param predicate
	 * @param function
	 */
	public void addRule(RealPredicate predicate, RealFunction function) {
		rules.put(predicate, function);
	}
	
	/**
	 * Call this after all of the rules have been defined, and it will
	 * yield a valid Predicate Function
	 * @return
	 */
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
