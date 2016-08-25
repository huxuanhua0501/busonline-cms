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
import net.busonline.core.exception.ServiceException;
import net.busonline.core.util.PubMethod;
import net.busonline.dictionary.service.impl.DictionaryService;

@Service
public class AllocationService extends BaseService implements IAllocationService {
	public static Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	@Autowired
	private AllocationMapper allocationMapper;

	@Override
	public String selectcityAndLine(String signid) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(signid)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.selectcityAndLine.001===signid签名参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.selectcityAndLine.001", "signid签名参数异常");
		}
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
		if (PubMethod.isEmpty(signid)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.updatesignline.001===signid签名参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.updatesignline.001", "signid签名参数异常");
		}
		if (PubMethod.isEmpty(lineidl)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.updatesignline.002===lineid签名参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.updatesignline.002", "lineid参数异常");
		}
		try {
			// 删除签名线路
			allocationMapper.delsignline(signid);
			// 插入签名线路
			String[] lineid = lineidl.split(",");
			List<Map<String,Object>>listlineid = allocationMapper.selectidbylineid(lineid);
			//添加一个查询
			for (int i = 0; i < listlineid.size(); i++) {
				allocationMapper.insertsignline(signid, listlineid.get(i).get("id").toString());
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
		if (PubMethod.isEmpty(cityid)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.selectcitybyid.001===cityid参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.selectcitybyid.001", "cityid参数异常");
		}
		try {
			return this.jsonSuccess(allocationMapper.selectcitybyid(cityid));
		} catch (Exception e) {
			logger.debug("查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String modifycitybyid(String namecn, String nameen, String cityid) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(cityid)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.001===cityid参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.001", "cityid参数异常");
		}
		if (PubMethod.isEmpty(nameen)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.002===nameen参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.002", "nameen参数异常");
		}
		if (PubMethod.isEmpty(namecn)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.003===namecn参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.003", "namecn参数异常");
		}
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
	public String insertcity(String namecn, String nameen) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(nameen)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.001===nameen参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.001", "nameen参数异常");
		}
		if (PubMethod.isEmpty(namecn)) {
			logger.debug("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.002===namecn参数异常");
			throw new ServiceException("net.busonline.allocation.service.impl.AllocationService.modifycitybyid.002", "namecn参数异常");
		}
		try {
			Map<String, Object> citymap = new HashMap<String, Object>();
			citymap.put("namecn", namecn);
			citymap.put("nameen", nameen);
			citymap.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			Long count = allocationMapper.selectcitylive(citymap);
			if(count==0){
				allocationMapper.insertcity(citymap);
			}else{
				return this.jsonFailure3();
			}
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
