package jbamboo;

import jbamboo.basetypes.JBambooNamespace;

import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;


public class JBambooMain extends JBambooNamespace {

	/**
	 * @param args
	 * @throws PolicyNotFinishedException 
	 * @throws BadTentConfigurationException 
	 * @throws IntegrationException 
	 * @throws InvalidElementException 
	 * @throws InvalidElementType 
	 */
	public static void main(String[] args) throws InvalidElementException, IntegrationException, InvalidElementType  {
		System.out.println("Hello from JBamboo");
		IntervalExample.doIntervalExample();
		RectangleExample.doRectangleExample();
	}
	
	
	
	public String toString() {
		return "JBamboo prototype";
	}
}
