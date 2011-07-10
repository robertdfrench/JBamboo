package jbamboo.elements;

import java.util.HashMap;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;

/**
 * Used to construct elements from their names without relying on nasty if-else chains or Reflection
 * @author robertdfrench
 *
 */
public class ElementFactory extends JBambooNamespace {
	
	private HashMap<String,FiniteElement> dictionary;
	
	/**
	 * Constructs new Element Factory
	 */
	public ElementFactory() {
		dictionary = new HashMap<String,FiniteElement>();
		dictionary.put(IntervalElement.class.getName(), new IntervalElement());
	}

	/**
	 * Returns a new string of the requested type, provided the suggested boundary points make sense.
	 * @param type
	 * @param points
	 * @return
	 * @throws InvalidElementType
	 * @throws InvalidElementException
	 */
	public FiniteElement getNewElement(String type, Point ... points) throws InvalidElementType, InvalidElementException {
		FiniteElement templateElement = dictionary.get(type);
		if (templateElement == null) throw new InvalidElementType();
		return templateElement.semiconstructor(points);
	}
	
	/**
	 * String representation.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ElementFactory with support for: \n");
		for (String key : dictionary.keySet()) {
			sb.append(String.format("\t¥%s\n", key));
		}
		return sb.toString();
	}

}
