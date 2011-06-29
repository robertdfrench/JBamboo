package jbamboo.basetypes;


/**
 * This should be a place for idioms, not algorithms.
 * @author robertdfrench
 *
 */
public abstract class JBambooNamespace {
	public abstract String toString(); 

	public static Point point(Double... coordinates) {
		Point p = new Point();
		Natural n = new Natural(coordinates.length);
		for(Integer i : n) {
			p.setCoordinate(i, coordinates[i - 1]);
		}
		return p;
	}
	
	public static Natural natural(int number) {
		return new Natural(number);
	}
	
	public static Natural natural(double number) {
		return new Natural(number);
	}

}
