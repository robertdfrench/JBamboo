package jbamboo;

import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;
import jbamboo.basetypes.JBambooNamespace;

import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;

/**
 * Main entry point for running example applications
 * @author robertdfrench
 *
 */
public class JBambooMain extends JBambooNamespace {

	/**
	 * @param args
	 * @throws PolicyNotFinishedException 
	 * @throws BadTentConfigurationException 
	 * @throws IntegrationException 
	 * @throws InvalidElementException 
	 * @throws InvalidElementType 
	 * @throws IterativeSolverNotConvergedException 
	 */
	public static void main(String[] args) throws InvalidElementException, IntegrationException, InvalidElementType, IterativeSolverNotConvergedException  {
		System.out.println("Hello from JBamboo");
		IntervalExample.doIntervalExample();
	}
	
	
	@Override
	public String toString() {
		return "JBamboo prototype";
	}
}
