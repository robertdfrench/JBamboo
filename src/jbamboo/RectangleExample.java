package jbamboo;

import java.util.Set;

import no.uib.cipr.matrix.DenseMatrix;
import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.elements.RectangleElement;
import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;
import jbamboo.functions.RealFunction;
import jbamboo.functions.TentFunction3D;
import jbamboo.functions.Zero;
import jbamboo.innerproducts.InnerProduct;
import jbamboo.innerproducts.StandardRealInnerProduct;
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import jbamboo.mesh.MeshSynthesizer;

public class RectangleExample extends JBambooNamespace {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void doRectangleExample() throws InvalidElementType, InvalidElementException, IntegrationException {
		System.out.println("***************Rectangle Example*****************");
		Integer numEdgeNodes = 5;
		Integer numNodes = numEdgeNodes * numEdgeNodes;
		Integer numEdgeElements = numEdgeNodes - 1;
		Double height = 1.0;
		Integer precision = 100;
		
		MeshSynthesizer ms = new MeshSynthesizer();
		Double h = 1.0 / (double) numEdgeElements;
		for(int i = 0; i < numEdgeNodes; i++) {
			for (int j = 0; j < numEdgeNodes; j++) {
				Point p = point(i * h, j * h);
				ms.createNode(p, i*numEdgeNodes + j);
			}
		}
		
		for(int i = 0; i < numEdgeElements; i++) {
			for(int j = 0; j < numEdgeElements; j++) {
				int bottomLeft  = i*numEdgeNodes + j;
				int bottomRight = bottomLeft + 1;
				int topLeft     = bottomLeft + numEdgeNodes;
				int topRight    = bottomLeft + numEdgeNodes + 1;
				ms.createElement(RectangleElement.class.getName(), bottomLeft, bottomRight, topLeft, topRight);
			}
		}
		
		Mesh m = ms.getMesh();
		DenseMatrix stiffnessMatrix = new DenseMatrix(numNodes, numNodes);
		for (MeshNode n : m) {
			RealFunction f;
			try {
				f = TentFunction3D.forNode(n, height);
			} catch (BadTentConfigurationException e) {
				f = new Zero();
			}
			n.attachBasisFunction(f);
		}
		
		InnerProduct ip = new StandardRealInnerProduct(natural(precision));
		for (Integer i :  natural(numNodes)) {
			for (Integer j : natural(numNodes)) {
				MeshNode mi = m.getNode(i);
				MeshNode mj = m.getNode(j);
				RealFunction fi = mi.getBasisFunction();
				RealFunction fj = mj.getBasisFunction();
				Set<FiniteElement> commonElements = mi.getCommonElements(mj);
				Double innerProduct = ip.compute(fi,fj,commonElements);
				stiffnessMatrix.set(i - 1,j - 1,innerProduct);
			}
		}
		System.out.println(stiffnessMatrix);
	}
}
