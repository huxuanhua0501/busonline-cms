package net.busonline.user.service;

import java.util.Map;

import net.busonline.core.model.Response;
import net.busonline.user.vo.Users;

public interface UserService {
	public Response getuserList(Map<String,Object> map)throws Exception;
	public Response addUser(Users user)throws Exception;
	public Response delUser(String id)throws Exception;
	public Response updateUser(Users user)throws Exception;
	public Response resetPsw(String id)throws Exception;
	public Users checkUser(Map<String,Object> map)throws Exception;
	public Response logout(String id)throws Exception;

}
