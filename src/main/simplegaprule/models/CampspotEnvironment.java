package simplegaprule.models;

public class CampspotEnvironment {
	private Search search;
	
	private GapRule[] gapRules;
	private Campsite[] campsites;
	private Reservation[] reservations;
	
	public CampspotEnvironment() {
		
	}
	
	public Search getSearch() {
		return search;
	}
	
	public void setSearch(Search search) {
		this.search = search;
	}
	
	public GapRule[] getGapRules() {
		return gapRules;
	}
	
	public void setGapRules(GapRule[] gapRules) {
		this.gapRules = gapRules;
	}
	
	public Campsite[] getCampsites() {
		return campsites;
	}
	
	public void setCampsites(Campsite[] campsites) {
		this.campsites = campsites;
	}
	
	public Reservation[] getReservations() {
		return reservations;
	}
	
	public void setReservations(Reservation[] reservations) {
		this.reservations = reservations;
	}
}