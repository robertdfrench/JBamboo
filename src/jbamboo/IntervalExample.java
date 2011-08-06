package jbamboo;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Natural;
import jbamboo.exceptions.IntegrationException;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;
import jbamboo.functions.RealFunction;
import jbamboo.innerproducts.InnerProduct;
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Vector;
import no.uib.cipr.matrix.sparse.CG;
import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;

/**
 * This is an example of solving an ODE. It should probably be refactored as a unit test.
 * @author robertdfrench
 *
 */
public class IntervalExample extends JBambooNamespace {

	@Override
	public String toString() {
		throw new IllegalArgumentException();
		//return "Interval Example";
	}

	/**
	 * Main Heat for Interval Example
	 * @throws InvalidElementType
	 * @throws InvalidElementException
	 * @throws IntegrationException
	 * @throws IterativeSolverNotConvergedException 
	 */
	public static void doIntervalExample(Integer numElements, RealFunction f, InnerProduct loadIP, InnerProduct stiffnessIP) throws InvalidElementType, InvalidElementException, IntegrationException, IterativeSolverNotConvergedException {
		System.out.println("***************Interval Example*****************");
		long[] timestamps = new long[6];
		long[] results = new long[5];
		timestamps[0] = System.currentTimeMillis();
		
		Integer numNodes = numElements + 1;
		Mesh m = uniformIntervalMesh(0.0,1.0,Natural.get(numElements));
		timestamps[1] = System.currentTimeMillis();
		
		applyDefaultBasisScheme(m);
		timestamps[2] = System.currentTimeMillis();
		
		DenseVector loadVector = computeLoadVector(m,loadIP,f);
		timestamps[3] = System.currentTimeMillis();
		
		DenseMatrix stiffnessMatrix = computeStiffnessMatrix(m, stiffnessIP);
		timestamps[4] = System.currentTimeMillis();
		
		DenseVector initialGuess = new DenseVector(numNodes);	
		CG solver = new CG(initialGuess);
		Vector solution = solver.solve(stiffnessMatrix, loadVector, initialGuess);
		timestamps[5] = System.currentTimeMillis();
		
		for(int i = 1; i < 6; i++) {
			results[i - 1] = timestamps[i] - timestamps[i - 1];
		}
		
		/**
		 * Print results
		 */
		System.out.format("Mesh Construction:            %d(ms)\n", results[0]);
		System.out.format("Mesh Decoration:              %d(ms)\n", results[1]);
		System.out.format("Load Vector Calculation:      %d(ms)\n", results[2]);
		System.out.format("Stiffness Matrix Calculation: %d(ms)\n", results[3]);
		System.out.format("Conjugate Gradient Iteration: %d(ms)\n", results[4]);
		
		System.out.println("Load Vector");
		System.out.println(loadVector);
		
		System.out.println("Stiffness Matrix:");
		System.out.println(stiffnessMatrix);
		
		System.out.println("Solution:");
		for(Integer i : Natural.get(numNodes)) {
			MeshNode n = m.getNode(i);
			Double value = solution.get(i - 1);
			System.out.format("%s,%s\n", n.getPoint().x(),value);
		}
	}
}
