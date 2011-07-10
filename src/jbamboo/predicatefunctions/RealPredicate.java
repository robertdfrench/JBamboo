package jbamboo.predicatefunctions;

import jbamboo.basetypes.Point;

/**
 * Common Interface for RealPredicates.
 * @author robert
 *
 */
public interface RealPredicate {
	public boolean matches(Point p);
}
