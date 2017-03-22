package simplegaprule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

public class Reservation implements Intervaled {
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
	
	@Override
	public DateTime getStartDate() {
		return startDate;
	}
	
	@Override
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public DateTime getEndDate() {
		return endDate;
	}
	
	@Override
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
}