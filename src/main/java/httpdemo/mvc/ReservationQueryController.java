package httpdemo.mvc;

import httpdemo.Reservation;
import httpdemo.ReservationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController extends AbsController {
	private ReservationService reservationService;

	@Autowired
	public ReservationQueryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void setupForm() {
	}

	@RequestMapping(method = RequestMethod.POST)
	//extract a request parameter by name
	public RouteResponse sumbitForm(
			@RequestParam("clientType") String clientType,
			@RequestParam("courtName") String courtName, Model model) {
		List<Reservation> reservations = java.util.Collections.emptyList();
		if (courtName != null) {
			reservations = reservationService.query(courtName);
		}
		//define an object in which to pass data onto the returning view
		model.addAttribute("reservations", reservations);
		//returns a view
		//return "reservationQuery";
		//return StringUtils.EMPTY;
		String result = "reservationQuery";
		model.addAttribute("courtName", courtName);
		model.addAttribute("clientType", clientType);
		return routeResponse(clientType, result, model);
	}
	
	public static void main(String[] args) {
		System.out.println("hello world 333");
	}
}
