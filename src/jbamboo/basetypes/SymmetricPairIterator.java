package jbamboo.basetypes;

import java.util.Iterator;
import java.util.List;

public class SymmetricPairIterator<E> implements Iterator<Pair<E>>, Iterable<Pair<E>> {
	
	private List<E> list;
	private int outerIndex;
	private int innerIndex;
	
	public SymmetricPairIterator(List<E> list) {
		this.list = list;
		this.outerIndex = 0;
		this.innerIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return outerIndex < list.size();
	}

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
	public Iterator<Pair<E>> iterator() {
		return this;
	}

}
