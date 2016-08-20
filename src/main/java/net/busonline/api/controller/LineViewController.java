package net.busonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.busonline.api.service.ILineViewService;

@RestController
@RequestMapping("/lineview")
public class LineViewController {
	@Autowired
	public ILineViewService lineViewService;
	/**
	 * 查询所有线路
	 * @param currentPage
	 * @param pageSize
	 * @param cityname
	 * @param linename
	 * @param linkdir
	 * @param linetype
	 * @param dictionaryid
	 * @return
	 */
	@RequestMapping(value = "/getallLine", method = { RequestMethod.POST, RequestMethod.GET })
	public String getAllLine(String currentPage, String pageSize, String cityname, String linename, String linkdir,
			String linetype, String dictionaryid) {
		return lineViewService.getAllLine(currentPage, pageSize, cityname, linename, linkdir, linetype, dictionaryid);
	}
	/**
	 * 删除线路通过线路id
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/delLineandstopbyid", method = { RequestMethod.POST, RequestMethod.GET })
	public String delLineandstopbyid(String id) {
		return lineViewService.delLineandstopbyid(id);
	}
	/**
	 * 查询线路通过线路id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getLineById", method = { RequestMethod.POST, RequestMethod.GET })
	public String getLineById(String id) {
		return lineViewService.getLineById(id);
	}

	/**
	 * 更新线路通过
	 * @param linename
	 * @param dictionaryid
	 * @param linetype
	 * @param linkdir
	 * @param installationnumber
	 * @param matchnumber
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateLinebyid", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateLinebyid(String linename, String dictionaryid, String linetype, String linkdir,
			String installationnumber, String matchnumber, String id) {
		return lineViewService.updateLinebyid(linename, dictionaryid, linetype, linkdir, installationnumber,
				matchnumber, id);
	}

	/**
	 * 查询站点，通过线路id
	 * @param lineid
	 * @return
	 */
	@RequestMapping(value = "/selectendstop", method = { RequestMethod.POST, RequestMethod.GET })
	public String selectendstop(String lineid) {
		return lineViewService.selectendstop(lineid);
	}

	/**
	 * 更改站点通过站点id
	 * @param id
	 * @param stopname
	 * @param lat
	 * @param lon
	 * @param stoptype
	 * @return
	 */
	@RequestMapping(value = "/updatestopbyid", method = { RequestMethod.POST, RequestMethod.GET })
	public String updatestopbyid(String id, String stopname, String lat, String lon, String stoptype) {
		return lineViewService.updatestopbyid(id, stopname, lat, lon, stoptype);
	}


}
