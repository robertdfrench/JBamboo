package jbamboo.tesselationpolicy;

import java.util.HashMap;
import java.util.Map.Entry;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.mesh.MeshNode;

public class TesselationPolicy extends JBambooNamespace {

	protected HashMap<Integer, FiniteElement> typeDictionary;
	
	protected TesselationPolicy() {
		typeDictionary = new HashMap<Integer, FiniteElement>();
	}
	
	protected void addRule(Integer numNodes, FiniteElement elementTemplate) {
		typeDictionary.put(numNodes,elementTemplate);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TesselationPolicy with rules:\n");
		for (Entry<Integer, FiniteElement> e : typeDictionary.entrySet()) {
			sb.append(String.format("\t%s for %d points", e.getValue(), e.getKey()));
		}
		return sb.toString();
	}

	public FiniteElement createElement(MeshNode[] requestedNodes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public FiniteElement createElement(Point[] points) {
		return null;
	}
}
