package simplegaprule;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

public class Reservation {
	private int campsiteId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private DateTime startDate, endDate;
	
	public Reservation() {
		
	}
	
	public int getCampsiteId() {
		return campsiteId;
	}
	
	public void setCampsiteId(int campsiteId) {
		this.campsiteId = campsiteId;
	}
	
	public DateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	public DateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
}