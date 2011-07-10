package jbamboo.mesh;

import java.util.HashSet;
import java.util.Iterator;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.functions.RealFunction;

/**
 * MeshNodes are responsible for knowing which basis function is attached to them, what their nodeIds are
 * and which neighboring elements they have.
 * @author robertdfrench
 *
 */
public class MeshNode extends JBambooNamespace implements Iterable<FiniteElement> {
	private Point p;
	private RealFunction basisFunction;
	private Integer nodeId;
	protected HashSet<FiniteElement> elements;
	
	/**
	 * This should only be called by the Mesh Synthesizer
	 * @param p
	 * @param nodeId
	 */
	protected MeshNode(Point p, Integer nodeId) {
		this.p = p;
		this.nodeId = nodeId;
		elements = new HashSet<FiniteElement>();
	}	

	/**
	 * Returns the position of the mesh node
	 * @return
	 */
	public Point getPoint() {
		return new Point(p);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Node at %s with Elements: %s", p, elements);
	}

	/**
	 * Returns the elements that <code>this</code> node has in common with <code>that</code> node.
	 * @param that
	 * @return
	 */
	public HashSet<FiniteElement> getCommonElements(MeshNode that) {
		HashSet<FiniteElement> copy = new HashSet<FiniteElement>(this.elements);
		copy.retainAll(that.elements);
		return copy;
	}

	/**
	 * Iterates over the adjacent Finite Elements
	 */
	@Override
	public Iterator<FiniteElement> iterator() {
		// TODO Auto-generated method stub
		return elements.iterator();
	}

	/**
	 * @param basisFunction the basisFunction to set
	 */
	public void attachBasisFunction(RealFunction basisFunction) {
		this.basisFunction = basisFunction;
	}

	/**
	 * @return the basisFunction
	 */
	public RealFunction getBasisFunction() {
		return basisFunction;
	}

	/**
	 * @return the nodeId
	 */
	public Integer getNodeId() {
		return nodeId;
	}
}
