package jbamboo;

import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;
import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;

import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;
import jbamboo.functions.Polynomial;
import jbamboo.functions.RealFunction;
import jbamboo.innerproducts.*;

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
		Integer numElements;
		Integer precision;
		RealFunction f;
		InnerProduct loadIP;
		InnerProduct stiffnessIP;
		
		
		System.out.println("Solving -u''=-6x+4 on [0,1] with bd=0");
		numElements = 100;
		precision = 10000;
		f = new Polynomial(-6.0,4.0);
		loadIP = new StandardRealInnerProduct(Natural.get(precision));
		stiffnessIP = new SobolevInnerProduct(Natural.get(precision));
		IntervalExample.doIntervalExample(numElements,f,loadIP,stiffnessIP);
		
		
		/**System.out.println("Solving -u'' + u = 0 on [0,1] with bd=0");
		numElements = 100;
		precision = 10000;
		f = new Zero();
		loadIP = new StandardRealInnerProduct(natural(precision));
		stiffnessIP = new SuperSobolevInnerProduct(natural(precision));
		IntervalExample.doIntervalExample(numElements,f,loadIP,stiffnessIP);**/
	}
	
	
	@Override
	public String toString() {
		return "JBamboo prototype";
	}
}
