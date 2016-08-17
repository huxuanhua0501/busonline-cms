package net.busonline.dictionary.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.busonline.core.base.BaseService;
import net.busonline.dictionary.dao.DictionaryMapper;
import net.busonline.dictionary.service.IDictionaryService;
@Service
public class DictionaryService extends BaseService implements IDictionaryService {
@Autowired
DictionaryMapper dictionaryMapper;
	@Override
	public void insertdict(String dictionaryname,String parentid) {
		// TODO Auto-generated method stub
		  Map<String, Object>map=new HashMap<String,Object>();
		  map.put("dictionaryname", dictionaryname);
		  map.put("parentid", parentid);
		  map.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		dictionaryMapper.insertdict(map);
		
	}
	@Override
	public String selectDicItme() {
		// TODO Auto-generated method stub
		dictionaryMapper.selectDicItme();
		return this.jsonSuccess(dictionaryMapper.selectDicItme());
	}
	@Override
	public List<Map<String, Object>> selectDicTwo(String id) {
		// TODO Auto-generated method stub
		dictionaryMapper.selectDicTwo(id);
		return null;
	}
	@Override
	public String updateDicname(String dictionaryname, String id) {
		// TODO Auto-generated method stub
		  Map<String, Object>map=new HashMap<String,Object>();
		  map.put("dictionaryname", dictionaryname);
		  map.put("parentid", id);
		  map.put("modifytime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		  dictionaryMapper.updateDicname(map);
		return id;
		
	}
	@Override
	public String delDic(String id) {
		// TODO Auto-generated method stub
		dictionaryMapper.delDic(id);
		return id;
		
	}
	@Override
	public String delDicTwo(String id) {
		// TODO Auto-generated method stub
		dictionaryMapper.delDicTwo(id);
		return id;
		
	}
	@Override
	public String selectDicItmeAll() {
		// TODO Auto-generated method stub
		return this.jsonSuccess(dictionaryMapper.selectDicItmeAll());
	}

}
