package httpdemo.mvc;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * super class for controller, to control the response data format.<br/>
 * to support c/s and b/s request gracefully with controller written once.<br/>
 * the specific logic controller will not care about mobile request or browser request<br/>
 * @author thomas.zheng
 *
 */
public abstract class AbsController {
	
	/**
	 * response to client, with json data or html page.<br/>
	 * according to the clientType.<br/>
	 * 
	 * @param clientType
	 * @param result
	 * @param body
	 * @return
	 */
	public RouteResponse routeResponse(String clientType, String result, Model model) {
		if(StringUtils.equals(RouteResponse.MOBILE, clientType)) {
			RRResponseEntity entity = new RRResponseEntity(model.asMap());
			//entity.getHeaders().setContentType(MediaType.TEXT_PLAIN);
			return entity;
		}
		else if(StringUtils.equals(RouteResponse.BROWSER, clientType)) {
			return new RRModelAndView(result, model);
		}
		return new RRModelAndView(result, model);
	}
	
	/**
	 * self defined response interface.<br/>
	 * to support multi clients gracefully.<br/>
	 * controller should return this interface.
	 * @author thomas.zheng
	 *
	 */
	public interface RouteResponse {
		String MOBILE = "mobile";
		String BROWSER = "browser";
	}
	
	/**
	 * response for browser request.<br/>
	 * html page is supposed to be responsed to client.<br/>
	 * @author thomas.zheng
	 *
	 */
	public class RRModelAndView extends ModelAndView implements RouteResponse {
		public RRModelAndView(String viewName, Model model) {
			super(viewName, model.asMap());
		}
	}
	
	/**
	 * response for ajax/websocket request.<br/>
	 * json data is supposed to be responsed to client.<br/>
	 * @author thomas.zheng
	 *
	 */
	public class RRResponseEntity extends ResponseEntity<Map<String, Object>> implements RouteResponse {
		public RRResponseEntity() {
			this(Collections.<String, Object>emptyMap());
		}
		
		public RRResponseEntity(Map<String, Object> body) {
			super(body, HttpStatus.OK);
		}
	}
}
