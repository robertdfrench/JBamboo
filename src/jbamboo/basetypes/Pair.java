package jbamboo.basetypes;

public class Pair<E> {

	private E i;
	private E j;
	
	public Pair(E i, E j) {
		this.i = i;
		this.j = j; 
	}
	
	public E i() {
		return i;
	}
	
	public E j() {
		return j;
	}
	
	public String toString() {
		return String.format("Pair (%s,%s)",i,j);
	}
}
