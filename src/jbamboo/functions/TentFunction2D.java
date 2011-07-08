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


public class TentFunction2D extends RealFunction {

	private PredicateFunction internalFunction;
	
	public static TentFunction2D forNode(MeshNode m, Double height) throws BadTentConfigurationException {
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
		if (LC == null || CR == null) throw new BadTentConfigurationException();
		return new TentFunction2D(LC, CR, height);
	}
	
	public TentFunction2D(IntervalElement LC, IntervalElement CR, Double height) throws BadTentConfigurationException {
		Point L, C, R, Top;
		RealPredicate leftPredicate, rightPredicate;

		if (height <= 0.0) throw new BadTentConfigurationException();
		try {
			if (!LC.getPoints()[1].equals(CR.getPoints()[0])) throw new BadTentConfigurationException();
			L = LC.getPoints()[0];
			C = LC.getPoints()[1];
			R = CR.getPoints()[1];
			Top = new Point(C);
			Top.setCoordinate(2, height);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new BadTentConfigurationException();
		}
		
		leftPredicate = new ElementPredicate(LC);
		rightPredicate = new ElementPredicate(CR);
		
		RealFunction leftWall = new Monomial(L,Top);
		RealFunction rightWall = new Monomial(Top,R);
		
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

	public PredicateFunction times(TentFunction2D that) {
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
	
	public PredicateFunction plus(TentFunction2D that) {
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
		return String.format("Tent Function with: %s",internalFunction.toString());
	}

	@Override
	public RealFunction getDerivative() {
		return internalFunction.getDerivative();
	}
}
