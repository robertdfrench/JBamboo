package jbamboo.mockobjects;

import java.util.Collection;
import java.util.HashSet;

import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import jbamboo.mesh.TesselationPolicy;

public class MockTesselationPolicy extends TesselationPolicy {

	@Override
	public boolean validateMeshNode(Point p, Collection<FiniteElement> elements) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateFiniteElement(FiniteElement e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateMesh(Mesh mesh) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MeshNode createMeshNode(Point p, HashSet<FiniteElement> hashSet,
			Integer currentMeshNodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Mock Tesselation Policy";
	}

}
