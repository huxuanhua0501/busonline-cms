package net.busonline.dictionary.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonEncoding;

import net.busonline.core.base.BaseService;
import net.busonline.core.exception.ServiceException;
import net.busonline.core.util.PubMethod;
import net.busonline.dictionary.dao.DictionaryMapper;
import net.busonline.dictionary.service.IDictionaryService;

@Service
public class DictionaryService extends BaseService implements IDictionaryService {
	public static Logger logger = LoggerFactory.getLogger(DictionaryService.class);
	@Autowired
	DictionaryMapper dictionaryMapper;

	@Override
	public String insertdict(String dictionaryname, String parentid) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(dictionaryname)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.insertdict.001===dictionaryname参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.insertdict.001", "dictionaryname参数异常");
		}
		if (PubMethod.isEmpty(parentid)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.insertdict.002===parentid参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.insertdict.002", "parentid参数异常");
		}
		try {
			List<Map<String,Object>> listmap = selectDicByName(dictionaryname);
			if(!listmap.isEmpty()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("dictionaryname", dictionaryname);
				map.put("parentid", parentid);
				map.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				dictionaryMapper.insertdict(map);
				return this.jsonSuccess();
			}else{
				return this.jsonSuccess2();	
			}
		} catch (Exception e) {
			logger.debug("入库异常", e);
			return this.jsonFailure();
		}

	}

	@Override
	public String selectDicItme() {
		// TODO Auto-generated method stub
		try {
			List<Map<String,Object>>listmap = dictionaryMapper.selectDicOne();
			Map<String,Object> onemap = new HashMap<String,Object>();
		//	Map<String,Object> twomap = new HashMap<String,Object>();
			for(int i = 0 ;i<listmap.size();i++){
				List<Map<String,Object>>twolist = dictionaryMapper.selectDicTwo(listmap.get(i).get("id").toString()); 
				listmap.get(i).put("two", twolist);
				onemap.put("one",listmap.get(i));
				//twomap.put("two",twolist );
			}
			return this.jsonSuccess(onemap);
		} catch (Exception e) {
			logger.debug("selectDicItme查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String selectDicTwo(String id) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(id)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.selectDicTwo.001===id参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.selectDicTwo.001", "id参数异常");
		}
		try {
			 
			return this.jsonSuccess(dictionaryMapper.selectDicTwo(id));
		} catch (Exception e) {
			logger.debug("selectDicTwo查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String updateDicname(String dictionaryname, String id) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(dictionaryname)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.updateDicname.001===dictionaryname参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.updateDicname.001", "dictionaryname参数异常");
		}
		if (PubMethod.isEmpty(id)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.updateDicname.002===id参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.updateDicname.002", "id参数异常");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dictionaryname", dictionaryname);
			map.put("parentid", id);
			map.put("modifytime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			dictionaryMapper.updateDicname(map);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("updateDicname查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String delDic(String id) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(id)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.delDic.001===id参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.delDic.002", "id参数异常");
		}
		try {
			dictionaryMapper.delDic(id);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("updateDicname查询异常", e);
			return this.jsonFailure();
		}

	}

	@Override
	public String delDicTwo(String id) {
		// TODO Auto-generated method stub
		if (PubMethod.isEmpty(id)) {
			logger.debug("net.busonline.dictionary.service.impl.BusApiService.delDicTwo.001===id参数异常");
			throw new ServiceException("net.busonline.dictionary.service.impl.BusApiService.delDicTwo.002", "id参数异常");
		}
		try {
			dictionaryMapper.delDicTwo(id);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("updateDicname查询异常", e);
			return this.jsonFailure();
		}

	}

	@Override
	public String selectDicOne() {
		try {
		// TODO Auto-generated method stub
			return this.jsonSuccess(dictionaryMapper.selectDicOne());
		} catch (Exception e) {
			logger.debug("updateDicname查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public List<Map<String,Object>> selectDicByName(String dictionaryname) {
		// TODO Auto-generated method stub
		List<Map<String,Object>>list =dictionaryMapper.selectDicByName(dictionaryname);
	 
		return list;
	}

}
