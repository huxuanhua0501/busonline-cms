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
	public String getAllLine(String currentPage,String pageSize,String cityname, String linename, String linkdir1, String linetype1,
			String dictionaryid1) {
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
		List<Map<String,Object>>listMap = lineViewMapper.getAllLine(map);
		Long totalCount = lineViewMapper.getAllLineCount(map);
		resultMap.put("total", totalCount);
		if(totalCount%page.getPageSize()>0){
			resultMap.put ("totalPage", totalCount/page.getPageSize()+1);			
		}else{
			resultMap.put("totalPage", totalCount/page.getPageSize());			
		}
		//resultMap.put ("totalPage", totalCount/Integer.parseInt(pageSize)+1);		
		resultMap.put("listAudit", listMap);
		return this.jsonSuccess(resultMap);
	}

}
