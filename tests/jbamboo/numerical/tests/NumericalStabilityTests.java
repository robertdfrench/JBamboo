package jbamboo.numerical.tests;


import java.util.Set;

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
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumericalStabilityTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStiffnessMatrix() throws InvalidElementException, IntegrationException, InvalidElementType {
		Integer numNodes = 51;
		Integer numElements = numNodes - 1;
		Double height = 1.0;
		Integer precision = 100;
		RealFunction f = new Polynomial(-6.0,4.0);
		
		MeshSynthesizer ms = new MeshSynthesizer();
		Double h = 1.0 / (double) numElements;
		for(Integer i : JBambooNamespace.natural(numNodes)) {
			Point p = JBambooNamespace.point((i - 1)*h);
			ms.createNode(p, i);
		}
		for(Integer i : JBambooNamespace.natural(numElements)) {
			ms.createElement(IntervalElement.class.getName(),i,i + 1);
		}
		Mesh m = ms.getMesh();
		DenseMatrix stiffnessMatrix = new DenseMatrix(numNodes,numNodes);
		DenseVector loadVector = new DenseVector(numNodes);
		InnerProduct loadIP = new StandardRealInnerProduct(JBambooNamespace.natural(precision));
		InnerProduct stiffnessIP = new SobolevInnerProduct(JBambooNamespace.natural(precision));
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
		
		
		for (Integer i : JBambooNamespace.natural(numNodes)) {
			for (Integer j : JBambooNamespace.natural(numNodes)) {
				MeshNode mi = m.getNode(i);
				MeshNode mj = m.getNode(j);
				RealFunction phi_i = mi.getBasisFunction();
				RealFunction phi_j = mj.getBasisFunction();
				Set<FiniteElement> commonElements = mi.getCommonElements(mj);
				Double innerProduct = stiffnessIP.compute(phi_i,phi_j,commonElements);
				stiffnessMatrix.set(i - 1,j - 1,innerProduct);
			}
		}
		
		
	}
}
