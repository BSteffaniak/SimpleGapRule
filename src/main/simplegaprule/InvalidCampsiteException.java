package simplegaprule;

public class InvalidCampsiteException extends RuntimeException {
	public InvalidCampsiteException() {
		this("Campsite does not exist in environment");
	}
	
	public InvalidCampsiteException(String message) {
		super(message);
	}
}