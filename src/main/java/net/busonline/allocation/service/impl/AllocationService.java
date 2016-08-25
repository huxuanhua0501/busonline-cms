package net.busonline.allocation.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.busonline.allocation.dao.AllocationMapper;
import net.busonline.allocation.service.IAllocationService;
import net.busonline.core.base.BaseService;
import net.busonline.dictionary.service.impl.DictionaryService;

@Service
public class AllocationService extends BaseService implements IAllocationService {
	public static Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	@Autowired
	private AllocationMapper allocationMapper;

	@Override
	public String selectcityAndLine(String signid) {
		// TODO Auto-generated method stub
		try {
			List<Map<String, Object>> listcity = allocationMapper.selectcity();
			List<Map<String, Object>> listresult = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < listcity.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				List<Map<String, Object>> listline = allocationMapper
						.selectlinebycityid(listcity.get(i).get("cityid").toString());
				for (int j = 0; j < listline.size(); j++) {
					Map<String, Object> signline = allocationMapper.selectsignlinebyid(signid,
							listline.get(j).get("id").toString());
					if (signline.size() != 0) {
						listline.get(j).put("signtype", 0);
					} else {
						listline.get(j).put("signtype", 1);
					}
				}
				map.put("city", listcity.get(i));
				map.put("line", listline);
				listresult.add(map);
			}
			return this.jsonSuccess(listresult);

		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}

	public String updatesignline(String lineidl, String signid) {
		try {
			// 删除签名线路
			allocationMapper.delsignline(signid);
			// 插入签名线路
			String[] lineid = lineidl.split(",");
			for (int i = 0; i < lineid.length; i++) {
				allocationMapper.insertsignline(signid, lineid[i]);
			}
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}

	}

	@Override
	public String selectcity() {
		// TODO Auto-generated method stub
		try {
			List<Map<String, Object>> listcity = allocationMapper.selectcity();
			return this.jsonSuccess(listcity);
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String selectcitybyid(String cityid) {
		// TODO Auto-generated method stub
		try {
			return this.jsonSuccess(allocationMapper.selectcitybyid(cityid));
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String modifycitybyid(String namecn, String nameen, String modifytime, String cityid) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> citymap = new HashMap<String, Object>();
			citymap.put("namecn", namecn);
			citymap.put("nameen", nameen);
			citymap.put("modifytime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			citymap.put("cityid", cityid);
			allocationMapper.modifycitybyid(citymap);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String insertcity(String namecn, String nameen, String createtime) {
		// TODO Auto-generated method stub
		try {
			Map<String, Object> citymap = new HashMap<String, Object>();
			citymap.put("namecn", namecn);
			citymap.put("nameen", nameen);
			citymap.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			allocationMapper.insertcity(citymap);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String selectallsign() {
		// TODO Auto-generated method stub
		try {
			return this.jsonSuccess(allocationMapper.selectallsign());
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}
}
