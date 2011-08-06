package jbamboo.basetypes;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

public class IntegerRange implements Iterable<Integer> {

	private final int lowerBound;
	private final int upperBound;
	
	protected IntegerRange(int lowerBound, int upperBound) {
		if (lowerBound > upperBound) throw new IllegalArgumentException();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public int lowerBound() {
		return lowerBound;
	}
	
	public int upperBound() {
		return upperBound;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof IntegerRange)) return false;
		IntegerRange ir = (IntegerRange) o;
		return
		(
			this.lowerBound == ir.lowerBound &&
			this.upperBound == ir.upperBound
		);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + lowerBound;
		result = 31 * result + upperBound;
		return result;
	}
	
	/**
	 * For printing
	 */
	public String toString() {
		return String.format("[%d...%d]", lowerBound, upperBound);
	}

	/**
	 * Idiom to help iterate {1,2,...,<code>toInt()</code>}
	 */
	public Iterator<Integer> iterator() {		
		return asList().iterator();
	}
	
	/**
	 * Create a fake list of integers from 1 to <code>number</code>
	 * @return
	 */
	public List<Integer> asList() {
		return new AbstractList<Integer>() {			
			@Override
			public Integer get(int index) {
				if (index < 0 || index > upperBound) throw new IndexOutOfBoundsException();
				return index + lowerBound;
			}

			@Override
			public int size() {
				return (upperBound - lowerBound) + 1;
			}
			
		};
	}
	
}
