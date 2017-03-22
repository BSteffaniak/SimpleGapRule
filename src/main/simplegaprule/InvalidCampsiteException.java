package simplegaprule;

/**
 * Exception that is thrown when a Campsite instance is used outside of
 * its environment.
 */
public class InvalidCampsiteException extends RuntimeException {
	public InvalidCampsiteException() {
		this("Campsite does not exist in environment");
	}
	
	public InvalidCampsiteException(String message) {
		super(message);
	}
}