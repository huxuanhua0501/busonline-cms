package net.busonline.user.dao;

import java.util.List;
import java.util.Map;

import net.busonline.user.vo.Users;

public interface UserMapper {
	public List<Users> getuserList(Map<String,Object> map);
	public int getUsersCount();
	public int addUser(Users user);
	public int delUser(String id);
	public int updateUser(Users user);
	public int updataReset(String id);
	public Users checkUser(Map<String,Object> map);

}
