package jbamboo.mesh;

import java.util.Collection;
import java.util.HashSet;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;


public abstract class TesselationPolicy extends JBambooNamespace {

	public abstract boolean validateMeshNode(Point p, Collection<FiniteElement> elements);
	public abstract boolean validateFiniteElement(FiniteElement e);
	public abstract boolean validateMesh(Mesh mesh);
	public abstract MeshNode createMeshNode(Point p, HashSet<FiniteElement> hashSet,
			Integer currentMeshNodeId);
}
