package jbamboo.basetypes;

import java.util.Iterator;

/**
 * Class that forces ints to act like Natural numbers.
 * Use this where affordable in order to alleviate issues
 * Stemming from unexpected negative numbers
 * @author robert
 *
 */
public class Natural implements Iterable<Integer> {

	private int number;
	
	/**
	 * Note, passing 0 as an argument will force Natural to
	 * take on the internal value 1. (0 is not a standard
	 * issue Natural number)
	 * @param number
	 */
	public Natural(int number) {
		this.number = Math.abs(number);
		this.number = (this.number == 0) ? 1 : this.number;
	}
	
	public Natural(double number) {
		this((int) number);
	}
	
	public int toInt() {
		return number;
	}
	
	public Double toDouble() {
		return (double) number;
	}
	
	public String toString() {
		return String.format("%d", number);
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		
		return new NaturalIterator(this);
	}
	
	public class NaturalIterator implements Iterator<Integer> {
		
		private int max;
		private int current;
		
		public NaturalIterator(Natural n) {
			this.max = n.toInt();
			this.current = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current < max);
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			current++;
			return current;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
