package jbamboo.predicatefunctions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import jbamboo.basetypes.Point;
import jbamboo.functions.RealFunction;
import jbamboo.functions.Zero;


public class PredicateFunction extends RealFunction {

	private HashMap<RealPredicate, RealFunction> functions;
	
	protected PredicateFunction(HashMap<RealPredicate, RealFunction> functions) {
		this.functions = functions;
	}

	@Override
	public Double valueForPoint(Point x) {
		for (RealPredicate predicate : functions.keySet()) {
			if (predicate.matches(x)) {
				RealFunction function = functions.get(predicate);
				return function.valueForPoint(x);
			}
		}
		// These are Real Functions, not extended Real Functions,
		// so they must vanish off their support (they must not diverge)
		return 0.0;
	}
	
	public PredicateFunction plus(PredicateFunction that) {
		PredicateFunctionConstructor pfc = new PredicateFunctionConstructor();
		HashSet<RealPredicate> predicates = this.joinedPredicates(that);
		
		for(RealPredicate predicate : predicates) {
			RealFunction f = this.functions.get(predicate);
			RealFunction g = that.functions.get(predicate);
			f = (f == null) ? new Zero() : f;
			g = (g == null) ? new Zero() : g;
			pfc.addRule(predicate, f.plus(g));
		}
		
		return pfc.constructPredicateFunction();
	}
	
	public PredicateFunction times(PredicateFunction that) {
		PredicateFunctionConstructor pfc = new PredicateFunctionConstructor();
		HashSet<RealPredicate> predicates = this.overlappingPredicates(that);
		
		for(RealPredicate predicate : predicates) {
			RealFunction f = this.functions.get(predicate);
			RealFunction g = that.functions.get(predicate);
			pfc.addRule(predicate, f.times(g));
		}
		
		return pfc.constructPredicateFunction();
	}
	
	public HashSet<RealPredicate> joinedPredicates(PredicateFunction that) {
		HashSet<RealPredicate> predicates = new HashSet<RealPredicate>();
		predicates.addAll(this.functions.keySet());
		predicates.addAll(that.functions.keySet());
		return predicates;
	}
	
	public HashSet<RealPredicate> overlappingPredicates(PredicateFunction that) {
		HashSet<RealPredicate> predicates = new HashSet<RealPredicate>(this.functions.keySet());
		predicates.retainAll(that.functions.keySet());
		return predicates;
	}
	
	public HashMap<RealPredicate, RealFunction> getFunctions() {
		return functions;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(Entry<RealPredicate,RealFunction> entry : functions.entrySet()) {
			sb.append(String.format("%s \\iff %s", entry.getValue(), entry.getKey()));
		}
		return null;
	}
}
