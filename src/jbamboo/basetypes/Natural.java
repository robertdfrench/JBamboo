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
	
	/**
	 * basically a shortcut for <code>new Natural((int) number)</code>.
	 * @param number
	 */
	public Natural(double number) {
		this((int) number);
	}
	
	/**
	 * Since Java doesn't do operator overloading...
	 * @return the int value
	 */
	public int toInt() {
		return number;
	}
	
	/**
	 * Returns it as a double to help ease division woes
	 * @return
	 */
	public Double toDouble() {
		return (double) number;
	}
	
	/**
	 * For printing
	 */
	public String toString() {
		return String.format("%d", number);
	}

	/**
	 * Idiom to help iterate {1,2,...,<code>toInt()</code>}
	 */
	@Override
	public Iterator<Integer> iterator() {		
		return new NaturalIterator(this);
	}
	
	/**
	 * Idiom to help iterate {1,2,...,<code>toInt()</code>}
	 * @author robertdfrench
	 *
	 */
	public class NaturalIterator implements Iterator<Integer> {
		
		private int max;
		private int current;
		
		/**
		 * Ye olde constructor. Constructs an iterator that runs from 1 to <b>n</b>.
		 * @param n
		 */
		public NaturalIterator(Natural n) {
			this.max = n.toInt();
			this.current = 0;
		}

		/**
		 * Ye olde hasNext(), tells us whether we have numbers remaining
		 */
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (current < max);
		}

		/**
		 * returns the next number in the sequence
		 */
		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			current++;
			return current;
		}

		/**
		 * Not implemented
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
