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
	@RequestMapping(value = "/getallLine", method = {RequestMethod.POST,RequestMethod.GET })
	public String getAllLine(String currentPage,String pageSize,String cityname,String linename,String linkdir,String linetype,String dictionaryid) {
		return  lineViewService.getAllLine( currentPage, pageSize,cityname, linename, linkdir, linetype, dictionaryid);

	}
}
