package simplegaprule.models;

/**
 * Model representing a number of days that cannot exist as a gap
 * between two reservations.
 */
public class GapRule {
	private int gapSize;
	
	public GapRule() {
		
	}
	
	public int getGapSize() {
		return gapSize;
	}
	
	public void setGapSize(int gapSize) {
		this.gapSize = gapSize;
	}
}