package httpdemo;

import org.springframework.validation.BindException;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.mvc.SimpleFormController;  
  
public class LoginController extends SimpleFormController {  
    /** 
     * 构造方法 
     */  
    public LoginController() {  
        // TODO Auto-generated constructor stub  
        //setCommandClass(LoginForm.class);   
        //这句话要是不写的话，那么在dd-servlet.xml中的loginController里面配置上如下：  
        // <property name="commandClass">  
        //<value>zz.it.beans.LoginForm</value>  
        //</property>  
        //这样效果也是一样的  
    }  
  
    public ModelAndView onSubmit(Object cmd, BindException errors) {
        LoginForm loginForm = (LoginForm) cmd;
        if (loginForm.getUserName().equals("test")
                && loginForm.getPassword().equals("test")) {
            return new ModelAndView(getSuccessView(), "welcomeuser", loginForm  
                    .getUserName());
        } else {
            errors.reject("ccc", "用户名或密码有误！");  
            errors.rejectValue("userName", "nameErr", null, "用户名错误");  
            errors.rejectValue("password", "passErr", null, "密码错误");  
            return new ModelAndView(getFormView(), errors.getModel());  
        }
    }
}
