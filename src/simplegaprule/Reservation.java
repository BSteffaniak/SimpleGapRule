package simplegaprule;

import java.util.Date;

public class Reservation {
	private int campsiteId;
	private Date startDate, endDate;
	
	public Reservation() {
		
	}
	
	public int getCampsiteId() {
		return campsiteId;
	}
	
	public void setCampsiteId(int campsiteId) {
		this.campsiteId = campsiteId;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}