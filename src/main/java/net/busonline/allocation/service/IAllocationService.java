package net.busonline.allocation.service;

 

public interface IAllocationService {
	/**
	 * 查询城市线路和已经签名的线路
	 * @return
	 */
	public String selectcityAndLine(String signid);
	/**
	 * 查询城市列表
	 * @return
	 */
	
	public String selectcity();
	/**
	 * 通过城市id查询城市
	 * @param id
	 * @return
	 */
	public String selectcitybyid(String cityid);
	/**
	 * 通过城市id修改城市信息
	 * @param namecn
	 * @param nameen
	 * @param modifytime
	 * @param cityid
	 * @return
	 */
	public String modifycitybyid(String namecn, String nameen, String cityid);
	
	/**
	 * 城市入库
	 * @param namecn
	 * @param nameen
	 * @param createtime
	 * @param cityid
	 * @return
	 */
	public String insertcity(String namecn,String nameen);
	
	/**
	 * 查询所有签名
	 * @return
	 */
	public String selectallsign();
	/**
	 * 更新签名信息
	 * @param lineidl
	 * @param signid
	 * @return
	 */
	public String updatesignline(String lineid, String signid);

}
