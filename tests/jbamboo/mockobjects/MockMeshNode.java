package jbamboo.mockobjects;

import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.mesh.MeshNode;

public class MockMeshNode extends MeshNode {

	public MockMeshNode(Point p, FiniteElement[] elements, Integer meshNodeId) {
		super(p, elements, meshNodeId);
		// TODO Auto-generated constructor stub
	}

	public static MeshNode make() throws InvalidIntervalException {
		Point L = new Point(-1.0);
		Point C = new Point(0.0);
		Point R = new Point(1.0);
		IntervalElement LC = new IntervalElement(L,C);
		IntervalElement CR = new IntervalElement(C,R);
		FiniteElement[] elements = {LC, CR};
		return new MockMeshNode(C, elements, 1);
		// TODO Auto-generated constructor stub
	}

}
