package mxc.busscheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Default implementation for data storage. This is a simple in-memory
 * representation.
 * 
 * In real life we would have a database and a persistence mechanism in 
 * front of it.
 * 
 * @author Moira
 */
class DefaultDataRepository implements DataRepository {
	
	/**
	 * The collection of buses, mapped by id.
	 */
	private Map<String, Bus> buses = new HashMap<>();
	
	/**
	 * The collection of bus stops, mapped by code.
	 */
	private Map<String, Stop> stops = new HashMap<>();
	
	/**
	 * The collection of routes, mapped by name.
	 */
	private Map<String, Route> routes = new HashMap<>();
	
	/**
	 * The collection of lines, mapped by number.
	 */
	private Map<Integer, Line> lines = new HashMap<>();
	
	/**
	 * A collection of line configurations by number key, representing
	 * a joining/index table.
	 */
	private Map<Integer, LineConfig> lineConfigs = new HashMap<>();
	
	@Override
	public void addBus(Bus bus) {
		buses.put(bus.getId(), bus);
	}

	@Override
	public void removeBus(String busId) {
		buses.remove(busId);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#findBusById(java.lang.String)
	 */
	@Override
	public Optional<Bus> findBusById(String id) {
		return Optional.ofNullable(buses.get(id));
	}
	
	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#addStop(mxc.busscheduler.Stop)
	 */
	@Override
	public void addStop(Stop stop) {
		stops.put(stop.getCode(), stop);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#findStopByCode(java.lang.String)
	 */
	@Override
	public Optional<Stop> findStopByCode(String code) {
		return Optional.ofNullable(stops.get(code));
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#addRoute(mxc.busscheduler.Route)
	 */
	@Override
	public void addRoute(Route route) {
		routes.put(route.getName(), route);
	}
	
	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#findRouteByName(java.lang.String)
	 */
	@Override
	public Optional<Route> findRouteByName(String name) {
		return Optional.ofNullable(routes.get(name));
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#addLine(mxc.busscheduler.Line)
	 */
	@Override
	public void addLine(Line line) {
		lines.put(line.getNumber(), line);
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#findLineByNumber(int)
	 */
	@Override
	public Optional<Line> findLineByNumber(int number) {
		return Optional.ofNullable(lines.get(number));
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#addRouteConfig(mxc.busscheduler.RouteConfig)
	 */
	@Override
	public LineConfig addLineConfig(LineConfig lineConfig) {
		int key = lineConfig.getLine().getNumber();
		lineConfigs.put(key, lineConfig);
		return lineConfig;
	}

	/* (non-Javadoc)
	 * @see mxc.busscheduler.DataRepository#findLineConfigByNumber(int)
	 */
	@Override
	public Optional<LineConfig> findLineConfigByNumber(int number) {
		return Optional.ofNullable(lineConfigs.get(number));
	}
}
