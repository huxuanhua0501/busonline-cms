package net.busonline.allocation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AllocationMapper {
	/*************** 城市 ************************/
	/**
	 * 查询城市
	 * @return
	 */
	public List<Map<String, Object>> selectcity();
	/**
	 * 通过城市id查询城市
	 * @param id
	 * @return
	 */

	public List<Map<String, Object>> selectcitybyid(@Param("cityid")String cityid);
	/**
	 * 更改城市通过城市id
	 * @param map
	 */

	public void modifycitybyid(@Param("map") Map<String, Object> map);
	/**
	 * 插入城市
	 * @param map
	 */

	public void insertcity(@Param("map") Map<String, Object> map);

	/****************** 签名线路 ***************************/
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public List<Map<String, Object>> selectlinebycityid(@Param("cityid")String cityid);
	
	
	/**
	 * 查询所有签名
	 */
	
	public List<Map<String, Object>> selectallsign();
	/**
	 * 查询签名的线路
	 * @param signid
	 * @param lineid
	 * @return
	 */
	
	public Map<String, Object> selectsignlinebyid(@Param("signid")String signid,@Param("lineid")String lineid);
	/**
	 * 删除签名线路
	 * @param signid
	 * @param lineid
	 */
	public void delsignline(@Param("signid")String signid);
	/**
	 * 插入签名线路
	 * @param signid
	 * @param lineid
	 */
	public void insertsignline(@Param("signid")String signid,@Param("lineid")String lineid,@Param("userid")String userid);
	/**
	 * 查询线路id,通过基础库的id
	 * @param lineid
	 * @return
	 */
	public List<Map<String, Object>> selectidbylineid(@Param("lineid")String[] lineid);
	
	public Long selectcitylive(@Param("map") Map<String, Object> map);

	
	
	
}
