package httpdemo;

import org.springframework.validation.BindException;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.mvc.SimpleFormController;  
  
public class LoginController extends SimpleFormController {  
    /** 
     * ���췽�� 
     */  
    public LoginController() {  
        // TODO Auto-generated constructor stub  
        //setCommandClass(LoginForm.class);   
        //��仰Ҫ�ǲ�д�Ļ�����ô��dd-servlet.xml�е�loginController�������������£�  
        // <property name="commandClass">  
        //<value>zz.it.beans.LoginForm</value>  
        //</property>  
        //����Ч��Ҳ��һ����  
    }  
  
    public ModelAndView onSubmit(Object cmd, BindException errors) {
        LoginForm loginForm = (LoginForm) cmd;
        if (loginForm.getUserName().equals("test")
                && loginForm.getPassword().equals("test")) {
            return new ModelAndView(getSuccessView(), "welcomeuser", loginForm  
                    .getUserName());
        } else {
            errors.reject("ccc", "�û�������������");  
            errors.rejectValue("userName", "nameErr", null, "�û�������");  
            errors.rejectValue("password", "passErr", null, "�������");  
            return new ModelAndView(getFormView(), errors.getModel());  
        }
    }
}
