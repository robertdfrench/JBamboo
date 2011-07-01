package jbamboo.tesselationpolicy;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.exceptions.PolicyNotFinishedException;

public abstract class PolicyAuthor extends JBambooNamespace {
	
	public abstract TesselationPolicy getFinishedPolicy() throws PolicyNotFinishedException;

}
