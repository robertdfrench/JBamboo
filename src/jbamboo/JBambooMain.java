package jbamboo;

import java.util.Set;

import no.uib.cipr.matrix.DenseMatrix;
import jbamboo.basetypes.JBambooNamespace;
import jbamboo.elements.FiniteElement;
import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidIntervalException;
import jbamboo.functions.RealFunction;
import jbamboo.functions.TentFunction;
import jbamboo.innerproducts.InnerProduct;
import jbamboo.innerproducts.StandardRealInnerProduct;
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import jbamboo.mesh.MockSynthesizer;
import jbamboo.mockobjects.MockTesselationPolicy;


public class JBambooMain extends JBambooNamespace {

	/**
	 * @param args
	 * @throws BadTentConfigurationException 
	 * @throws IntegrationException 
	 * @throws InvalidElementException 
	 */
	public static void main(String[] args) throws BadTentConfigurationException, InvalidElementException, IntegrationException {
		// TODO Auto-generated method stub
		System.out.println("Hello from JBamboo");
		
		MockSynthesizer meshSynth = new MockSynthesizer(new MockTesselationPolicy());
		try {
			meshSynth.fauxMeshConstructor(0.0, 1.0, natural(9));
		} catch (InvalidIntervalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mesh mesh = meshSynth.getMesh();
		DenseMatrix stiffnessMatrix = new DenseMatrix(10,10);
		// TODO: this could cause boundary nodes to think they have overlapping
		InnerProduct ip = new StandardRealInnerProduct(natural(100));
		for(MeshNode i : mesh) {
			if (i.isBoundaryNode()) continue;
			for (MeshNode j : mesh) {
				if (j.isBoundaryNode()) continue;
				Set<FiniteElement> sharedNodes = i.sharedElementsWithNode(j);
				if (sharedNodes.size() > 0) {
					RealFunction f = TentFunction.forNode(i, 1.0);
					RealFunction g = TentFunction.forNode(j, 1.0);
					stiffnessMatrix.set(i.getMeshNodeId(), j.getMeshNodeId(), ip.compute(f,g,sharedNodes));
				}
			}
		}
		System.out.println(stiffnessMatrix);
	}
	
	public String toString() {
		return "JBamboo prototype";
	}
}
