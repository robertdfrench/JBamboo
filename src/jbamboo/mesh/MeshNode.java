package jbamboo.mesh;

import java.util.Iterator;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;

public class MeshNode extends JBambooNamespace implements Iterable<FiniteElement> {
	private Point p;
	protected FiniteElement[] elements;
	
	protected MeshNode(Point p) {
		
	}	

	public Point getPoint() {
		return new Point(p);
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Node at %s", p);
	}

	@Override
	public Iterator<FiniteElement> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
