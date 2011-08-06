package jbamboo.mesh;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.basetypes.Point;
import jbamboo.elements.ElementFactory;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;


/**
 * This is responsible for construction of the mesh. It uses two calls <code>createNode</code> and
 * <code>createElement</code> to fill the Mesh with nodes and elements. It provides facilities to ensure
 * that element creation is possible, but it does not check for Mesh Consistency. That is, if you overlap
 * two elements, or leave gaps in your domain, it is your problem.
 * @author robertdfrench
 *
 */
public class MeshSynthesizer extends JBambooNamespace {
	private Mesh mesh;
	private ElementFactory factory;
	
	/**
	 * Create Synthesizer with empty mesh
	 */
	public MeshSynthesizer() {
		this.mesh = new Mesh();
		this.factory = new ElementFactory();
	}
	
	/**
	 * Add a node to the mesh
	 * @param p
	 * @param nodeId
	 */
	public void createNode(Point p, Integer nodeId) {
		MeshNode node = new MeshNode(p, nodeId);
		mesh.addNode(nodeId, node);
	}
	
	/**
	 * Hang an element on the Mesh. <code>elementType</code> must be the package name of a registered element type. Normally
	 * this is called like <code>synthesizer.createElement(IntervalElement.class.getName(), nodeIds)</code>.
	 * @param elementType
	 * @param nodeIds
	 * @throws InvalidElementType
	 * @throws InvalidElementException
	 */
	public void createElement(String elementType, Integer ... nodeIds) throws InvalidElementType, InvalidElementException {
		MeshNode[] requestedNodes = new MeshNode[nodeIds.length];
		Point[] associatedPoints = new Point[nodeIds.length];
		for (Integer i : Natural.get(nodeIds.length)) {
			requestedNodes[i - 1] = mesh.getNode(nodeIds[i - 1]);
			associatedPoints[i - 1] = requestedNodes[i - 1].getPoint();
		}
		FiniteElement el = factory.getNewElement(elementType, associatedPoints);
		hangElementOnMesh(el, requestedNodes);
	}
	
	/**
	 * Informs all of the nodes involved that they are to be responsible for the new element
	 * @param el
	 * @param requestedNodes
	 */
	private void hangElementOnMesh(FiniteElement el, MeshNode[] requestedNodes) {
		for (MeshNode node : requestedNodes) {
			node.elements.add(el);
		}
	}
	
	/**
	 * Returns the mesh in its current state.
	 * @return
	 */
	public Mesh getMesh() {
		return mesh;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Mesh Synthesizer for %s", mesh);
	}
}
