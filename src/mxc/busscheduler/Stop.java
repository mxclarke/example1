package mxc.busscheduler;

/**
 * <p>A bus stop. Assumes that a Stop can be serviced by more than one Route.</p>
 * 
 * <p>This class could, if necessary, be changed to an interface with a 
 * DefaultStop implementation.</p>
 * 
 * @author Moira
 */
public class Stop {

	/** 
	 * the code (identifier) of the bus stop, which is immutable and
	 * which should be unique
	 */
	private final String code;
	
	public Stop(final String code) {
		this.code = code;
	}

	/**
	 * @return the code (identifier) of the bus stop
	 */
	public String getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stop [code=" + code + "]";
	}
}
