package net.busonline.dictionary.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.busonline.core.base.BaseService;
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
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dictionaryname", dictionaryname);
			map.put("parentid", parentid);
			map.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			dictionaryMapper.insertdict(map);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("入库异常", e);
			return this.jsonFailure();
		}

	}

	@Override
	public String selectDicItme() {
		// TODO Auto-generated method stub
		try {
			dictionaryMapper.selectDicItme();
			return this.jsonSuccess(dictionaryMapper.selectDicItme());
		} catch (Exception e) {
			logger.debug("selectDicItme查询异常", e);
			return this.jsonFailure();
		}
	}

	@Override
	public String selectDicTwo(String id) {
		// TODO Auto-generated method stub
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
		try {
			dictionaryMapper.delDicTwo(id);
			return this.jsonSuccess();
		} catch (Exception e) {
			logger.debug("updateDicname查询异常", e);
			return this.jsonFailure();
		}

	}

	@Override
	public String selectDicItmeAll() {
		// TODO Auto-generated method stub
		try {
			return this.jsonSuccess(dictionaryMapper.selectDicItmeAll());
		} catch (Exception e) {
			logger.debug("updateDicname查询异常", e);
			return this.jsonFailure();
		}
	}

}
