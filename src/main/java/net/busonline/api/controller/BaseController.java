package net.busonline.api.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	Map<String, Object> resultMap = new HashMap<String,Object>();
	public Map<String, Object> getParamMap(HttpServletRequest request){
		 Map<String, Object>param=convert(request);
		 return param;
	}
	public static Map<String,Object> convert(HttpServletRequest request){  
         Map<String, Object>map=new HashMap<String,Object>();
         Enumeration<String> names = request.getParameterNames();  
         while(names.hasMoreElements()){  
            String key = names.nextElement();  
            String value = request.getParameter(key);  
             if(value == null || value.trim().equals("") || StringUtils.isBlank(value)){  
                continue;  
            }  
             map.put(key, value);  
         }  
         return map;  
    }  
	/**
	 * 验证参数项数量正确且不为空 
	 * @param request
	 * @param str
	 * @return
	 */
	public Map<String,Object> verifyParam(HttpServletRequest request,String[] str){
		 Map<String,Object> map=new HashMap<String,Object>();
		 boolean bl=false;
		 for (int i = 0; i < str.length; i++) {
			String s=request.getParameter(str[i]);
			if(StringUtils.isBlank(s)){
				bl=true;break;
			}else if("-1".equals(s)){
				bl=true;break;
			}
			map.put(str[i], s);
		}
		 if(bl){
			 map.clear();
		 }
	     return map;
	}
}
