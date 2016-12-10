package mxc.busscheduler;

import java.util.Collections;
import java.util.List;

/**
 * <p>A route for a bus. The route is identified by its name, and includes
 * a sequential list of stops.</p>
 * 
 * <p>This class could, if necessary, be changed to an interface with a 
 * DefaultSchedule implementation.</p>
 * 
 * @author Moira
 */
public class Route {
	
	/** 
	 * The name (identifier) of the route, which is immutable and which
	 * should be unique.
	 */
	private final String name;
	
	/** The sequence of stops that make up the route. */
	private List<Stop> stops;
		
	public Route(final String name, final List<Stop> stops) {
		this.name = name;
		this.stops = stops;
	}

	/**
	 * @return the name (identifier) of the Route
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return an immutable list of stops
	 */
	public List<Stop> getStops() {
		List<Stop> immutableStops = Collections.unmodifiableList(stops);
		return immutableStops;
	}
	
	/**
	 * Changes the sequence of stops for the route.
	 * 
	 * @param stops the new sequence of stops
	 */
	public void setStops(final List<Stop> stops) {
		this.stops = stops;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Route [name=" + name + "]";
	}
}
