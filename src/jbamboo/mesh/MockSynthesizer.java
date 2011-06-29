package jbamboo.mesh;

import jbamboo.basetypes.Point;

import jbamboo.basetypes.Natural;
import jbamboo.elements.FiniteElement;
import jbamboo.elements.IntervalElement;
import jbamboo.exceptions.InvalidIntervalException;

/**
 * This should be removed. Absolutely and unequivocally removed.
 * @author robertdfrench
 *
 */
public class MockSynthesizer extends MeshSynthesizer {

	public MockSynthesizer(TesselationPolicy tp) {
		super(tp);
		mesh = new Mesh();
	}

	private Point[] generateUniformPoints(Double lowerBound, Double upperBound, Natural numIntervals) {
		Point[] points = new Point[numIntervals.toInt() + 1];
		Double width = 1.0 / numIntervals.toInt();
		
		points[0] = point(lowerBound);
		for(Integer i : numIntervals) {
			points[i] = point(lowerBound + i*width);
		}
		points[numIntervals.toInt()] = point(upperBound);
		return points;
	}
	
	public void fauxMeshConstructor(Double lowerBound, Double upperBound, Natural numIntervals) throws InvalidIntervalException {
		Point[] points = generateUniformPoints(lowerBound, upperBound, numIntervals);
		
		Integer j = new Integer(0);
		FiniteElement e_previous = null;
		mesh.nodes.ensureCapacity(numIntervals.toInt() + 1);
		for (Integer i : numIntervals) {
			j = i - 1;
			FiniteElement e = new IntervalElement(points[j], points[i]);
			FiniteElement[] elements = {e_previous, e};
			MeshNode m = new MeshNode(points[j], elements, nextMeshNodeId());
			mesh.nodes.add(j, m);
			mesh.elements.add(e);
			e_previous = e;
		}
		FiniteElement[] elements = {e_previous, null};
		MeshNode m = new MeshNode(points[points.length - 1], elements, nextMeshNodeId());
		mesh.nodes.add(numIntervals.toInt(), m);
	}
}
