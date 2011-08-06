package jbamboo.basetypes;

import java.util.Set;

import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
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
import jbamboo.mesh.Mesh;
import jbamboo.mesh.MeshNode;
import jbamboo.mesh.MeshSynthesizer;


/**
 * This should be a place for idioms, not algorithms.
 * @author robertdfrench
 *
 */
public abstract class JBambooNamespace {
	/**
	 * Short description of the object's state
	 */
	public abstract String toString(); 

	/**
	 * Idiom for creating a new point from arbitrarily many doubles
	 * @param coordinates
	 * @return
	 */
	public static Point point(Double... coordinates) {
		Point p = new Point();
		Index n = Index.get(coordinates.length - 1);
		for(Integer i : n) {
			p.setCoordinate(i + 1, coordinates[i]);
		}
		return p;
	}

	/**
	 * Gets the 2nd derivative
	 * @param f
	 * @return
	 */
	public static RealFunction laplacian(RealFunction f) {
		return f.getDerivative().getDerivative();
	}
	
	/**
	 * generates a uniform interval mesh
	 */
	public static Mesh uniformIntervalMesh(Double lowerBound, Double upperBound, Natural numIntervals) {
		MeshSynthesizer ms = new MeshSynthesizer();
		int numNodes = numIntervals.toInt() + 1;
		Double totalIntervalLength = upperBound - lowerBound;
		Double subIntervalLength = totalIntervalLength / numIntervals.toDouble();
		for(Integer i : Natural.get(numNodes)) {
			Point p = point(lowerBound + (i - 1)*subIntervalLength);
			ms.createNode(p, i);
		}
		for(Integer i : numIntervals) {
			try {
				ms.createElement(IntervalElement.class.getName(),i,i + 1);
			} catch (InvalidElementType e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ms.getMesh();
	}
	
	/**
	 * Attaches TentFunctions of height 1 to all internal nodes and the Zero function to all boundary nodes.
	 * @param m
	 */
	public static void applyDefaultBasisScheme(Mesh m) {
		for (MeshNode n : m) {
			RealFunction phi_n;
			try {
				phi_n = TentFunction2D.forNode(n, 1.0);
			} catch (BadTentConfigurationException e) {
				phi_n = new Zero();
			}
			n.attachBasisFunction(phi_n);
		}
	}
	
	/**
	 * Computes the load vector
	 * @param m
	 * @param loadIP
	 * @param f
	 * @return
	 */
	public static DenseVector computeLoadVector(Mesh m, InnerProduct loadIP, RealFunction f) {
		DenseVector loadVector = new DenseVector(m.getNumNodes().toInt());
		for (MeshNode n : m) {
			RealFunction phi_n = n.getBasisFunction();
			Double innerProduct = 0.0;
			try {
				innerProduct = loadIP.computeNumerically(f, phi_n, n.getCommonElements(n));
			} catch (InvalidElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IntegrationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadVector.set(n.getNodeId() - 1, innerProduct);
		}
		return loadVector;
	}
	
	/**

	 * Computes the stiffness matrix
	 * @param m
	 * @param stiffnessIP
	 * @return
	 */
	public static DenseMatrix computeStiffnessMatrix(Mesh m, InnerProduct stiffnessIP) {
		int numNodes = m.getNumNodes().toInt();
		DenseMatrix stiffnessMatrix = new DenseMatrix(numNodes,numNodes);

		for (Pair<MeshNode> p : m.symmetricPairIterator()) {
			MeshNode mi = p.i();
			MeshNode mj = p.j();
			RealFunction phi_i = mi.getBasisFunction();
			RealFunction phi_j = mj.getBasisFunction();
			Set<FiniteElement> commonElements = mi.getCommonElements(mj);
			Double innerProduct = 0.0;
			try {
				innerProduct = stiffnessIP.computeGeometrically(phi_i,phi_j,commonElements);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stiffnessMatrix.set(mi.getNodeId() - 1,mj.getNodeId() - 1,innerProduct);
			stiffnessMatrix.set(mj.getNodeId() - 1,mi.getNodeId() - 1,innerProduct);
		}
		
		/*for (Integer i : natural(numNodes)) {
			for (Integer j : natural(numNodes)) {
				MeshNode mi = m.getNode(i);
				MeshNode mj = m.getNode(j);
				RealFunction phi_i = mi.getBasisFunction();
				RealFunction phi_j = mj.getBasisFunction();
				Set<FiniteElement> commonElements = mi.getCommonElements(mj);
				Double innerProduct = 0.0;
				try {
					innerProduct = stiffnessIP.compute(phi_i,phi_j,commonElements);
				} catch (InvalidElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IntegrationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stiffnessMatrix.set(i - 1,j - 1,innerProduct);
			}
		}*/
		
		return stiffnessMatrix;
	}
	
}
