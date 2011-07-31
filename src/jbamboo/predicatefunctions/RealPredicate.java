package jbamboo.predicatefunctions;

import jbamboo.basetypes.Point;

/**
 * Common Interface for RealPredicates.
 * @author robertdfrench
 *
 */
public interface RealPredicate {
	public boolean matches(Point p);
}
