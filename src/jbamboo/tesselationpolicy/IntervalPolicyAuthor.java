package jbamboo.tesselationpolicy;

import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.PolicyNotFinishedException;

public class IntervalPolicyAuthor extends PolicyAuthor {
	
	private TesselationPolicy tp;
	private SemiconstructorAuthorization sa;
	
	public IntervalPolicyAuthor() {
		sa = new SemiconstructorAuthorization();
		tp = new TesselationPolicy(sa);
		IntervalElement templateElement = new IntervalElement(sa);
		tp.addRule(2, templateElement);
	}

	@Override
	public TesselationPolicy getFinishedPolicy()
			throws PolicyNotFinishedException {
		// TODO Auto-generated method stub
		return tp;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Interval Policy Author";
	}

}
