package jbamboo.basetypes;

import java.util.Iterator;
import java.util.List;

/**
 * Abstract wrapper class that portrays an IntegerRange as a "Bounded" integer type, that is
 * something like Natural or Index.
 * @author robert
 *
 */
abstract class AbstractBoundedInteger implements Iterable<Integer> {
	
	private IntegerRange ir;
	
	protected AbstractBoundedInteger(IntegerRange ir) {
		this.ir = ir;
	}

	public int toInt() {
		return ir.upperBound();
	}
	
	public double toDouble() {
		return ir.upperBound();
	}
	
	public Iterator<Integer> iterator() {
		return ir.iterator();
	}
	
	public List<Integer> asList() {
		return ir.asList();
	}
	
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof AbstractBoundedInteger)) return false;
		AbstractBoundedInteger bi = (AbstractBoundedInteger) o;
		return (this.toInt() == bi.toInt());
	}
	
	public int hashcode() {
		return 31 * 17 + this.toInt();
	}
	
	public String toString() {
		return String.format("%d",toInt());
	}
}
