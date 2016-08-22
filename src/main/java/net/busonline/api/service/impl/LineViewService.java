package net.busonline.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.busonline.api.dao.LineViewMapper;
import net.busonline.api.service.ILineViewService;
import net.busonline.core.base.BaseService;
import net.busonline.core.base.Page;
import net.busonline.core.util.PubMethod;
import net.busonline.dictionary.service.impl.DictionaryService;

@Service
public class LineViewService extends BaseService implements ILineViewService {
	public static Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	@Autowired
	public LineViewMapper lineViewMapper;

	@Override
	public String getAllLine(String currentPage, String pageSize, String cityname, String linename, String linkdir1,
			String linetype1, String dictionaryid1) {
		// TODO Auto-generated method stub
		Page page = new Page();
		if (!PubMethod.isEmpty(currentPage)) {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		if (!PubMethod.isEmpty(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		if (!PubMethod.isEmpty(cityname)) {
			map.put("cityname", cityname);
		}
		if (!PubMethod.isEmpty(linename)) {
			map.put("linename", linename);
		}
		if (!PubMethod.isEmpty(linkdir1)) {
			String[] linkdir = linkdir1.split(",");
			map.put("linkdir", linkdir);
		}
		if (!PubMethod.isEmpty(linetype1)) {
			String[] linetype = linetype1.split(",");
			map.put("linetype", linetype);
		}
		if (!PubMethod.isEmpty(dictionaryid1)) {
			String[] dictionaryid = dictionaryid1.split(",");
			map.put("dictionaryid", dictionaryid);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMap = lineViewMapper.getAllLine(map);
		for(int i = 0 ; i<listMap.size();i++){
			
			String endstop = lineViewMapper.selectendstop(listMap.get(i).get("id").toString());
			String startstop = lineViewMapper.selectstartstop(listMap.get(i).get("id").toString());
			Long totalstop = lineViewMapper.selectstopcount(listMap.get(i).get("id").toString());
			listMap.get(i).put("startstop", startstop);
			listMap.get(i).put("endstop", endstop);
			listMap.get(i).put("totalstop", totalstop);
		}
		Long totalCount = lineViewMapper.getAllLineCount(map);
		resultMap.put("total", totalCount);
		if (totalCount % page.getPageSize() > 0) {
			resultMap.put("totalPage", totalCount / page.getPageSize() + 1);
		} else {
			resultMap.put("totalPage", totalCount / page.getPageSize());
		}
		// resultMap.put ("totalPage", totalCount/Integer.parseInt(pageSize)+1);
		resultMap.put("listAudit", listMap);
		try{
		return this.jsonSuccess(resultMap);
		} catch (Exception e) {
			logger.debug("异常数据===="+e);
			return this.jsonFailure();
		}
	}

	@Override
	public String delLineandstopbyid(String id) {
		// TODO Auto-generated method stub
		try {
			lineViewMapper.delstopbylineid(id);
			lineViewMapper.delLinebyid(id);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("异常数据===="+e);
			return this.jsonFailure();
		}
	}

	@Override
	public String selectendstop(String lineid) {
		// TODO Auto-generated method stub
		try {
			return this.jsonSuccess(lineViewMapper.selectstopbylineid(lineid));
		} catch (Exception e) {
			logger.debug("异常数据===="+e);
			return this.jsonFailure();
		}
	}

	@Override
	public String updatestopbyid(String id,String stopname,String lat,String lon,String stoptype) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("stopname", stopname);
		map.put("lat", lat);
		map.put("lon", lon);
		map.put("stoptype", stoptype);
		try {
			 lineViewMapper.updatestopbyid(map);
			 return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("异常数据===="+e);
			return this.jsonFailure();
		}
	}

	public String getLineById(String id){
//		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map =lineViewMapper.getLineById(id);
		Long totalstop = lineViewMapper.selectstopcount(id);
		String endstop = lineViewMapper.selectendstop(id);
		String startstop = lineViewMapper.selectstartstop(id);
		map.put("totalstop", totalstop);
		map.put("endstop", endstop);
		map.put("startstop", startstop);
		try {
		return this.jsonSuccess(map);
		} catch (Exception e) {
			logger.debug("异常数据===="+e);
			return this.jsonFailure();
		}
	}

	@Override
	public String updateLinebyid(String linename, String dictionaryid, String linetype, String linkdir,
			String installationnumber, String matchnumber, String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("linename", linename);
        map.put("dictionaryid", dictionaryid);
        map.put("linetype", linetype);
        map.put("linkdir", linkdir);
        map.put("installationnumber", installationnumber);
        map.put("matchnumber", matchnumber);
        map.put("id", id);
        lineViewMapper.updateLinebyid(map);
        try {
    		return this.jsonSuccess();
    		} catch (Exception e) {
    			logger.debug("异常数据===="+e);
    			return this.jsonFailure();
    		}
	}

	@Override
	public String delLinebyid(String id) {
		// TODO Auto-generated method stub
		lineViewMapper.delLinebyid(id);
		  try {
	    		return this.jsonSuccess();
	    		} catch (Exception e) {
	    			logger.debug("异常数据===="+e);
	    			return this.jsonFailure();
	    		}
	}
}
