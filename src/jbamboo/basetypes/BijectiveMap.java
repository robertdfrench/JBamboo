package jbamboo.basetypes;

import java.util.HashMap;

public class BijectiveMap<A,B> {
	private HashMap<A,B> a2b;
	private HashMap<B,A> b2a;
	
	public BijectiveMap() {
		a2b = new HashMap<A,B>();
		b2a = new HashMap<B,A>();
	}
	
	public boolean addEntry(A a, B b) {
		if (!a2b.keySet().contains(a) && !b2a.keySet().contains(b)) {
			a2b.put(a, b);
			b2a.put(b, a);
			return true;
		}
		return false;
	}
	
	public boolean containsEntry(A a, B b) {
		return (a2b.containsKey(a) || b2a.containsKey(b));
	}
}
