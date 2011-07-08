package jbamboo.mesh;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.ElementFactory;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;


/**
 * This is responsible for construction of the mesh
 * @author robertdfrench
 *
 */
public class MeshSynthesizer extends JBambooNamespace {
	private Mesh mesh;
	private ElementFactory factory;
	
	public MeshSynthesizer() {
		this.mesh = new Mesh();
		this.factory = new ElementFactory();
	}
	
	public void createNode(Point p, Integer nodeId) {
		MeshNode node = new MeshNode(p, nodeId);
		mesh.addNode(nodeId, node);
	}
	
	public void createElement(String elementType, Integer ... nodeIds) throws InvalidElementType, InvalidElementException {
		MeshNode[] requestedNodes = new MeshNode[nodeIds.length];
		Point[] associatedPoints = new Point[nodeIds.length];
		for (Integer i : natural(nodeIds.length)) {
			requestedNodes[i - 1] = mesh.getNode(nodeIds[i - 1]);
			associatedPoints[i - 1] = requestedNodes[i - 1].getPoint();
		}
		FiniteElement el = factory.getNewElement(elementType, associatedPoints);
		hangElementOnMesh(el, requestedNodes);
	}
	
	private void hangElementOnMesh(FiniteElement el, MeshNode[] requestedNodes) {
		for (MeshNode node : requestedNodes) {
			node.elements.add(el);
		}
	}
	
	public Mesh getMesh() {
		return mesh;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Mesh Synthesizer for %s", mesh);
	}
}
