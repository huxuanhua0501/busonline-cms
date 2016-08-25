package net.busonline.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 线路查看界面的相关sql
 * @author xuanhua.hu
 *
 */
public interface LineViewMapper {
/**
 * 根据城市和线路名字查询线路
 * @param city
 * @param linename
 * @returnH
 */
	public List<Map<String,Object>> getAllLine(@Param("map")Map<String,Object>map);
	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	
	public Long getAllLineCount(@Param("map")Map<String,Object>map);
	/**
	 * 查询站点，通过线路id
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getStopBylineid(@Param("id")String id);
	
	/**
	 * 查询单条线路
	 * @param id
	 * @return
	 */
	public Map<String,Object> getLineById(String id);
	
	/**
	 * 查询站点
	 * @param lineid
	 * @return
	 */
	
	public List<Map<String,Object>> selectstopbylineid(String lineid);
	
	/**
	 * 查询单条线路
	 * @param id
	 * @return
	 */
	public String selectstartstop(String id);
	/**
	 * 查询站点
	 * @param id
	 * @return
	 */
	public String selectendstop(String id);
	/**
	 * 查询单条线路
	 * @param id
	 * @return
	 */
	public Long selectstopcount(String id);
	
	/**
	 * 根据线路id删除线路
	 * @param id
	 * @return
	 */
	public void delLinebyid(String id);
	
	
	/**
	 * 根据线路id删除站点
	 * @param id
	 * @return
	 */
	public void delstopbylineid(String id);
	
	/**
	 * 更改站点
	 * @param map
	 */
	public void updatestopbyid(@Param("map")Map<String,Object>map);
	
	/**
	 * 修改线路
	 * @param map
	 */
	public void updateLinebyid(@Param("map")Map<String,Object>map);
	/**
	 * 删除签名线路
	 * @param lineid
	 */
	public void delsignline(String lineid);
	
 
	 
}
