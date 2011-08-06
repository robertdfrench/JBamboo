package jbamboo.integration;

import java.util.HashMap;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Pair;

public class IntegrationStrategyMatrix extends JBambooNamespace {

	private static IntegrationStrategyMatrix singleton;
	
	public IntegrationStrategyMatrix get() {
		if (singleton == null) singleton = new IntegrationStrategyMatrix();
		return singleton;
	}
	
	private HashMap<Pair<String>,IntegrationStrategy> dictionary;
	
	private IntegrationStrategyMatrix() {
		dictionary = new HashMap<Pair<String>,IntegrationStrategy>();
	}
	
	public IntegrationStrategy get(String functionType, String elementType) {
		return dictionary.get(new Pair<String>(functionType,elementType));
	}
	
	public void put(String functionType, String elementType, IntegrationStrategy strategy) {
		dictionary.put(new Pair<String>(functionType,elementType),strategy);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Integration Strategy Matrix";
	}

}
