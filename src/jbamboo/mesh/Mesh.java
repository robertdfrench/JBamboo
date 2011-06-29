package jbamboo.mesh;

import java.util.ArrayList;
import java.util.Iterator;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.elements.FiniteElement;



/**
 * This is a passive storage container for MeshNodes and FiniteElements
 * It may be indexed, but it should be agnostic of the Tesselation Policy
 * Or other construction issues.
 * @author robertdfrench
 *
 */
public class Mesh extends JBambooNamespace implements Iterable<MeshNode> {
	protected ArrayList<MeshNode> nodes;
	protected ArrayList<FiniteElement> elements;
	
	protected Mesh() {
		nodes = new ArrayList<MeshNode>();
		elements = new ArrayList<FiniteElement>();
	}
	
	public MeshNode getNodeById(Integer nodeId) {
		return nodes.get(nodeId);
	}
	
	public FiniteElement getElementById(Integer elementId) {
		return elements.get(elementId);
	}
	
	public void addNode() {
		
	}

	@Override
	public Iterator<MeshNode> iterator() {
		// TODO Auto-generated method stub
		return nodes.iterator();
	}
	
	@Override
	public String toString() {
		return String.format("Mesh with %d nodes and %d elements", nodes.size(), elements.size());
	}
}
