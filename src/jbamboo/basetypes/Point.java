package jbamboo.basetypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Representation of a point in R^n
 * @author robertdfrench
 *
 */
public class Point {

	HashMap<Integer,Double> coordinates;
	boolean stringBuilderIsDirty;
	StringBuilder sb;
	
	/**
	 * constructs the origin
	 */
	public Point() {
		coordinates = new HashMap<Integer,Double>();
		stringBuilderIsDirty = true;
	}
	
	/**
	 * Copy constructor
	 * @param p
	 */
	public Point(Point p) {
		coordinates = new HashMap<Integer,Double>(p.coordinates);
		stringBuilderIsDirty = true;
	}
	
	/**
	 * Constructor from a list of doubles
	 * @param doubles
	 */
	public Point(Double ...doubles) {
		this();
		for (int i = 1; i <= doubles.length; i++) {
			this.setCoordinate(i,doubles[i - 1]);
		}
	}
	
	/**
	 * Adds each coordinate of <b>this</b> and <b>that</b>, returning a new Point.
	 * Again, operator overloading would be nice here. 
	 * @param that
	 * @return (this + that)
	 */
	public Point plus(Point that) {
		Point sum = new Point();
		HashSet<Integer> keys = new HashSet<Integer>();
		keys.addAll(this.coordinates.keySet());
		keys.addAll(that.coordinates.keySet());
		for (Integer key : keys) {
			Double value = this.getCoordinate(key) + that.getCoordinate(key);
			sum.setCoordinate(key, value);
		}
		return sum;
	}

	public void setCoordinate(Integer dimension, Double coordinateValue) {
		coordinates.put(dimension, coordinateValue);
		stringBuilderIsDirty = true;
	}
	
	public Double getCoordinate(Integer dimension) {
		Double coordinateValue = coordinates.get(dimension);
		return (coordinateValue == null) ? 0.0 : coordinateValue;
	}
	
	@Override
	public boolean equals(Object o) {
		Point that = (Point) o;
		TreeSet<Integer> keys = new TreeSet<Integer>(this.coordinates.keySet());
		keys.addAll(that.coordinates.keySet());
		boolean equals = true;
		for (Integer i : keys) {
			Double thisVal = this.getCoordinate(i);
			Double thatVal = that.getCoordinate(i);
			equals &= thisVal.equals(thatVal);
		}
		return equals;
	}
	
	// Convenience Functions
	
	public Double x() {
		return this.getCoordinate(1);
	}
	
	public void x(Double val) {
		this.setCoordinate(1, val);
	}
	
	public Double y() {
		return this.getCoordinate(2);
	}
	
	public void y(Double val) {
		this.setCoordinate(2, val);
	}
	
	public Double z() {
		return this.getCoordinate(3);
	}
	
	public void z(Double val) {
		this.setCoordinate(3, val);
	}
	
	public String toString() {
		if (stringBuilderIsDirty) cleanStringBuilder();
		return sb.toString();
	}
	
	public void cleanStringBuilder() {
		sb = new StringBuilder();
		sb.append("<");
		
		TreeSet<Integer> sortedKeys = new TreeSet<Integer>(coordinates.keySet());
		for(Integer key : sortedKeys) {
			sb.append(String.format("%f,",coordinates.get(key)));
		}
		sb.append(">");
	}
}
