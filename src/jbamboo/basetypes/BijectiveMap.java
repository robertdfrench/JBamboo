package jbamboo.basetypes;

import java.util.HashMap;

/**
 * It's just like a HashMap<A,B>, except that an inverse map is automatically maintained.
 * @author robert
 *
 * @param <A>
 * @param <B>
 */
public class BijectiveMap<A,B> {
	private HashMap<A,B> a2b;
	private HashMap<B,A> b2a;
	
	/**
	 * Normal constructor, no big deal
	 */
	public BijectiveMap() {
		a2b = new HashMap<A,B>();
		b2a = new HashMap<B,A>();
	}
	
	/**
	 * Ensures that the map's 1:1-ness is not violated
	 * @param a
	 * @param b
	 * @return insertion success
	 */
	public boolean addEntry(A a, B b) {
		if (this.willEntryBeSafe(a,b)) {
			a2b.put(a, b);
			b2a.put(b, a);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if a potential entry will muddle the map
	 * @param a
	 * @param b
	 * @return <b>true</b> if this entry is available to be take, <b>false</b> if either a or b is already involved in the map
	 */
	public boolean willEntryBeSafe(A a, B b) {
		return (a2b.containsKey(a) || b2a.containsKey(b));
	}
	
	/**
	 * Gets a value for the key a
	 * @param a
	 * @return map(a)
	 */
	public B get(A a) {
		return a2b.get(a);
	}
	
	/**
	 * Gets a key for the value b
	 * @param b
	 * @return map^-1(b)
	 */
	public A inverseGet(B b) {
		return b2a.get(b);
	}
}
