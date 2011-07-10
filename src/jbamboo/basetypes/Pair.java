package jbamboo.basetypes;

/**
 * Holds a pair of the same type of elements, good for various PairIterators.
 * @author robertdfrench
 *
 * @param <E>
 */
public class Pair<E> {

	private E i;
	private E j;
	
	/**
	 * Pass in two elements of the same type
	 * @param i
	 * @param j
	 */
	public Pair(E i, E j) {
		this.i = i;
		this.j = j; 
	}
	
	/**
	 * get the first item in the pair
	 * @return
	 */
	public E i() {
		return i;
	}
	
	/**
	 * get the second item in the pair
	 * @return
	 */
	public E j() {
		return j;
	}
	
	/**
	 * String representation
	 */
	public String toString() {
		return String.format("Pair (%s,%s)",i,j);
	}
}
