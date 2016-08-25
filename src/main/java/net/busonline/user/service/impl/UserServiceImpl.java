package net.busonline.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.busonline.core.base.Page;
import net.busonline.core.model.Response;
import net.busonline.core.redis.JedisResultSet;
import net.busonline.core.util.CACHE_KEY;
import net.busonline.core.util.PubMethod;
import net.busonline.user.dao.UserMapper;
import net.busonline.user.service.UserService;
import net.busonline.user.vo.Users;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userDao;
	@Autowired
	private JedisResultSet jedisResultSet;
	@Override
	public Response getuserList(Map<String, Object> map) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>(); 
		List<Users> list = userDao.getuserList(map);
		if(list.size()>0){
			int totalCount = userDao.getUsersCount();
			result.put("total",totalCount);
			Page page= (Page) map.get("page");
			if (totalCount % page.getPageSize() > 0) {
				result.put("totalPage", totalCount/page.getPageSize() + 1);
			} else {
				result.put("totalPage", totalCount/page.getPageSize());
			}
			result.put("totalPage", userDao.getUsersCount());
			result.put("users", list);
			return new Response().success(result);
		}
		return new Response().failure("未找到", "500");
	}

	@Override
	public Response addUser(Users user) throws Exception{
		int i = userDao.addUser(user);
		if(i>0){
			return new Response().success();
		}
		return new Response().failure("插入失败", "500");
	}

	@Override
	public Response delUser(String id) throws Exception{
		int i = userDao.delUser(id);
		if(i>0){
			return new Response().success();
		}
		return new Response().failure("删除失败", "500");
	}

	@Override
	public Response updateUser(Users user) throws Exception{
		int i = userDao.updateUser(user);
		if(i>0){
			return new Response().success();
		}
		return new Response().failure("修改失败", "500");
	}

	@Override
	public Response resetPsw(String id) throws Exception{
		int i = userDao.updataReset(id);
		if(i>0){
			return new Response().success();
		}
		return new Response().failure("重置失败", "500");
		
	}

	@Override
	public Users checkUser(Map<String, Object> map) throws Exception{
		Users user = userDao.checkUser(map);
		if(null != user){
			if(!jedisResultSet.isExists(CACHE_KEY.SESSION+"_"+user.getId())){
				jedisResultSet.set(0, CACHE_KEY.SESSION+"_"+user.getId(), user.getId());
				jedisResultSet.expire(CACHE_KEY.SESSION+"_"+user.getId());
			}
		}else{
			return null;
		}
		return user;
	}

	@Override
	public Response logout(String id) throws Exception{
		if(jedisResultSet.isExists(CACHE_KEY.SESSION+"_"+id)){
			jedisResultSet.del(0, CACHE_KEY.SESSION+"_"+id);
		}
		return new Response().success();
	}
	
	
	
	

}
