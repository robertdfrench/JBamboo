package jbamboo.mesh;

import java.util.HashSet;
import java.util.Iterator;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.functions.RealFunction;

public class MeshNode extends JBambooNamespace implements Iterable<FiniteElement> {
	private Point p;
	private RealFunction basisFunction;
	protected HashSet<FiniteElement> elements;
	
	protected MeshNode(Point p) {
		this.p = p;
		elements = new HashSet<FiniteElement>();
	}	

	public Point getPoint() {
		return new Point(p);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Node at %s with Elements: %s", p, elements);
	}

	public HashSet<FiniteElement> getCommonElements(MeshNode that) {
		HashSet<FiniteElement> copy = new HashSet<FiniteElement>(this.elements);
		copy.retainAll(that.elements);
		return copy;
	}

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
}
