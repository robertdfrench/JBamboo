package jbamboo.mesh;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.tesselationpolicy.TesselationPolicy;


/**
 * This is responsible for construction of the mesh
 * @author robertdfrench
 *
 */
public class MeshSynthesizer extends JBambooNamespace {
	private Mesh mesh;
	private TesselationPolicy tp;
	
	public MeshSynthesizer(TesselationPolicy tp) {
		mesh = new Mesh();
		this.tp = tp;
	}
	
	public void createNode(Point p, Integer nodeId) {
		MeshNode node = new MeshNode(p);
		mesh.addNode(nodeId, node);
	}
	
	public void createElement(Integer ... nodeIds) {
		MeshNode[] requestedNodes = new MeshNode[nodeIds.length];
		for (Integer i : natural(nodeIds.length)) {
			requestedNodes[i - 1] = mesh.getNode(nodeIds[i - 1]);
		}
		FiniteElement el = tp.createElement(requestedNodes);
		hangElementOnMesh(el, requestedNodes);
	}
	
	private void hangElementOnMesh(FiniteElement el, MeshNode[] requestedNodes) {
		for (MeshNode node : requestedNodes) {
			node.elements.add(el);
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Mesh Synthesizer for %s", mesh);
	}
}
