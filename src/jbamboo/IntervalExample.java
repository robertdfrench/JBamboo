package jbamboo;

import java.util.Set;

import no.uib.cipr.matrix.DenseMatrix;
import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;
import jbamboo.functions.RealFunction;
import jbamboo.functions.TentFunction2D;
import jbamboo.functions.Zero;
import jbamboo.innerproducts.InnerProduct;
import jbamboo.innerproducts.StandardRealInnerProduct;
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import jbamboo.mesh.MeshSynthesizer;

public class IntervalExample extends JBambooNamespace {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void doIntervalExample() throws InvalidElementType, InvalidElementException, IntegrationException {
		System.out.println("***************Interval Example*****************");
		Integer numNodes = 11;
		Integer numElements = numNodes - 1;
		Double height = 1.0;
		Integer precision = 100;
		
		MeshSynthesizer ms = new MeshSynthesizer();
		Double h = 1.0 / (double) numElements;
		for(Integer i : natural(numNodes)) {
			Point p = point((i - 1)*h);
			ms.createNode(p, i);
		}
		for(Integer i : natural(numElements)) {
			ms.createElement(IntervalElement.class.getName(),i,i + 1);
		}
		Mesh m = ms.getMesh();
		DenseMatrix stiffnessMatrix = new DenseMatrix(numNodes,numNodes);
		for (MeshNode n : m) {
			RealFunction f;
			try {
				f = TentFunction2D.forNode(n, height);
			} catch (BadTentConfigurationException e) {
				f = new Zero();
			}
			n.attachBasisFunction(f);
		}
		
		InnerProduct ip = new StandardRealInnerProduct(natural(precision));
		for (Integer i : natural(numNodes)) {
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
