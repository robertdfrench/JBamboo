package jbamboo.predicatefunctions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import jbamboo.basetypes.Point;
import jbamboo.functions.RealFunction;
import jbamboo.functions.Zero;

/**
 * This is a RealFunction that associates various Predicates with various Functions, presumeably of
 * the same domain. It must be constructed via  a PredicateFunctionConstructor in order to gaurantee
 * that it has been built in a consistent state.
 * @author robertdfrench
 *
 */
public class PredicateFunction extends RealFunction {

	private HashMap<RealPredicate, RealFunction> functions;
	
	/**
	 * Used by the PredicateFunctionConstructor to prepare a finished PredicateFunction
	 * @param functions
	 */
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
	
	/**
	 * Yields a new PredicateFunction that is defined for all of the predicates used by either <code>this</code>
	 * or <code>that</code>. In the case where <code>this</code> and <code>that</code> share overlapping predicates,
	 * the function corresponding to those predicates will be the <code>Sum</code> of the original two functions.
	 * Otherwise, it will be the individual function.<br/>
	 * <br/>
	 * For example, if I have two Real Functions, <code>f(x)</code> with a support of <code>[0,2]</code>, and 
	 * <code>g(x)</code> with a support of <code>[1,3]</code>, then <code>f.plus(g)</code> will act as follows:
	 * <ul>
	 * <li> <code>f</code> on <code>[0,1]</code></li>
	 * <li> <code>f + g</code> on <code>[1,2]</code></li>
	 * <li> <code>g</code> on <code>[2,3]</code></li>
	 * </ul>
	 * @param that
	 * @return
	 */
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
	
	/**
	 * Yields a new PredicateFunction that is defined for all of the predicates used by both <code>this</code>
	 * and <code>that</code>. In the case where <code>this</code> and <code>that</code> share overlapping predicates,
	 * the function corresponding to those predicates will be the <code>Product</code> of the original two functions.
	 * Otherwise, it will be <code>Zero</code>.<br/>
	 * <br/>
	 * For example, if I have two Real Functions, <code>f(x)</code> with a support of <code>[0,2]</code>, and 
	 * <code>g(x)</code> with a support of <code>[1,3]</code>, then <code>f.times(g)</code> will act as follows:
	 * <ul>
	 * <li> <code>f</code> on <code>[0,1]</code></li>
	 * <li> <code>f * g</code> on <code>[1,2]</code></li>
	 * <li> <code>g</code> on <code>[2,3]</code></li>
	 * </ul>
	 * @param that
	 * @return
	 */
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
	
	/**
	 * Calculates the union of the predicates.
	 * @param that
	 * @return
	 */
	public HashSet<RealPredicate> joinedPredicates(PredicateFunction that) {
		HashSet<RealPredicate> predicates = new HashSet<RealPredicate>();
		predicates.addAll(this.functions.keySet());
		predicates.addAll(that.functions.keySet());
		return predicates;
	}
	
	/**
	 * Calculates the intersection of the predicates
	 * @param that
	 * @return
	 */
	public HashSet<RealPredicate> overlappingPredicates(PredicateFunction that) {
		HashSet<RealPredicate> predicates = new HashSet<RealPredicate>(this.functions.keySet());
		predicates.retainAll(that.functions.keySet());
		return predicates;
	}
	
	/**
	 * Gets the underlying Predicate -> Function map.
	 * @return
	 */
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
		return sb.toString();
	}

	@Override
	public RealFunction getDerivative() {
		PredicateFunctionConstructor pfc = new PredicateFunctionConstructor();
		for (RealPredicate pred : functions.keySet()) {
			pfc.addRule(pred, functions.get(pred).getDerivative());
		}
		return pfc.constructPredicateFunction();
	}
}
