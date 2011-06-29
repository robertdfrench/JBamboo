package jbamboo.mesh;

import java.util.HashMap;
import java.util.HashSet;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.TesselationPolicyViolation;


/**
 * This is responsible for construction of the mesh
 * @author robert
 *
 */
public class MeshSynthesizer extends JBambooNamespace {
	private TesselationPolicy tp;
	protected Mesh mesh;
	private Integer meshNodeIdSource;
	private HashMap<Point, HashSet<FiniteElement>> elementDictionary;
	
	public MeshSynthesizer(TesselationPolicy tp) {
		this.meshNodeIdSource = new Integer(0);
		this.tp = tp;
		this.mesh = new Mesh();
		this.elementDictionary = new HashMap<Point, HashSet<FiniteElement>>();
	}
	
	public void addFiniteElement(FiniteElement e) throws TesselationPolicyViolation {
		tp.validateFiniteElement(e);
		Point[] points = e.getPoints();
		Integer currentMeshNodeId = currentMeshNodeId();
		boolean successfullyAddedElement = false;
		for (Point p : points) {
			associatePointWithElement(p,e);
			MeshNode m = tp.createMeshNode(p, elementDictionary.get(p), currentMeshNodeId);
			if(m != null) {
				currentMeshNodeId = nextMeshNodeId();
				mesh.elements.add(e);
				mesh.nodes.ensureCapacity(m.getMeshNodeId());
				
			}
		}
		
		
		
		if (!successfullyAddedElement) {
			throw new TesselationPolicyViolation();
		}
	}
	
	public boolean meshIsComplete() {
		return tp.validateMesh(mesh);
	}

	public Mesh getMesh() {
		return mesh;
	}
	
	private void associatePointWithElement(Point p, FiniteElement e) {
		if (!elementDictionary.containsKey(p)) elementDictionary.put(p, new HashSet<FiniteElement>());
		elementDictionary.get(p).add(e);
	}
	
	protected Integer nextMeshNodeId() {
		meshNodeIdSource++;
		return new Integer(meshNodeIdSource);
	}

	private Integer currentMeshNodeId() {
		return new Integer(meshNodeIdSource);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Mesh Synthesizer with Tesselation Policy: %s", tp);
	}
}
