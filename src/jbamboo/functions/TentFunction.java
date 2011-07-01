package jbamboo.functions;

import java.util.HashSet;

import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.mesh.MeshNode;
import jbamboo.predicatefunctions.ElementPredicate;
import jbamboo.predicatefunctions.PredicateFunction;
import jbamboo.predicatefunctions.PredicateFunctionConstructor;
import jbamboo.predicatefunctions.RealPredicate;


public class TentFunction extends RealFunction {

	private PredicateFunction internalFunction;
	
	public static TentFunction forNode(MeshNode m, Double height) throws BadTentConfigurationException {
		Point C = m.getPoint();
		IntervalElement LC = null;
		IntervalElement CR = null;
		for (FiniteElement e : m) {
			IntervalElement currentInterval = (IntervalElement) e;
			Point[] intervalPoints = currentInterval.getPoints();
			if (C.equals(intervalPoints[0])) {
				CR = currentInterval;
			} else {
				LC = currentInterval;
			}
		}
		
		return new TentFunction(LC, CR, height);
	}
	
	public TentFunction(IntervalElement LC, IntervalElement CR, Double height) throws BadTentConfigurationException {
		Point L, C, R;
		RealPredicate leftPredicate, rightPredicate;

		if (height <= 0.0) throw new BadTentConfigurationException();
		try {
			if (LC.getPoints()[1] != CR.getPoints()[0]) throw new BadTentConfigurationException();
			L = LC.getPoints()[0];
			C = LC.getPoints()[1];
			R = CR.getPoints()[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new BadTentConfigurationException();
		}
		
		leftPredicate = new ElementPredicate(LC);
		rightPredicate = new ElementPredicate(CR);
		
		RealFunction leftWall = new Monomial(L,C);
		RealFunction rightWall = new Monomial(C,R);
		
		PredicateFunctionConstructor pfc = new PredicateFunctionConstructor();
		pfc.addRule(leftPredicate, leftWall);
		pfc.addRule(rightPredicate, rightWall);
		internalFunction = pfc.constructPredicateFunction();
	}
	
	@Override
	public Double valueForPoint(Point p) {
		// TODO Auto-generated method stub
		return internalFunction.valueForPoint(p);
	}

	public PredicateFunction times(TentFunction that) {
		PredicateFunctionConstructor pfc = new PredicateFunctionConstructor();
		HashSet<RealPredicate> predicates = this.internalFunction.overlappingPredicates(that.internalFunction);
		for (RealPredicate predicate : predicates) {
			Polynomial f = (Polynomial) this.internalFunction.getFunctions().get(predicate);
			Polynomial g = (Polynomial) that.internalFunction.getFunctions().get(predicate);
			f = (f == null) ? new Polynomial() : f;
			g = (g == null) ? new Polynomial() : g;
			pfc.addRule(predicate, f.times(g));
		}
		return pfc.constructPredicateFunction();
	}
	
	public PredicateFunction plus(TentFunction that) {
		PredicateFunctionConstructor pfc = new PredicateFunctionConstructor();
		HashSet<RealPredicate> predicates = this.internalFunction.joinedPredicates(that.internalFunction);
		for (RealPredicate predicate : predicates) {
			Polynomial f = (Polynomial) this.internalFunction.getFunctions().get(predicate);
			Polynomial g = (Polynomial) that.internalFunction.getFunctions().get(predicate);
			pfc.addRule(predicate, f.plus(g));
		}
		return pfc.constructPredicateFunction();
	}
	
	public String toString() {
		return internalFunction.toString();
	}
}
