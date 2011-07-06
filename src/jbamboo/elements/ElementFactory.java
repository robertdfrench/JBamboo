package jbamboo.elements;

import java.util.HashMap;

import jbamboo.basetypes.JBambooNamespace;
import jbamboo.basetypes.Point;
import jbamboo.exceptions.InvalidElementException;
import jbamboo.exceptions.InvalidElementType;

public class ElementFactory extends JBambooNamespace {
	
	private HashMap<String,FiniteElement> dictionary;
	
	public ElementFactory() {
		dictionary = new HashMap<String,FiniteElement>();
		dictionary.put(IntervalElement.class.getName(), new IntervalElement());
	}

	public FiniteElement getNewElement(String type, Point ... points) throws InvalidElementType, InvalidElementException {
		FiniteElement templateElement = dictionary.get(type);
		if (templateElement == null) throw new InvalidElementType();
		return templateElement.semiconstructor(points);
	}
	
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
