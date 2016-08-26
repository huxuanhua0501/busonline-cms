package net.busonline.api.service;


public interface ILineViewService {
	/**
	 * 查询线路
	 * @param currentPage
	 * @param pageSize
	 * @param cityname
	 * @param linename
	 * @param linkdir
	 * @param linetype
	 * @param dictionaryid
	 * @return
	 */
	public String getAllLine(String currentPage, String pageSize, String cityname, String linename, String linkdir,
			String linetype, String dictionaryid);
	/**
	 * 删除线路
	 * @param id
	 * @return
	 */
	public String delLineandstopbyid(String id);
	
	/**
	 * 查询站点
	 * @param lineid
	 * @return
	 */
	public String selectendstop(String lineid);
	
	/**
	 * 修改站点
	 * @param id
	 * @return
	 */
	public String updatestopbyid(String id,String stopname,String lat,String lon,String stoptype,String userid);
	
	/**
	 * 查询线路通过线路id
	 * @param id
	 * @return
	 */
	public String getLineById(String id);
	/**
	 * 更新线路通过id
	 * @param linename
	 * @param dictionaryid
	 * @param linetype
	 * @param linkdir
	 * @param installationnumber
	 * @param matchnumber
	 * @return
	 */
	
	public String updateLinebyid(String linename,String dictionaryid,String linetype,String linkdir,String installationnumber,String matchnumber,String id,String userid);
	
	public String delLinebyid(String id);;
}
