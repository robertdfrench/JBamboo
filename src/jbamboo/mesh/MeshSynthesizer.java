package jbamboo.mesh;

import jbamboo.basetypes.BijectiveMap;
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
	private BijectiveMap<Point,Integer> dictionary;
	private TesselationPolicy tp;
	
	public MeshSynthesizer(TesselationPolicy tp) {
		mesh = new Mesh();
		dictionary = new BijectiveMap<Point,Integer>();
		this.tp = tp;
	}
	
	public boolean createNode(Point p, Integer nodeId) {
		if (dictionary.containsEntry(p,nodeId)) return false;
		MeshNode node = new MeshNode(p);
		mesh.addNode(nodeId, node);
		return true;
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
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Mesh Synthesizer for %s", mesh);
	}
}
