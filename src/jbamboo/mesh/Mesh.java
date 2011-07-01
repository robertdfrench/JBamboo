package jbamboo.mesh;

import java.util.Iterator;
import java.util.TreeMap;

import jbamboo.basetypes.JBambooNamespace;


/**
 * This is a passive storage container for MeshNodes and FiniteElements
 * It may be indexed, but it should be agnostic of the Tesselation Policy
 * Or other construction issues.
 * @author robertdfrench
 *
 */
public class Mesh extends JBambooNamespace implements Iterable<MeshNode> {
	protected TreeMap<Integer,MeshNode> nodes;
	
	
	protected Mesh() {
		nodes = new TreeMap<Integer,MeshNode>();
	}
	
	public MeshNode getNode(Integer nodeId) {
		return nodes.get(nodeId);
	}
	
	public void addNode(Integer nodeId, MeshNode node) {
		nodes.put(nodeId,node);
	}

	@Override
	public Iterator<MeshNode> iterator() {
		// TODO Auto-generated method stub
		return nodes.values().iterator();
	}
	
	@Override
	public String toString() {
		return String.format("Mesh with %d nodes", nodes.size());
	}
}
