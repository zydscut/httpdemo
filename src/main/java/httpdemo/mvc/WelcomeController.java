package httpdemo.mvc;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//defines the class as a Spring MVC controller
@Controller
//specify the URL on which the controller is actionable
@RequestMapping("/welcome")
public class WelcomeController extends AbsController {
	//decorate the method as the controllers default HTTP GET handler method
	@RequestMapping(method = RequestMethod.GET)
	public RouteResponse welcome(
			@RequestParam("clientType") String clientType,
			Model model) {
		Date today = new Date();
		model.addAttribute("today", today);
		//return "welcome";
		String result = "welcome";
		try {
			Thread.sleep(5000L);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return routeResponse(clientType, result, model);
	}
}