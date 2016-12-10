package mxc.busscheduler;

import java.util.Optional;

/**
 * <p>A system which allows the user to:
 * <ul>
 * <li>add buses</li>
 * <li>remove buses</li>
 * <li>define stops</li>
 * <li>define routes</li>
 * <li>define lines</li>
 * <li>assign lines and time schedules to buses</li>
 * </ul>
 * </p>
 * 
 * @author Moira
 */
public interface BusScheduler {
	
	/**
	 * Adds a bus to the system. If a bus with that id already exists
	 * in the system, it will be replaced with this one.
	 * 
	 * @param bus the new bus, which must not be null
	 */
	void addBus(Bus bus);
	
	/**
	 * Removes the bus, if it exists within the sytem.
	 * 
	 * @param bus the bus to be removed, which must not be null
	 */
	void removeBus(Bus bus);
		
	/**
	 * Creates a bus stop using the given code, and adds it to the
	 * system.
	 * 
	 * @param code the identifier for the bus stop
	 * 
	 * @return the newly created bus stop
	 */
	Stop createStop(String code);
	
	/**
	 * Creates a route using the given name, and adds it to the
	 * system.
	 * 
	 * @param code the name of the new route
	 * 
	 * @return the newly created route
	 */
	Route createRoute(String name, Stop... stops);
	
	/**
	 * Creates, stores and returns a default line, which consists of 
	 * one or more routes.
	 * 
	 * If a line of that number already exists, it will be replaced.
	 * 
	 * @param number the line number
	 * @param routes the list of routes, of which there must be at least one
	 * 
	 * @return the new line
	 */
	Line createLine(int number, Route... routes);
	
	/**
	 * Creates, stores and returns a simple line, which consists of a
	 * forward route and a return route.
	 * 
	 * If a line of that number already exists, it will be replaced.
	 * 
	 * @param number the line number
	 * @param forwardRoute the forward route
	 * @param returnRoute the return route
	 * 
	 * @return the new line
	 */
	Line createSimpleLine(int number, Route forwardRoute, Route returnRoute);
	
	/**
	 * Assigns a line and a time schedule to a bus.
	 * 
	 * @param bus
	 * @param line
	 * @param schedule
	 */
	void assignBus(Bus bus, Line line, Schedule schedule);
	
	// Some query methods . . .
	
	Optional<Bus> findBusById(String id);
	
	Optional<Stop> findStopByCode(String code);
	
	Optional<Route> findRouteByName(String name);
	
	Optional<Line> findLineByNumber(int number);
	
	Optional<Bus> findBusByLineAndSchedule(int number, Schedule schedule);
}
