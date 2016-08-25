package net.busonline.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.busonline.core.base.BaseController;
import net.busonline.core.base.Page;
import net.busonline.core.model.Response;
import net.busonline.core.util.MD5Util;
import net.busonline.core.util.PubMethod;
import net.busonline.user.service.UserService;
import net.busonline.user.vo.Users;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@RequestMapping("/getUsers")
	public @ResponseBody Response getUserList(String currentPage,String pageSize)throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		Page page = new Page();
		if (!PubMethod.isEmpty(currentPage)) {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		if (!PubMethod.isEmpty(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		param.put("page", page);
		
		return userService.getuserList(param);
	}
	@RequestMapping("/addUser")
	public @ResponseBody Response addUser(String username,String email,String nickname)throws Exception{
		Users user = new Users();
		user.setNickname(nickname);
		user.setEmail(email);
		user.setUsername(username);
		return userService.addUser(user);
	}
	@RequestMapping("/delUser")
	public @ResponseBody Response delUser(String id)throws Exception{
		return userService.delUser(id);
	}
	
	@RequestMapping("/upUser")
	public @ResponseBody Response updateUser(String username,String email,String nickname,String id)throws Exception{
		Users user = new Users();
		user.setNickname(nickname);
		user.setEmail(email);
		user.setUsername(username);
		user.setId(id);
		return userService.updateUser(user);
	}
	@RequestMapping("/resetPsw")
	public @ResponseBody Response resetPassword(String id)throws Exception{
		return userService.resetPsw(id);
	}
	
	@RequestMapping("/checkUser")
	public @ResponseBody Response checkUser(String username,String password)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", MD5Util.string2MD5(password));
		Users user = userService.checkUser(map);
		if(null != user){
			return new Response().success(user);
		}
		return new Response().failure("用户名密码不正确","500");
	}
	
	@RequestMapping("/logout")
	public @ResponseBody Response logout(String userid)throws Exception{
		return userService.logout(userid);
	}
	
	
	
	
}
