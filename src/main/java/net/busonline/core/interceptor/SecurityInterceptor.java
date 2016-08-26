package net.busonline.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.busonline.core.redis.JedisResultSet;
import net.busonline.core.util.CACHE_KEY;

@Component
public class SecurityInterceptor implements HandlerInterceptor{
	@Autowired
	private JedisResultSet jedisResultSet;
	private List<String> uncheckUrls;  
	private static final String LOGIN_URL = "../../busLineTool/login.html";
	
	
	public JedisResultSet getJedisResultSet() {
		return jedisResultSet;
	}

	public void setJedisResultSet(JedisResultSet jedisResultSet) {
		this.jedisResultSet = jedisResultSet;
	}

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		String requestUrl = req.getRequestURI(); 
		if(requestUrl.matches(".*checkUser.*")){ 
			return true; 
		}else{
			String userid = req.getParameter("userid");
			if(!jedisResultSet.isExists(CACHE_KEY.SESSION+"_"+userid)){
				res.sendRedirect(LOGIN_URL);  
	            //System.out.println("登录失败跳转登录页面");
				return false;
			}
			
		}
		return true;
	}

}
