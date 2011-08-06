package jbamboo.basetypes;

import java.util.HashMap;


public class Index extends AbstractBoundedInteger {

	private static HashMap<Integer,Index> cache = new HashMap<Integer,Index>();

	private Index(int number) {
		super(new IntegerRange(0,number));
	}
	
	public static Index get(int number) {
		if (!cache.containsKey(number)) cache.put(number, new Index(number));
		return cache.get(number);
	}
}
