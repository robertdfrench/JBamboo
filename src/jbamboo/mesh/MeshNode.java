package jbamboo.mesh;

import java.util.HashSet;
import java.util.Iterator;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;

public class MeshNode extends JBambooNamespace implements Iterable<FiniteElement> {
	private Point p;
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
		return String.format("Node at %s", p);
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
}
