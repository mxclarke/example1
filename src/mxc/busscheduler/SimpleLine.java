package mxc.busscheduler;

import java.util.List;

/**
 * <p>A simple Line with a forward Route and return Route.</p>
 *  
 * @author Moira
 */
public class SimpleLine implements Line {
	
	/** 
	 * The number (identifier) of the line, which is immutable and 
	 * which should be unique.
	 */
	private final int number;
	
	/** The forward route */
	private Route forwardRoute;
	
	/** The return route */
	private Route returnRoute;
	
	/** Transient list of routes, so our getter doesn't have to create it each time. 
	 * TODO mark this with @Transient annotation, depending on your persistence
	 * mechanism.
	 */
	private final List<Route> routes;
	
	public SimpleLine(final int number, 
			final Route forwardRoute, final Route returnRoute) {
		this.number = number;
		this.forwardRoute = forwardRoute;
		this.returnRoute = returnRoute;
		
		this.routes = Line.createRoutes(this.forwardRoute, this.returnRoute);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.Line#getNumber()
	 */
	@Override
	public int getNumber() {
		return number;
	}

	/**
	 * @return the routes on this bus line, which in this case will consist
	 * of the forward route and the return route
	 */
	@Override
	public final List<Route> getRoutes() {
		return routes;
	}

	/**
	 * @return the forwardRoute
	 */
	public Route getForwardRoute() {
		return forwardRoute;
	}

	/**
	 * @return the returnRoute
	 */
	public Route getReturnRoute() {
		return returnRoute;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DefaultLine [number=" + number + ", forwardRoute=" + forwardRoute + ", returnRoute=" + returnRoute
				+ "]";
	}
}
