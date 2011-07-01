package jbamboo.tesselationpolicy;

import java.util.HashMap;
import java.util.Map.Entry;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidSemiconstructorAuthorization;
import jbamboo.mesh.MeshNode;

public class TesselationPolicy extends JBambooNamespace {

	private HashMap<Integer, FiniteElement> typeDictionary;
	private SemiconstructorAuthorization sa;
	
	protected TesselationPolicy(SemiconstructorAuthorization sa) {
		this.sa = sa;
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
		Point[] points = new Point[requestedNodes.length];
		for(Integer i : natural(requestedNodes.length)) {
			points[i - 1] = requestedNodes[i - 1].getPoint();
		}
		return createElement(points);
	}

	public FiniteElement createElement(Point[] points) {
		FiniteElement template = typeDictionary.get(points.length);
		FiniteElement newElement = null;
		try {
			newElement = template.semiconstructor(sa, points);
		} catch (InvalidElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSemiconstructorAuthorization e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newElement;
	}
}
