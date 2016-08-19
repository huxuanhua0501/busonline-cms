package net.busonline.core.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpUtil {
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

}
