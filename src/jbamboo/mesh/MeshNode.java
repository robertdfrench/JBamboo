package jbamboo.mesh;

import java.util.HashSet;
import java.util.Set;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;

public class MeshNode extends JBambooNamespace {
	private Point p;
	private Integer meshNodeId;
	private boolean isBoundaryNode;
	protected FiniteElement[] elements;
	
	protected MeshNode(Point p, FiniteElement[] elements, Integer meshNodeId) {
		this.p = p;
		this.elements = elements;
		this.meshNodeId = meshNodeId;
		
		this.isBoundaryNode = false;
		for(FiniteElement e : elements) {
			if (e == null) this.isBoundaryNode = true;
		}
	}	

	public FiniteElement[] getElements() {
		return elements;
	}
	
	public Integer getMeshNodeId() {
		return new Integer(meshNodeId);
	}
	
	public Point getPoint() {
		return new Point(p);
	}
	
	public boolean isBoundaryNode() {
		return isBoundaryNode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String type = (isBoundaryNode) ? "Boundary" : "Inner";
		return String.format("%s node at %s", type, p);
	}
	
	public Set<FiniteElement> sharedElementsWithNode(MeshNode that) {
		HashSet<FiniteElement> tally = new HashSet<FiniteElement>();
		HashSet<FiniteElement> shared = new HashSet<FiniteElement>();
		
		for (FiniteElement e : this.elements) {
			if (e != null) tally.add(e);
		}
		
		for (FiniteElement e : that.elements) {
			if ((e != null) && tally.contains(e)) shared.add(e);
		}
		
		return shared;
	}
}
