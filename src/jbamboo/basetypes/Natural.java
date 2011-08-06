package jbamboo.basetypes;

import java.util.HashMap;

/**
 * Class that forces ints to act like Natural numbers.
 * Use this where affordable in order to alleviate issues
 * Stemming from unexpected negative numbers
 * @author robert
 *
 */
public class Natural extends AbstractBoundedInteger {

	private static HashMap<Integer,Natural> cache = new HashMap<Integer,Natural>();
	
	private Natural(int number) {
		super(new IntegerRange(1,number));
	}
	
	public static Natural get(int number) {
		if (!cache.containsKey(number)) cache.put(number, new Natural(number));
		return cache.get(number);

	}
}
