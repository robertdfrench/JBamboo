package jbamboo;

import java.util.Set;

import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Vector;
import no.uib.cipr.matrix.sparse.CG;
import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;
import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.elements.FiniteElement;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.BadTentConfigurationException;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;
import jbamboo.functions.Polynomial;
import jbamboo.functions.RealFunction;
import jbamboo.functions.TentFunction2D;
import jbamboo.functions.Zero;
import jbamboo.innerproducts.InnerProduct;
import jbamboo.innerproducts.SobolevInnerProduct;
import jbamboo.innerproducts.StandardRealInnerProduct;
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import jbamboo.mesh.MeshSynthesizer;

/**
 * This is an example of solving an ODE. It should probably be refactored as a unit test.
 * @author robertdfrench
 *
 */
public class IntervalExample extends JBambooNamespace {

	@Override
	public String toString() {
		return "Interval Example";
	}

	/**
	 * Main Heat for Interval Example
	 * @throws InvalidElementType
	 * @throws InvalidElementException
	 * @throws IntegrationException
	 * @throws IterativeSolverNotConvergedException 
	 */
	public static void doIntervalExample() throws InvalidElementType, InvalidElementException, IntegrationException, IterativeSolverNotConvergedException {
		System.out.println("***************Interval Example*****************");
		Integer numNodes = 11;
		Integer numElements = numNodes - 1;
		Double height = 1.0;
		Integer precision = 100;
		RealFunction f = new Polynomial(-6.0,4.0);
		
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
		DenseVector loadVector = new DenseVector(numNodes);
		InnerProduct loadIP = new StandardRealInnerProduct(natural(precision));
		InnerProduct stiffnessIP = new SobolevInnerProduct(natural(precision));
		for (MeshNode n : m) {
			RealFunction phi_n;
			try {
				phi_n = TentFunction2D.forNode(n, height);
			} catch (BadTentConfigurationException e) {
				phi_n = new Zero();
			}
			n.attachBasisFunction(phi_n);
			Double innerProduct = loadIP.compute(f, phi_n, n.getCommonElements(n));
			loadVector.set(n.getNodeId() - 1, innerProduct);
		}
		System.out.println("Load Vector:");
		System.out.println(loadVector);
		
		
		for (Integer i : natural(numNodes)) {
			for (Integer j : natural(numNodes)) {
				MeshNode mi = m.getNode(i);
				MeshNode mj = m.getNode(j);
				RealFunction phi_i = mi.getBasisFunction();
				RealFunction phi_j = mj.getBasisFunction();
				Set<FiniteElement> commonElements = mi.getCommonElements(mj);
				Double innerProduct = stiffnessIP.compute(phi_i,phi_j,commonElements);
				stiffnessMatrix.set(i - 1,j - 1,innerProduct);
			}
		}
		System.out.println("Stiffness Matrix:");
		System.out.println(stiffnessMatrix);
		
		DenseVector initialGuess = new DenseVector(numNodes);
		System.out.println("Initial Guess:");
		System.out.println(initialGuess);
		
		CG solver = new CG(initialGuess);
		Vector solution = solver.solve(stiffnessMatrix, loadVector, initialGuess);
		System.out.println("Potentially Modified Initial Guess:");
		System.out.println(initialGuess);
		
		System.out.println("Solution:");
		for(Integer i : natural(numNodes)) {
			MeshNode n = m.getNode(i);
			Double value = solution.get(i - 1);
			System.out.format("%s,%s\n", n.getPoint().x(),value);
		}
	}
}
