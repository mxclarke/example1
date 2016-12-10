package mxc.busscheduler;

import java.util.List;

/**
 * <p>A default bus line with one or more routes.</p>
 * 
 * <p>This gives us the flexibility of working with lines with any
 * number of routes.</p>
 * 
 * <p>Another implementation of Line could have a forward route, a 
 * return route, and a list of additional routes. A further implementation
 * could have a list of forward-return pairs. Much depends on what the
 * bus company's bus lines end up looking like.</p>
 * 
 * @author Moira
 * 
 * @see SimpleLine
 */
public class DefaultLine implements Line {
	
	/** 
	 * the number (identifier) of the line, which is immutable and 
	 * which should be unique
	 */
	private final int number;
	
	/** The collection of routes. */
	private final List<Route> routes;
	
	public DefaultLine(final int number, Route...routes) {
		if ( routes.length < 1 ) {
			throw new IllegalArgumentException("There must be at least one route");
		}
		
		this.number = number;
		this.routes = Line.createRoutes(routes);
	}

	@Override
	public int getNumber() {
		return number;
	}

	/**
	 * @return the list of routes, of which there should be at least one
	 */
	@Override
	public final List<Route> getRoutes() {
		return routes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DefaultLine [number=" + number + ", routes=" + routes + "]";
	}
}
