package jbamboo.basetypes;

import java.util.Iterator;
import java.util.List;

/**
 * Iterates over a List, producing pairs of elements in the list. Will not produce "symmetric" duplicates, that
 * is, it will yield a pair <code>(i,j)</code> but it will not subsequently yield a pair <code>(j,i)</code>. Imagine
 * that you have the following set <code>{1,2,3}</code>, then the corresponding set of pairs would be 
 * <code><p>{(1,1),(1,2),(1,3),<br/>(2,1),(2,2),(2,3),<br/>(3,1),(3,2),(3,3)}</p></code>
 * 
 * However, SymmertricPairIterator considers <code>(2,1)</code> to be the same as <code>(1,2)</code>, so it will not
 * be enumerated. Instead, only the following pairs would be enumerated:
 * <code><p>{(1,1),(1,2),(1,3),<br/>(2,2),(2,3),<br/>(3,3)}</p></code>
 * 
 * @author justinlauria
 * @author robertdfrench
 *
 *
 * @param <E>
 */
public class SymmetricPairIterator<E> implements Iterator<Pair<E>>, Iterable<Pair<E>> {
	
	private List<E> list;
	private int outerIndex;
	private int innerIndex;
	
	/**
	 * Takes a list of elements over which to iterate subsequently
	 * @param list
	 */
	public SymmetricPairIterator(List<E> list) {
		this.list = list;
		this.outerIndex = 1;
		this.innerIndex = 1;
	}

	/**
	 * Yo, does it got a next pair dawg?
	 */
	@Override
	public boolean hasNext() {
		return outerIndex < list.size();
	}

	/**
	 * Grab the next pair
	 */
	@Override
	public Pair<E> next() {
		E i = list.get(outerIndex);
		E j = list.get(innerIndex);
		innerIndex++;
		if (innerIndex >= list.size()) {
			outerIndex++;
			innerIndex = outerIndex;
		}
		Pair<E> p = new Pair<E>(i,j);
		return p;
	}

	@Override
	public void remove() {
		
	}

	@Override
	/**
	 * This is a convenience method to make SymmetricPairIterators compatible with java's for-each idiom
	 * @return self
	 */
	public Iterator<Pair<E>> iterator() {
		return this;
	}

}
