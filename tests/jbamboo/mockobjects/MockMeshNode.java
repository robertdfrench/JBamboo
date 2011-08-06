package jbamboo.mockobjects;

import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.mesh.MeshNode;

public class MockMeshNode extends MeshNode {

	public MockMeshNode(Point p, Integer meshNodeId) {
		super(p, meshNodeId);
		// TODO Auto-generated constructor stub
	}

	public static MeshNode make() throws InvalidIntervalException {
		Point C = new Point(0.0);
		return new MockMeshNode(C,  1);
		// TODO Auto-generated constructor stub
	}

}
