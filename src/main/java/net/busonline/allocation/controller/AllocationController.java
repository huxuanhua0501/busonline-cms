package net.busonline.allocation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.busonline.allocation.service.IAllocationService;

@RestController
@RequestMapping("/allocation")
public class AllocationController {
	@Autowired
	private IAllocationService allocationService;
	
	/**
	 * 查询线路,签名企业的id
	 * @param signid
	 * @return
	 */
	@RequestMapping(value = "/selectcityAndLine", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectcityAndLine(String signid) {
		return  allocationService.selectcityAndLine(signid);

	}
	
	/**
	 * 更改签名路线
	 * @param lineid
	 * @param signid
	 * @return
	 */
	@RequestMapping(value = "/updatesignline", method = { RequestMethod.GET, RequestMethod.POST })
	public String updatesignline(String lineid, String signid,String userid) {
		return  allocationService.updatesignline(lineid, signid,userid);

	}
	
	
	/**
	 *查询所有企业签名
	 * @return
	 */
	@RequestMapping(value = "/selectallsign", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectallsign() {
		return  allocationService.selectallsign();

	}
 
	
	/**
	 * 查询所有城市
	 * @return
	 */
	@RequestMapping(value = "/selectcity", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectcity() {
		return  allocationService.selectcity();

	}
	
	
	/**
	 * 查询所有城市通过城市id
	 * @param cityid
	 * @return
	 */
	@RequestMapping(value = "/selectcitybyid", method = { RequestMethod.GET, RequestMethod.POST })
	public String selectcitybyid(String cityid) {
		return  allocationService.selectcitybyid(cityid);

	}
	
	/**
	 * 修改城市信息，通过城市id
	 * @param namecn
	 * @param nameen
	 * @param modifytime
	 * @param cityid
	 * @return
	 */
	@RequestMapping(value = "/modifycitybyid", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifycitybyid(String namecn,String nameen,String cityid,String userid) {
		return  allocationService.modifycitybyid(namecn, nameen,cityid,userid);

	}
	
	/**
	 * 插入城市信息
	 * @param namecn
	 * @param nameen
	 * @return
	 */
	@RequestMapping(value = "/insertcity", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertcity(String namecn,String nameen,String userid) {
		return  allocationService.insertcity(namecn, nameen,userid);

	}
	
	 
}
