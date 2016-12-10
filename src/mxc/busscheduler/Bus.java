package mxc.busscheduler;

/**
 * <p>Represents a bus.</p>
 * 
 * <p>Note that I have included an id although it is not part of the
 * requirements. I have assumed that buses will need to be stored in
 * a database, although the requirements are not clear on this point.</p>
 * 
 * <p>This class could, if necessary, be changed to an interface with a 
 * DefaultBus implementation.</p>
 * 
 * @author Moira
 *
 */
public class Bus {
	
	/** the identifier of the bus, which is immutable and which should be unique */
	private final String id;
	
	public Bus(final String id) {
		this.id = id;
	}

	/**
	 * @return the bus's identifier
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bus [id=" + id + "]";
	}
}
