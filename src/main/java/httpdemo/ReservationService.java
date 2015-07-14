package httpdemo;

import java.util.List;

public interface ReservationService {
	public List<Reservation> query(String courtName);
}
